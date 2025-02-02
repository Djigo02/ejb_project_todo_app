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
    <style>
        .task-table th, .task-table td {
            text-align: center;
            vertical-align: middle;
        }
        .task-form input {
            width: 80%;
            margin-right: 10px;
        }
    </style>
</head>
<body>

<div class="container mt-5">
    <h1 class="mb-4 text-center">Tâches de la TodoList</h1>

    <%
        TodoList todoList = (TodoList) request.getAttribute("todoList");
        List<Task> tasks = (List<Task>) request.getAttribute("tasks");
    %>

    <h2 class="text-center"><%= (todoList != null) ? todoList.getName() : "TodoList inconnue" %></h2>

    <!-- Formulaire pour ajouter une tâche -->
    <form method="post" action="tasks" class="task-form mb-4 d-flex justify-content-center">
        <input type="hidden" name="action" value="create">
        <input type="hidden" name="todolistId" value="<%= (todoList != null) ? todoList.getId() : "" %>">
        <input type="text" class="form-control" id="description" name="description" placeholder="Ajouter une nouvelle tâche" required>
        <button type="submit" class="btn btn-primary ms-2">Ajouter</button>
    </form>

    <!-- Liste des tâches -->
    <table class="table task-table table-bordered table-striped">
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
                <!-- Formulaire de suppression -->
                <form method="post" action="tasks" class="d-inline">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="taskId" value="<%= task.getId() %>">
                    <input type="hidden" name="todolistId" value="<%= todoList.getId() %>">
                    <button type="submit" class="btn btn-danger btn-sm">Supprimer</button>
                </form>

                <!-- Formulaire de modification -->
                <form method="post" action="tasks" class="d-inline">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="taskId" value="<%= task.getId() %>">
                    <input type="hidden" name="todolistId" value="<%= todoList.getId() %>">
                    <input type="text" name="description" value="<%= task.getDescription() %>" class="form-control mb-1" required>
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

    <div class="text-center">
        <a href="todolists" class="btn btn-secondary">Retour aux TodoLists</a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
