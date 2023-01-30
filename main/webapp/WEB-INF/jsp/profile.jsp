
<%@ include file="header.jsp" %>
<%@ include file="menu.jsp" %>

<div class="mx-8 my-2">

	<div class="card my-5 mx-auto" style="width:90%;">
		<h4 class="card-header">
	    	Vos informations
	  	</h4>
	  	<div class="card-body">
	      <form method="post" action="profile">
		      
		      <div class="mb-3 row">
			    <label class="col-sm-2 col-form-label">Nom :</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="name" name="name" value="${sessionScope.name}">
			    </div>
			  </div>
			  
			  <div class="mb-3 row">
			  	<label class="col-sm-2 col-form-label">Email :</label>
			  	<div class="col-sm-10">
			      <input type="text" class="form-control" id="email" name="email" value="${sessionScope.login}">
			    </div>
			  </div>
			  
			  <div class="mb-3 row">
			    <label for="password" class="col-sm-2 col-form-label">Nouveau mot de passe : </label>
			    <div class="col-sm-10">
			      <input type="password" class="form-control" id="newPassword" name="newPassword">
			    </div>
			  </div>
			  
			  <div class="mb-3 row">
			    <label for="password" class="col-sm-2 col-form-label">Confirmez le nouveau mot de passe : </label>
			    <div class="col-sm-10">
			      <input type="password" class="form-control" id="confirmNewPassword" name="confirmNewPassword">
			    </div>
			  </div>
			  
			  <hr>
			  
			  <div class="mb-3 row">
			    <label for="password" class="col-sm-2 col-form-label">Mot de passe actuel : </label>
			    <div class="col-sm-10">
			      <input type="password" class="form-control" id="password" name="password">
			    </div>
			  </div>
			  
			  <div class="mb-3 row text-center">
			      <div class="col">
			      <input type="submit" class="btn btn-danger form-control mx-auto col-sm-2" id="submit" style="width:auto" disabled>
			      	<img src="images/info.gif" style="width:25px; height:25px; margin-left:0px" data-toggle="tooltip" data-placement="left" title="Veuillez entrer votre mot de passe pour valider les modifications"/>
			      </div>
			  </div>
		  </form>
	  	</div>
	</div>
	
	
	<c:forEach items="${ errorsSuppr }" var="errorSuppr">
		<c:if test="${ !errors.isEmpty()}">
			<div class="alert alert-danger mx-auto" style="width: 600px; padding: 10px 50px" role="alert">
			  <c:out value="${ errorSuppr }"/>
			</div>
		</c:if>
	</c:forEach>
	
	<div class="card my-5 mx-auto" style="width:90%;">
		<h4 class="card-header">
	    	Supprimer votre compte (ceci est définitif)
	  	</h4>
	  	<div class="card-body">
	      <form method="post" action="unregister">
		      		  
			  <div class="mb-3 row">
			    <label for="password" class="col-sm-2 col-form-label">Mot de passe : </label>
			    <div class="col-sm-10">
			      <input type="password" class="form-control" id="passwordSuppr" name="passwordSuppr">
			    </div>
			  </div>
			  
			  <div class="mb-3 row text-center">
			      <div class="col">
			      	<input type="submit" class="btn btn-danger form-control mx-auto col-sm-2" id="submitSuppr" style="width:auto" disabled>
			      	<img src="images/info.gif" style="width:25px; height:25px; margin-left:0px" data-toggle="tooltip" data-placement="left" title="Veuillez entrer votre mot de passe pour valider les modifications"/>
			      </div>
			  </div>
			  
		  </form>
		  
	  	</div>
	</div>

</div>

<%@ include file="footer.jsp" %>
<script type="text/javascript">
	
	pwInput = document.getElementById("password");
	pwInput.addEventListener("keyup", function() {
	    var nameInput = pwInput.value;
	    if (nameInput != "") {
	        document.getElementById('submit').removeAttribute("disabled");
	    } else {
	        document.getElementById('submit').setAttribute("disabled", null);
	    }
	});
	
	pwInputSuppr = document.getElementById("passwordSuppr");
	pwInputSuppr.addEventListener("keyup", function() {
	    var nameInput = pwInputSuppr.value;
	    if (nameInput != "") {
	        document.getElementById('submitSuppr').removeAttribute("disabled");
	    } else {
	        document.getElementById('submitSuppr').setAttribute("disabled", null);
	    }
	});


</script>


