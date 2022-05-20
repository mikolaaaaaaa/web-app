<%--@elvariable id="program" type="com.epam.webapp.entity.Program"--%>
<%--
  Created by IntelliJ IDEA.
  User: Mikola
  Date: 10.03.2022
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>

<%@ page isELIgnored="false" pageEncoding="UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="orderList" scope="session" type="java.util.List"/>

<c:if test="${sessionScope.locale == null}">
    <c:set var="locale" value="ru_RU" scope="session"/>
</c:if>

<% session.setAttribute("currentPage", "clients.jsp");%>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="pagecontent"/>

<fmt:message key="client.menu.order" var="order"/>
<fmt:message key="client.menu.myprogram" var="myprogram"/>
<fmt:message key="client.menu.history" var="history"/>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <script src="https://kit.fontawesome.com/7d33ba923d.js" crossorigin="anonymous"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>history</title>

    <link rel="stylesheet" href="static/styles/style.css">
</head>
<body>
<header>
    <div class="position-logo">
        <a href="controller?command=showMainPage"><img src="img/logo1.png" alt=""></a>
    </div>
    <div class="position-menu-header">
        <form method="post" action="controller?command=languageChange">
            <div class="language">
                <div class="position-ru">
                    <button type="submit" name="locale" value="ru_RU" class="form-button-ru">RU</button>
                </div>
                <div class="position-by">
                    <button type="submit" name="locale" value="bel_BEL" class="form-button-by">BY</button>
                </div>

                <div class="position-en">
                    <button type="submit" name="locale" value="en_US" class="form-button-en">EN</button>
                </div>
            </div>
        </form>
        <div class="exit">
            <a href="controller?command=logout"> <img src="img/Vector.png" alt=""> </a>
        </div>
    </div>

</header>
<main>


    <div class="fitnes-menu">
        <div class="position-button-programm">
            <a href="controller?command=showClients">
                <button type="submit" class="form-button">clients</button>
            </a>
        </div>
        <div class="button-order-position">
            <a href="#">
                <button type="submit" class="form-button">feedback</button>
            </a>
        </div>
        <div class="button-order-position">
            <a href="controller?command=showOrders">
                <button type="submit" class="form-button">orders</button>
            </a>
        </div>
    </div>
    <div class="program-list">
        <div class="list-information">
            <c:if test="${!orderList.isEmpty()}">
                <c:forEach var="position" begin="0" end="${orderList.size() - 1}" step="1">
                    <a href="#" class="list-information-link">
                        <span class="list-information-date start-date">
                                ${orderList.get(position).clientId}
                        </span>
                        <span class="separator">
                            &ndash;
                        </span>
                        <span class="list-information-date end-date">
                                ${orderList.get(position).date}
                        </span>
                    </a>
                </c:forEach>
            </c:if>
            <c:if test="${orderList.isEmpty()}">
                <div class="list-information-date start-date"> Нет заказов :( </div>
            </c:if>
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
