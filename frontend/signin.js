function getSignInFormValues() {
  var username = document.getElementById("username").value;
  var password = document.getElementById("password").value;
  var type = document.getElementById("type").value;

  return {
    userId: username,
    password: password,
    type: type
  };
}

document.getElementById("signin-form").addEventListener("submit", function(event) {
  event.preventDefault(); 
  var formData = getSignInFormValues();
  signIn(formData.userId, formData.password);


});

function signIn(username, password) {
  var apiEndpoint = 'http://localhost:8080/signIn';
  var authHeader = 'Basic ' + btoa(username + ':' + password);
  var requestOptions = {
    method: 'GET',
    headers: {
      'Authorization': authHeader
    }
  };

  fetch(apiEndpoint, requestOptions)
    .then(response => {
      if (response.ok) {
        const authHeader = response.headers.get('Authorization');
        localStorage.setItem("authkey", authHeader);
        return response.json();
      } else {
        throw new Error('Error: ' + response.status + ' ' + response.statusText);
      }
    })
    .then(data => {
      return data;
    })
    .catch(error => {
      console.error('Error:', error.message);
    });
}







