<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modifier une TodoList</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1 class="my-4">Modifier la TodoList</h1>

    <form method="post" action="edit-todolist">
        <input type="hidden" name="todolistId" value="${todoList.id}">
        <div class="mb-3">
            <label for="name" class="form-label">Nom de la TodoList</label>
            <input type="text" class="form-control" id="name" name="name" value="${todoList.name}" required>
        </div>

        <button type="submit" class="btn btn-warning">Mettre à jour</button>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
