// Ticket Form JavaScript
document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('ticketForm');
    const successModal = document.getElementById('successModal');
    const fileUpload = document.getElementById('attachments');
    const uploadArea = document.querySelector('.upload-area');
    
    // Initialize form
    initializeForm();
    
    // Form submission
    form.addEventListener('submit', handleFormSubmit);
    
    // File upload handling
    if (fileUpload) {
        fileUpload.addEventListener('change', handleFileSelect);
        setupDragAndDrop();
    }
    
    // Priority selection
    setupPrioritySelection();
    
    // Form validation
    setupFormValidation();
});

// Initialize form with default values and setup
function initializeForm() {
    // Set default due date to 24 hours from now
    const dueDateInput = document.getElementById('dueDate');
    if (dueDateInput) {
        const tomorrow = new Date();
        tomorrow.setDate(tomorrow.getDate() + 1);
        tomorrow.setHours(9, 0, 0, 0); // Set to 9 AM tomorrow
        dueDateInput.value = tomorrow.toISOString().slice(0, 16);
    }
    
    // Auto-save draft every 30 seconds
    setInterval(autoSaveDraft, 30000);
    
    // Load draft if exists
    loadDraft();
}

// Handle form submission
async function handleFormSubmit(event) {
    event.preventDefault();
    
    if (!validateForm()) {
        return;
    }
    
    const submitBtn = event.target.querySelector('button[type="submit"]');
    const originalText = submitBtn.innerHTML;
    
    try {
        // Show loading state
        submitBtn.classList.add('loading');
        submitBtn.disabled = true;
        
        // Prepare form data
        const formData = new FormData(event.target);
        const ticketData = {
            title: formData.get('title'),
            description: formData.get('description'),
            priority: formData.get('priority'),
            category: formData.get('category'),
            dueDate: formData.get('dueDate') ? new Date(formData.get('dueDate')).toISOString() : null,
            estimatedResolutionHours: formData.get('estimatedHours') ? parseInt(formData.get('estimatedHours')) : null
            // createdByUserId is intentionally NOT sent — the server sets it from the
            // logged-in session (see TicketController.create), so it can never be spoofed
            // or default to the wrong user.
        };
        
        // Submit ticket
        const response = await fetch('/api/tickets', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(ticketData)
        });
        
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        
        const result = await response.json();
        
        // Clear draft
        clearDraft();
        
        // Show success modal
        showSuccessModal(result.id);
        
        // Reset form
        event.target.reset();
        
    } catch (error) {
        console.error('Error submitting ticket:', error);
        showError('Failed to submit ticket. Please try again.');
    } finally {
        // Reset button state
        submitBtn.classList.remove('loading');
        submitBtn.disabled = false;
        submitBtn.innerHTML = originalText;
    }
}

// Form validation
function validateForm() {
    let isValid = true;
    const requiredFields = ['title', 'category', 'priority', 'description'];
    
    // Clear previous errors
    clearErrors();
    
    // Check required fields
    requiredFields.forEach(fieldName => {
        const field = document.getElementById(fieldName);
        if (!field.value.trim()) {
            showFieldError(field, 'This field is required');
            isValid = false;
        }
    });
    
    // Validate title length
    const titleField = document.getElementById('title');
    if (titleField.value.trim().length < 5) {
        showFieldError(titleField, 'Title must be at least 5 characters long');
        isValid = false;
    }
    
    // Validate description length
    const descField = document.getElementById('description');
    if (descField.value.trim().length < 20) {
        showFieldError(descField, 'Description must be at least 20 characters long');
        isValid = false;
    }
    
    // Validate estimated hours
    const hoursField = document.getElementById('estimatedHours');
    if (hoursField.value && (hoursField.value < 1 || hoursField.value > 168)) {
        showFieldError(hoursField, 'Estimated hours must be between 1 and 168');
        isValid = false;
    }
    
    return isValid;
}

// Show field error
function showFieldError(field, message) {
    const formGroup = field.closest('.form-group');
    formGroup.classList.add('error');
    
    // Remove existing error message
    const existingError = formGroup.querySelector('.error-message');
    if (existingError) {
        existingError.remove();
    }
    
    // Add new error message
    const errorDiv = document.createElement('div');
    errorDiv.className = 'error-message';
    errorDiv.textContent = message;
    formGroup.appendChild(errorDiv);
    
    // Focus on the field
    field.focus();
}

