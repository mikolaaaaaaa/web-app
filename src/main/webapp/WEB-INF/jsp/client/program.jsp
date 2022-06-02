<%@ page isELIgnored="false" pageEncoding="UTF-8" language="java" %>
<%--start redesign--%>

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

<%session.setAttribute("currentPage", "program.jsp");%>

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
            <a href="controller?command=showProgramPage">
                <button type="submit" class="form-button">${myprogram}</button>
            </a>
        </div>
        <div class="button-order-position">
            <a href="controller?command=order">
                <button type="submit" class="form-button">${order}</button>
            </a>
        </div>
        <div class="position-button-history">
            <a href="controller?command=history">
                <button type="submit" class="form-button">${history}</button>
            </a>
        </div>
    </div>
    <c:if test="${program != null}">
    <div class="program-list">
        <p class="information-about-program">
                ${customHeader}
        </p>

        <div class="list-information">
            <div class="list">
                <p>${start}</p>
                <p>${program.startDate}</p>
            </div>
            <div class="list">
                <p>${end}</p>
                <p>${program.endDate}</p>
            </div>

            <div class="list">
                <p>${diet}</p>
                <p>${hasDiet}</p>
            </div>
            <div class="list">
                <p>${exercises}</p>
                <ul>
                    <c:if test="${program.exercises.size() > 0}">
                        <c:forEach var="position" begin="0" end="${program.exercises.size() - 1}" step="1">
                            <li>${program.exercises.get(position).name}</li>
                        </c:forEach>
                    </c:if>
                </ul>
            </div>

            <c:if test="${program.programState.toString() == 'PROCESS'}">
                <form method="POST" action="controller?command=rescindProgram&programId=${program.id}">
                    <div class="position-button-submit-list">
                        <button class="button-submit-list">${rescind}</button>
                    </div>
                </form>
            </c:if>


            <div class="review">
                <form action="controller?command=addFeedback" method="post">
                    <div>
                        <fmt:requestEncoding value="UTF-8"/>
                        <textarea class="form-review" name="clientFeedback" placeholder="ОТЗЫВ:"></textarea>
                    </div>
                    <div class="position-button-submit-review">
                        <button type="submit" class="button-submit-review">Отправить отзыв</button>
                    </div>
                </form>
            </div>
        </div>
        </c:if>
        <c:if test="${program == null}">
        <p class="information-about-program">
            На данный момент у вас нет программы :(
        </p>
        </c:if>
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