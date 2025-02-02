<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="sn.jgotechmaker.ejbmodule.entities.Task" %>
<%@ page import="sn.jgotechmaker.ejbmodule.entities.TodoList" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des Tâches</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">
    <h1 class="my-4">Tâches de la TodoList</h1>

    <%
        TodoList todoList = (TodoList) request.getAttribute("todoList");
        List<Task> tasks = (List<Task>) request.getAttribute("tasks");
    %>

    <h2><%= (todoList != null) ? todoList.getName() : "TodoList inconnue" %></h2>

    <!-- Formulaire pour ajouter une tâche -->
    <form method="post" action="tasks">
        <input type="hidden" name="action" value="create">
        <input type="hidden" name="todolistId" value="<%= (todoList != null) ? todoList.getId() : "" %>">
        <div class="mb-3">
            <label for="description" class="form-label">Nouvelle tâche</label>
            <input type="text" class="form-control" id="description" name="description" required>
        </div>
        <button type="submit" class="btn btn-primary">Ajouter</button>
    </form>

    <!-- Liste des tâches -->
    <table class="table mt-4">
        <thead>
        <tr>
            <th>#</th>
            <th>Description</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            if (tasks != null) {
                for (Task task : tasks) {
        %>
        <tr>
            <td><%= task.getId() %></td>
            <td><%= task.getDescription() %></td>
            <td>
                <form method="post" action="tasks" class="d-inline">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="taskId" value="<%= task.getId() %>">
                    <input type="hidden" name="todolistId" value="<%= todoList.getId() %>">
                    <button type="submit" class="btn btn-danger btn-sm">Supprimer</button>
                </form>

                <form method="post" action="tasks" class="d-inline">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="taskId" value="<%= task.getId() %>">
                    <input type="hidden" name="todolistId" value="<%= todoList.getId() %>">
                    <input type="text" name="description" value="<%= task.getDescription() %>" required>
                    <button type="submit" class="btn btn-warning btn-sm">Modifier</button>
                </form>
            </td>
        </tr>
        <%
                }
            }
        %>
        </tbody>
    </table>

    <a href="todolists" class="btn btn-secondary">Retour aux TodoLists</a>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
