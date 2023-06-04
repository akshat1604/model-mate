$('#login-ref').on('click',function(event){
$.ajax({
    type: 'GET',
    url: '/api/users/checkLogin',
    success: function(response) {
      // If the user is logged in, render the restricted page
      window.location.href = '/playground.html';
    },
    error: function(xhr, status, error) {
      // If the user is not logged in, render the login form
      window.location.href = '/login.html'
    }
  });
});

$('#login-link').on('click',function(event){
$.ajax({
    type: 'GET',
    url: '/api/users/checkLogin',
    success: function(response) {
      // If the user is logged in, render the restricted page
      window.location.href = '/playground.html';
    },
    error: function(xhr, status, error) {
      // If the user is not logged in, render the login form
      window.location.href = '/login.html'
    }
  });
});


$('#login-playground').on('click',function(event){
$.ajax({
    type: 'GET',
    url: '/api/users/checkLogin',
    success: function(response) {
      // If the user is logged in, render the restricted page
      window.location.href = '/playground.html';
    },
    error: function(xhr, status, error) {
      // If the user is not logged in, render the login form
      window.location.href = '/login.html'
    }
  });
});

  // Check if the user is already logged in


  // Handle form submit for login form
  $('#login-form').submit(function(event) {
    event.preventDefault(); // Prevent form from submitting normally
    var formData = $(this).serializeArray(); // Serialize form data as array
    var requestData = {}; // Create an empty object for the JSON data
    $.each(formData, function(index, field) {
      requestData[field.name] = field.value; // Add each form field to the JSON object
    });
    $.ajax({
      type: 'POST',
      url: '/api/users/login',
      data: JSON.stringify(requestData), // Serialize JSON object as a string
      contentType: "application/json", // Set content type to JSON
      success: function(response) {
        $('#login-message').html('');
        setSessionCookie(response);
        setTimeout(function() {
                          window.location.href = '/playground.html';
                        }, 3000);
      },
      error: function(xhr, status, error) {
        // If login fails, display error message
        $('#login-message').html('<div class="alert alert-danger" role="alert">' + xhr.responseJSON.error + '</div>');
      }
    });
  });

  // Handle logout button click
  $('#logout-link').click(function() {
    // Delete session cookie and render login form
    deleteSessionCookie();
    window.location.href = '/index.html'
  });


  // Function to set session cookie
  function setSessionCookie(token) {
    document.cookie = 'session_token=' + token.sessionId;
  }

  // Function to delete session cookie
  function deleteSessionCookie() {
    document.cookie = 'session_token=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
  }

