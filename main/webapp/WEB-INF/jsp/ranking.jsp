
<%@ include file="header.jsp" %>
<%@ include file="menu.jsp" %>

<div class="mx-8 my-2">

	<div class="card my-5 mx-auto" style="width:90%;">
		<h4 class="card-header">
	    	Classement
	  	</h4>
	  	<div class="card-body">
	  	
	  		<label for="map-select">Choisir une map :</label>
	  		
	  		<select name="maps" id="map-select">
	  			
	  			<option value="all">Toutes les maps</option>
	  			
		  		<c:forEach items="${ mapsName }" var="map">
					<c:if test="${ !mapsName.isEmpty()}">
						<option value="${ map }">${ map }</option>
					</c:if>
				</c:forEach>	
	  		</select>
			
			<br/><br/>
			
			<table class="table table-striped">
			  <thead>
			    <tr class="table-dark">
			      <th scope="col">#</th>
			      <th scope="col">Nom</th>
			      <th scope="col">Map</th>
			      <th scope="col">Score</th>
			      <th scope="col">Nombre tours</th>
			      <th scope="col">Vitesse</th>
			      <th scope="col">Statut</th>
			      <th scope="col">Date</th>
			    </tr>
			  </thead>
			  <tbody>
			  	<c:set var="i" value="0" scope="page" />
				  <c:forEach items="${ games }" var="game">
					<c:if test="${ !games.isEmpty()}">
					<c:set var="i" value="${i + 1}" scope="page"/>
						<tr class="partie">
					      <th scope="row">${i}</th>
					      <td>${ game.getJoueur().getName() }</td>
					      <td>${ game.getMap() }</td>
					      <td>${ game.getScore() }</td>
					      <td>${ game.getTours() }</td>
					      <td>${ game.getSpeed() }</td>
					      <td>${ game.getStatus() }</td>
					      <td>${ game.getDepart() }</td>
					    </tr>
					</c:if>
				</c:forEach>
			    
			  </tbody>
			</table>
	  	
	  	</div>
	  </div>

</div>

<script>
	mapSelector = document.getElementById("map-select");
	mapSelector.addEventListener("change", function(){
		lignes = document.querySelectorAll(".table-striped > tbody > .partie");
		let counter = 1;
		if(mapSelector.value != "all"){
			lignes.forEach(row => {
				number = row.getElementsByTagName("th");
					
				col = row.getElementsByTagName("td");
				console.log(col[0].innerHTML);
				
				if(mapSelector.value == col[1].innerHTML){
					//console.log("debug");
					row.style.display = '';
					number[0].innerHTML = counter;
					counter ++;
				} else {
					row.style.display = 'none';
				}
			});
		} else {
			lignes.forEach(row => {
				number = row.getElementsByTagName("th");
				row.style.display = '';
				number[0].innerHTML = counter;
				counter ++;
			});
		}
	});
</script>

<%@ include file="footer.jsp" %>