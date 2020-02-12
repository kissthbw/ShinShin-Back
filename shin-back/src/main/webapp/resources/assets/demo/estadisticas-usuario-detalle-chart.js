var estadisticas_usuario_detalle = {
		
		loadCharts : function() {
			
			var url = $(location).attr('href')
			var n = url.lastIndexOf("/");
			var id = url.substring(n + 1, url.length);

			$.ajax({
				url : "http://www.shingshing.com/estadisticas/usuario/" + id,
				dataType : "json",
				success : function(result) {
					
					
					console.log(result);
					var marcasData=[], marcasLabel=[];
					var deptosData=[], deptosLabel=[];
					var tiendasDatasets = []; 
					var tiendasLabel = ["Ene", "Feb", "Mar", "Abr", "May", "Jun",
						"Jul", "Ago", "Sep", "Oct", "Nov", "Dic"];


					$.each(result.listaTopMarcas, function(index, item) {
						marcasData.push(item.total)
						marcasLabel.push(item.topico)
						
					});
					
					$.each(result.listaTopDeptos, function(index, item) {
						deptosData.push(item.total)
						deptosLabel.push(item.topico)
						
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
					});

					
					var ctx = document.getElementById('marcasChart').getContext('2d');

					window.usuariosChart = new Chart(ctx, {
						type: 'bar',
						data: {
							labels: marcasLabel,
							datasets: [{
								label: 'Total',
								data: marcasData,
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
					
					var ctx = document.getElementById('deptosChart').getContext('2d');

					window.ticketsChart = new Chart(ctx, {
						type: 'bar',
						data: {
							labels: deptosLabel,
							datasets: [{
								label: 'Total',
								data: deptosData,
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
					
					var ctx = document.getElementById('tiendasChart').getContext('2d');

					window.tiendasChart = new Chart(ctx, {
						type: 'bar',
						data: {
							labels: tiendasLabel,
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
		estadisticas_usuario_detalle.loadCharts();
	}
};

$(document).ready(function() {
	estadisticas_usuario_detalle.init();
});
