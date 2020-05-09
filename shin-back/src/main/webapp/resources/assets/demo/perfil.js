
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
	  		            return  ( $("#contrasenia").val().length > 0 || $("#confirmarContrasenia").val().length > 0 );
	  		        }
	  			},
	  			contrasenia: {
	  				minlength: 8,
	  				required: function(element){	  					
	  		            return  ( $("#contraseniaActual").val().length > 0 || $("#confirmarContrasenia").val().length > 0 );
	  		        },
	  				equalTo: '#confirmarContrasenia'
	  			},
	  			confirmarContrasenia: {
	  				minlength: 8,
	  				required: function(element){	  					
	  		            return  ( $("#contrasenia").val().length > 0 || $("#contraseniaActual").val().length > 0 );
	  		        },
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
				console.log( "Respuesta: " + data.code);
				if( data.code == 200 ){
					clean();
				}
			});// Done
		}

	},

	clean : function(){
		$("#contraseniaActual").val('');
		$("#contrasenia").val('');
		$("#confirmarContrasenia").val('');
	}
};

$(document).ready(function() {
	$("#submitPerfil").click(function(e) {
		validations.valid()

	}); // Click
});
