var estadisticas_tickets = {

	loadCharts : function() {

		$.ajax({
			url : "http://www.shingshing.com/estadisticas/tickets",
			dataType : "json",
			success : function(result) {

				console.log(result);
				var ticketsMesData = [], label = [];
				var ticketsTiendaData = [], ticketsTiendaLabel = [];

				$.each(result.totalTicketsMes, function(index, item) {
					ticketsMesData.push(item.total)
					label.push(item.topico)

				});

				$.each(result.totalTicketsPorTienda, function(index, item) {
					ticketsTiendaData.push(item.total)
					ticketsTiendaLabel.push(item.topico)

				});

				var ctx = document.getElementById('ticketsChart').getContext(
						'2d');

				window.myChart = new Chart(ctx, {
					type : 'bar',
					data : {
						labels : label,
						datasets : [ {
							label : 'Total',
							data : ticketsMesData,
							backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
									'rgba(54, 162, 235, 0.2)',
									'rgba(255, 206, 86, 0.2)',
									'rgba(75, 192, 192, 0.2)',
									'rgba(153, 102, 255, 0.2)',
									'rgba(255, 159, 64, 0.2)' ],
							borderColor : [ 'rgba(255, 99, 132, 1)',
									'rgba(54, 162, 235, 1)',
									'rgba(255, 206, 86, 1)',
									'rgba(75, 192, 192, 1)',
									'rgba(153, 102, 255, 1)',
									'rgba(255, 159, 64, 1)' ],
							borderWidth : 1
						} ]
					},
					options : {
						scales : {
							yAxes : [ {
								ticks : {
									beginAtZero : true
								}
							} ]
						}
					}
				});

				var ctx = document.getElementById('ticketsTiendaChart').getContext(
						'2d');

				window.myChart = new Chart(ctx, {
					type : 'bar',
					data : {
						labels : label,
						datasets : [ {
							label : 'Total',
							data : ticketsTiendaData,
							backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
									'rgba(54, 162, 235, 0.2)',
									'rgba(255, 206, 86, 0.2)',
									'rgba(75, 192, 192, 0.2)',
									'rgba(153, 102, 255, 0.2)',
									'rgba(255, 159, 64, 0.2)' ],
							borderColor : [ 'rgba(255, 99, 132, 1)',
									'rgba(54, 162, 235, 1)',
									'rgba(255, 206, 86, 1)',
									'rgba(75, 192, 192, 1)',
									'rgba(153, 102, 255, 1)',
									'rgba(255, 159, 64, 1)' ],
							borderWidth : 1
						} ]
					},
					options : {
						scales : {
							yAxes : [ {
								ticks : {
									beginAtZero : true
								}
							} ]
						}
					}
				});

			}
		});
	},
	ticketsPorDiaChart : function() {

		$.ajax({
			url : "http://www.shingshing.com/estadisticas/tickets",
			dataType : "json",
			success : function(result) {

				console.log(result);
				var ticketsData = [], ticketslabel = [];

				$.each(result.totalTicketsDia, function(index, item) {
					ticketsData.push(item.total)
					ticketslabel.push("D " + item.indice)

				});

				var ctx = document.getElementById('ticketsChart').getContext('2d');

				// Elmina dataset anterior
				window.myChart.data.labels.splice(0,window.myChart.data.labels.length);
				window.myChart.data.datasets.forEach(function(dataset) {
					dataset.data.pop();
				});
				window.myChart.data.datasets.pop();

				window.myChart.update();

				// Actualizar con nuevos datos
				var newDataset = {
					label : 'Total',
					backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
							'rgba(54, 162, 235, 0.2)',
							'rgba(255, 206, 86, 0.2)',
							'rgba(75, 192, 192, 0.2)',
							'rgba(153, 102, 255, 0.2)' ],
					borderColor : [ 'rgba(255, 99, 132, 1)',
							'rgba(54, 162, 235, 1)',
							'rgba(255, 206, 86, 1)',
							'rgba(75, 192, 192, 1)',
							'rgba(153, 102, 255, 1)' ],
					borderWidth : 1,
					data : ticketsData
				};

				window.myChart.data.labels = ticketslabel;
				window.myChart.data.datasets.push(newDataset);
				window.myChart.update();
			}
		});
	},
	ticketsPorSemanaChart : function() {
		$.ajax({
			url : "http://www.shingshing.com/estadisticas/tickets",
			dataType : "json",
			success : function(result) {

				console.log(result);
				var ticketsData = [], ticketslabel = [];

				$.each(result.totalTicketsSemana, function(index, item) {
					ticketsData.push(item.total)
					ticketslabel.push("Semana " + item.indice)
				});

				var ctx = document.getElementById('ticketsChart').getContext('2d');

				// Elmina dataset anterior
				window.myChart.data.labels.splice(0,window.myChart.data.labels.length);
				window.myChart.data.datasets.forEach(function(dataset) {
					dataset.data.pop();
				});
				window.myChart.data.datasets.pop();

				window.myChart.update();

				// Actualizar con nuevos datos
				var newDataset = {
					label : 'Total',
					backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
							'rgba(54, 162, 235, 0.2)',
							'rgba(255, 206, 86, 0.2)',
							'rgba(75, 192, 192, 0.2)',
							'rgba(153, 102, 255, 0.2)' ],
					borderColor : [ 'rgba(255, 99, 132, 1)',
							'rgba(54, 162, 235, 1)',
							'rgba(255, 206, 86, 1)',
							'rgba(75, 192, 192, 1)',
							'rgba(153, 102, 255, 1)' ],
					borderWidth : 1,
					data : ticketsData
				};

				window.myChart.data.labels = ticketslabel;
				window.myChart.data.datasets.push(newDataset);
				window.myChart.update();
			}
		});
	},
	ticketsPorMesChart : function() {
		$.ajax({
			url : "http://www.shingshing.com/estadisticas/tickets",
			dataType : "json",
			success : function(result) {

				console.log(result);
				var ticketsData = [], ticketslabel = [];

				$.each(result.totalTicketsMes, function(index, item) {
					ticketsData.push(item.total)
					ticketslabel.push(item.topico)
				});

				var ctx = document.getElementById('ticketsChart').getContext('2d');

				// Elmina dataset anterior
				window.myChart.data.labels.splice(0,window.myChart.data.labels.length);
				window.myChart.data.datasets.forEach(function(dataset) {
					dataset.data.pop();
				});
				window.myChart.data.datasets.pop();

				window.myChart.update();

				// Actualizar con nuevos datos
				var newDataset = {
					label : 'Total',
					backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
						'rgba(54, 162, 235, 0.2)',
						'rgba(255, 206, 86, 0.2)',
						'rgba(75, 192, 192, 0.2)',
						'rgba(153, 102, 255, 0.2)' ],
					borderColor : [ 'rgba(255, 99, 132, 1)',
						'rgba(54, 162, 235, 1)',
						'rgba(255, 206, 86, 1)',
						'rgba(75, 192, 192, 1)',
						'rgba(153, 102, 255, 1)' ],
					borderWidth : 1,
					data : ticketsData
				};

				window.myChart.data.labels = ticketslabel;
				window.myChart.data.datasets.push(newDataset);
				window.myChart.update();
			}
		});
	},
	ticketsPorTiendaDiaChart : function() {
		$.ajax({
			url : "http://www.shingshing.com/estadisticas/tickets",
			dataType : "json",
			success : function(result) {

				console.log(result);
				var ticketsTiendaData = [], ticketsTiendalabel = [];

				$.each(result.totalTicketsTiendaDias, function(index, item) {
					ticketsTiendaData.push(item.total)
					ticketsTiendalabel.push(item.topico)
				});

				var ctx = document.getElementById('ticketsTiendaChart')
						.getContext('2d');

				// Elmina dataset anterior
				window.myChart.data.labels.splice(0,window.myChart.data.labels.length);
				window.myChart.data.datasets.forEach(function(dataset) {
					dataset.data.pop();
				});
				window.myChart.data.datasets.pop();

				window.myChart.update();

				// Actualizar con nuevos datos
				var newDataset = {
					label : 'Total',
					backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
						'rgba(54, 162, 235, 0.2)',
						'rgba(255, 206, 86, 0.2)',
						'rgba(75, 192, 192, 0.2)',
						'rgba(153, 102, 255, 0.2)' ],
					borderColor : [ 'rgba(255, 99, 132, 1)',
						'rgba(54, 162, 235, 1)',
						'rgba(255, 206, 86, 1)',
						'rgba(75, 192, 192, 1)',
						'rgba(153, 102, 255, 1)' ],
					borderWidth : 1,
					data : ticketsTiendaData
				};

				window.myChart.data.labels = ticketsTiendalabel;
				window.myChart.data.datasets.push(newDataset);
				window.myChart.update();
			}
		});
	},
	ticketsPorTiendaSemanaChart : function() {
		$.ajax({
			url : "http://www.shingshing.com/estadisticas/tickets",
			dataType : "json",
			success : function(result) {

				console.log(result);
				var ticketsTiendaData = [], ticketsTiendalabel = [];

				$.each(result.totalTicketsTiendaSemana, function(index, item) {
					ticketsTiendaData.push(item.total)
					ticketsTiendalabel.push(item.topico)
				});

				var ctx = document.getElementById('ticketsTiendaChart')
						.getContext('2d');

				// Elmina dataset anterior
				window.myChart.data.labels.splice(0,window.myChart.data.labels.length);
				window.myChart.data.datasets.forEach(function(dataset) {
					dataset.data.pop();
				});
				window.myChart.data.datasets.pop();

				window.myChart.update();

				// Actualizar con nuevos datos
				var newDataset = {
					label : 'Total',
					backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
						'rgba(54, 162, 235, 0.2)',
						'rgba(255, 206, 86, 0.2)',
						'rgba(75, 192, 192, 0.2)',
						'rgba(153, 102, 255, 0.2)' ],
					borderColor : [ 'rgba(255, 99, 132, 1)',
						'rgba(54, 162, 235, 1)',
						'rgba(255, 206, 86, 1)',
						'rgba(75, 192, 192, 1)',
						'rgba(153, 102, 255, 1)' ],
					borderWidth : 1,
					data : ticketsTiendaData
				};

				window.myChart.data.labels = ticketsTiendalabel;
				window.myChart.data.datasets.push(newDataset);
				window.myChart.update();
			}
		});
	},
	ticketsPorTiendaMesChart : function() {
		$.ajax({
			url : "http://www.shingshing.com/estadisticas/tickets",
			dataType : "json",
			success : function(result) {

				console.log(result);
				var ticketsTiendaData = [], ticketsTiendalabel = [];

				$.each(result.totalTicketsTiendaMes, function(index, item) {
					ticketsTiendaData.push(item.total)
					ticketsTiendalabel.push(item.topico)
				});

				var ctx = document.getElementById('ticketsTiendaChart')
						.getContext('2d');

				// Elmina dataset anterior
				window.myChart.data.labels.splice(0,window.myChart.data.labels.length);
				window.myChart.data.datasets.forEach(function(dataset) {
					dataset.data.pop();
				});
				window.myChart.data.datasets.pop();

				window.myChart.update();

				// Actualizar con nuevos datos
				var newDataset = {
					label : 'Total',
					backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
						'rgba(54, 162, 235, 0.2)',
						'rgba(255, 206, 86, 0.2)',
						'rgba(75, 192, 192, 0.2)',
						'rgba(153, 102, 255, 0.2)' ],
					borderColor : [ 'rgba(255, 99, 132, 1)',
						'rgba(54, 162, 235, 1)',
						'rgba(255, 206, 86, 1)',
						'rgba(75, 192, 192, 1)',
						'rgba(153, 102, 255, 1)' ],
					borderWidth : 1,
					data : ticketsTiendaData
				};

				window.myChart.data.labels = ticketsTiendalabel;
				window.myChart.data.datasets.push(newDataset);
				window.myChart.update();
			}
		});
	},
	init : function() {
		estadisticas_tickets.loadCharts();
	}
};

$(document).ready(function() {
	estadisticas_tickets.init();
});
