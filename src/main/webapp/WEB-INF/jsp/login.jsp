<%--
  Created by IntelliJ IDEA.
  User: Mikola
  Date: 10.03.2022
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${sessionScope.locale == null}">
    <c:set var="locale" value="ru_RU" scope="session"/>
</c:if>

<%session.setAttribute("currentPage", "login.jsp");%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="pagecontent"/>

<fmt:message key="lable.login" var="login"/>
<fmt:message key="lable.password" var="password"/>
<fmt:message key="button.submit" var="submit"/>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <script src="https://kit.fontawesome.com/7d33ba923d.js" crossorigin="anonymous"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="static/styles/login-style.css">
    <title>login</title>
</head>
<body>
<header>
    <a href="#" style="text-decoration: none;">
        <div class="logo">
            Fitness center
        </div>
    </a>
    <form method="post" action="controller?command=languageChange">
        <div class="language">
            <div class="position-ru">
                <button type="submit" name="locale" value="ru_RU" class="form-button-ru">ru</button>
            </div>
            <div class="position-be">
                <button type="submit" name="locale" value="bel_BEL" class="form-button-by">by</button>
            </div>
            <div class="position-en">
                <button type="submit" name="locale" value="en_US" class="form-button-en">en</button>
            </div>
        </div>
    </form>
</header>
<main>
    <div class="modal">
        <form action="controller?command=login" class="position-form" method="post">
            <label class="size-lable-name" for="fname">${login}</label><br>
            <input class="form-login" required type="text" id="lname" name="login"><br><br>
            <label class="size-lable-name" for="fname">${password}</label><br>
            <input class="form-password" required type="password" id="lname" name="password"><br><br>
            <div class = "button-position">
                <button class="form-button-submit" type="submit" name="submit-log-in">
                    ${submit}
                </button>
            </div>
            <div style="color:red" ;>${errorMessage}</div>
        </form>
        <div class = position-button-reg>
            <a class="design-button-reg" href="controller?command=getClientRegistrationForm">
                <button class="form-button-reg" type="submit" name="submit-log-in">
                    SIGN UP
                </button>
            </a>
        </div>

    </div>

</main>
<div class="line">

</div>
<div class="footer-message">
    <a href="https://www.facebook.com/#!/"> <i class="fab fa-facebook-f"></i></a>
    <a href="https://www.instagram.com/"> <i class="fab fa-instagram"></i> </a>
    <a href="https://mobile.twitter.com/i/flow/login">  <i class="fab fa-twitter"></i> </a>
    <a href="https://support.google.com/answer/2451065?hl=en"> <i class="fab fa-google-plus-g"></i> </a>
    <a href="https://www.pinterest.com/"> <i class="fab fa-pinterest"></i> </a>
    <a href="https://vimeo.com/"> <i class="fab fa-vimeo-v"></i> </a>
    <a href="https://www.linkedin.com/uas/login-submit">  <i class="fab fa-linkedin-in"></i> </a>
    <a href="https://www.youtube.com/">  <i class="fab fa-youtube" ></i> </a>
</div>
</body>
</html>
