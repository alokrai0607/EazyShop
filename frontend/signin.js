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
sendSignInRequest(formData);


});

function sendSignInRequest(formData) {
  var apiEndpoint = 'http://localhost:8080/login';
  var requestOptions = {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(formData)
  };

  fetch(apiEndpoint, requestOptions)
    .then(response => {
      if (response.ok) {
        return response.json();
      } else {
        throw new Error('Error: ' + response.status + ' ' + response.statusText);
      }
    })
    .then(data => {
      localStorage.setItem('uuid', data.uuid);
    })
    .catch(error => {
      console.error('Error:', error.message);
    });
}




