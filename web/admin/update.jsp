<%-- 
    Document   : update
    Created on : 21.03.2017, 10:43:53
    Author     : IOAdmin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Обновить | IO Admin Panel</title>
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
                    <li><a href="#">Пользователь</a></li>
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
                    <div class="search-music">
                        <form>
                            <input type="search" class="search-text" name="search" placeholder="Поиск...">
                            <select class="select-box">
                                <option>По названию</option>
                                <option>По автору</option>
                            </select>
                            <input type="submit" class="button" name="submit" value="Поиск">
                        </form>
                        <div class="clear"></div>
                    </div>
                    <div class="new-musics">
                        <h2>Музыка:</h2>
                        <a href="update" class="link-update">Редактировать</a>
                        <div class="music-wrap">
                            <ul>
                                <li><a href="#">1</a></li>
                                <li><a href="#">2</a></li>
                                <li><a href="#">3</a></li>
                                <li><a href="#">4</a></li>
                                <li><a href="#">5</a></li>
                            </ul>
                            <form action="GET">
                                <input type="radio" name="radio-update">
                            </form>
                        </div>
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
