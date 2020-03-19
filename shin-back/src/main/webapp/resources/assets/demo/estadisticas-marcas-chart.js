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
	
var estadisticas_marcas = {
		
		loadCharts : function() {

			$.ajax({
				url : url + "/estadisticas/marcas",
				dataType : "json",
				success : function(result) {
					
					var tiendaslabel = ["Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"];
					var tiendasDatasets =[];
					var deptoslabel = ["Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"];
					var deptosDatasets =[];

					//totalEscaneaosTiendaMes, contiene un arreglo del objeto Category
					//que contiene el titulo de la seria y la lista de items
					//totalEscaneaosDepartamentoMes
					
					$.each(result.totalEscaneaosTiendaMes, function(index, tienda) {
						var tiendasData=[];
						
						$.each(tienda.items, function(i, t){
							//tiendaslabel.push( t.topico )
							tiendasData.push( t.total )
						});
						
						var tmpDataset = {
								label: tienda.titulo,
								backgroundColor: chartBackground,
								borderColor: chartBackgroundBorder,
								borderWidth: 1,
								data: tiendasData
						};
						
						tiendasDatasets.push(tmpDataset)
					});
					
					$.each(result.totalEscaneaosDepartamentoMes, function(index, depto) {
						var deptosData=[];
						
						$.each(depto.items, function(i, d){
							//deptoslabel.push( d.topico )
							deptosData.push( d.total )
						});
						
						var tmpDataset = {
								label: depto.titulo,
								backgroundColor: chartBackground,
								borderColor: chartBackgroundBorder,
								borderWidth: 1,
								data: deptosData
						};
						
						deptosDatasets.push(tmpDataset)
					});

					
					var ctx = document.getElementById('tiendasChart').getContext('2d');

					window.tiendasChart = new Chart(ctx, {
						type: 'bar',
						data: {
							labels: tiendaslabel,
							datasets: tiendasDatasets
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
					
					var ctx = document.getElementById('deptosChart').getContext('2d');

					window.deptosChart = new Chart(ctx, {
						type: 'bar',
						data: {
							labels: deptoslabel,
							datasets: deptosDatasets
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

					//Fin
					
				}
			});
	},
	usuariosPorDiaChart : function() {

		$.ajax({
			url :  url + "/estadisticas/marcas",
			dataType : "json",
			success : function(result) {

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
			url : url + "/estadisticas/marcas",
			dataType : "json",
			success : function(result) {

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
			url : url + "/estadisticas/marcas",
			dataType : "json",
			success : function(result) {

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
		estadisticas_marcas.loadCharts();
	}
};

$(document).ready(function() {
	estadisticas_marcas.init();
});
