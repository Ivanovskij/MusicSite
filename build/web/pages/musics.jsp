<%-- 
    Document   : musics
    Created on : 12.03.2017, 12:58:39
    Author     : IOAdmin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="../WEB-INF/jspf/Search.jspf" %>
<%@include file="../WEB-INF/jspf/LetterSearch.jspf" %>
<%@include file="../WEB-INF/jspf/Popularity.jspf" %>

<jsp:useBean id="genres" scope="application" class="com.github.ivanovskij.dao.models.GenresDAO"/>
<jsp:useBean id="musics" scope="request" class="com.github.ivanovskij.dao.models.MusicsDAO"/>
<jsp:useBean id="albums" scope="request" class="com.github.ivanovskij.dao.models.AlbumsDAO"/>

<content>
    <div class="right-menu">
        <h2 class="search-by-genres">Поиск по жанрам:</h2>
        <ul>
            <c:forEach var="g" items="${genres.getGenreList()}">
                <li><a href="${pageContext.request.contextPath}/MusicsController?genre_id=${g.idGenre}"><c:out value="${g.name}"/></a></li>
                </c:forEach>
        </ul>
    </div>
    <div class="left-content">
        <div class="new-musics">
            <h2>Новая музыка:</h2>
            <ul>
                <c:forEach var="m" items="${listMusics}">
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
