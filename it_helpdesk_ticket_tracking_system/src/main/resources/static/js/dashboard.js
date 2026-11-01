// Enhanced Dashboard JavaScript
document.addEventListener('DOMContentLoaded', function() {
    // Initialize dashboard
    initializeDashboard();
    
    // Setup event listeners
    setupEventListeners();
    
    // Initialize charts, then fill them with real data for the default period
    initializeCharts();
    refreshChartsForPeriod('week');
    
    // Setup real-time updates
    setupRealTimeUpdates();
    
    // Initialize search functionality
    initializeSearch();
});

// Initialize dashboard
function initializeDashboard() {
    console.log('Initializing Help Desk Dashboard...');
    
    // Load initial data
    loadDashboardData();
    
    // Setup sidebar toggle for mobile
    setupSidebarToggle();
    
    // Setup activity tabs
    setupActivityTabs();
}

// Setup event listeners
function setupEventListeners() {
    // Chart filter buttons
    document.querySelectorAll('.btn-filter').forEach(btn => {
        btn.addEventListener('click', function() {
            const period = this.dataset.period;
            updateChartPeriod(period, this);
        });
    });
    
    // Search input
    const searchInput = document.getElementById('searchInput');
    if (searchInput) {
        searchInput.addEventListener('input', debounce(handleSearch, 300));
    }
    
    // Notification bell
}

