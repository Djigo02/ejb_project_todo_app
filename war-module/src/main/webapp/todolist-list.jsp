<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="sn.jgotechmaker.ejbmodule.entities.TodoList" %>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Liste des TodoLists</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    .table th, .table td {
      text-align: center;
      vertical-align: middle;
    }
    .btn-actions {
      display: flex;
      gap: 5px;
      justify-content: center;
    }
    .table-container {
      margin-top: 30px;
    }
  </style>
</head>
<body>

<div class="container">
  <!-- Barre supérieure avec déconnexion -->
  <div class="d-flex justify-content-between align-items-center my-4">
    <h1>Mes TodoLists</h1>
    <a href="logout" class="btn btn-danger">Se déconnecter</a>
  </div>

  <!-- Bouton pour créer une nouvelle TodoList -->
  <a href="create-todolist.jsp" class="btn btn-primary mb-3">Créer une TodoList</a>

  <!-- Tableau des TodoLists -->
  <div class="table-container">
    <table class="table table-bordered table-striped">
      <thead>
      <tr>
        <th>#</th>
        <th>Nom</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <%
        List<TodoList> todoLists = (List<TodoList>) request.getAttribute("todoLists");
        if (todoLists != null) {
          for (TodoList todoList : todoLists) {
      %>
      <tr>
        <td><%= todoList.getId() %></td>
        <td><%= todoList.getName() %></td>
        <td class="btn-actions">
          <!-- Voir les tâches -->
          <a href="tasks?todolistId=<%= todoList.getId() %>" class="btn btn-info btn-sm">Voir les tâches</a>
          <!-- Modifier -->
          <a href="edit-todolist?todolistId=<%= todoList.getId() %>" class="btn btn-warning btn-sm">Modifier</a>
          <!-- Supprimer -->
          <a href="delete-todolist?todolistId=<%= todoList.getId() %>" class="btn btn-danger btn-sm">Supprimer</a>
        </td>
      </tr>
      <%
          }
        }
      %>
      </tbody>
    </table>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
