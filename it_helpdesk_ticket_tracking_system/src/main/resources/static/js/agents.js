document.addEventListener('DOMContentLoaded', function () {
    const showBtn = document.getElementById('showAddAgentBtn');
    const form = document.getElementById('addAgentForm');
    const cancelBtn = document.getElementById('cancelAddAgentBtn');
    const submitBtn = document.getElementById('submitAddAgentBtn');

    if (showBtn && form) {
        showBtn.addEventListener('click', () => {
            form.style.display = 'block';
            showBtn.style.display = 'none';
            document.getElementById('agentName').focus();
        });
    }

    if (cancelBtn && form) {
        cancelBtn.addEventListener('click', () => {
            form.style.display = 'none';
            if (showBtn) showBtn.style.display = 'inline-flex';
            clearAgentForm();
        });
    }

    if (submitBtn) {
        submitBtn.addEventListener('click', handleAddAgent);
    }

    document.querySelectorAll('.btn-remove-agent').forEach(btn => {
        btn.addEventListener('click', handleRemoveAgent);
    });
});

function clearAgentForm() {
    ['agentName', 'agentEmail', 'agentPassword', 'agentMaxTickets'].forEach(id => {
        const el = document.getElementById(id);
        if (el) el.value = '';
    });
}

async function handleAddAgent() {
    const name = document.getElementById('agentName').value.trim();
    const email = document.getElementById('agentEmail').value.trim();
    const password = document.getElementById('agentPassword').value;
    const maxTicketsRaw = document.getElementById('agentMaxTickets').value;

    if (!name || !email || !password) {
        showAgentNotification('Please fill in name, email and password.', 'error');
        return;
    }
    if (password.length < 6) {
        showAgentNotification('Password must be at least 6 characters.', 'error');
        return;
    }

    const submitBtn = document.getElementById('submitAddAgentBtn');
    const originalText = submitBtn.textContent;
    submitBtn.disabled = true;
    submitBtn.textContent = 'Creating…';

    try {
        const response = await fetch('/api/agents', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                name: name,
                email: email,
                password: password,
                maxTickets: maxTicketsRaw ? parseInt(maxTicketsRaw, 10) : null
            })
        });

        const result = await response.json().catch(() => ({}));

        if (!response.ok) {
            throw new Error(result.error || `HTTP ${response.status}`);
        }

        showAgentNotification(name + ' was added as an agent.', 'success');
        // Reload so the new agent appears in the grid, pulled fresh from the server.
        setTimeout(() => window.location.reload(), 800);
    } catch (error) {
        console.error('Error creating agent:', error);
        showAgentNotification('Could not add agent: ' + error.message, 'error');
        submitBtn.disabled = false;
        submitBtn.textContent = originalText;
    }
}

async function handleRemoveAgent(event) {
    const btn = event.currentTarget;
    const card = btn.closest('.agent-card');
    const agentId = card.dataset.agentId;
    const agentName = card.querySelector('h4').textContent;

    if (!confirm('Remove ' + agentName + ' as an agent?')) {
        return;
    }

    btn.disabled = true;

    try {
        const response = await fetch('/api/agents/' + agentId, { method: 'DELETE' });
        const result = await response.json().catch(() => ({}));

        if (!response.ok) {
            throw new Error(result.error || `HTTP ${response.status}`);
        }

        card.remove();
        showAgentNotification(agentName + ' removed.', 'success');
    } catch (error) {
        console.error('Error removing agent:', error);
        showAgentNotification('Could not remove agent: ' + error.message, 'error');
        btn.disabled = false;
    }
}

function showAgentNotification(message, type) {
    const notification = document.createElement('div');
    notification.style.cssText = 'position:fixed;top:20px;right:20px;background:#fff;border:1px solid #e2e8f0;' +
        'border-left:4px solid ' + (type === 'error' ? '#ef4444' : '#10b981') + ';border-radius:8px;padding:1rem;' +
        'box-shadow:0 10px 15px -3px rgb(0 0 0 / 0.1);z-index:1000;display:flex;align-items:center;gap:.75rem;';
    notification.innerHTML = `<span>${message}</span>
        <button type="button" onclick="this.parentElement.remove()" style="background:none;border:none;cursor:pointer;">
            <i class="fas fa-times"></i>
        </button>`;
    document.body.appendChild(notification);
    setTimeout(() => notification.remove(), 4000);
}
