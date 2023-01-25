
<%@ include file="header.jsp" %>
<%@ include file="menu.jsp" %>

<div class="" style="padding: 10px 50px;">
	
	<div class="card my-5 mx-auto" style="width:500px;">
	  <div class="card-body text-center">
	    <h3>Bienvenu ${sessionScope.name} !</h3>
	  </div>
	</div>
	
	<div class="d-flex flex-wrap justify-content-around">
		
		<div class="card text-center border-success m-2" style="max-width:90%; min-width:33%;">  
		  <div class="card-header">
		    Featured
		  </div>
		  <div class="card-body">
		  	<div class="text-start py-2">
		    <h5 class="card-title">Special title treatment</h5>
		    <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
		  	</div>
		    <a href="#" class="btn btn-primary">Go somewhere</a>
		  </div>
		</div>
		
		<div class="card text-center border-success m-2" style="max-width:90%; min-width:33%;">
		  <div class="card-header">
		    Featured
		  </div>
		  <div class="card-body">
		  	<div class="text-start py-2">
		    <h5 class="card-title">Special title treatment</h5>
		    <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
		  	</div>
		    <a href="#" class="btn btn-primary">Go somewhere</a>
		  </div>
		</div>
	
	</div>
	
</div>


<%@ include file="footer.jsp" %>