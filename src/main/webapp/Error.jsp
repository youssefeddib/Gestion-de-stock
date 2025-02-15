<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Erreur</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <style>
        .error-container {
            margin-top: 50px;
            text-align: center;
        }
        .error-code {
            font-size: 100px;
            font-weight: bold;
            color: red;
        }
        .error-message {
            font-size: 24px;
        }
    </style>
</head>
<body>
    <div class="container error-container">
        <h1 class="error-code">
            <c:choose>
                <c:when test="${not empty pageContext.errorData.statusCode}">
                    <c:out value="${pageContext.errorData.statusCode}" />
                </c:when>
                <c:otherwise>Erreur</c:otherwise>
            </c:choose>
        </h1>
        <p class="error-message">
            <c:choose>
                <c:when test="${not empty requestScope.errorMessage}">
                    <c:out value="${requestScope.errorMessage}" />
                </c:when>
                <c:otherwise>Une erreur inattendue s'est produite.</c:otherwise>
            </c:choose>
        </p>
        <a href="<%=request.getContextPath()%>/list" class="btn btn-primary">Retour à l'accueil</a>
    </div>
</body>
</html>
