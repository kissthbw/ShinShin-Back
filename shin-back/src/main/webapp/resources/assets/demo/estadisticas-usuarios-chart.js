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

var estadisticas_usuarios = {
		
		loadCharts : function() {

			$.ajax({
				url : url + "/usuarios/totales",
				dataType : "json",
				success : function(result) {

					console.log(result);
					var usuariosMesData=[], label=[];
					var generoData=[], generoLabel=[];
					var edadesData=[], edadesLabel=[];

					$.each(result.totalUsuariosMes, function(index, item) {
						usuariosMesData.push(item.total)
						label.push(item.topico)
						
					});
					
					$.each(result.totalUsuariosPorGenero, function(index, item) {
						generoData.push(item.total)
						generoLabel.push(item.topico)
						
					});
					
					$.each(result.rangoEdadUsuarios, function(index, item) {
						edadesData.push(item.total)
						edadesLabel.push(item.topico)
						
					});
					
					
					var ctx = document.getElementById('usuariosChart').getContext('2d');

					window.myChart = new Chart(ctx, {
						type: 'bar',
						data: {
							labels: label,
							datasets: [{
								label: 'Total',
								data: usuariosMesData,
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
					
					var ctx = document.getElementById('generoChart').getContext('2d');

					window.generoChart = new Chart(ctx, {
						type: 'bar',
						data: {
							labels: generoLabel,
							datasets: [{
								label: 'Total',
								data: generoData,
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
					
					var ctx = document.getElementById('edadesChart').getContext('2d');

					window.edadesChart = new Chart(ctx, {
						type: 'bar',
						data: {
							labels: edadesLabel,
							datasets: [{
								label: 'Total',
								data: edadesData,
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
					
				}
			});
	},
	usuariosPorDiaChart : function() {

		$.ajax({
			url : url + "/usuarios/totales",
			dataType : "json",
			success : function(result) {

				console.log(result);
				var usuariosData=[], usuarioslabel=[];
				

				$.each(result.totalUsuariosDias, function(index, item) {
					usuariosData.push(item.total)
					usuarioslabel.push("D " + item.indice)
					
				});
				
				
				var ctx = document.getElementById('usuariosChart').getContext('2d');

				//Elmina dataset anterior
				window.myChart.data.labels.splice(0,window.myChart.data.labels.length);
				window.myChart.data.datasets.forEach(function(dataset) {
					dataset.data.pop();
				});
				window.myChart.data.datasets.pop();
				
				window.myChart.update();
				
				//Actualizar con nuevos datos
				var newDataset = {
						label: 'Total' ,
						backgroundColor: chartBackground,
						borderColor: chartBackgroundBorder,
						borderWidth: 1,
						data: usuariosData
				};
				
				window.myChart.data.labels = usuarioslabel;
				window.myChart.data.datasets.push(newDataset);
				window.myChart.update();
			}
		});
	},
	usuariosPorSemanaChart : function(){
		$.ajax({
			url : url + "/usuarios/totales",
			dataType : "json",
			success : function(result) {

				console.log(result);
				var usuariosData=[], usuarioslabel=[];
				

				$.each(result.totalUsuariosSemana, function(index, item) {
					usuariosData.push(item.total)
					usuarioslabel.push("Semana " + item.indice)
					
				});
				
				
				var ctx = document.getElementById('usuariosChart').getContext('2d');

				//Elmina dataset anterior
				window.myChart.data.labels.splice(0,window.myChart.data.labels.length);
				window.myChart.data.datasets.forEach(function(dataset) {
					dataset.data.pop();
				});
				window.myChart.data.datasets.pop();
				
				window.myChart.update();
				
				//Actualizar con nuevos datos
				var newDataset = {
						label: 'Total' ,
						backgroundColor: chartBackground,
						borderColor: chartBackgroundBorder,
						borderWidth: 1,
						data: usuariosData
				};
				
				window.myChart.data.labels = usuarioslabel;
				window.myChart.data.datasets.push(newDataset);
				window.myChart.update();
			}
		});
	},
	usuariosPorMesChart : function(){
		$.ajax({
			url : url + "/usuarios/totales",
			dataType : "json",
			success : function(result) {

				console.log(result);
				var usuariosData=[], usuarioslabel=[];
				

				$.each(result.totalUsuariosMes, function(index, item) {
					usuariosData.push(item.total)
					usuarioslabel.push(item.topico)
					
				});
				
				
				var ctx = document.getElementById('usuariosChart').getContext('2d');

				//Elmina dataset anterior
				window.myChart.data.labels.splice(0,window.myChart.data.labels.length);
				window.myChart.data.datasets.forEach(function(dataset) {
					dataset.data.pop();
				});
				window.myChart.data.datasets.pop();
				
				window.myChart.update();
				
				//Actualizar con nuevos datos
				var newDataset = {
						label: 'Total' ,
						backgroundColor: chartBackground,
						borderColor: chartBackgroundBorder,
						borderWidth: 1,
						data: usuariosData
				};
				
				window.myChart.data.labels = usuarioslabel;
				window.myChart.data.datasets.push(newDataset);
				window.myChart.update();

			}
		});
	},
	init : function() {
		estadisticas_usuarios.loadCharts();
	}
};

$(document).ready(function() {
	estadisticas_usuarios.init();
});
