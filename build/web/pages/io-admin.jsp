<%-- 
    Document   : io-admin
    Created on : 18.03.2017, 13:06:24
    Author     : IOAdmin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                    <a href="#"><li>Рабочий стол</li></a>
                    <a href="#"><li>Пользователь</li></a>
                    <a href="#"><li>Выход</li></a>
                </ul>
            </div>
        </div>
        
        <div id="wrap">
            <content>
                <div class="left-menu">
                    <h2>Действия:</h2>
                    <ul>
                        <a href="#"><li>Добавить</li></a>
                        <a href="#"><li>Обновить</li></a>
                        <a href="#"><li>Удалить</li></a>
                    </ul>
                </div>
                <div class="right-content">
                    
                </div>
            </content>
        </div>
    </body>
</html>
