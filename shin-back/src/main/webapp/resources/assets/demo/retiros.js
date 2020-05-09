
var validations = {
	retiroBancarioValidation : function() {

		var form = $("#formRetiroBancario");
		
		form.validate({
			rules : {
				cantidadBonificacion: {
	      			required: true,
	      			range: [100.0, 500.0]
	  			}
			}
		});

		console.log(form.valid())
		if (form.valid()) {
			console.log( "Enviando" )
			
			var post_url = form.attr("action"); //get form action url
			var request_method = form.attr("method"); //get form GET/POST method
			var form_data = form.serialize(); //Encode form elements for submission
			
			
			var data = form.serialize()
			
			$.ajax({
				url : post_url,
				type: request_method,
				data : form_data
			})//Ajax
			.done(function( data ) {
				console.log( data.code );
			});//Done
		}

	},
	retiroBancarioValidation : function() {

		var form = $("#formRetiroPaypal");
		
		form.validate({
			rules : {
				cantidadBonificacion: {
	      			required: true,
	      			range: [100.0, 500.0]
	  			}
			}
		});

		console.log(form.valid())
		if (form.valid()) {
			console.log( "Enviando" )
			
			var post_url = form.attr("action"); //get form action url
			var request_method = form.attr("method"); //get form GET/POST method
			var form_data = form.serialize(); //Encode form elements for submission
			
			
			var data = form.serialize()
			
			$.ajax({
				url : post_url,
				type: request_method,
				data : form_data
			})//Ajax
			.done(function( data ) {
				console.log( data.code );
			});//Done
		}

	},
	retiroRecargaValidation: function(){

		var form = $("#formRetiroRecarga");
		
		form.validate({
			rules : {
				cantidadBonificacion: {
	      			required: true,
	      			range: [100.0, 500.0]
	  			}
			}
		});

		console.log(form.valid())
		if (form.valid()) {
			console.log( "Enviando" )
			
			var post_url = form.attr("action"); //get form action url
			var request_method = form.attr("method"); //get form GET/POST method
			var form_data = form.serialize(); //Encode form elements for submission
			
			
			var data = form.serialize()
			
			$.ajax({
				url : post_url,
				type: request_method,
				data : form_data
			})//Ajax
			.done(function( data ) {
				console.log( data.code );
			});//Done
		}

	},
	formCuentaBancariaValidation: function(){
		var form = $("#formCuentaBancaria");
		
		form.validate();

		console.log(form.valid())
		if (form.valid()) {
			var post_url = form.attr("action"); //get form action url
			var request_method = form.attr("method"); //get form GET/POST method
			var form_data = form.serialize(); //Encode form elements for submission
			
			
			var data = form.serialize()
			
			$.ajax({
				url : post_url,
				type: request_method,
				data : form_data
			})//Ajax
			.done(function( data ) {
				console.log( "Status" + data );
			});//Done
		}
	},
	formCuentaTarjetaValidation: function(){
		var form = $("#formCuentaTarjeta");
		
		form.validate();

		console.log(form.valid())
		if (form.valid()) {
			var post_url = form.attr("action"); //get form action url
			var request_method = form.attr("method"); //get form GET/POST method
			var form_data = form.serialize(); //Encode form elements for submission
			
			
			var data = form.serialize()
			
			$.ajax({
				url : post_url,
				type: request_method,
				data : form_data
			})//Ajax
			.done(function( data ) {
				console.log( "Status" + data );
			});//Done
		}
	},
	formCuentaClabeValidation: function(){
		var form = $("#formCuentaClabe");
		
		form.validate();

		console.log(form.valid())
		if (form.valid()) {
			var post_url = form.attr("action"); //get form action url
			var request_method = form.attr("method"); //get form GET/POST method
			var form_data = form.serialize(); //Encode form elements for submission
			
			
			var data = form.serialize()
			
			$.ajax({
				url : post_url,
				type: request_method,
				data : form_data
			})//Ajax
			.done(function( data ) {
				console.log( "Status" + data );
			});//Done
		}
	},
	formCuentaPaypalValidation: function(){
		var form = $("#formCuentaPaypal");
		
		form.validate();

		console.log(form.valid())
		if (form.valid()) {
			var post_url = form.attr("action"); //get form action url
			var request_method = form.attr("method"); //get form GET/POST method
			var form_data = form.serialize(); //Encode form elements for submission
			
			
			var data = form.serialize()
			
			$.ajax({
				url : post_url,
				type: request_method,
				data : form_data
			})//Ajax
			.done(function( data ) {
				console.log( "Status" + data );
			});//Done
		}
	},
	formCuentaTelefonicaValidation: function(){
		var form = $("#formCuentaTelefonica");
		var v = $("#formCuentaTelefonica #cuentaMedioBonificacion");
		var v1 = $("#formCuentaTelefonica #cuentaMedioBonificacion").val();
		var v2 = $("#formCuentaTelefonica #cuentaMedioBonificacion2").val();
		
		form.validate();
		
		$( "#formCuentaTelefonica #cuentaMedioBonificacion" ).rules( "add", {
			  required: true,
			  rangelength:[10,10],
			  equalTo: '#formCuentaTelefonica #cuentaMedioBonificacion2'
		});
		
		$( "#formCuentaTelefonica #cuentaMedioBonificacion2" ).rules( "add", {
			  required: true,
			  rangelength:[10,10],
			  equalTo: '#formCuentaTelefonica #cuentaMedioBonificacion'
		});

		console.log(form.valid())
		if (form.valid()) {
			var post_url = form.attr("action"); //get form action url
			var request_method = form.attr("method"); //get form GET/POST method
			var form_data = form.serialize(); //Encode form elements for submission
			
			
			var data = form.serialize()
			
			$.ajax({
				url : post_url,
				type: request_method,
				data : form_data
			})//Ajax
			.done(function( data ) {
				console.log( "Status" + data );
			});//Done
		}
	},
	clean : function(){
	}
};

