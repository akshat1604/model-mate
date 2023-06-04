function handleSignupFormSubmit(event) {
  event.preventDefault(); // Prevent form from submitting normally
  $('#signup-message').empty();
  var formData = $(this).serializeArray(); // Serialize form data as array
  var requestData = {}; // Create an empty object for the JSON data
  $.each(formData, function(index, field) {
    requestData[field.name] = field.value; // Add each form field to the JSON object
  });
  $.ajax({
    type: 'POST',
    url: '/api/users/createUser',
    data: JSON.stringify(requestData), // Serialize JSON object as a string
    contentType: "application/json", // Set content type to JSON
    success: function(response) {
    $('#signup-message').html('');
    $('#signup-message').html('<div class="alert alert-success" role="alert">' + response.message +  '</div>');
      // Redirect to new page on successful signup
    setTimeout(function() {
                  window.location.href = '/login.html';
                }, 3000);

    },
    error: function(xhr, status, error) {
      // Display error message if signup fails
      console.log('error!');
      console.log(status);
      $('#signup-message').html('<div class="alert alert-danger" role="alert">' + xhr.responseJSON.error + '</div>');
    }
  });
}

$(function() {
  $('#signup-form').submit(handleSignupFormSubmit);
});
