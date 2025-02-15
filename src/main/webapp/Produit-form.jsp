<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Produit Gestion</title>
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
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <h2>
                <c:choose>
                    <c:otherwise>Ajouter Nouveau Produit</c:otherwise>
                </c:choose>
            </h2>

         <form action="${pageContext.request.contextPath}/${Produit != null ? 'produits/update' : 'produits/insert'}" method="post">
    
  
    <c:if test="${Produit != null}">
        <input type="hidden" name="id" value="<c:out value='${Produit.id}' />" />
    </c:if>

    <fieldset class="form-group">
        <label>Nom de Produit</label>
        <input type="text" name="nom" class="form-control" value="<c:out value='${Produit.nom}' />" required />
    </fieldset>

    <fieldset class="form-group">
        <label>Description de Produit</label>
        <input type="text" name="description" class="form-control" value="<c:out value='${Produit.description}' />" required />
    </fieldset>

    <fieldset class="form-group">
        <label>Quantité de Produit</label>
        <input type="number" name="quantite" class="form-control" value="<c:out value='${Produit.quantite}' />" required />
    </fieldset>

    <fieldset class="form-group">
        <label>Prix de Produit</label>
        <input type="number" name="prix" class="form-control" value="<c:out value='${Produit.prix}' />" required />
    </fieldset>

    <fieldset class="form-group">
        <label>Categorie de Produit</label>
        <input type="text" name="categorie" class="form-control" value="<c:out value='${Produit.categorie}' />" required />
    </fieldset>

    <button type="submit" class="btn btn-success">Sauvegarder</button>
</form>
         
        </div>
    </div>
</div>
</body>
</html>