$(document).ready(function() {
	
	$("#submitBancario").click(function(e){
		validations.retiroBancarioValidation();		
	});
	
	$("#submitPaypal").click(function(e){
		validations.retiroBancarioValidation();
	});
	
	$("#submitRecarga").click(function(e){
		validations.retiroRecargaValidation();
	});
	
	$("#toStep2").click(function(e){
		var id = $('#tipo-cuenta').val();
		
		var form = $('#formStep1');
		form.validate({
			rules : {
				'tipo-cuenta': {
	      			required: true
	  			}
			}
		});

		console.log(form.valid())
		if (form.valid()) {
			if( id == '1' ){
				$('#step1').modal('hide')
				$('#step2-bancaria').modal('show')	
			}
			else if( id == '2' ){
				$('#step1').modal('hide')
				$('#step2-tarjeta').modal('show')	
			}
			else if( id == '3' ){
				$('#step1').modal('hide')
				$('#step2-clabe').modal('show')	
			}
			else if( id == '4' ){
				$('#step1').modal('hide')
				$('#step2-paypal').modal('show')	
			}
			else if( id == '5' ){
				$('#step1').modal('hide')
				$('#step2-recarga').modal('show')	
			}
			else{
				
			}
		}
	});
	
	//Alta de cuentas
	$("#submitCuentaBancaria").click(function(e){
		validations.formCuentaBancariaValidation();
	}); //Click
	
		
	$("#submitTarjetaBancaria").click(function(e){
		validations.formCuentaTarjetaValidation();
	}); //Click
	
	
	$("#submitCuentaClabe").click(function(e){
		validations.formCuentaClabeValidation();
		
	}); //Click
	
	$("#submitCuentaPayPal").click(function(e){
		validations.formCuentaPaypalValidation();
	}); //Click
	
	$("#submitTelefono").click(function(e){
		validations.formCuentaTelefonicaValidation();
		
	}); //Click
		
});//ready