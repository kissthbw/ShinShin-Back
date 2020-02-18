var estadisticas_general = {
		
		loadCharts : function() {
			//http://shinshin-env.m7izq9trpe.us-east-2.elasticbeanstalk.com
			//http://www.shingshing.com
			//www.shingshing.com
			$.ajax({
				url : "http://www.shingshing.com/estadisticas/general",
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
				},
				error: function() {
			        console.log("No se ha podido obtener la información");
			    }
			});
	},
	usuariosPorDiaChart : function() {

		$.ajax({
			url : "http://www.shingshing.com/estadisticas/general",
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
				window.usuariosChart.data.labels.splice(0,window.usuariosChart.data.labels.length);
				window.usuariosChart.data.datasets.forEach(function(dataset) {
					dataset.data.pop();
				});
				window.usuariosChart.data.datasets.pop();
				
				window.usuariosChart.update();
				
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
				
				window.usuariosChart.data.labels = usuarioslabel;
				window.usuariosChart.data.datasets.push(newDataset);
				window.usuariosChart.update();
			}
		});
	},
	usuariosPorSemanaChart : function(){
		$.ajax({
			url : "http://www.shingshing.com/estadisticas/general",
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
				window.usuariosChart.data.labels.splice(0,window.usuariosChart.data.labels.length);
				window.usuariosChart.data.datasets.forEach(function(dataset) {
					dataset.data.pop();
				});
				window.usuariosChart.data.datasets.pop();
				
				window.usuariosChart.update();
				
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
				
				window.usuariosChart.data.labels = usuarioslabel;
				window.usuariosChart.data.datasets.push(newDataset);
				window.usuariosChart.update();
			}
		});
	},
	usuariosPorMesChart : function(){
		$.ajax({
			url : "http://www.shingshing.com/estadisticas/general",
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
				window.usuariosChart.data.labels.splice(0,window.usuariosChart.data.labels.length);
				window.usuariosChart.data.datasets.forEach(function(dataset) {
					dataset.data.pop();
				});
				window.usuariosChart.data.datasets.pop();
				
				window.usuariosChart.update();
				
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
				
				window.usuariosChart.data.labels = usuarioslabel;
				window.usuariosChart.data.datasets.push(newDataset);
				window.usuariosChart.update();

			}
		});
	},
	
	//Tickets
	ticketsPorDiaChart : function() {

		$.ajax({
			url : "http://www.shingshing.com/estadisticas/general",
			dataType : "json",
			success : function(result) {

				console.log(result);
				var usuariosData=[], usuarioslabel=[];
				

				$.each(result.totalEscaneosDias, function(index, item) {
					usuariosData.push(item.total)
					usuarioslabel.push("D " + item.indice)
					
				});

				//Elmina dataset anterior
				window.ticketsChart.data.labels.splice(0,window.ticketsChart.data.labels.length);
				window.ticketsChart.data.datasets.forEach(function(dataset) {
					dataset.data.pop();
				});
				window.ticketsChart.data.datasets.pop();
				
				window.ticketsChart.update();
				
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
				
				window.ticketsChart.data.labels = usuarioslabel;
				window.ticketsChart.data.datasets.push(newDataset);
				window.ticketsChart.update();
			}
		});
	},
	ticketsPorSemanaChart : function(){
		$.ajax({
			url : "http://www.shingshing.com/estadisticas/general",
			dataType : "json",
			success : function(result) {

				console.log(result);
				var usuariosData=[], usuarioslabel=[];
				

				$.each(result.totalEscaneosSemana, function(index, item) {
					usuariosData.push(item.total)
					usuarioslabel.push("Semana " + item.indice)
					
				});

				//Elmina dataset anterior
				window.ticketsChart.data.labels.splice(0,window.ticketsChart.data.labels.length);
				window.ticketsChart.data.datasets.forEach(function(dataset) {
					dataset.data.pop();
				});
				window.ticketsChart.data.datasets.pop();
				
				window.ticketsChart.update();
				
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
				
				window.ticketsChart.data.labels = usuarioslabel;
				window.ticketsChart.data.datasets.push(newDataset);
				window.ticketsChart.update();
			}
		});
	},
	ticketsPorMesChart : function(){
		$.ajax({
			url : "http://www.shingshing.com/estadisticas/general",
			dataType : "json",
			success : function(result) {

				console.log(result);
				var usuariosData=[], usuarioslabel=[];
				

				$.each(result.totalEscaneosMes, function(index, item) {
					usuariosData.push(item.total)
					usuarioslabel.push(item.topico)
					
				});

				//Elmina dataset anterior
				window.ticketsChart.data.labels.splice(0,window.ticketsChart.data.labels.length);
				window.ticketsChart.data.datasets.forEach(function(dataset) {
					dataset.data.pop();
				});
				window.ticketsChart.data.datasets.pop();
				
				window.ticketsChart.update();
				
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
				
				window.ticketsChart.data.labels = usuarioslabel;
				window.ticketsChart.data.datasets.push(newDataset);
				window.ticketsChart.update();

			}
		});
	},
	
	//Productos
	productosPorDiaChart : function() {

		$.ajax({
			url : "http://www.shingshing.com/estadisticas/general",
			dataType : "json",
			success : function(result) {

				console.log(result);
				var usuariosData=[], usuarioslabel=[];
				

				$.each(result.totalProductosEscaneadosDias, function(index, item) {
					usuariosData.push(item.total)
					usuarioslabel.push("D " + item.indice)
					
				});

				//Elmina dataset anterior
				window.productosChart.data.labels.splice(0,window.productosChart.data.labels.length);
				window.productosChart.data.datasets.forEach(function(dataset) {
					dataset.data.pop();
				});
				window.productosChart.data.datasets.pop();
				
				window.productosChart.update();
				
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
				
				window.productosChart.data.labels = usuarioslabel;
				window.productosChart.data.datasets.push(newDataset);
				window.productosChart.update();
			}
		});
	},
	productosPorSemanaChart : function(){
		$.ajax({
			url : "http://www.shingshing.com/estadisticas/general",
			dataType : "json",
			success : function(result) {

				console.log(result);
				var usuariosData=[], usuarioslabel=[];
				

				$.each(result.totalProductosEscaneadosSemana, function(index, item) {
					usuariosData.push(item.total)
					usuarioslabel.push("Semana " + item.indice)
					
				});

				//Elmina dataset anterior
				window.productosChart.data.labels.splice(0,window.productosChart.data.labels.length);
				window.productosChart.data.datasets.forEach(function(dataset) {
					dataset.data.pop();
				});
				window.productosChart.data.datasets.pop();
				
				window.productosChart.update();
				
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
				
				window.productosChart.data.labels = usuarioslabel;
				window.productosChart.data.datasets.push(newDataset);
				window.productosChart.update();
			}
		});
	},
	productosPorMesChart : function(){
		$.ajax({
			url : "http://www.shingshing.com/estadisticas/general",
			dataType : "json",
			success : function(result) {

				console.log(result);
				var usuariosData=[], usuarioslabel=[];
				

				$.each(result.totalProductosEscaneadosMes, function(index, item) {
					usuariosData.push(item.total)
					usuarioslabel.push(item.topico)
					
				});

				//Elmina dataset anterior
				window.productosChart.data.labels.splice(0,window.productosChart.data.labels.length);
				window.productosChart.data.datasets.forEach(function(dataset) {
					dataset.data.pop();
				});
				window.productosChart.data.datasets.pop();
				
				window.productosChart.update();
				
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
				
				window.productosChart.data.labels = usuarioslabel;
				window.productosChart.data.datasets.push(newDataset);
				window.productosChart.update();

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
