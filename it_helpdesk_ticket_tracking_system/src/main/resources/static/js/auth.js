// Toggle password visibility for password input
function togglePassword() {
    const passwordInput = document.getElementById('password');
    const toggleBtnIcon = document.querySelector('.toggle-password i');

    if (passwordInput.type === 'password') {
        passwordInput.type = 'text';
        toggleBtnIcon.classList.remove('fa-eye');
        toggleBtnIcon.classList.add('fa-eye-slash');
    } else {
        passwordInput.type = 'password';
        toggleBtnIcon.classList.remove('fa-eye-slash');
        toggleBtnIcon.classList.add('fa-eye');
    }
}

// Toggle password visibility for confirm password input
function toggleConfirmPassword() {
    const confirmPasswordInput = document.getElementById('confirmPassword');
    const toggleBtnIcon = confirmPasswordInput.nextElementSibling.querySelector('i');

    if (confirmPasswordInput.type === 'password') {
        confirmPasswordInput.type = 'text';
        toggleBtnIcon.classList.remove('fa-eye');
        toggleBtnIcon.classList.add('fa-eye-slash');
    } else {
        confirmPasswordInput.type = 'password';
        toggleBtnIcon.classList.remove('fa-eye-slash');
        toggleBtnIcon.classList.add('fa-eye');
    }
}

document.addEventListener('DOMContentLoaded', function () {
    const loginForm = document.querySelector('.login-form');
    const registerForm = document.querySelector('.register-form');

    if (loginForm) {
        loginForm.addEventListener('submit', function (e) {
            const email = document.getElementById('email')?.value.trim();
            const password = document.getElementById('password')?.value.trim();

            if (!email || !password) {
                e.preventDefault();
                showAlert('Please fill in all fields', 'error');
            }
        });
    }

    if (registerForm) {
        registerForm.addEventListener('submit', function (e) {
            const password = document.getElementById('password')?.value;
            const confirmPassword = document.getElementById('confirmPassword')?.value;

            if (!password || !confirmPassword) {
                e.preventDefault();
                showAlert('Please fill in all fields', 'error');
                return;
            }

            if (password !== confirmPassword) {
                e.preventDefault();
                showAlert('Passwords do not match', 'error');
                return;
            }

            if (password.length < 6) {
                e.preventDefault();
                showAlert('Password must be at least 6 characters long', 'error');
                return;
            }
        });
    }
});

function showAlert(message, type) {
    const alertDiv = document.createElement('div');
    alertDiv.className = `alert alert-${type}`;
    alertDiv.textContent = message;

    const form = document.querySelector('.auth-form');
    if (form) {
        form.insertBefore(alertDiv, form.firstChild);

        setTimeout(() => {
            alertDiv.remove();
        }, 5000);
    }
}
