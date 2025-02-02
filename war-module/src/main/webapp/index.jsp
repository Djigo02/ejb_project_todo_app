<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login - Se connecter</title>
  <!-- Lien vers Bootstrap -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<div class="container">
  <h1 class="mt-5">Se connecter</h1>

  <!-- Affichage d'un message d'erreur -->
  <% String error = (String) request.getAttribute("error"); %>
  <% if (error != null) { %>
  <div class="alert alert-danger">
    <%= error %>
  </div>
  <% } %>

  <form method="post" action="login" novalidate>
    <div class="mb-3">
      <label for="username" class="form-label">Nom d'utilisateur</label>
      <input type="text" class="form-control" id="username" name="username" placeholder="Nom d'utilisateur" autocomplete="username" required>
    </div>
    <div class="mb-3">
      <label for="password" class="form-label">Mot de passe</label>
      <input type="password" class="form-control" id="password" name="password" placeholder="Mot de passe" autocomplete="current-password" required>
    </div>
    <button type="submit" class="btn btn-primary">Se connecter</button>
  </form>

  <br>
  <p>Pas de compte ? <a href="signup.jsp">S'inscrire</a></p>
</div>

<!-- Lien vers Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
