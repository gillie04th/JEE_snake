
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
	  	
	  		<c:forEach items="${ games }" var="game">
				<c:if test="${ !games.isEmpty()}">
					<div class="card my-5 mx-auto" style="width: 600px; padding: 10px 50px">
					  <c:out value="Joueur : ${ game.getJoueur().getName() } | Map : ${ game.getMap() } | Score = ${ game.getScore() }"/>
					</div>
				</c:if>
			</c:forEach>
	  	
	  	</div>
	  </div>

</div>

<%@ include file="footer.jsp" %>