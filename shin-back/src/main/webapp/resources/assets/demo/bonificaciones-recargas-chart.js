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
	
var bonificaciones_recargas = {
		
		loadCharts : function() {

			$.ajax({
				url : url + "/estadisticas/bonificaciones-general",
				dataType : "json",
				success : function(result) {

					console.log(result);
					var depositosData=[], depositosLabel=[];
					var bonificacionesData=[], bonificacionesLabel=[];
					var recargasData=[], recargasLabel=[];

					$.each(result.recargas, function(index, item) {
						recargasData.push(item.total)
						recargasLabel.push('D ' + item.indice)
						
					});

					
					var ctx = document.getElementById('recargasChart').getContext('2d');

					window.recargasChart = new Chart(ctx, {
						type: 'bar',
						data: {
							labels: recargasLabel,
							datasets: [{
								label: '#',
								data: recargasData,
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
			
			//Para la grafica de recargas por compania
			$.ajax({
				url : url + "/estadisticas/bonificaciones-recargas?categoria=d",
				dataType : "json",
				success : function(result) {

					console.log(result);

					var companiaDatasets = []; 
					var mesLabel = ["Ene", "Feb", "Mar", "Abr", "May", "Jun",
						"Jul", "Ago", "Sep", "Oct", "Nov", "Dic"];
					var label = [];
					
					$.each(result, function(index, tienda) {
						var tiendasData=[];
						
						$.each(tienda.items, function(i, t){
							tiendasData.push( t.total )
							label.push( "D " + t.indice )
						});
						
						var tmpDataset = {
								label: tienda.titulo,
								backgroundColor: chartBackground,
								borderColor: chartBackgroundBorder,
								borderWidth: 1,
								data: tiendasData
						};
						
						companiaDatasets.push(tmpDataset)
					});
//					
					var ctx = document.getElementById('companiaChart').getContext('2d');

					window.tiendasChart = new Chart(ctx, {
						type: 'bar',
						data: {
							labels: label,
							datasets: companiaDatasets
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
	loadChart : function( idChart, tipo, categoria ) {

		console.log( idChart + ' ' + tipo + ' ' + categoria )
		
		$.ajax({
			url : url + "/estadisticas/bonificaciones-general?tipo="+tipo+"&categoria="+categoria,
			dataType : "json",
			success : function(result) {

				console.log(result);
				var depositosData=[], depositosLabel=[];
				
				if( tipo == '1' ){
					$.each(result.depositos, function(index, item) {
						depositosData.push(item.total)
						
						if( categoria == 'm' ){
							depositosLabel.push(item.topico)
						}
						else if( categoria == 's' ) {
							depositosLabel.push("S " + item.indice)
						}
						else{
							depositosLabel.push("D " + item.indice)
						}
						
					});
					
					bonificaciones_recargas.updateDepositosChart(depositosData, depositosLabel);
				}
				if( tipo == '2' ){
					$.each(result.bonificaciones, function(index, item) {
						depositosData.push(item.importe)
						if( categoria == 'm' ){
							depositosLabel.push(item.topico)
						}
						else if( categoria == 's' ) {
							depositosLabel.push("S " + item.indice)
						}
						else{
							depositosLabel.push("D " + item.indice)
						}
					});
					
					bonificaciones_recargas.updateBonificacionesChart(depositosData, depositosLabel);
				}
				if( tipo == '3' ){
					$.each(result.recargas, function(index, item) {
						depositosData.push(item.total)
						if( categoria == 'm' ){
							depositosLabel.push(item.topico)
						}
						else if( categoria == 's' ) {
							depositosLabel.push("S " + item.indice)
						}
						else{
							depositosLabel.push("D " + item.indice)
						}
					});
					bonificaciones_recargas.updateRecargasChart(depositosData, depositosLabel);
				}
			}
		});
	},
	loadCompaniaChart : function( idChart, tipo, categoria ) {

		console.log( idChart + ' ' + tipo + ' ' + categoria )
		
		$.ajax({
			url : url + "/estadisticas/bonificaciones-recargas?categoria="+categoria,
			dataType : "json",
			success : function(result) {

				console.log(result);
				var depositosData=[], depositosLabel=[];
				
				if( categoria == 'd' ){
					var companiaDatasets = []; 
					var label = [];
					
					$.each(result, function(index, tienda) {
						var tiendasData=[];
						
						$.each(tienda.items, function(i, t){
							tiendasData.push( t.total )
							label.push( "D " + t.indice )
						});
						
						var tmpDataset = {
								label: tienda.titulo,
								backgroundColor: chartBackground,
								borderColor: chartBackgroundBorder,
								borderWidth: 1,
								data: tiendasData
						};
						
						companiaDatasets.push(tmpDataset)
					});
					
					bonificaciones_recargas.updateCompaniasChart(companiaDatasets, label);
				}
				if( categoria == 's' ){
					var companiaDatasets = []; 
					var mesLabel = ["Ene", "Feb", "Mar", "Abr", "May", "Jun",
						"Jul", "Ago", "Sep", "Oct", "Nov", "Dic"];
					var label = [];
					
					$.each(result, function(index, tienda) {
						var tiendasData=[];
						
						$.each(tienda.items, function(i, t){
							tiendasData.push( t.total )
							label.push( "S " + t.indice )
						});
						
						var tmpDataset = {
								label: tienda.titulo,
								backgroundColor: chartBackground,
								borderColor: chartBackgroundBorder,
								borderWidth: 1,
								data: tiendasData
						};
						
						companiaDatasets.push(tmpDataset)
					});
					
					bonificaciones_recargas.updateCompaniasChart(companiaDatasets, label);
				}
				if( categoria == 'm' ){
					var companiaDatasets = []; 
					var mesLabel = ["Ene", "Feb", "Mar", "Abr", "May", "Jun",
						"Jul", "Ago", "Sep", "Oct", "Nov", "Dic"];
					var label = [];
					
					$.each(result, function(index, tienda) {
						var tiendasData=[];
						
						$.each(tienda.items, function(i, t){
							tiendasData.push( t.total )
							label.push( t.indice )
						});
						
						var tmpDataset = {
								label: tienda.titulo,
								backgroundColor: chartBackground,
								borderColor: chartBackgroundBorder,
								borderWidth: 1,
								data: tiendasData
						};
						
						companiaDatasets.push(tmpDataset)
					});
					bonificaciones_recargas.updateCompaniasChart(companiaDatasets, mesLabel);
				}
			}
		});
	},
	updateDepositosChart(data, labels){
		
		//var ctx = document.getElementById( idChart ).getContext('2d');
		
		window.myChart.data.labels.splice(0,window.myChart.data.labels.length);
		window.myChart.data.datasets.forEach(function(dataset) {
			dataset.data.pop();
		});
		window.myChart.data.datasets.pop();
		
		window.myChart.update();
		
		//Actualizar con nuevos datos
		var newDataset = {
				label: '#' ,
				backgroundColor: chartBackground,
				borderColor: chartBackgroundBorder,
				borderWidth: 1,
				data: data
		};
		
		window.myChart.data.labels = labels;
		window.myChart.data.datasets.push(newDataset);
		window.myChart.update();
	},
	updateBonificacionesChart(data, labels){
		
		//var ctx = document.getElementById( idChart ).getContext('2d');
		
		window.bonificacionesChart.data.labels.splice(0,window.bonificacionesChart.data.labels.length);
		window.bonificacionesChart.data.datasets.forEach(function(dataset) {
			dataset.data.pop();
		});
		window.bonificacionesChart.data.datasets.pop();
		
		window.bonificacionesChart.update();
		
		//Actualizar con nuevos datos
		var newDataset = {
				label: '#' ,
				backgroundColor: chartBackground,
				borderColor: chartBackgroundBorder,
				borderWidth: 1,
				data: data
		};
		
		window.bonificacionesChart.data.labels = labels;
		window.bonificacionesChart.data.datasets.push(newDataset);
		window.bonificacionesChart.update();
	},
	updateRecargasChart(data, labels){
		
		//var ctx = document.getElementById( idChart ).getContext('2d');
		
		window.recargasChart.data.labels.splice(0,window.recargasChart.data.labels.length);
		window.recargasChart.data.datasets.forEach(function(dataset) {
			dataset.data.pop();
		});
		window.recargasChart.data.datasets.pop();
		
		window.recargasChart.update();
		
		//Actualizar con nuevos datos
		var newDataset = {
				label: '#' ,
				backgroundColor: chartBackground,
				borderColor: chartBackgroundBorder,
				borderWidth: 1,
				data: data
		};
		
		window.recargasChart.data.labels = labels;
		window.recargasChart.data.datasets.push(newDataset);
		window.recargasChart.update();
	},
	updateCompaniasChart(data, labels){

		
		window.tiendasChart.data.labels.splice(0,window.tiendasChart.data.labels.length);
		window.tiendasChart.data.datasets.forEach(function(dataset) {
			dataset.data.pop();
		});
		window.tiendasChart.data.datasets.pop();
		
		window.tiendasChart.update();
		
		//Actualizar con nuevos datos
		var newDataset = {
				label: '#' ,
				backgroundColor: chartBackground,
				borderColor: chartBackgroundBorder,
				borderWidth: 1,
				data: data
		};
		
		window.tiendasChart.data.labels = labels;
		//window.tiendasChart.data.datasets.push(newDataset);
		window.tiendasChart.data.datasets = data
		window.tiendasChart.update();
	},
	init : function() {
		bonificaciones_recargas.loadCharts();
	}
};

$(document).ready(function() {
	bonificaciones_recargas.init();
});
