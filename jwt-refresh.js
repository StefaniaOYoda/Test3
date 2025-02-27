$(document).ready(function () {
    // Instead of auto-login with hard-coded credentials, we should prompt for login
    setupLoginForm();
})

function setupLoginForm() {
    // This would typically be HTML form setup code
    // For demonstration purposes only
    $('#loginButton').on('click', function() {
        var username = $('#username').val();
        var password = $('#password').val();
        login(username, password);
    });
}

function login(user, password) {
    if (!user || !password) {
        console.error('Username and password are required');
        return;
    }
    
    $.ajax({
        type: 'POST',
        contentType: "application/json",
        data: JSON.stringify({user: user, password: password})
    }).success(
        function (response) {
            localStorage.setItem('access_token', response['access_token']);
            localStorage.setItem('refresh_token', response['refresh_token']);
        }
    )
}

//Dev comment: Pass token as header as we had an issue with tokens ending up in the access_log
webgoat.customjs.addBearerToken = function () {
    var headers_to_set = {};
    headers_to_set['Authorization'] = 'Bearer ' + localStorage.getItem('access_token');
    return headers_to_set;
}

//Dev comment: Temporarily disabled from page we need to work out the refresh token flow but for now we can go live with the checkout page
function newToken() {
    localStorage.getItem('refreshToken');
    $.ajax({
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('access_token')
        },
        type: 'POST',
        data: JSON.stringify({refreshToken: localStorage.getItem('refresh_token')})
    }).success(
        function () {
            localStorage.setItem('access_token', apiToken);
            localStorage.setItem('refresh_token', refreshToken);
        }
    )
}
