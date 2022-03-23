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

<c:if test="${sessionScope.locale == null}">
    <c:set var="locale" value="ru_RU" scope="session"/>
</c:if>

<% session.setAttribute("currentPage","order.jsp");%>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="pagecontent"/>

<fmt:message key="client.menu.order" var="order"/>
<fmt:message key="client.menu.myprogram" var="myprogram"/>
<fmt:message key="client.menu.history" var="history"/>
<fmt:message key="client.order.header" var="customHeader"/>
<fmt:message key="client.order.calculate" var="calculate"/>
<fmt:message key="client.order.pay" var="pay"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>order</title>

    <link rel="stylesheet" href="static/styles/style.css"></head>
<body>
<header>
    <div class="position-logo">
        <a href="/index.html"><img src="img/logo1.png" alt=""></a>
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
            <a href="/login.html"> <img src="img/Vector.png" alt=""> </a>
        </div>
    </div>

</header>
<main>


    <div class="fitnes-menu">
        <div class="position-button-programm">
            <a href="controller?command=program"><button type="submit" class="form-button">${myprogram}</button></a>
        </div>
        <div class="button-order-position">
            <a href="#"><button type="submit" class="form-button">${order}</button></a>
        </div>
        <div class="position-button-history">
            <a href="controller?command=history"> <button type="submit" class="form-button">${history}</button></a>
        </div>
    </div>
    <div class="order">
        <p>
            ${customHeader}
        </p>
        <div class="form-order">
            <form action="#">
                <input class="form-input-order" type="number" >
            </form>
        </div>
        <div class="position-calculate-button">
            <button class="buttton-calculate-cost">${calculate}</button>
        </div>
        <div class="position-pay-button">
            <button class="buttton-calculate-cost">${pay}</button>
        </div>
    </div>



</main>
</body>
</html>