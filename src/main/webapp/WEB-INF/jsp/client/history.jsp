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

<c:if test="${sessionScope.locale == null}">
    <c:set var="locale" value="ru_RU" scope="session"/>
</c:if>

<% session.setAttribute("currentPage", "history.jsp");%>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="pagecontent"/>

<fmt:message key="client.menu.order" var="order"/>
<fmt:message key="client.menu.myprogram" var="myprogram"/>
<fmt:message key="client.menu.history" var="history"/>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>history</title>

    <link rel="stylesheet" href="static/styles/style.css">
</head>
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
            <a href="controller?command=order">
                <button type="submit" class="form-button">${order}</button>
            </a>
        </div>
        <div class="position-button-history">
            <a href="#">
                <button type="submit" class="form-button">${history}</button>
            </a>
        </div>
    </div>
    <div class="program-list">
        <div class="list-information">
            <c:forEach var="position" begin="0" end="${historyPrograms.size() - 1}" step="1">
                <a href="controller?command=showHistoryProgram&programId=${historyPrograms.get(position).id}" class="list-information-link">
                        <span class="list-information-date start-date">
                                ${historyPrograms.get(position).startDate}
                        </span>
                    <span class="separator">
                            &ndash;
                        </span>
                    <span class="list-information-date end-date">
                            ${historyPrograms.get(position).endDate}
                    </span>
                </a>
            </c:forEach>
        </div>
    </div>
</main>
</body>
</html>
