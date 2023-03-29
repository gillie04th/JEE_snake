
<%@ include file="header.jsp" %>
<%@ include file="menu.jsp" %>

<div class="mx-8 my-2 px-4 py-2">
		<div class="card">
			<h1 class="card-header my-2 px-4 py-2">Opération spéciale : tout est gratuit !</h1>
		  	<div class="card-body my-2 px-4 py-2 d-flex align-content-around flex-wrap justify-content-around">
			<c:forEach items="${ imageFiles }" var="skin">
			  	
			  	<div class="card mb-3" style="max-width: 540px;">
				  <div class="row g-0">
				    <div class="col-md-4">
				      <img src="images/skins/${ skin }" class="img-fluid rounded-start" style="width:100px;height:100px" alt="${ skin }">
				    </div>
				    <div class="col-md-8">
				      	<div class="card-body">
					        <h6 class="card-title">${ skin }</h6>
					        <form method="post" action="shop">
					        	<input type="hidden" value="${ skin }" name="skin">
						    	<input type="submit" class="btn btn-primary" value="Utiliser ce skin">
					        </form>
				    	</div>
				    </div>
				  </div>
				</div>
			</c:forEach>
			</div>
		</div>
</div>

<%@ include file="footer.jsp" %>