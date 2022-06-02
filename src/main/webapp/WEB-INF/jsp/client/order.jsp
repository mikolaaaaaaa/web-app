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

<% session.setAttribute("currentPage", "order.jsp");%>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="pagecontent"/>

<fmt:message key="client.menu.order" var="order"/>
<fmt:message key="client.menu.myprogram" var="myprogram"/>
<fmt:message key="client.menu.history" var="history"/>
<fmt:message key="client.order.header" var="customHeader"/>
<fmt:message key="client.order.calculate" var="calculate"/>
<fmt:message key="client.order.pay" var="pay"/>
<fmt:message key="client.order.personal" var="personal"/>
<fmt:message key="client.order.error_message.not_enough_money" var="not_enough_money"/>
<fmt:message key="client.order.error_message.not_fount_trainer" var="not_found_trainer"/>
<fmt:message key="client.order.message.thank" var="thank"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <script src="https://kit.fontawesome.com/7d33ba923d.js" crossorigin="anonymous"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>order</title>

    <link rel="stylesheet" href="static/styles/style.css">
</head>
<body>
<header>
    <div class="position-logo">
        <a href="controller?command=showMainPage"><img src="img/logo1.png" alt=""></a>
    </div>
    <div class="balance">
        <p>BALANCE : </p>
        <p> ${user.getBalance() } BYN</p>
        <a href="controller?command=showCashIn">
            <button type="" class="top-up">Пополнить</button>
        </a>
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
            <a href="controller?command=program">
                <button type="submit" class="form-button">${myprogram}</button>
            </a>
        </div>
        <div class="button-order-position">
            <a href="#">
                <button type="submit" class="form-button">${order}</button>
            </a>
        </div>
        <div class="position-button-history">
            <a href="controller?command=history">
                <button type="submit" class="form-button">${history}</button>
            </a>
        </div>
    </div>
    <div class="order">
        <p>
            ${customHeader}
        </p>
        <form action="controller?command=calculateOrderPrice" method="post">
            <div class="form-order">
                <input class="form-input-order" type="number" min="1" max="100" required="required"
                       name="trainingCount">
            </div>
            <div>
                <input class="trainer-checkbox" type="checkbox" name="personalTrainer" value="true">
                ${personal}
            </div>
            <div class="position-calculate-button">
                <button type="submit" class="buttton-calculate-cost">${calculate}</button>
            </div>
        </form>
        <c:if test="${orderCoast == null}">
            <div class="workout-counter">
                0 BYN
            </div>
        </c:if>
        <c:if test="${orderCoast != null}">
            <div class="workout-counter">
                    ${orderCoast} BYN
            </div>
            <form action="controller?command=createOrder" method="post">
                <div class="position-pay-button">
                    <button class="buttton-calculate-cost" type="submit">${pay}</button>
                </div>
            </form>
            <c:if test="${notEnoughMoney == true}">
                <div>
                        ${not_enough_money}
                </div>
            </c:if>
            <c:if test="${notEnoughMoney == false and trainerNotFound == false and hasOrder == false}">
                <div>
                        ${thank}
                </div>
            </c:if>
            <c:if test="${trainerNotFound == true}">
                <div>
                        ${not_found_trainer}
                </div>
            </c:if>
            <c:if test="${hasOrder == true}">
                <div>
                    You have order!
                </div>
            </c:if>

        </c:if>
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