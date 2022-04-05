
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

<%session.setAttribute("currentPage","program.jsp");%>

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
<fmt:message key="client.program.rescind" var = "rescind"/>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>program</title>
    <link rel="stylesheet" href="static/styles/style.css"></head>
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
            <a href="controller?command=showProgramPage"><button type="submit" class="form-button">${myprogram}</button></a>
        </div>
        <div class="button-order-position">
            <a href="controller?command=order"><button type="submit" class="form-button">${order}</button></a>
        </div>
        <div class="position-button-history">
            <a href="controller?command=history"> <button type="submit" class="form-button">${history}</button></a>
        </div>
    </div>
    <c:if test="${program != null}">
    <div class="program-list">
        <p class="information-about-program">
            ${customHeader}
        </p>
        <div class="positiont-list">
            <div class="list-information">
                <div >${start}</div>
                <p class="list">${end}</p>
                <p class="list">${diet}</p>
                <p class="list">${exercises}</p>
                <c:if test="${program.programState} == FINISHED">
                    <div class="position-button-submit-list">
                        <button class="button-submit-list">${rescind}</button>
                    </div>
                </c:if>
            </div>
            <div class="data-list">
                <p class="list">${program.startDate}</p>
                <p class="list">${program.endDate}</p>
                <p class="list">${hasDiet}</p>
                <ul>
                <c:forEach var="position" begin="0" end="${program.exercises.size() - 1}" step="1">
                    <li>${program.exercises.get(position).name}</li>
                </c:forEach>
                </ul>
            </div>
        </div>
        <div></div>
    </div>
    </c:if>
    <c:if test="${program == null}">
        <p class="information-about-program">
               На данный момент у вас нет программы :(
        </p>
    </c:if>
</main>
</body>
</html>