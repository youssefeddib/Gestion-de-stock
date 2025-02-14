<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des Produits</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
        <div>
            <a href="https://www.javaguides.net" class="navbar-brand">App Gestion Produit</a>
        </div>
        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Produits</a></li>
        </ul>
    </nav>
</header>
<br>

<div class="container">
    <h2 class="text-center">Liste des Produits</h2>
    <a href="new" class="btn btn-success mb-3">Ajouter Nouveau Produit</a>

    <table class="table table-bordered">
        <thead class="thead-dark">
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Description</th>
                <th>Quantity</th>
                <th>Prix</th>
                <th>Categorie</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="produit" items="${produits}">
                <tr>
                    <td><c:out value="${produit.id}" /></td>
                    <td><c:out value="${produit.nom}" /></td>
                    <td><c:out value="${produit.description}" /></td>
                    <td><c:out value="${produit.quantite}" /></td>
                    <td><c:out value="${produit.prix}" /></td>
                    <td><c:out value="${produit.categorie}" /></td>
                   
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
