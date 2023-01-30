<nav class="navbar sticky-top navbar-expand-lg bg-body-tertiary bg-dark" data-bs-theme="dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="/snake/home"><img class="rounded" src="images/snake.gif" style="width:50px; height:50px; margin-left:0px" data-toggle="tooltip" data-placement="left" title="Veuillez entrer votre mot de passe pour valider les modifications" alt="snake" disabled/></a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/snake/ranking">Classement</a>
        </li>
		<li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/snake/shop">Boutique</a>
        </li>
      </ul>
        <div class="nav-item dropdown active text-white mx-2" aria-current="page">
          <a class="nav-link dropdown-toggle" href="/snake/profile" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            ${sessionScope.name}
          </a>
          <ul class="dropdown-menu dropdown-menu-end"> 
            <li><a class="dropdown-item" href="/snake/profile">Vos informations</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="/snake/logout">Se déconnecter</a></li>
          </ul>
        </div>
    </div>
  </div>
</nav>