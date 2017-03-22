<%-- 
    Document   : add
    Created on : 21.03.2017, 10:43:34
    Author     : IOAdmin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="genres" scope="application" class="com.github.ivanovskij.dao.models.GenresDAO"/>
<jsp:useBean id="albums" scope="request" class="com.github.ivanovskij.dao.models.AlbumsDAO"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Добавить | IO Admin Panel</title>
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
                        <li><a href="#">Добавить</a></li>
                        <li><a href="update.jsp">Обновить</a></li>
                        <li><a href="delete.jsp">Удалить</a></li>
                    </ul>
                </div>
                <div class="right-content">
                    <div class="line-box"></div>
                    <div class="add-text">
                        <h2>Добавление:</h2>
                    </div>
                    <div class="form-add-wrap">
                        <form action="${pageContext.request.contextPath}/ActionMusicsController" method="GET">
                            <label for="name" class="label">Название: </label>
                            <input type="text" class="input" name="name" required>
                            <label for="album" class="label">Альбом:</label>
                            <select class="select-box" name="album" required>
                                <c:forEach var="alb" items="${albums.getAllAlbums()}">
                                    <option><c:out value="${alb.name}"/></option>
                                </c:forEach>
                            </select>
                            <label for="genre" class="label">Жанр:</label>
                            <select name="genre" class="select-box" required>
                                <c:forEach var="g" items="${genres.getGenreList()}">
                                    <option><c:out value="${g.name}"/></option>
                                </c:forEach>
                            </select>
                            <input type="submit" class="button" name="add" value="Добавить">
                        </form>
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
