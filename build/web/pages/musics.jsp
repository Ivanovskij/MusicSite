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

<content>
    <%@include file="../WEB-INF/jspf/RightMenu.jspf" %>
    
    <div class="left-content">
        <%@include file="../WEB-INF/jspf/newMusics.jspf" %>
        <%@include file="../WEB-INF/jspf/newAlbums.jspf" %>
        <div class="clear"></div>
    </div>
    <div class="clear"></div>
</content>
