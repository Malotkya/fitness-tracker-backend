<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Fitness Tracker</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/mine.css">
    </head>
<body>
<div class="container">
    <%@include file="jsp/navbar.jsp" %>
    <div id="error"></div>
    <main id="main">
        <%@include file="jsp/login.jsp" %>
        <%@include file="jsp/signup.jsp"%>
        <%@include file="jsp/card.jsp"%>
        <%@include file="jsp/settings.jsp"%>
    </main>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<script src="js/index.js"></script>
<script src="js/Signup.js"></script>
<script src="js/Card.js"></script>
<script src="js/NavBar.js"></script>
<script src="js/Main.js"></script>
<script src="js/Login.js"></script>
<script src="js/Settings.js"></script>
<script src="js/App.js"></script>
</body>
</html>
