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

var estadisticas_general = {
		
		loadCharts : function() {
			//http://shinshin-env.m7izq9trpe.us-east-2.elasticbeanstalk.com
			//http://www.shingshing.com
			//www.shingshing.com
			
			//usuarios
			//dia: total, indice
			//semana: total, indice
			//mes: total, indice o topico
			
			//bonificaciones
			//dia: importe, indice
			//semana: importe, indice
			//mes: importe, indide o  topico
			
			//productos
			//dia: total, indice
			//semana: total, indice
			//mes: total, indice o topico
			
			var str = $("#marca.idCatalogoMarca").val();
			var idMarca = $("#idMarca").val();
			var params = "?idMarca=" + idMarca;
			//console.log( "Proveedor: " + str + " - " + v1 );
			
			$.ajax({
				url : url + "/estadisticas/empresa/charts/dashboard" + params,
				dataType : "json",
				success : function(result) {

					console.log(result);
					var usuariosData=[], usuariosLabel=[];
					var escaneosData=[], escaneosLabel=[];
					var bonificacionesData=[], bonificacionesLabel=[];
					var tiendasDatasets =[];
					
					//Finanzas(Bonificaciones), Productos, Usuarios
					$.each(result.totalBonificacionesDias, function(index, item) {
						bonificacionesData.push(item.importe)
						bonificacionesLabel.push("D " + item.indice)
						
					});
					
					$.each(result.totalEscaneosDias, function(index, item) {
						escaneosData.push(item.total)
						escaneosLabel.push("D " + item.indice)
						
					});
					
					$.each(result.totalUsuariosDias, function(index, item) {
						usuariosData.push(item.total)
						usuariosLabel.push("D " + item.indice)
						
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
								backgroundColor: chartBackground,
								borderColor: chartBackgroundBorder,
								borderWidth: 1,
								data: tiendasData
						};
						
						tiendasDatasets.push(tmpDataset)
						console.log( tiendasDatasets )
					});
					
					var ctx = document.getElementById('finanzasChart').getContext('2d');

					window.finanzasChart = new Chart(ctx, {
						type: 'bar',
						data: {
							labels: bonificacionesLabel,
							datasets: [{
								label: '#',
								data: bonificacionesData,
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
					
					var ctx = document.getElementById('productosChart').getContext('2d');

					window.productosChart = new Chart(ctx, {
						type: 'bar',
						data: {
							labels: escaneosLabel,
							datasets: [{
								label: '#',
								data: escaneosData,
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

					var ctx = document.getElementById('usuariosChart').getContext('2d');

					window.usuariosChart = new Chart(ctx, {
						type: 'bar',
						data: {
							labels: usuariosLabel,
							datasets: [{
								label: '#',
								data: usuariosData,
								backgroundColor: chartBackground,
								borderColor: chartBackgroundBorder,
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
					
//					var ctx = document.getElementById('tiendasChart').getContext('2d');
//
//					window.tiendasChart = new Chart(ctx, {
//						type: 'bar',
//						data: {
//							labels: usuariosLabel,
//							datasets: tiendasDatasets
//						},
//						options: {
//							scales: {
//								yAxes: [{
//									ticks: {
//										beginAtZero: true
//									}
//								}]
//							}
//						}
//					 });
				},
				error: function() {
			        console.log("No se ha podido obtener la información");
			    }
			});
	},
	usuariosPorDiaChart : function( idMarca, tipo, categoria ) {

		var params = "?idMarca=" + idMarca + "&tipo=" + tipo + "&categoria="+categoria;
		
		$.ajax({
			url : url + "/estadisticas/empresa/charts/dashboard" + params,
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
						label: '#' ,
						backgroundColor: chartBackground,
						borderColor: chartBackgroundBorder,
						borderWidth: 1,
						data: usuariosData
				};
				
				window.usuariosChart.data.labels = usuarioslabel;
				window.usuariosChart.data.datasets.push(newDataset);
				window.usuariosChart.update();
			}
		});
	},
	usuariosPorSemanaChart : function( idMarca, tipo, categoria ){
		
		var params = "?idMarca=" + idMarca + "&tipo=" + tipo + "&categoria="+categoria;
		
		$.ajax({
			url : url + "/estadisticas/empresa/charts/dashboard" + params,
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
						label: '#' ,
						backgroundColor: chartBackground,
						borderColor: chartBackgroundBorder,
						borderWidth: 1,
						data: usuariosData
				};
				
				window.usuariosChart.data.labels = usuarioslabel;
				window.usuariosChart.data.datasets.push(newDataset);
				window.usuariosChart.update();
			}
		});
	},
	usuariosPorMesChart : function( idMarca, tipo, categoria ){
		
		var params = "?idMarca=" + idMarca + "&tipo=" + tipo + "&categoria="+categoria;
		
		$.ajax({
			url : url + "/estadisticas/empresa/charts/dashboard" + params,
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
						label: '#' ,
						backgroundColor: chartBackground,
						borderColor: chartBackgroundBorder,
						borderWidth: 1,
						data: usuariosData
				};
				
				window.usuariosChart.data.labels = usuarioslabel;
				window.usuariosChart.data.datasets.push(newDataset);
				window.usuariosChart.update();

			}
		});
	},
	
	//Finanzas
	finanzasPorDiaChart : function( idMarca, tipo, categoria ) {
		
		var params = "?idMarca=" + idMarca + "&tipo=" + tipo + "&categoria="+categoria;

		$.ajax({
			url : url + "/estadisticas/empresa/charts/dashboard" + params,
			dataType : "json",
			success : function(result) {

				console.log(result);
				var usuariosData=[], usuarioslabel=[];
				

				$.each(result.totalBonificacionesDias, function(index, item) {
					usuariosData.push(item.importe)
					usuarioslabel.push("D " + item.indice)
					
				});

				//Elmina dataset anterior
				window.finanzasChart.data.labels.splice(0,window.finanzasChart.data.labels.length);
				window.finanzasChart.data.datasets.forEach(function(dataset) {
					dataset.data.pop();
				});
				window.finanzasChart.data.datasets.pop();
				
				window.finanzasChart.update();
				
				//Actualizar con nuevos datos
				var newDataset = {
						label: '#' ,
						backgroundColor: chartBackgroundBorder,
						borderColor: chartBackground,
						borderWidth: 1,
						data: usuariosData
				};
				
				window.finanzasChart.data.labels = usuarioslabel;
				window.finanzasChart.data.datasets.push(newDataset);
				window.finanzasChart.update();
			}
		});
	},
	finanzasPorSemanaChart : function( idMarca, tipo, categoria ){
		
		var params = "?idMarca=" + idMarca + "&tipo=" + tipo + "&categoria="+categoria;
		
		$.ajax({
			url : url + "/estadisticas/empresa/charts/dashboard" + params,
			dataType : "json",
			success : function(result) {

				console.log(result);
				var usuariosData=[], usuarioslabel=[];
				

				$.each(result.totalBonificacionesSemana, function(index, item) {
					usuariosData.push(item.importe)
					usuarioslabel.push("Semana " + item.indice)
					
				});

				//Elmina dataset anterior
				window.finanzasChart.data.labels.splice(0,window.finanzasChart.data.labels.length);
				window.finanzasChart.data.datasets.forEach(function(dataset) {
					dataset.data.pop();
				});
				window.finanzasChart.data.datasets.pop();
				
				window.finanzasChart.update();
				
				//Actualizar con nuevos datos
				var newDataset = {
						label: '#' ,
						backgroundColor: chartBackground,
						borderColor: chartBackgroundBorder,
						borderWidth: 1,
						data: usuariosData
				};
				
				window.finanzasChart.data.labels = usuarioslabel;
				window.finanzasChart.data.datasets.push(newDataset);
				window.finanzasChart.update();
			}
		});
	},
	finanzasPorMesChart : function( idMarca, tipo, categoria ){
		
		var params = "?idMarca=" + idMarca + "&tipo=" + tipo + "&categoria="+categoria;
		
		$.ajax({
			url : url + "/estadisticas/empresa/charts/dashboard" + params,
			dataType : "json",
			success : function(result) {

				console.log(result);
				var usuariosData=[], usuarioslabel=[];
				

				$.each(result.totalBonificacionesMes, function(index, item) {
					usuariosData.push(item.importe)
					usuarioslabel.push(item.topico)
					
				});

				//Elmina dataset anterior
				window.finanzasChart.data.labels.splice(0,window.finanzasChart.data.labels.length);
				window.finanzasChart.data.datasets.forEach(function(dataset) {
					dataset.data.pop();
				});
				window.finanzasChart.data.datasets.pop();
				
				window.finanzasChart.update();
				
				//Actualizar con nuevos datos
				var newDataset = {
						label: '#' ,
						backgroundColor: chartBackground,
						borderColor: chartBackgroundBorder,
						borderWidth: 1,
						data: usuariosData
				};
				
				window.finanzasChart.data.labels = usuarioslabel;
				window.finanzasChart.data.datasets.push(newDataset);
				window.finanzasChart.update();

			}
		});
	},
	
	//Productos
	productosPorDiaChart : function( idMarca, tipo, categoria ) {

		var params = "?idMarca=" + idMarca + "&tipo=" + tipo + "&categoria="+categoria;
		
		$.ajax({
			url : url + "/estadisticas/empresa/charts/dashboard" + params,
			dataType : "json",
			success : function(result) {

				console.log(result);
				var usuariosData=[], usuarioslabel=[];
				

				$.each(result.totalEscaneosDias, function(index, item) {
					
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
						label: '#' ,
						backgroundColor: chartBackground,
						borderColor: chartBackgroundBorder,
						borderWidth: 1,
						data: usuariosData
				};
				
				window.productosChart.data.labels = usuarioslabel;
				window.productosChart.data.datasets.push(newDataset);
				window.productosChart.update();
			}
		});
	},
	productosPorSemanaChart : function( idMarca, tipo, categoria ){
		
		var params = "?idMarca=" + idMarca + "&tipo=" + tipo + "&categoria="+categoria;
		
		$.ajax({
			url : url + "/estadisticas/empresa/charts/dashboard" + params,
			dataType : "json",
			success : function(result) {

				console.log(result);
				var usuariosData=[], usuarioslabel=[];
				

				$.each(result.totalEscaneosSemana, function(index, item) {
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
						label: '#' ,
						backgroundColor: chartBackground,
						borderColor: chartBackgroundBorder,
						borderWidth: 1,
						data: usuariosData
				};
				
				window.productosChart.data.labels = usuarioslabel;
				window.productosChart.data.datasets.push(newDataset);
				window.productosChart.update();
			}
		});
	},
	productosPorMesChart : function( idMarca, tipo, categoria ){
		
		var params = "?idMarca=" + idMarca + "&tipo=" + tipo + "&categoria="+categoria;
		
		$.ajax({
			url : url + "/estadisticas/empresa/charts/dashboard" + params,
			dataType : "json",
			success : function(result) {

				console.log(result);
				var usuariosData=[], usuarioslabel=[];
				

				$.each(result.totalEscaneosMes, function(index, item) {
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
						label: '#' ,
						backgroundColor: chartBackgroundBorder,
						borderColor: chartBackgroundBorder,
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