// Clear all errors
function clearErrors() {
    document.querySelectorAll('.form-group.error').forEach(group => {
        group.classList.remove('error');
        const errorMessage = group.querySelector('.error-message');
        if (errorMessage) {
            errorMessage.remove();
        }
    });
}

// Show general error message
function showError(message) {
    // Create error notification
    const notification = document.createElement('div');
    notification.className = 'error-notification';
    notification.innerHTML = `
        <i class="fas fa-exclamation-circle"></i>
        <span>${message}</span>
        <button onclick="this.parentElement.remove()">
            <i class="fas fa-times"></i>
        </button>
    `;
    
    // Add to page
    document.body.appendChild(notification);
    
    // Auto-remove after 5 seconds
    setTimeout(() => {
        if (notification.parentElement) {
            notification.remove();
        }
    }, 5000);
}

// File handling
function handleFileSelect(event) {
    const files = Array.from(event.target.files);
    const maxSize = 10 * 1024 * 1024; // 10MB
    const allowedTypes = ['.jpg', '.jpeg', '.png', '.gif', '.pdf', '.doc', '.docx', '.txt'];
    
    // Validate files
    const validFiles = files.filter(file => {
        if (file.size > maxSize) {
            showError(`File ${file.name} is too large. Maximum size is 10MB.`);
            return false;
        }
        
        const extension = '.' + file.name.split('.').pop().toLowerCase();
        if (!allowedTypes.includes(extension)) {
            showError(`File ${file.name} has an unsupported format.`);
            return false;
        }
        
        return true;
    });
    
    // Update upload area
    updateUploadArea(validFiles);
}

// Setup drag and drop
function setupDragAndDrop() {
    if (!uploadArea) return;
    
    uploadArea.addEventListener('dragover', (e) => {
        e.preventDefault();
        uploadArea.style.borderColor = 'var(--primary-color)';
        uploadArea.style.background = 'var(--bg-tertiary)';
    });
    
    uploadArea.addEventListener('dragleave', (e) => {
        e.preventDefault();
        uploadArea.style.borderColor = 'var(--border-color)';
        uploadArea.style.background = 'var(--bg-secondary)';
    });
    
    uploadArea.addEventListener('drop', (e) => {
        e.preventDefault();
        uploadArea.style.borderColor = 'var(--border-color)';
        uploadArea.style.background = 'var(--bg-secondary)';
        
        const files = Array.from(e.dataTransfer.files);
        fileUpload.files = e.dataTransfer.files;
        handleFileSelect({ target: { files: files } });
    });
}

// Update upload area display
function updateUploadArea(files) {
    if (!uploadArea) return;
    
    if (files.length === 0) {
        uploadArea.innerHTML = `
            <i class="fas fa-cloud-upload-alt"></i>
            <p>Drag and drop files here or click to browse</p>
            <span>Max file size: 10MB</span>
        `;
        return;
    }
    
    const fileList = files.map(file => `
        <div class="file-item">
            <i class="fas fa-file"></i>
            <span>${file.name}</span>
            <small>${formatFileSize(file.size)}</small>
        </div>
    `).join('');
    
    uploadArea.innerHTML = `
        <div class="files-preview">
            <h4>Selected Files (${files.length})</h4>
            ${fileList}
        </div>
    `;
}

// Format file size
function formatFileSize(bytes) {
    if (bytes === 0) return '0 Bytes';
    const k = 1024;
    const sizes = ['Bytes', 'KB', 'MB', 'GB'];
    const i = Math.floor(Math.log(bytes) / Math.log(k));
    return parseFloat((bytes / Math.pow(k, i)).toFixed(2)) + ' ' + sizes[i];
}