// Initialize charts
function initializeCharts() {
    // Status Distribution Chart
    const statusCtx = document.getElementById('statusChart');
    if (statusCtx) {
        window.statusChart = new Chart(statusCtx, {
            type: 'doughnut',
            data: {
                labels: [],
                datasets: [{
                    data: [],
                    backgroundColor: [],
                    borderWidth: 0,
                    hoverOffset: 4
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    legend: {
                        position: 'bottom',
                        labels: {
                            padding: 20,
                            usePointStyle: true,
                            font: {
                                size: 12
                            }
                        }
                    },
                    tooltip: {
                        backgroundColor: 'rgba(0, 0, 0, 0.8)',
                        titleColor: '#fff',
                        bodyColor: '#fff',
                        borderColor: '#6366f1',
                        borderWidth: 1
                    }
                },
                cutout: '60%'
            }
        });
    }
    
    // Response Time Trends Chart
    const trendCtx = document.getElementById('trendChart');
    if (trendCtx) {
        window.trendChart = new Chart(trendCtx, {
            type: 'line',
            data: {
                labels: DAY_LABELS,
                datasets: [{
                    label: 'Average Response Time (hours)',
                    data: [0, 0, 0, 0, 0, 0, 0],
                    borderColor: '#6366f1',
                    backgroundColor: 'rgba(99, 102, 241, 0.1)',
                    borderWidth: 3,
                    fill: true,
                    tension: 0.4,
                    pointBackgroundColor: '#6366f1',
                    pointBorderColor: '#fff',
                    pointBorderWidth: 2,
                    pointRadius: 6
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    legend: {
                        display: false
                    },
                    tooltip: {
                        backgroundColor: 'rgba(0, 0, 0, 0.8)',
                        titleColor: '#fff',
                        bodyColor: '#fff',
                        borderColor: '#6366f1',
                        borderWidth: 1
                    }
                },
                scales: {
                    y: {
                        beginAtZero: true,
                        grid: {
                            color: 'rgba(0, 0, 0, 0.1)'
                        },
                        ticks: {
                            color: '#64748b'
                        }
                    },
                    x: {
                        grid: {
                            color: 'rgba(0, 0, 0, 0.1)'
                        },
                        ticks: {
                            color: '#64748b'
                        }
                    }
                }
            }
        });
    }
}

// Update chart period
function updateChartPeriod(period, clickedBtn) {
    // Only toggle "active" among buttons in the same chart card as the one clicked,
    // so clicking Week on the status chart doesn't also un-highlight Week on the trend chart.
    const card = clickedBtn.closest('.chart-card');
    card.querySelectorAll('.btn-filter').forEach(btn => btn.classList.remove('active'));
    clickedBtn.classList.add('active');

    const chartId = card.querySelector('canvas').id;
    refreshChartsForPeriod(period, chartId);
}

const STATUS_LABELS = {
    OPEN: 'Open',
    IN_PROGRESS: 'In Progress',
    PENDING_CUSTOMER: 'Pending (Customer)',
    PENDING_VENDOR: 'Pending (Vendor)',
    RESOLVED: 'Resolved',
    CLOSED: 'Closed',
    CANCELLED: 'Cancelled'
};
const STATUS_COLORS = {
    OPEN: '#3b82f6',
    IN_PROGRESS: '#f59e0b',
    PENDING_CUSTOMER: '#8b5cf6',
    PENDING_VENDOR: '#a78bfa',
    RESOLVED: '#10b981',
    CLOSED: '#6b7280',
    CANCELLED: '#ef4444'
};
const DAY_LABELS = ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'];
const PERIOD_DAYS = { week: 7, month: 30, year: 365 };

let allTicketsCache = null;

// Fetches real tickets (cached for the session) and recomputes both charts for the
// given period from them. Previously this just swapped between three sets of
// hardcoded, made-up numbers — none of it reflected your actual tickets.
async function refreshChartsForPeriod(period, onlyChartId) {
    showLoadingState();
    try {
        if (!allTicketsCache) {
            const response = await fetch('/api/tickets');
            if (!response.ok) throw new Error(`HTTP ${response.status}`);
            allTicketsCache = await response.json();
        }

        const days = PERIOD_DAYS[period] || 7;
        const cutoff = new Date();
        cutoff.setDate(cutoff.getDate() - days);

        const inPeriod = allTicketsCache.filter(t => t.createdAt && new Date(t.createdAt) >= cutoff);

        if (!onlyChartId || onlyChartId === 'statusChart') {
            updateStatusChart(inPeriod);
        }
        if (!onlyChartId || onlyChartId === 'trendChart') {
            updateTrendChart(inPeriod, days);
        }
    } catch (error) {
        console.error('Error loading chart data:', error);
        showNotification('Could not load chart data', 'error');
    } finally {
        hideLoadingState();
    }
}

// Update status chart with a real count per status actually present in this period
function updateStatusChart(tickets) {
    if (!window.statusChart) return;

    const counts = {};
    tickets.forEach(t => { counts[t.status] = (counts[t.status] || 0) + 1; });

    const presentStatuses = Object.keys(counts);
    const labels = presentStatuses.map(s => STATUS_LABELS[s] || s);
    const data = presentStatuses.map(s => counts[s]);
    const colors = presentStatuses.map(s => STATUS_COLORS[s] || '#94a3b8');

    window.statusChart.data.labels = labels.length ? labels : ['No tickets yet'];
    window.statusChart.data.datasets[0].data = data.length ? data : [1];
    window.statusChart.data.datasets[0].backgroundColor = colors.length ? colors : ['#e2e8f0'];
    window.statusChart.update();
}

// Update trend chart with real average resolution time (hours) by day of week,
// from tickets actually resolved within the selected period.
function updateTrendChart(tickets, days) {
    if (!window.trendChart) return;

    const sums = [0, 0, 0, 0, 0, 0, 0];
    const counts = [0, 0, 0, 0, 0, 0, 0];

    tickets.forEach(t => {
        if (t.resolvedAt && t.actualResolutionHours != null) {
            const dayIdx = new Date(t.resolvedAt).getDay();
            sums[dayIdx] += t.actualResolutionHours;
            counts[dayIdx]++;
        }
    });

    const averages = sums.map((sum, i) => counts[i] > 0 ? Math.round((sum / counts[i]) * 10) / 10 : 0);

    window.trendChart.data.labels = DAY_LABELS;
    window.trendChart.data.datasets[0].data = averages;
    window.trendChart.update();
}

// Setup sidebar toggle
function setupSidebarToggle() {
    const sidebarToggle = document.getElementById('sidebarToggle');
    const sidebar = document.querySelector('.sidebar');
    
    if (sidebarToggle && sidebar) {
        sidebarToggle.addEventListener('click', function() {
            sidebar.classList.toggle('open');
        });
        
        // Close sidebar when clicking outside on mobile
        document.addEventListener('click', function(event) {
            if (window.innerWidth <= 1024) {
                if (!sidebar.contains(event.target) && !sidebarToggle.contains(event.target)) {
                    sidebar.classList.remove('open');
                }
            }
        });
    }
}

// Setup activity tabs
function setupActivityTabs() {
    const tabButtons = document.querySelectorAll('.tab-btn');
    const tabContents = document.querySelectorAll('.tab-content');
    
    tabButtons.forEach(button => {
        button.addEventListener('click', function() {
            const targetTab = this.dataset.tab;
            
            // Update active tab button
            tabButtons.forEach(btn => btn.classList.remove('active'));
            this.classList.add('active');
            
            // Update active tab content
            tabContents.forEach(content => {
                content.classList.remove('active');
                if (content.id === `${targetTab}-tab`) {
                    content.classList.add('active');
                }
            });
            
            // Load tab-specific data
            loadTabData(targetTab);
        });
    });
}

// Load tab data
function loadTabData(tabName) {
    // Simulate loading different data for each tab
    console.log(`Loading data for ${tabName} tab...`);
    
    // In a real application, you would fetch data from the server
    // For now, we'll just show a loading indicator
    showTabLoading(tabName);
    
    setTimeout(() => {
        hideTabLoading(tabName);
        // Update tab content with new data
    }, 1000);
}

// Show tab loading
function showTabLoading(tabName) {
    const tabContent = document.getElementById(`${tabName}-tab`);
    if (tabContent) {
        const loadingDiv = document.createElement('div');
        loadingDiv.className = 'tab-loading';
        loadingDiv.innerHTML = `
            <div class="loading-spinner"></div>
            <p>Loading ${tabName}...</p>
        `;
        tabContent.appendChild(loadingDiv);
    }
}

// Hide tab loading
function hideTabLoading(tabName) {
    const tabContent = document.getElementById(`${tabName}-tab`);
    if (tabContent) {
        const loadingDiv = tabContent.querySelector('.tab-loading');
        if (loadingDiv) {
            loadingDiv.remove();
        }
    }
}

// Initialize search functionality
function initializeSearch() {
    const searchInput = document.getElementById('searchInput');
    if (searchInput) {
        // Add search suggestions
        setupSearchSuggestions(searchInput);
    }
}

// Setup search suggestions
function setupSearchSuggestions(searchInput) {
    const suggestions = [
        'System login issue',
        'Password reset',
        'Email not working',
        'Software installation',
        'Network connectivity',
        'Hardware problem',
        'Account access',
        'Billing question'
    ];
    
    let suggestionIndex = 0;
    
    // Rotate placeholder text
    setInterval(() => {
        searchInput.placeholder = `Search tickets... (e.g., ${suggestions[suggestionIndex]})`;
        suggestionIndex = (suggestionIndex + 1) % suggestions.length;
    }, 3000);
}

// Handle search
function handleSearch(event) {
    const query = event.target.value.trim();
    
    if (query.length >= 2) {
        // Perform search
        performSearch(query);
    } else if (query.length === 0) {
        // Clear search results
        clearSearchResults();
    }
}

// Perform search — calls the real /api/tickets/search endpoint.
// Previously this called simulateSearch(), which always returned three fake
// tickets named after whatever you typed, with a hardcoded status — never
// your real ticket. It also never called hideSearchLoading(), so the search
// box stayed in its "loading" state permanently after the first search.
async function performSearch(query) {
    showSearchLoading();
    try {
        const response = await fetch('/api/tickets/search?query=' + encodeURIComponent(query));
        if (!response.ok) {
            throw new Error(`HTTP ${response.status}`);
        }
        const results = await response.json();
        displaySearchResults(results);
    } catch (error) {
        console.error('Search error:', error);
        showSearchError();
    } finally {
        hideSearchLoading();
    }
}

// Display search results
function displaySearchResults(results) {
    // Create search results dropdown
    let searchResults = document.getElementById('searchResults');
    
    if (!searchResults) {
        searchResults = document.createElement('div');
        searchResults.id = 'searchResults';
        searchResults.className = 'search-results';
        document.querySelector('.search-box').appendChild(searchResults);
    }
    
    if (results.length === 0) {
        searchResults.innerHTML = '<div class="no-results">No tickets found</div>';
    } else {
        const resultsHtml = results.map(result => `
            <div class="search-result-item" onclick="viewTicket(${result.id})">
                <div class="result-title">${result.title}</div>
                <div class="result-meta">
                    <span class="status-badge status-${result.status.toLowerCase()}">${result.status}</span>
                    <span class="priority-badge priority-${result.priority.toLowerCase()}">${result.priority}</span>
                </div>
            </div>
        `).join('');
        
        searchResults.innerHTML = resultsHtml;
    }
    
    searchResults.style.display = 'block';
}

// Clear search results
function clearSearchResults() {
    const searchResults = document.getElementById('searchResults');
    if (searchResults) {
        searchResults.style.display = 'none';
    }
}

// Show search loading
function showSearchLoading() {
    const searchBox = document.querySelector('.search-box');
    if (searchBox) {
        searchBox.classList.add('loading');
    }
}

// Hide search loading
function hideSearchLoading() {
    const searchBox = document.querySelector('.search-box');
    if (searchBox) {
        searchBox.classList.remove('loading');
    }
}

// Show search error
function showSearchError() {
    hideSearchLoading();
    showNotification('Search failed. Please try again.', 'error');
}

// Show notifications
// Get notification icon
function getNotificationIcon(type) {
    switch (type) {
        case 'info': return 'info-circle';
        case 'warning': return 'exclamation-triangle';
        case 'success': return 'check-circle';
        case 'error': return 'times-circle';
        default: return 'bell';
    }
}

// Load dashboard data — the stat cards already show the real count on page load
// (server-rendered by DashboardController). This refreshes them without a full
// reload, using the same metrics the Reports page uses.
async function loadDashboardData() {
    try {
        const response = await fetch('/api/tickets/metrics');
        if (!response.ok) return;
        const metrics = await response.json();
        updateDashboardStats({
            totalTickets: metrics.totalTickets,
            openTickets: metrics.openTickets,
            resolvedTickets: metrics.resolvedTickets
        });
    } catch (error) {
        console.error('Error loading dashboard data:', error);
    }
}

// Update dashboard stats
function updateDashboardStats(data) {
    // Update stat cards with animation
    Object.keys(data).forEach(key => {
        const element = document.querySelector(`[data-stat="${key}"]`);
        if (element) {
            animateNumber(element, 0, data[key], 1000);
        }
    });
}

// Animate number
function animateNumber(element, start, end, duration) {
    const startTime = performance.now();
    const difference = end - start;
    
    function updateNumber(currentTime) {
        const elapsed = currentTime - startTime;
        const progress = Math.min(elapsed / duration, 1);
        
        const current = Math.floor(start + (difference * progress));
        element.textContent = current;
        
        if (progress < 1) {
            requestAnimationFrame(updateNumber);
        }
    }
    
    requestAnimationFrame(updateNumber);
}

// Setup real-time updates
function setupRealTimeUpdates() {
    // Periodically re-fetch real tickets and refresh the charts and stat cards.
    // Clearing the cache forces refreshChartsForPeriod to pull fresh data instead
    // of recomputing from what might now be stale.
    setInterval(() => {
        allTicketsCache = null;
        const activeBtn = document.querySelector('#statusChart')
            ?.closest('.chart-card')?.querySelector('.btn-filter.active');
        refreshChartsForPeriod(activeBtn ? activeBtn.dataset.period : 'week');
        loadDashboardData();
    }, 30000);
}

// Show notification
function showNotification(message, type = 'info') {
    const notification = document.createElement('div');
    notification.className = `dashboard-notification ${type}`;
    notification.innerHTML = `
        <i class="fas fa-${getNotificationIcon(type)}"></i>
        <span>${message}</span>
        <button onclick="this.parentElement.remove()">
            <i class="fas fa-times"></i>
        </button>
    `;
    
    document.body.appendChild(notification);
    
    // Auto-remove after 5 seconds
    setTimeout(() => {
        if (notification.parentElement) {
            notification.remove();
        }
    }, 5000);
}

// Show loading state
function showLoadingState() {
    document.body.classList.add('loading');
}

// Hide loading state
function hideLoadingState() {
    document.body.classList.remove('loading');
}

// View ticket
function viewTicket(ticketId) {
    // There is no single-ticket detail page yet, so this goes to the full list,
    // which is the real page where the ticket actually lives.
    window.location.href = `/tickets`;
}

// Utility function: debounce
function debounce(func, wait) {
    let timeout;
    return function executedFunction(...args) {
        const later = () => {
            clearTimeout(timeout);
            func(...args);
        };
        clearTimeout(timeout);
        timeout = setTimeout(later, wait);
    };
}

// Add CSS for new features
const dashboardStyles = document.createElement('style');
dashboardStyles.textContent = `
    .search-results {
        position: absolute;
        top: 100%;
        left: 0;
        right: 0;
        background: white;
        border: 1px solid #e2e8f0;
        border-radius: 8px;
        box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1);
        max-height: 300px;
        overflow-y: auto;
        z-index: 1000;
        display: none;
    }
    
    .search-result-item {
        padding: 12px;
        border-bottom: 1px solid #f1f5f9;
        cursor: pointer;
        transition: background-color 0.2s ease;
    }
    
    .search-result-item:hover {
        background-color: #f8fafc;
    }
    
    .search-result-item:last-child {
        border-bottom: none;
    }
    
    .result-title {
        font-weight: 500;
        color: #1e293b;
        margin-bottom: 4px;
    }
    
    .result-meta {
        display: flex;
        gap: 8px;
        align-items: center;
    }
    
    .no-results {
        padding: 20px;
        text-align: center;
        color: #64748b;
    }
    
    .notifications-dropdown {
        position: absolute;
        top: 100%;
        right: 0;
        background: white;
        border: 1px solid #e2e8f0;
        border-radius: 8px;
        box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1);
        width: 350px;
        max-height: 400px;
        overflow-y: auto;
        z-index: 1000;
        display: none;
    }
    
    .notification-item {
        display: flex;
        gap: 12px;
        padding: 16px;
        border-bottom: 1px solid #f1f5f9;
        transition: background-color 0.2s ease;
    }
    
    .notification-item:hover {
        background-color: #f8fafc;
    }
    
    .notification-item:last-child {
        border-bottom: none;
    }
    
    .notification-icon {
        width: 32px;
        height: 32px;
        border-radius: 50%;
        display: flex;
        align-items: center;
        justify-content: center;
        flex-shrink: 0;
    }
    
    .notification-item.info .notification-icon {
        background-color: #dbeafe;
        color: #1d4ed8;
    }
    
    .notification-item.warning .notification-icon {
        background-color: #fef3c7;
        color: #d97706;
    }
    
    .notification-item.success .notification-icon {
        background-color: #d1fae5;
        color: #059669;
    }
    
    .notification-content {
        flex: 1;
    }
    
    .notification-message {
        font-weight: 500;
        color: #1e293b;
        margin-bottom: 4px;
    }
    
    .notification-time {
        font-size: 0.8rem;
        color: #64748b;
    }
    
    .tab-loading {
        text-align: center;
        padding: 40px;
        color: #64748b;
    }
    
    .loading-spinner {
        width: 40px;
        height: 40px;
        border: 3px solid #f3f4f6;
        border-top: 3px solid #6366f1;
        border-radius: 50%;
        animation: spin 1s linear infinite;
        margin: 0 auto 16px;
    }
    
    .new-activity {
        animation: highlightNew 2s ease;
    }
    
    @keyframes highlightNew {
        0% { background-color: #dbeafe; }
        100% { background-color: transparent; }
    }
    
    .dashboard-notification {
        position: fixed;
        top: 20px;
        right: 20px;
        background: white;
        border: 1px solid #e2e8f0;
        border-radius: 8px;
        padding: 16px;
        box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1);
        display: flex;
        align-items: center;
        gap: 12px;
        z-index: 1000;
        animation: slideIn 0.3s ease;
    }
    
    .dashboard-notification.info {
        border-left: 4px solid #3b82f6;
    }
    
    .dashboard-notification.warning {
        border-left: 4px solid #f59e0b;
    }
    
    .dashboard-notification.success {
        border-left: 4px solid #10b981;
    }
    
    .dashboard-notification.error {
        border-left: 4px solid #ef4444;
    }
    
    .dashboard-notification button {
        background: none;
        border: none;
        cursor: pointer;
        color: #64748b;
        padding: 4px;
        border-radius: 4px;
    }
    
    .dashboard-notification button:hover {
        background: #f1f5f9;
    }
    
    .search-box.loading::after {
        content: '';
        position: absolute;
        right: 12px;
        top: 50%;
        transform: translateY(-50%);
        width: 16px;
        height: 16px;
        border: 2px solid #e2e8f0;
        border-top: 2px solid #6366f1;
        border-radius: 50%;
        animation: spin 1s linear infinite;
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
    
    @keyframes spin {
        0% { transform: rotate(0deg); }
        100% { transform: rotate(360deg); }
    }
    
    @keyframes pulse {
        0%, 100% { opacity: 1; }
        50% { opacity: 0.5; }
    }
    
    .loading {
        pointer-events: none;
    }
    
    .loading::after {
        content: '';
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background: rgba(255, 255, 255, 0.8);
        z-index: 9999;
        display: flex;
        align-items: center;
        justify-content: center;
    }
    
    .loading::before {
        content: 'Loading...';
        position: fixed;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        z-index: 10000;
        font-size: 18px;
        color: #6366f1;
        font-weight: 500;
    }
`;

document.head.appendChild(dashboardStyles);


