var chartBackground = [
	'rgba(255, 99, 132, 0.2)',
	'rgba(54, 162, 235, 0.2)',
	'rgba(255, 206, 86, 0.2)',
	'rgba(75, 192, 192, 0.2)',
	'rgba(153, 102, 255, 0.2)',
	'rgba(255, 159, 64, 0.2)',
	'rgba(250, 99, 132, 0.2)',
	'rgba(49, 162, 235, 0.2)',
	'rgba(250, 206, 86, 0.2)',
	'rgba(70, 192, 192, 0.2)',
	'rgba(148, 102, 255, 0.2)',
	'rgba(250, 159, 64, 0.2)'
]

var chartBackgroundBorder = [
	'rgba(255, 99, 132, 1)',
	'rgba(54, 162, 235, 1)',
	'rgba(255, 206, 86, 1)',
	'rgba(75, 192, 192, 1)',
	'rgba(153, 102, 255, 1)',
	'rgba(255, 159, 64, 1)',
	'rgba(250, 99, 132, 1)',
	'rgba(49, 162, 235, 1)',
	'rgba(250, 206, 86, 1)',
	'rgba(70, 192, 192, 1)',
	'rgba(148, 102, 255, 1)',
	'rgba(250, 159, 64, 1)'
]

var url = 'http://www.shingshing.com'
//var url = 'http://localhost:8080/shin-back'

var estadisticas_usuario = {
		
		loadCharts : function() {
			//http://shinshin-env.m7izq9trpe.us-east-2.elasticbeanstalk.com
			//http://www.shingshing.com
			//www.shingshing.com
			
			var str = $("#marca.idCatalogoMarca").val();
			var idMarca = $("#idMarca").val();
			var params = "?idMarca=" + idMarca;
			//console.log( "Proveedor: " + str + " - " + v1 );
			
			$.ajax({
				url : url + "/estadisticas/empresa/charts/dashboard-usuario" + params,
				dataType : "json",
				success : function(result) {

					console.log(result);
					var data=[], label=[];

					//Finanzas(Bonificaciones), Productos, Usuarios
					$.each(result, function(index, item) {
						data.push(item.total)
						label.push(item.titulo)
						
					});
					
					var ctx = document.getElementById('geografiaChart').getContext('2d');

					window.finanzasChart = new Chart(ctx, {
						type: 'bar',
						data: {
							labels: label,
							datasets: [{
								label: '#',
								data: data,
								backgroundColor: chartBackground,
								borderColor: chartBackgroundBorder,
								borderWidth: 1
							}]
						},
						options: {
							scales: {
								yAxes: [{
									ticks: {
										beginAtZero: true
									}
								}]
							}
						}
					 });
				},
				error: function() {
			        console.log("No se ha podido obtener la información");
			    }
			});
	},
	init : function() {
		estadisticas_usuario.loadCharts();
	}
};

$(document).ready(function() {
	estadisticas_usuario.init();
});
