
<%@ include file="header.jsp" %>
<%@ include file="menu.jsp" %>

<div class="" style="padding: 10px 50px;">
	
	<div class="card my-5 mx-auto" style="width:500px;">
	  <div class="card-body text-center">
	    <h3>Salut ${sessionScope.name} !</h3>
	  </div>
	</div>
	
	<div class="d-flex flex-wrap justify-content-around">
		
		<div class="card text-center border-success m-2" style="max-width:90%; min-width:33%;">  
		  <div class="card-header">
		    Nouveau skin disponible dans la boutique!!
		  </div>
		  <div class="card-body">
		  	<div class="text-start py-2">
		    <h5 class="card-title">Le skin iconique de Marge Simpson</h5>
		    <div class="d-flex align-items-center">
				<img src="images/skins/snake_marge.png" class="img-fluid rounded-start" style="width:100px;height:100px" alt="${ skin }">
			</div>
		  	</div>
		    <a href="shop" class="btn btn-primary">Go à la boutique</a>
		  </div>
		</div>
				  
		
		<div class="card text-center border-success m-2" style="max-width:90%; min-width:33%;">
		  <div class="card-header">
		    Ta meilleure partie!!
		  </div>
		  <div class="card-body">
		  	<div>
		    	<table class="table table-striped">
				  <thead>
				    <tr class="table-dark">
				      <th scope="col">Map</th>
				      <th scope="col">Score</th>
				      <th scope="col">Nombre tours</th>
				      <th scope="col">Date</th>
				    </tr>
				  </thead>
				  <tbody>
				  	<tr>
						<td>${ game.getMap() }</td>
						<td>${ game.getScore() }</td>
						<td>${ game.getTours() }</td>
						<td>${ game.getDepart() }</td>
					</tr>   
				  </tbody>
				</table>
		  	</div>
		    <a href="ranking" class="btn btn-primary">Go au classement</a>
		  </div>
		</div>
	
	</div>
	
</div>


<%@ include file="footer.jsp" %>