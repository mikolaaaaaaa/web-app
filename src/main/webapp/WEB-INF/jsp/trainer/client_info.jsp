<%@ page isELIgnored="false" pageEncoding="UTF-8" language="java" %>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${sessionScope.locale == null}">
    <c:set var="locale" value="ru_RU" scope="session"/>
</c:if>


<c:if test="${program.hasDiet == true}">
    <c:set var="hasDiet" value="&#9989"/>
</c:if>
<c:if test="${program.hasDiet == false}">
    <c:set var="hasDiet" value="&#10060"/>
</c:if>

<%session.setAttribute("currentPage", "client_info.jsp");%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="pagecontent"/>

<fmt:message key="client.menu.order" var="order"/>
<fmt:message key="client.menu.myprogram" var="myprogram"/>
<fmt:message key="client.menu.history" var="history"/>
<fmt:message key="client.program.header" var="customHeader"/>
<fmt:message key="client.program.start" var="start"/>
<fmt:message key="client.program.end" var="end"/>
<fmt:message key="client.program.diet" var="diet"/>
<fmt:message key="client.program.exercises" var="exercises"/>
<fmt:message key="client.program.rescind" var="rescind"/>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <script src="https://kit.fontawesome.com/7d33ba923d.js" crossorigin="anonymous"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>program</title>
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
        <p class="information-about-program">
            Client information
        </p>

        <div class="list-information">
            <div class="list">
                <p>NAME</p>
                <p>${client.name}</p>
            </div>
            <div class="list">
                <p>SURNAME</p>
                <p>${client.surname}</p>
            </div>

            <div class="list">
                <p>TYPE</p>
                <p>${client.type}</p>
            </div>
            <div class="list">
                <p>GENDER</p>
                <p>${client.gender}</p>
            </div>
            <div class="list">
                <p>STATE</p>
                <p>${client.state}</p>
            </div>


                <c:choose>
                    <c:when test="${sessionScope.clientState == 'ACTIVE' || sessionScope.clientState == null}">
                        <form method="POST" action="controller?command=blockClient&clientId=${client.id}">
                            <div class="position-button-submit-list">
                                <button class="button-submit-list">BLOCK</button>
                            </div>
                        </form>
                    </c:when>
                    <c:when test="${sessionScope.clientState == 'BLOCKED'}">
                        <form method="POST" action="controller?command=unblockClient&clientId=${client.id}">
                            <div class="position-button-submit-list">
                                <button class="button-submit-list">UNBLOCK</button>
                            </div>
                        </form>
                    </c:when>
                </c:choose>
                <c:choose>
                    <c:when test="${sessionScope.clientState == 'DELETED' || sessionScope.clientState == null}">
                        <form method="POST" action="controller?command=restoreClient&clientId=${client.id}">
                            <div class="position-button-submit-list">
                                <button class="button-submit-list">RESTORE</button>
                            </div>
                        </form>
                    </c:when>
                    <c:when test="${sessionScope.clientState != 'DELETED'}">
                        <form method="POST" action="controller?command=deleteClient&clientId=${client.id}">
                            <div class="position-button-submit-list">
                                <button class="button-submit-list">DELETE</button>
                            </div>
                        </form>
                    </c:when>
                </c:choose>
            </div>
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