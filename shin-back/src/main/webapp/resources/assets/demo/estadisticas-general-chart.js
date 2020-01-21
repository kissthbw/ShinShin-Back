var estadisticas_general = {
		
		loadCharts : function() {

			$.ajax({
				url : "http://localhost:8080/shin-back/estadisticas/general",
				dataType : "json",
				success : function(result) {

					console.log(result);
					var usuariosData=[], usuariosLabel=[];
					var ticketsData=[], ticketsLabel=[];
					var productosData=[], productosLabel=[];
					var tiendasDatasets =[];

					$.each(result.totalUsuariosMes, function(index, item) {
						usuariosData.push(item.total)
						usuariosLabel.push(item.topico)
						
					});
					
					$.each(result.totalEscaneosMes, function(index, item) {
						ticketsData.push(item.total)
						ticketsLabel.push(item.topico)
						
					});
					
					$.each(result.totalProductosEscaneadosMes, function(index, item) {
						productosData.push(item.total)
						productosLabel.push(item.topico)
						
					});
					
					//totalEscaneaosTiendaMes, contiene un arreglo del objeto Category
					//que contiene el titulo de la seria y la lista de items
					
					$.each(result.totalEscaneaosTiendaMes, function(index, tienda) {
						var tiendasData=[];
						
						$.each(tienda.items, function(i, t){
							tiendasData.push( t.total )
						});
						
						var tmpDataset = {
								label: tienda.titulo,
								backgroundColor: [
									'rgba(255, 99, 132, 0.2)',
									'rgba(54, 162, 235, 0.2)',
									'rgba(255, 206, 86, 0.2)',
									'rgba(75, 192, 192, 0.2)',
									'rgba(153, 102, 255, 0.2)',
									'rgba(255, 99, 132, 0.2)',
									'rgba(54, 162, 235, 0.2)',
									'rgba(255, 206, 86, 0.2)',
									'rgba(75, 192, 192, 0.2)',
									'rgba(153, 102, 255, 0.2)',
									'rgba(75, 192, 192, 0.2)',
									'rgba(153, 102, 255, 0.2)'
								],
								borderColor: [
									'rgba(255, 99, 132, 1)',
									'rgba(54, 162, 235, 1)',
									'rgba(255, 206, 86, 1)',
									'rgba(75, 192, 192, 1)',
									'rgba(153, 102, 255, 1)',
									'rgba(255, 99, 132, 1)',
									'rgba(54, 162, 235, 1)',
									'rgba(255, 206, 86, 1)',
									'rgba(75, 192, 192, 1)',
									'rgba(153, 102, 255, 1)',
									'rgba(75, 192, 192, 1)',
									'rgba(153, 102, 255, 1)'
								],
								borderWidth: 1,
								data: tiendasData
						};
						
						tiendasDatasets.push(tmpDataset)
						console.log( tiendasDatasets )
					});

					
					var ctx = document.getElementById('usuariosChart').getContext('2d');

					window.usuariosChart = new Chart(ctx, {
						type: 'bar',
						data: {
							labels: usuariosLabel,
							datasets: [{
								label: 'Total',
								data: usuariosData,
								backgroundColor: [
									'rgba(255, 99, 132, 0.2)',
									'rgba(54, 162, 235, 0.2)',
									'rgba(255, 206, 86, 0.2)',
									'rgba(75, 192, 192, 0.2)',
									'rgba(153, 102, 255, 0.2)',
									'rgba(255, 159, 64, 0.2)'
								],
								borderColor: [
									'rgba(255, 99, 132, 1)',
									'rgba(54, 162, 235, 1)',
									'rgba(255, 206, 86, 1)',
									'rgba(75, 192, 192, 1)',
									'rgba(153, 102, 255, 1)',
									'rgba(255, 159, 64, 1)'
								],
								borderWidth: 1
							}
							]
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
					
					var ctx = document.getElementById('ticketsChart').getContext('2d');

					window.ticketsChart = new Chart(ctx, {
						type: 'bar',
						data: {
							labels: ticketsLabel,
							datasets: [{
								label: 'Total',
								data: ticketsData,
								backgroundColor: [
									'rgba(255, 99, 132, 0.2)',
									'rgba(54, 162, 235, 0.2)',
									'rgba(255, 206, 86, 0.2)',
									
								],
								borderColor: [
									'rgba(255, 99, 132, 1)',
									'rgba(54, 162, 235, 1)',
									'rgba(255, 206, 86, 1)',
								],
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
					
					var ctx = document.getElementById('productosChart').getContext('2d');

					window.productosChart = new Chart(ctx, {
						type: 'bar',
						data: {
							labels: productosLabel,
							datasets: [{
								label: 'Total',
								data: productosData,
								backgroundColor: [
									'rgba(255, 99, 132, 0.2)',
									'rgba(54, 162, 235, 0.2)',
									'rgba(255, 206, 86, 0.2)',
									'rgba(75, 192, 192, 0.2)',
									'rgba(153, 102, 255, 0.2)',
									'rgba(255, 159, 64, 0.2)'
								],
								borderColor: [
									'rgba(255, 99, 132, 1)',
									'rgba(54, 162, 235, 1)',
									'rgba(255, 206, 86, 1)',
									'rgba(75, 192, 192, 1)',
									'rgba(153, 102, 255, 1)',
									'rgba(255, 159, 64, 1)'
								],
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

					
					
					var ctx = document.getElementById('tiendasChart').getContext('2d');

					window.tiendasChart = new Chart(ctx, {
						type: 'bar',
						data: {
							labels: usuariosLabel,
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
					
					
//					window.tiendasChart.data.datasets.push( tiendasDatasets[0] );
					
					/*
					var ctx = document.getElementById('departamentosChart').getContext('2d');

					window.departamentosChart = new Chart(ctx, {
						type: 'bar',
						data: {
							labels: edadesLabel,
							datasets: [{
								label: 'Total',
								data: edadesData,
								backgroundColor: [
									'rgba(255, 99, 132, 0.2)',
									'rgba(54, 162, 235, 0.2)',
									'rgba(255, 206, 86, 0.2)',
									'rgba(75, 192, 192, 0.2)',
									'rgba(153, 102, 255, 0.2)',
									'rgba(255, 159, 64, 0.2)'
								],
								borderColor: [
									'rgba(255, 99, 132, 1)',
									'rgba(54, 162, 235, 1)',
									'rgba(255, 206, 86, 1)',
									'rgba(75, 192, 192, 1)',
									'rgba(153, 102, 255, 1)',
									'rgba(255, 159, 64, 1)'
								],
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
					*/
					//Fin
					
				}
			});
	},
	usuariosPorDiaChart : function() {

		$.ajax({
			url : "http://localhost:8080/shin-back/estadisticas/general",
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
						backgroundColor: [
							'rgba(255, 99, 132, 0.2)',
							'rgba(54, 162, 235, 0.2)',
							'rgba(255, 206, 86, 0.2)',
							'rgba(75, 192, 192, 0.2)',
							'rgba(153, 102, 255, 0.2)'
						],
						borderColor: [
							'rgba(255, 99, 132, 1)',
							'rgba(54, 162, 235, 1)',
							'rgba(255, 206, 86, 1)',
							'rgba(75, 192, 192, 1)',
							'rgba(153, 102, 255, 1)'
						],
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
			url : "http://localhost:8080/shin-back/estadisticas/general",
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
						backgroundColor: [
							'rgba(255, 99, 132, 0.2)',
							'rgba(54, 162, 235, 0.2)',
							'rgba(255, 206, 86, 0.2)',
							'rgba(75, 192, 192, 0.2)',
							'rgba(153, 102, 255, 0.2)'
						],
						borderColor: [
							'rgba(255, 99, 132, 1)',
							'rgba(54, 162, 235, 1)',
							'rgba(255, 206, 86, 1)',
							'rgba(75, 192, 192, 1)',
							'rgba(153, 102, 255, 1)'
						],
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
			url : "http://localhost:8080/shin-back/estadisticas/general",
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
						backgroundColor: [
							'rgba(255, 99, 132, 0.2)',
							'rgba(54, 162, 235, 0.2)',
							'rgba(255, 206, 86, 0.2)',
							'rgba(75, 192, 192, 0.2)',
							'rgba(153, 102, 255, 0.2)'
						],
						borderColor: [
							'rgba(255, 99, 132, 1)',
							'rgba(54, 162, 235, 1)',
							'rgba(255, 206, 86, 1)',
							'rgba(75, 192, 192, 1)',
							'rgba(153, 102, 255, 1)'
						],
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
		estadisticas_general.loadCharts();
	}
};

$(document).ready(function() {
	estadisticas_general.init();
});