// Priority selection setup
function setupPrioritySelection() {
    const prioritySelect = document.getElementById('priority');
    const priorityItems = document.querySelectorAll('.priority-item');
    
    if (prioritySelect) {
        prioritySelect.addEventListener('change', function() {
            const selectedPriority = this.value;
            
            // Update priority info display
            priorityItems.forEach(item => {
                if (item.dataset.priority === selectedPriority) {
                    item.style.background = 'var(--bg-tertiary)';
                    item.style.borderRadius = 'var(--border-radius-sm)';
                    item.style.padding = '0.5rem';
                } else {
                    item.style.background = 'transparent';
                    item.style.borderRadius = '0';
                    item.style.padding = '0';
                }
            });
        });
    }
}

// Auto-save draft
function autoSaveDraft() {
    const formData = new FormData(document.getElementById('ticketForm'));
    const draft = {
        title: formData.get('title'),
        description: formData.get('description'),
        category: formData.get('category'),
        priority: formData.get('priority'),
        dueDate: formData.get('dueDate'),
        estimatedHours: formData.get('estimatedHours'),
        environment: formData.get('environment'),
        browser: formData.get('browser'),
        operatingSystem: formData.get('operatingSystem'),
        contactPreference: formData.get('contactPreference'),
        timestamp: new Date().toISOString()
    };
    
    localStorage.setItem('ticketDraft', JSON.stringify(draft));
}

// Load draft — asks first instead of silently overwriting whatever the user is about to type.
// Previously this ran on every page load and filled every field from localStorage without
// asking, so a form that looked "already filled in" with old data was actually this running,
// not a bug in your typing.
function loadDraft() {
    const draft = localStorage.getItem('ticketDraft');
    if (!draft) return;

    try {
        const draftData = JSON.parse(draft);
        const form = document.getElementById('ticketForm');

        // Check if draft is less than 24 hours old
        const draftTime = new Date(draftData.timestamp);
        const now = new Date();
        const hoursDiff = (now - draftTime) / (1000 * 60 * 60);

        if (hoursDiff >= 24) {
            clearDraft();
            return;
        }

        const applyDraft = () => {
            Object.keys(draftData).forEach(key => {
                if (key !== 'timestamp') {
                    const field = form.querySelector(`[name="${key}"]`);
                    if (field && draftData[key]) {
                        field.value = draftData[key];
                    }
                }
            });
        };

        showDraftPrompt(applyDraft);
    } catch (error) {
        console.error('Error loading draft:', error);
        clearDraft();
    }
}

// Non-blocking banner offering to restore a saved draft. Does nothing until the user clicks
// "Restore" — the form stays blank and ready for manual entry otherwise.
function showDraftPrompt(onRestore) {
    const banner = document.createElement('div');
    banner.className = 'notification notification-info';
    banner.innerHTML = `
        <i class="fas fa-info-circle"></i>
        <span>You have a saved draft from your last visit. Restore it?</span>
        <button type="button" class="draft-restore-btn">Restore</button>
        <button type="button" onclick="this.parentElement.remove()">
            <i class="fas fa-times"></i>
        </button>
    `;
    banner.querySelector('.draft-restore-btn').addEventListener('click', () => {
        onRestore();
        banner.remove();
        showNotification('Draft restored', 'success');
    });
    document.body.appendChild(banner);
}

// Save draft manually
function saveDraft() {
    autoSaveDraft();
    showNotification('Draft saved successfully', 'success');
}

// Clear draft
function clearDraft() {
    localStorage.removeItem('ticketDraft');
}

// Show success modal
function showSuccessModal(ticketId) {
    const modal = document.getElementById('successModal');
    const ticketNumber = document.getElementById('ticketNumber');
    
    if (modal && ticketNumber) {
        ticketNumber.textContent = `#${ticketId}`;
        modal.classList.add('show');
    }
}

// Create another ticket
function createAnother() {
    const modal = document.getElementById('successModal');
    if (modal) {
        modal.classList.remove('show');
    }
    
    // Reset form
    document.getElementById('ticketForm').reset();
    
    // Scroll to top
    window.scrollTo({ top: 0, behavior: 'smooth' });
}

// Show notification
function showNotification(message, type = 'info') {
    const notification = document.createElement('div');
    notification.className = `notification notification-${type}`;
    notification.innerHTML = `
        <i class="fas fa-${type === 'success' ? 'check-circle' : 'info-circle'}"></i>
        <span>${message}</span>
        <button onclick="this.parentElement.remove()">
            <i class="fas fa-times"></i>
        </button>
    `;
    
    document.body.appendChild(notification);
    
    // Auto-remove after 3 seconds
    setTimeout(() => {
        if (notification.parentElement) {
            notification.remove();
        }
    }, 3000);
}

