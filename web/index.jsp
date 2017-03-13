<%-- 
    Document   : index
    Created on : 12.03.2017, 12:58:39
    Author     : IOAdmin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="genres" scope="application" class="com.github.ivanovskij.dao.models.GenresDAO"/>
<jsp:useBean id="musics" scope="request" class="com.github.ivanovskij.dao.models.MusicsDAO"/>
<jsp:useBean id="albums" scope="request" class="com.github.ivanovskij.dao.models.AlbumsDAO"/>
<jsp:useBean id="searchListBox" scope="application" class="com.github.ivanovskij.controllers.SearchListboxController"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="css/main.css">
    </head>
    <body>
        <div class="top-panel">
            <div class="top-panel-wrap">
                <div class="lang">
                    <img src="img/rus.png" alt="rus" width="16" height="13">
                    <img src="img/usa.png" alt="eng" width="16" height="13">
                </div>
                <div class="soc-icon">
                    <a href="#"><img src="img/vk.jpg" alt="vk" width="16" height="16"></a>
                    <a href="#"><img src="img/facebook.jpg" alt="facebook" width="16" height="16"></a>
                </div>
                <!--<div class="user">
                        <a href="userProfile" class="user-name">
                                <p>User</p>
                        </a>
                        <a href="logout" class="logout">Выход</a>
                </div>
                -->
                <div class="auth">
                    <form action="" method="POST">
                        <input type="text" class="userlogin" name="userlogin" placeholder="логин">
                        <input type="password" class="userpass" name="userpass" placeholder="пароль">
                        <input type="submit" class="btn-login" name="submit" value="Войти">
                    </form>
                </div>
            </div>
            <div class="clear"></div>
        </div>

        <div id="wrap">
            <header>
                <!-- logotype -->
            </header>

            <div class="search">
                <form>
                    <input type="search" class="search-text" name="search" placeholder="Поиск музыки...">
                    <select class="select-box">
                        <c:forEach var="searchLb" items="${searchListBox.getSearchListbox()}">
                            <option><c:out value="${searchLb}"/></option>
                        </c:forEach>
                    </select>
                    <input type="submit" class="button" name="submit" value="Поиск">
                </form>
                <div class="clear"></div>
            </div>

            <div class="popularity">
                <ul>
                    <li><a href="popular">Популярные</a></li>
                    <li><a href="rating">Рейтинг</a></li>
                </ul>
            </div>

            <content>
                <div class="right-menu">
                    <h2 class="search-by-genres">Поиск по жанрам:</h2>
                    <ul>
                        <c:forEach var="g" items="${genres.getGenreList()}">
                            <li><a href="musics?genre_id=${g.idGenre}"><c:out value="${g.name}"/></a></li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="left-content">
                    <div class="new-musics">
                        <h2>Новая музыка:</h2>
                        <ul>
                            <c:forEach var="m" items="${musics.getAllMusics()}">
                                <li><a href="musics?music_id=${m.idMusic}"><c:out value="${m.name}"/></a></li>
                            </c:forEach>
                        </ul>
                    </div>
                    <div class="new-albums">
                        <h2>Новые альбомы:</h2>
                        <ul>
                            <c:forEach var="alb" items="${albums.getAllAlbums()}">
                                <li>
                                    <img src="img/prev.jpg" alt="prev" width="100" height="147">
                                    <p>
                                        <strong>Имя:</strong>
                                        <c:out value="${alb.name}"/>
                                    </p>
                                    <p>
                                        <strong>Описание:</strong>
                                        <c:out value="${alb.descr}"/>
                                    </p>
                                </li> 
                            </c:forEach>
                        </ul>
                        <div class="clear"></div>
                    </div>
                </div>
                <div class="clear"></div>
            </content>
        </div> <!-- end wrap -->

        <footer>
            <div class="footer-wrap">
                <p>&copy 2017, Music Online!
            </div>
        </footer>
    </body>
</html>
