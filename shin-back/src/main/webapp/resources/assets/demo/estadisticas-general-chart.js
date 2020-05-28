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
			// http://shinshin-env.m7izq9trpe.us-east-2.elasticbeanstalk.com
			// http://www.shingshing.com
			// www.shingshing.com
			$.ajax({
				url : url + "/estadisticas/general",
				dataType : "json",
				success : function(result) {

					console.log(result);
					var usuariosData=[], usuariosLabel=[];
					var ticketsData=[], ticketsLabel=[];
					var productosData=[], productosLabel=[];
					var tiendasDatasets =[];
					var deptosDatasets=[];

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
					
						
					// totalEscaneaosTiendaMes, contiene un arreglo del objeto
					// Category
					// que contiene el titulo de la seria y la lista de items
					
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
					// DEPARTAMENTOS POR MES
					$.each(result.totalEscaneaosDepartamentoMes, function(index, depto) {
						var deptosData=[];
						
						$.each(depto.items, function(i, t){
							deptosData.push( t.total )
						});
						
						var tmpDataset = {
								label: depto.titulo,
								backgroundColor: chartBackground,
								borderColor: chartBackgroundBorder,
								borderWidth: 1,
								data: deptosData
						};
						
						deptosDatasets.push(tmpDataset)
						console.log( deptosDatasets )
					});

					var ctx = document.getElementById('departamentosChart').getContext('2d');

					window.deptosChart = new Chart(ctx, {
						type: 'bar',
						data: {
							labels: usuariosLabel,
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
					
					var ctx = document.getElementById('ticketsChart').getContext('2d');

					window.ticketsChart = new Chart(ctx, {
						type: 'bar',
						data: {
							labels: ticketsLabel,
							datasets: [{
								label: '#',
								data: ticketsData,
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
							labels: productosLabel,
							datasets: [{
								label: '#',
								data: productosData,
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
			url : url + "/estadisticas/general",
			dataType : "json",
			success : function(result) {

				console.log(result);
				var usuariosData=[], usuarioslabel=[];
				

				$.each(result.totalUsuariosDias, function(index, item) {
					usuariosData.push(item.total)
					usuarioslabel.push("D " + item.indice)
					
				});
				
				
				var ctx = document.getElementById('usuariosChart').getContext('2d');

				// Elmina dataset anterior
				window.usuariosChart.data.labels.splice(0,window.usuariosChart.data.labels.length);
				window.usuariosChart.data.datasets.forEach(function(dataset) {
					dataset.data.pop();
				});
				window.usuariosChart.data.datasets.pop();
				
				window.usuariosChart.update();
				
				// Actualizar con nuevos datos
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
	usuariosPorSemanaChart : function(){
		$.ajax({
			url : url + "/estadisticas/general",
			dataType : "json",
			success : function(result) {

				console.log(result);
				var usuariosData=[], usuarioslabel=[];
				

				$.each(result.totalUsuariosSemana, function(index, item) {
					usuariosData.push(item.total)
					usuarioslabel.push("Semana " + item.indice)
					
				});
				
				
				var ctx = document.getElementById('usuariosChart').getContext('2d');

				// Elmina dataset anterior
				window.usuariosChart.data.labels.splice(0,window.usuariosChart.data.labels.length);
				window.usuariosChart.data.datasets.forEach(function(dataset) {
					dataset.data.pop();
				});
				window.usuariosChart.data.datasets.pop();
				
				window.usuariosChart.update();
				
				// Actualizar con nuevos datos
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
	usuariosPorMesChart : function(){
		$.ajax({
			url : url + "/estadisticas/general",
			dataType : "json",
			success : function(result) {

				console.log(result);
				var usuariosData=[], usuarioslabel=[];
				

				$.each(result.totalUsuariosMes, function(index, item) {
					usuariosData.push(item.total)
					usuarioslabel.push(item.topico)
					
				});
				
				
				var ctx = document.getElementById('usuariosChart').getContext('2d');

				// Elmina dataset anterior
				window.usuariosChart.data.labels.splice(0,window.usuariosChart.data.labels.length);
				window.usuariosChart.data.datasets.forEach(function(dataset) {
					dataset.data.pop();
				});
				window.usuariosChart.data.datasets.pop();
				
				window.usuariosChart.update();
				
				// Actualizar con nuevos datos
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
	
	// Tickets
	ticketsPorDiaChart : function() {

		$.ajax({
			url : url + "/estadisticas/general",
			dataType : "json",
			success : function(result) {

				console.log(result);
				var usuariosData=[], usuarioslabel=[];
				

				$.each(result.totalEscaneosDias, function(index, item) {
					usuariosData.push(item.total)
					usuarioslabel.push("D " + item.indice)
					
				});

				// Elmina dataset anterior
				window.ticketsChart.data.labels.splice(0,window.ticketsChart.data.labels.length);
				window.ticketsChart.data.datasets.forEach(function(dataset) {
					dataset.data.pop();
				});
				window.ticketsChart.data.datasets.pop();
				
				window.ticketsChart.update();
				
				// Actualizar con nuevos datos
				var newDataset = {
						label: '#' ,
						backgroundColor: chartBackgroundBorder,
						borderColor: chartBackground,
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
			url : url + "/estadisticas/general",
			dataType : "json",
			success : function(result) {

				console.log(result);
				var usuariosData=[], usuarioslabel=[];
				

				$.each(result.totalEscaneosSemana, function(index, item) {
					usuariosData.push(item.total)
					usuarioslabel.push("Semana " + item.indice)
					
				});

				// Elmina dataset anterior
				window.ticketsChart.data.labels.splice(0,window.ticketsChart.data.labels.length);
				window.ticketsChart.data.datasets.forEach(function(dataset) {
					dataset.data.pop();
				});
				window.ticketsChart.data.datasets.pop();
				
				window.ticketsChart.update();
				
				// Actualizar con nuevos datos
				var newDataset = {
						label: '#' ,
						backgroundColor: chartBackground,
						borderColor: chartBackgroundBorder,
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
			url : url + "/estadisticas/general",
			dataType : "json",
			success : function(result) {

				console.log(result);
				var usuariosData=[], usuarioslabel=[];
				

				$.each(result.totalEscaneosMes, function(index, item) {
					usuariosData.push(item.total)
					usuarioslabel.push(item.topico)
					
				});

				// Elmina dataset anterior
				window.ticketsChart.data.labels.splice(0,window.ticketsChart.data.labels.length);
				window.ticketsChart.data.datasets.forEach(function(dataset) {
					dataset.data.pop();
				});
				window.ticketsChart.data.datasets.pop();
				
				window.ticketsChart.update();
				
				// Actualizar con nuevos datos
				var newDataset = {
						label: '#' ,
						backgroundColor: chartBackground,
						borderColor: chartBackgroundBorder,
						borderWidth: 1,
						data: usuariosData
				};
				
				window.ticketsChart.data.labels = usuarioslabel;
				window.ticketsChart.data.datasets.push(newDataset);
				window.ticketsChart.update();

			}
		});
	},
	
	// Productos
	productosPorDiaChart : function() {

		$.ajax({
			url : url + "/estadisticas/general",
			dataType : "json",
			success : function(result) {

				console.log(result);
				var usuariosData=[], usuarioslabel=[];
				

				$.each(result.totalProductosEscaneadosDias, function(index, item) {
					usuariosData.push(item.total)
					usuarioslabel.push("D " + item.indice)
					
				});

				// Elmina dataset anterior
				window.productosChart.data.labels.splice(0,window.productosChart.data.labels.length);
				window.productosChart.data.datasets.forEach(function(dataset) {
					dataset.data.pop();
				});
				window.productosChart.data.datasets.pop();
				
				window.productosChart.update();
				
				// Actualizar con nuevos datos
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
	productosPorSemanaChart : function(){
		$.ajax({
			url : url + "/estadisticas/general",
			dataType : "json",
			success : function(result) {

				console.log(result);
				var usuariosData=[], usuarioslabel=[];
				

				$.each(result.totalProductosEscaneadosSemana, function(index, item) {
					usuariosData.push(item.total)
					usuarioslabel.push("Semana " + item.indice)
					
				});

				// Elmina dataset anterior
				window.productosChart.data.labels.splice(0,window.productosChart.data.labels.length);
				window.productosChart.data.datasets.forEach(function(dataset) {
					dataset.data.pop();
				});
				window.productosChart.data.datasets.pop();
				
				window.productosChart.update();
				
				// Actualizar con nuevos datos
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
	productosPorMesChart : function(){
		$.ajax({
			url : url + "/estadisticas/general",
			dataType : "json",
			success : function(result) {

				console.log(result);
				var usuariosData=[], usuarioslabel=[];
				

				$.each(result.totalProductosEscaneadosMes, function(index, item) {
					usuariosData.push(item.total)
					usuarioslabel.push(item.topico)
					
				});

				// Elmina dataset anterior
				window.productosChart.data.labels.splice(0,window.productosChart.data.labels.length);
				window.productosChart.data.datasets.forEach(function(dataset) {
					dataset.data.pop();
				});
				window.productosChart.data.datasets.pop();
				
				window.productosChart.update();
				
				// Actualizar con nuevos datos
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
	deptosPorDiaChart : function() {

		$.ajax({
			url : url + "/estadisticas/general",
			dataType : "json",
			success : function(result) {

				console.log(result);
				var deptosData=[], deptosLabel=[], deptosDatasets=[],deptosLabel1=[];	
				var topicos=[];

				// Elmina dataset anterior
				window.deptosChart.data.labels.splice(0,window.deptosChart.data.labels.length);
				window.deptosChart.data.datasets.forEach(function(dataset) {
					dataset.data.pop();
				});
				window.deptosChart.data.datasets.pop();
				
				window.deptosChart.update();
				//OBTENEMOS LOS DIAS PRESENTES 
				$.each(result.totalEscaneaosDepartamentoDias, function(index, depto) {
					if(deptosLabel1.indexOf(depto.indice)==-1 || deptosLabel1.length==0){
						deptosLabel1.push(depto.indice);
					}
				});
				console.log(deptosLabel1);
				$.each(result.totalEscaneaosDepartamentoDias, function(index, depto) {
					deptosLabel=[];
					for(var x=0;x<deptosLabel1.length;x++){
						deptosLabel.push(deptosLabel1[x]);
					}
					//console.log(deptosLabel1);
					var deptosData=[];
					var deptosD=[];
					var dia=[];
					if(topicos.length==0 || topicos.indexOf(depto.topico)==-1){
						topicos.push(depto.topico);
						
						//entra un departamento una sola vez y barre todos los dias
						
							$.each(result.totalEscaneaosDepartamentoDias, function(index, d) {
								
								if(depto.topico==d.topico){
									//console.log(d.total);
									deptosData.push(d.total);
									dia.push(d.indice);
									//console.log(d.indice);
								}
							});
							
							
							
						do
						{
							var p=false;
							console.log(dia);
							console.log(deptosLabel);
								if(dia[0]==deptosLabel[0]){
									
									deptosD.push(deptosData[0]);
									dia.splice(0,1);
									deptosData.splice(0,1);
									deptosLabel.splice(0,1);
									
								}else if(dia[0]>deptosLabel[0]){
									deptosD.push(0);
									deptosLabel.splice(0,1)
								}
								
								if(dia.length==0 && deptosLabel.length==0){
									p=true;
								}
								if(dia.length==0 && deptosLabel.length>0){
									deptosD.push(0);
									deptosLabel.splice(0,1)
									if(dia.length==0 && deptosLabel.length==0){
										p=true;
									}
								}
								console.log(p);
							}while(!p);
						
						
						var tmpDataset = {
								label: depto.topico,
								backgroundColor: chartBackground,
								borderColor: chartBackgroundBorder,
								borderWidth: 1,
								data: deptosD
						};
						
						deptosDatasets.push(tmpDataset)
						console.log( tmpDataset )
					}
					
				});
				var ctx = document.getElementById('departamentosChart').getContext('2d');
				if (window.deptosChart) {
				    window.deptosChart.clear();
				    window.deptosChart.destroy();
				}
				window.deptosChart = new Chart(ctx, {
					type: 'bar',
					data: {
						labels: deptosLabel1,
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
			}
		});
	},
	deptosPorSemanaChart : function(){
		$.ajax({
			url : url + "/estadisticas/general",
			dataType : "json",
			success : function(result) {
				console.log(result);
				var deptosData=[], deptosLabel=[], deptosDatasets=[],deptosLabel1=[];	
				var topicos=[];

				// Elmina dataset anterior
				window.deptosChart.data.labels.splice(0,window.deptosChart.data.labels.length);
				window.deptosChart.data.datasets.forEach(function(dataset) {
					dataset.data.pop();
				});
				window.deptosChart.data.datasets.pop();
				
				window.deptosChart.update();
				//OBTENEMOS LOS DIAS PRESENTES 
				$.each(result.totalEscaneaosDepartamentoSemana, function(index, depto) {
					if(deptosLabel1.indexOf(depto.indice)==-1 || deptosLabel1.length==0){
						deptosLabel1.push(depto.indice);
					}
				});
				console.log(deptosLabel1);
				$.each(result.totalEscaneaosDepartamentoSemana, function(index, depto) {
					deptosLabel=[];
					for(var x=0;x<deptosLabel1.length;x++){
						deptosLabel.push(deptosLabel1[x]);
					}
					//console.log(deptosLabel1);
					var deptosData=[];
					var deptosD=[];
					var dia=[];
					if(topicos.length==0 || topicos.indexOf(depto.topico)==-1){
						topicos.push(depto.topico);
						
						//entra un departamento una sola vez y barre todos los dias
						
							$.each(result.totalEscaneaosDepartamentoSemana, function(index, d) {
								
								if(depto.topico==d.topico){
									//console.log(d.total);
									deptosData.push(d.total);
									dia.push(d.indice);
									//console.log(d.indice);
								}
							});
							
							
							
						do
						{
							var p=false;
							console.log(dia);
							console.log(deptosLabel);
								if(dia[0]==deptosLabel[0]){
									
									deptosD.push(deptosData[0]);
									dia.splice(0,1);
									deptosData.splice(0,1);
									deptosLabel.splice(0,1);
									
								}else if(dia[0]>deptosLabel[0]){
									deptosD.push(0);
									deptosLabel.splice(0,1)
								}
								
								if(dia.length==0 && deptosLabel.length==0){
									p=true;
								}
								if(dia.length==0 && deptosLabel.length>0){
									deptosD.push(0);
									deptosLabel.splice(0,1)
									if(dia.length==0 && deptosLabel.length==0){
										p=true;
									}
								}
								console.log(p);
							}while(!p);
						
						
						var tmpDataset = {
								label: depto.topico,
								backgroundColor: chartBackground,
								borderColor: chartBackgroundBorder,
								borderWidth: 1,
								data: deptosD
						};
						
						deptosDatasets.push(tmpDataset)
						console.log( tmpDataset )
					}
					
				});
				var ctx = document.getElementById('departamentosChart').getContext('2d');

				if (window.deptosChart) {
				    window.deptosChart.clear();
				    window.deptosChart.destroy();
				}
				window.deptosChart = new Chart(ctx, {
					type: 'bar',
					data: {
						labels: deptosLabel1,
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
				
			}
		});
	},
	deptosPorMesChart : function(){
		var deptosDatasets=[];
		var meses=["Ene","Feb","Mar","Abr","May","Jun","Jul","Ago","Sep","Oct","Nov","Dic"];
		$.ajax({
			url : url + "/estadisticas/general",
			dataType : "json",
			success : function(result) {
				$.each(result.totalEscaneaosDepartamentoMes, function(index, depto) {
					var deptosData=[];
					
					$.each(depto.items, function(i, t){
						deptosData.push( t.total )
					});
					
					var tmpDataset = {
							label: depto.titulo,
							backgroundColor: chartBackground,
							borderColor: chartBackgroundBorder,
							borderWidth: 1,
							data: deptosData
					};
					
					deptosDatasets.push(tmpDataset)
					console.log( deptosDatasets )
				});

				var ctx = document.getElementById('departamentosChart').getContext('2d');
				if (window.deptosChart) {
				    window.deptosChart.clear();
				    window.deptosChart.destroy();
				}
				window.deptosChart = new Chart(ctx, {
					type: 'bar',
					data: {
						labels: meses,
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
				
			}
		});
	},

	tiendasPorDiaChart : function() {

		$.ajax({
			url : url + "/estadisticas/general",
			dataType : "json",
			success : function(result) {

				console.log(result);
				var tiendasData=[], tiendasLabel=[], tiendasDatasets=[],tiendasLabel1=[];	
				var topicos=[];

				// Elmina dataset anterior
				window.tiendasChart.data.labels.splice(0,window.tiendasChart.data.labels.length);
				window.tiendasChart.data.datasets.forEach(function(dataset) {
					dataset.data.pop();
				});
				window.tiendasChart.data.datasets.pop();
				
				window.tiendasChart.update();
				//OBTENEMOS LOS DIAS PRESENTES 
				$.each(result.totalEscaneaosTiendaDias, function(index, tienda) {
					if(tiendasLabel1.indexOf(tienda.indice)==-1 || tiendasLabel1.length==0){
						tiendasLabel1.push(tienda.indice);
					}
				});
				console.log(tiendasLabel1);
				$.each(result.totalEscaneaosTiendaDias, function(index, tienda) {
					tiendasLabel=[];
					for(var x=0;x<tiendasLabel1.length;x++){
						tiendasLabel.push(tiendasLabel1[x]);
					}
					//console.log(tiendasLabel1);
					var tiendasData=[];
					var tiendasD=[];
					var dia=[];
					if(topicos.length==0 || topicos.indexOf(tienda.topico)==-1){
						topicos.push(tienda.topico);
						
						//entra un departamento una sola vez y barre todos los dias
						
							$.each(result.totalEscaneaosTiendaDias, function(index, d) {
								
								if(tienda.topico==d.topico){
									//console.log(d.total);
									tiendasData.push(d.total);
									dia.push(d.indice);
									//console.log(d.indice);
								}
							});
							
							
							
						do
						{
							var p=false;
							console.log(dia);
							console.log(tiendasLabel);
								if(dia[0]==tiendasLabel[0]){
									
									tiendasD.push(tiendasData[0]);
									dia.splice(0,1);
									tiendasData.splice(0,1);
									tiendasLabel.splice(0,1);
									
								}else if(dia[0]>tiendasLabel[0]){
									tiendasD.push(0);
									tiendasLabel.splice(0,1)
								}
								
								if(dia.length==0 && tiendasLabel.length==0){
									p=true;
								}
								if(dia.length==0 && tiendasLabel.length>0){
									tiendasD.push(0);
									tiendasLabel.splice(0,1)
									if(dia.length==0 && tiendasLabel.length==0){
										p=true;
									}
								}
								console.log(p);
							}while(!p);
						
						
						var tmpDataset = {
								label: tienda.topico,
								backgroundColor: chartBackground,
								borderColor: chartBackgroundBorder,
								borderWidth: 1,
								data: tiendasD
						};
						
						tiendasDatasets.push(tmpDataset)
						console.log( tmpDataset )
					}
					
				});
				var ctx = document.getElementById('tiendasChart').getContext('2d');
				if (window.tiendasChart) {
				    window.tiendasChart.clear();
				    window.tiendasChart.destroy();
				}
				window.tiendasChart = new Chart(ctx, {
					type: 'bar',
					data: {
						labels: tiendasLabel1,
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
			}
		});
	},
	tiendasPorSemanaChart : function(){
		$.ajax({
			url : url + "/estadisticas/general",
			dataType : "json",
			success : function(result) {
				console.log(result);
				var tiendasData=[], tiendasLabel=[], tiendasDatasets=[],tiendasLabel1=[];	
				var topicos=[];

				// Elmina dataset anterior
				window.tiendasChart.data.labels.splice(0,window.tiendasChart.data.labels.length);
				window.tiendasChart.data.datasets.forEach(function(dataset) {
					dataset.data.pop();
				});
				window.tiendasChart.data.datasets.pop();
				
				window.tiendasChart.update();
				//OBTENEMOS LOS DIAS PRESENTES 
				$.each(result.totalEscaneaosTiendaSemana, function(index, tienda) {
					if(tiendasLabel1.indexOf(tienda.indice)==-1 || tiendasLabel1.length==0){
						tiendasLabel1.push(tienda.indice);
					}
				});
				console.log(tiendasLabel1);
				$.each(result.totalEscaneaosTiendaSemana, function(index, tienda) {
					tiendasLabel=[];
					for(var x=0;x<tiendasLabel1.length;x++){
						tiendasLabel.push(tiendasLabel1[x]);
					}
					//console.log(tiendasLabel1);
					var tiendasData=[];
					var tiendasD=[];
					var dia=[];
					if(topicos.length==0 || topicos.indexOf(tienda.topico)==-1){
						topicos.push(tienda.topico);
						
						//entra un departamento una sola vez y barre todos los dias
						
							$.each(result.totalEscaneaosTiendaSemana, function(index, d) {
								
								if(tienda.topico==d.topico){
									//console.log(d.total);
									tiendasData.push(d.total);
									dia.push(d.indice);
									//console.log(d.indice);
								}
							});
							
							
							
						do
						{
							var p=false;
							console.log(dia);
							console.log(tiendasLabel);
								if(dia[0]==tiendasLabel[0]){
									
									tiendasD.push(tiendasData[0]);
									dia.splice(0,1);
									tiendasData.splice(0,1);
									tiendasLabel.splice(0,1);
									
								}else if(dia[0]>tiendasLabel[0]){
									tiendasD.push(0);
									tiendasLabel.splice(0,1)
								}
								
								if(dia.length==0 && tiendasLabel.length==0){
									p=true;
								}
								if(dia.length==0 && tiendasLabel.length>0){
									tiendasD.push(0);
									tiendasLabel.splice(0,1)
									if(dia.length==0 && tiendasLabel.length==0){
										p=true;
									}
								}
								console.log(p);
							}while(p);
						
						
						var tmpDataset = {
								label: tienda.topico,
								backgroundColor: chartBackground,
								borderColor: chartBackgroundBorder,
								borderWidth: 1,
								data: tiendasD
						};
						
						tiendasDatasets.push(tmpDataset)
						console.log( tmpDataset )
					}
					
				});
				var ctx = document.getElementById('tiendasChart').getContext('2d');

				if (window.tiendasChart) {
				    window.tiendasChart.clear();
				    window.tiendasChart.destroy();
				}
				window.tiendasChart = new Chart(ctx, {
					type: 'bar',
					data: {
						labels: tiendasLabel1,
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
				
			}
		});
	},
	tiendasPorMesChart : function(){
		var tiendasDatasets=[];
		var meses=["Ene","Feb","Mar","Abr","May","Jun","Jul","Ago","Sep","Oct","Nov","Dic"];
		$.ajax({
			url : url + "/estadisticas/general",
			dataType : "json",
			success : function(result) {
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

				var ctx = document.getElementById('tiendasChart').getContext('2d');
				if (window.tiendasChart) {
				    window.tiendasChart.clear();
				    window.tiendasChart.destroy();
				}
				window.tiendasChart = new Chart(ctx, {
					type: 'bar',
					data: {
						labels: meses,
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
