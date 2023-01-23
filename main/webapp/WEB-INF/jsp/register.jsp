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


<form method="post" action="register">

  <div class="mb-3">
    <label for="exampleInputEmail1" class="form-label">Pseudo</label>
    <input class="form-control" id="pseudo" name="pseudo" value="${ pseudo }">
  </div>
  
  <div class="mb-3">
    <label for="exampleInputEmail1" class="form-label">Email</label>
    <input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp" value="${ email }">
  </div>
  
  <div class="mb-3">
    <label for="exampleInputEmail1" class="form-label">Confirmer l'email</label>
    <input type="email" class="form-control" id="emailConfirm" name="emailConfirm" aria-describedby="emailHelp" value="${ emailConfirm }">
  </div>
  
  <div class="mb-3">
    <label for="exampleInputPassword1" class="form-label">Mot de passe</label>
    <input type="password" class="form-control" id="password" name="password">
  </div>
  
  <div class="mb-3">
    <label for="exampleInputPassword1" class="form-label">Confirmer le mot de passe</label>
    <input type="password" class="form-control" id="passwordConfirm" name="passwordConfirm">
  </div>
  
  
  <div class="mb-3">
  <button type="submit" class="btn btn-primary" style="width:100%">S'inscrire</button>
  </div>
  
  
</form>

</div>
	

</div>

<%@ include file="footer.jsp" %>