
<%@ include file="header.jsp" %>

<div class="mx-8 my-2" style="padding: 100px 0px">

<c:forEach items="${ errors }" var="error">
	<c:if test="${ !errors.isEmpty()}">
		<div class="alert alert-danger mx-auto" style="width: 600px; padding: 10px 50px" role="alert">
		  <c:out value="${ error }"/>
		</div>
	</c:if>
</c:forEach>

<div class="card mx-auto" style="width: 600px; padding: 10px 50px">


<form method="post" action="login">
  <div class="mb-3">
    <label for="exampleInputEmail1" class="form-label">Login</label>
    <input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp">
    <div id="emailHelp" class="form-text">Si vous avez perdu votre nom d'utilisateur, tant pis !</div>
  </div>
  <div class="mb-3">
    <label for="exampleInputPassword1" class="form-label">Mot de passe</label>
    <input type="password" class="form-control" id="password" name="password">
    <div id="passwordHelp" class="form-text">... Pareil pour votre mot de passe !</div>
  </div>
  <div class="mb-3">
  <button type="submit" class="btn btn-primary" style="width:100%;">Se connecter</button>
  </div>
  
  <div class="mb-2">
	<a href="/snake/register">
		Pas de compte, enregistrez-vous !
  	</a>
  </div>
  
</form>

</div>
	

</div>

<%@ include file="footer.jsp" %>