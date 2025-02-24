$(document).ready(function () {
    login('Jerry');
})

function login(user) {
    // Password retrieval mechanism should be implemented here securely.
    // This example uses a placeholder; DO NOT USE THIS IN PRODUCTION.
    let password = getPasswordFromSecureSource(user); 
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

function getPasswordFromSecureSource(user) {
    // Replace this with your secure password retrieval method
    //  (e.g., environment variable, secure config service, database lookup)
    // This is a placeholder and should NOT be used in production code.
    console.warn("getPasswordFromSecureSource is a placeholder and should be replaced with a secure implementation.");
    return ""; // Placeholder – replace with actual password retrieval
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