// Setup form validation
function setupFormValidation() {
    const form = document.getElementById('ticketForm');
    
    // Real-time validation
    const fields = form.querySelectorAll('input, select, textarea');
    fields.forEach(field => {
        field.addEventListener('blur', () => {
            validateField(field);
        });
        
        field.addEventListener('input', () => {
            clearFieldError(field);
        });
    });
}

// Validate individual field
function validateField(field) {
    const value = field.value.trim();
    
    // Clear previous error
    clearFieldError(field);
    
    // Required field validation
    if (field.hasAttribute('required') && !value) {
        showFieldError(field, 'This field is required');
        return false;
    }
    
    // Field-specific validation
    switch (field.name) {
        case 'title':
            if (value.length < 5) {
                showFieldError(field, 'Title must be at least 5 characters long');
                return false;
            }
            break;
            
        case 'description':
            if (value.length < 20) {
                showFieldError(field, 'Description must be at least 20 characters long');
                return false;
            }
            break;
            
        case 'estimatedHours':
            if (value && (parseInt(value) < 1 || parseInt(value) > 168)) {
                showFieldError(field, 'Estimated hours must be between 1 and 168');
                return false;
            }
            break;
    }
    
    return true;
}

// Clear field error
function clearFieldError(field) {
    const formGroup = field.closest('.form-group');
    if (formGroup) {
        formGroup.classList.remove('error');
        const errorMessage = formGroup.querySelector('.error-message');
        if (errorMessage) {
            errorMessage.remove();
        }
    }
}

// Add CSS for notifications
const style = document.createElement('style');
style.textContent = `
    .notification {
        position: fixed;
        top: 20px;
        right: 20px;
        background: white;
        border: 1px solid #e2e8f0;
        border-radius: 8px;
        padding: 1rem;
        box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1);
        display: flex;
        align-items: center;
        gap: 0.75rem;
        z-index: 1000;
        animation: slideIn 0.3s ease;
    }
    
    .notification-success {
        border-left: 4px solid #10b981;
    }
    
    .notification-info {
        border-left: 4px solid #3b82f6;
    }
    
    .notification button {
        background: none;
        border: none;
        cursor: pointer;
        color: #64748b;
        padding: 0.25rem;
        border-radius: 4px;
    }
    
    .notification button:hover {
        background: #f1f5f9;
    }

    .draft-restore-btn {
        background: #6366f1 !important;
        color: #fff !important;
        padding: 0.3rem 0.75rem !important;
        border-radius: 6px !important;
        font-size: 0.85rem;
        font-weight: 600;
    }

    .draft-restore-btn:hover {
        background: #4f46e5 !important;
    }
    
    .error-notification {
        position: fixed;
        top: 20px;
        right: 20px;
        background: #fef2f2;
        border: 1px solid #fecaca;
        border-radius: 8px;
        padding: 1rem;
        box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1);
        display: flex;
        align-items: center;
        gap: 0.75rem;
        z-index: 1000;
        animation: slideIn 0.3s ease;
        color: #dc2626;
    }
    
    .files-preview {
        text-align: left;
    }
    
    .files-preview h4 {
        margin-bottom: 1rem;
        color: var(--text-primary);
        font-size: 1rem;
    }
    
    .file-item {
        display: flex;
        align-items: center;
        gap: 0.5rem;
        padding: 0.5rem;
        background: var(--bg-primary);
        border-radius: 4px;
        margin-bottom: 0.5rem;
        border: 1px solid var(--border-light);
    }
    
    .file-item i {
        color: var(--primary-color);
    }
    
    .file-item span {
        flex: 1;
        font-size: 0.9rem;
        color: var(--text-primary);
    }
    
    .file-item small {
        color: var(--text-muted);
        font-size: 0.8rem;
    }
    
    @keyframes slideIn {
        from {
            transform: translateX(100%);
            opacity: 0;
        }
        to {
            transform: translateX(0);
            opacity: 1;
        }
    }
`;

document.head.appendChild(style);
