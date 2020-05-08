
var validations = {
	valid : function() {

		var form = $("#formPerfil");

		form.validate({
			rules : {
				nombre: {
	      			required: true
	  			},
	  			contraseniaActual: { //contrasenia, confirmarContrasenia
	  				minlength: 8,
	  				required: function(element){
	  					console.log( $("#contraseniaActual").val().length > 0 )
	  					console.log( $("#contrasenia").val().length > 0 )
	  					console.log( $("#confirmarContrasenia").val().length > 0 )
	  					
	  		            return $("#contrasenia").val().length > 0;
	  		        }
	  			},
	  			contrasenia: {
	  				minlength: 8,
	  				equalTo: '#confirmarContrasenia'
	  			},
	  			confirmarContrasenia: {
	  				minlength: 8,
	  				equalTo: '#contrasenia'
	  			},
				telMovil:{
	  				required: true,
	      			minlength: 10
	  	  	  	}
			}
			
		});

		console.log(form.valid())
		if (form.valid()) {
			console.log("Enviando")

			var post_url = form.attr("action"); 
			var request_method = form.attr("method");
			var form_data = form.serialize();
			console.log("Data: " + form_data)

			var data = form.serialize()

			$.ajax({
				url : post_url,
				type : request_method,
				data : form_data
			})// Ajax
			.done(function(data) {
				console.log(data.code);
			});// Done
		}

	}
};

$(document).ready(function() {
	$("#submitPerfil").click(function(e) {
		validations.valid()

	}); // Click
});
