<%-- 
    Document   : update
    Created on : 21.03.2017, 10:43:53
    Author     : IOAdmin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="allMusics" scope="session" class="com.github.ivanovskij.dao.models.MusicsDAO"/>
<jsp:useBean id="genres" scope="application" class="com.github.ivanovskij.dao.models.GenresDAO"/>
<jsp:useBean id="albums" scope="request" class="com.github.ivanovskij.dao.models.AlbumsDAO"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Обновить | IO Admin Panel</title>
        <link rel="stylesheet" type="text/css" href="../css/admin.css">
        <script type="text/javascript" src="../js/handler.js"></script>
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
                        <li><a href="#">Обновить</a></li>
                        <li><a href="delete.jsp">Удалить</a></li>
                    </ul>
                </div>
                <div class="right-content">
                    <div class="line-box"></div>
                    <div class="new-musics">
                        <h2>Редактирование:</h2>
                        <div class="music-wrap">
                            <ul>
                                <c:forEach var="m" items="${allMusics.selectAll()}">
                                    <li><a href="${pageContext.request.contextPath}/ActionMusicsController?getMusic=true&id=${m.idMusic}">${m.name}</a></li>
                                </c:forEach>    
                            </ul>
                        </div>

                        <!-- default: display: none -->
                        <div id="popupUpdate">
                            <div class="popup-update-wrap">
                                <form class="form-update" action="${pageContext.request.contextPath}/ActionMusicsController">
                                    <label for="idMusic" class="label">ID: </label>
                                    <input type="text" class="input" name="idMusic" value="${mUpdate.idMusic}" readonly/>
                                    <label for="name" class="label">Название: </label>
                                    <input type="text" class="input" name="name" value="${mUpdate.name}" required=""/>
                                    <label for="album" class="label">Альбом:</label>
                                    <select class="select-box" name="album" required>
                                        <c:forEach var="alb" items="${albums.selectAll()}">
                                            <option><c:out value="${alb.name}"/></option>
                                        </c:forEach>
                                    </select>
                                    <label for="genre" class="label">Жанр:</label>
                                    <select name="genre" class="select-box" required>
                                        <c:forEach var="g" items="${genres.selectAll()}">
                                            <option><c:out value="${g.name}"/></option>
                                        </c:forEach>
                                    </select>

                                    <a class="cancel-update" href="#close">Отмена</a>
                                    <input type="submit" class="button" name="update" value="Редактировать" required>
                                </form>
                            </div>
                        </div> 
                        <!-- end popupUpdate -->
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
