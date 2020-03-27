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

var bonificaciones_generales = {
		
		loadCharts : function() {
			$.ajax({
				url : url + "/estadisticas/bonificaciones-general",
				dataType : "json",
				success : function(result) {

					var depositosData=[], depositosLabel=[];
					var bonificacionesData=[], bonificacionesLabel=[];
					var recargasData=[], recargasLabel=[];

					$.each(result.depositos, function(index, item) {
						depositosData.push(item.total)
						depositosLabel.push('D ' + item.indice)
						
					});
					
					$.each(result.bonificaciones, function(index, item) {
						bonificacionesData.push(item.importe)
						bonificacionesLabel.push('D ' + item.indice)
						
					});

					$.each(result.recargas, function(index, item) {
						recargasData.push(item.total)
						recargasLabel.push('D ' + item.indice)
						
					});
					
					
					var ctx = document.getElementById('depositosChart').getContext('2d');

					window.myChart = new Chart(ctx, {
						type: 'bar',
						data: {
							labels: depositosLabel,
							datasets: [{
								label: '#',
								data: depositosData,
								backgroundColor:chartBackground,
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
					
					var ctx = document.getElementById('bonificacionesChart').getContext('2d');

					window.bonificacionesChart = new Chart(ctx, {
						type: 'bar',
						data: {
							labels: bonificacionesLabel,
							datasets: [{
								label: '$',
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
					
					bonificaciones_generales.updateDepositosChart(depositosData, depositosLabel);
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
					
					bonificaciones_generales.updateBonificacionesChart(depositosData, depositosLabel);
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
					bonificaciones_generales.updateRecargasChart(depositosData, depositosLabel);
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
				label: '$' ,
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
	init : function() {
		bonificaciones_generales.loadCharts();
	}
};

$(document).ready(function() {
	bonificaciones_generales.init();
});
