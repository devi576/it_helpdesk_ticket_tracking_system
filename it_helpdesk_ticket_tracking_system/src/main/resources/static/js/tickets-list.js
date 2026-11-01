// Powers the Assign / Remove actions on /tickets for admins and managers.
document.addEventListener('DOMContentLoaded', function () {
    document.querySelectorAll('.btn-assign').forEach(function (btn) {
        btn.addEventListener('click', handleAssign);
    });
    document.querySelectorAll('.btn-remove').forEach(function (btn) {
        btn.addEventListener('click', handleRemove);
    });
    document.querySelectorAll('.btn-resolve').forEach(function (btn) {
        btn.addEventListener('click', handleResolve);
    });
});

async function handleResolve(event) {
    const btn = event.currentTarget;
    const row = btn.closest('tr');
    const ticketId = row.dataset.ticketId;

    const resolutionNotes = prompt('Resolution notes (what fixed this ticket?):');
    if (resolutionNotes === null) return; // cancelled
    if (!resolutionNotes.trim()) {
        showListNotification('Resolution notes are required.', 'error');
        return;
    }

    const hoursInput = prompt('Actual hours spent resolving this ticket:', '1');
    if (hoursInput === null) return; // cancelled
    const actualHours = parseInt(hoursInput, 10);
    if (isNaN(actualHours) || actualHours < 0) {
        showListNotification('Please enter a valid number of hours.', 'error');
        return;
    }

    const originalText = btn.textContent;
    btn.disabled = true;
    btn.textContent = 'Saving…';

    try {
        const params = new URLSearchParams({
            resolutionNotes: resolutionNotes,
            actualHours: actualHours
        });
        const response = await fetch('/api/tickets/' + ticketId + '/resolve?' + params.toString(), {
            method: 'POST'
        });

        if (!response.ok) {
            const err = await response.json().catch(() => ({}));
            throw new Error(err.error || `HTTP ${response.status}`);
        }

        const statusBadge = row.querySelector('td:nth-child(3) .badge');
        if (statusBadge) {
            statusBadge.textContent = 'RESOLVED';
            statusBadge.className = 'badge badge-RESOLVED';
        }
        btn.remove();
        showListNotification('Ticket #' + ticketId + ' marked resolved.', 'success');
    } catch (error) {
        console.error('Error resolving ticket:', error);
        showListNotification('Could not resolve ticket: ' + error.message, 'error');
        btn.disabled = false;
        btn.textContent = originalText;
    }
}

async function handleAssign(event) {
    const btn = event.currentTarget;
    const row = btn.closest('tr');
    const ticketId = row.dataset.ticketId;
    const select = row.querySelector('.assign-select');
    const agentId = select.value;

    if (!agentId) {
        showListNotification('Choose an agent first.', 'error');
        return;
    }

    const originalText = btn.textContent;
    btn.disabled = true;
    btn.textContent = 'Assigning…';

    try {
        const response = await fetch('/api/tickets/assign', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                ticketId: parseInt(ticketId, 10),
                assignedToUserId: parseInt(agentId, 10)
                // assignedByUserId is set server-side from the logged-in session
            })
        });

        if (!response.ok) {
            const err = await response.json().catch(() => ({}));
            throw new Error(err.error || `HTTP ${response.status}`);
        }

        const result = await response.json();
        const assignedName = select.options[select.selectedIndex].text;
        row.querySelector('.assigned-cell').textContent = assignedName;
        const statusBadge = row.querySelector('td:nth-child(3) .badge');
        if (statusBadge) {
            statusBadge.textContent = 'IN_PROGRESS';
            statusBadge.className = 'badge badge-IN_PROGRESS';
        }
        showListNotification('Ticket #' + ticketId + ' assigned to ' + assignedName, 'success');
    } catch (error) {
        console.error('Error assigning ticket:', error);
        showListNotification('Could not assign ticket: ' + error.message, 'error');
    } finally {
        btn.disabled = false;
        btn.textContent = originalText;
    }
}

async function handleRemove(event) {
    const btn = event.currentTarget;
    const row = btn.closest('tr');
    const ticketId = row.dataset.ticketId;

    if (!confirm('Remove ticket #' + ticketId + '? This cannot be undone.')) {
        return;
    }

    btn.disabled = true;
    btn.textContent = 'Removing…';

    try {
        const response = await fetch('/api/tickets/' + ticketId, { method: 'DELETE' });
        if (!response.ok) {
            throw new Error(`HTTP ${response.status}`);
        }
        row.remove();
        showListNotification('Ticket #' + ticketId + ' removed', 'success');
    } catch (error) {
        console.error('Error removing ticket:', error);
        showListNotification('Could not remove ticket: ' + error.message, 'error');
        btn.disabled = false;
        btn.textContent = 'Remove';
    }
}

function showListNotification(message, type) {
    const notification = document.createElement('div');
    notification.className = `notification notification-${type === 'error' ? 'error' : 'success'}`;
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
