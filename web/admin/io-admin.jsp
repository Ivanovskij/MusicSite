<%-- 
    Document   : io-admin
    Created on : 18.03.2017, 13:06:24
    Author     : IOAdmin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="newMusics" scope="session" class="com.github.ivanovskij.dao.models.MusicsDAO"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IO Admin Panel</title>
        <link rel="stylesheet" type="text/css" href="../css/admin.css">
        <!--[if lt IE 9]>
        <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
    </head>
    <body>
        <div class="top-panel">
            <div class="top-panel-wrap">
                <ul>
                    <li><a href="io-admin.jsp">Рабочий стол</a></li>
                    <li><a href="${pageContext.request.contextPath}/UserAuthController?logout=true">Выход</a></li>
                </ul>
            </div>
            <div class="clear"></div>
        </div>

        <div id="wrap">
            <content>
                <div class="left-menu">
                    <h2>Действия:</h2>
                    <ul>
                        <li><a href="add.jsp">Добавить</a></li>
                        <li><a href="update.jsp">Обновить</a></li>
                        <li><a href="delete.jsp">Удалить</a></li>
                    </ul>
                </div>
                <div class="right-content">
                    <div class="line-box"></div>
                    <div class="info-wrap">
                        <p class="info-text">Количество музыки: ${countAllMusics}</p>
                        <p class="info-text">Количество альбомов: ${countAllAlbums}</p>
                    </div>
                    <div class="new-musics">
                        <h2>Новая музыка:</h2>
                        <ul>
                            <c:forEach var="m" items="${newMusics.getMusicsByLimit(0, 15)}">
                                <li><a href="musics?music_id=${m.idMusic}"><c:out value="${m.name}"/></a></li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <div class="clear"></div>
            </content>
        </div>

        <footer>
            <div class="footer-wrap">
                <p>&copy 2017, Онлайн музыка!
            </div>
        </footer>
    </body>
</html>
