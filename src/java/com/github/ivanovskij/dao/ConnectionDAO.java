/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.ivanovskij.dao;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author IOAdmin
 */
public class ConnectionDAO {

    private static Context context;
    private static DataSource ds;
    private static Connection conn;

    public static Connection getConnection() 
            throws NamingException, SQLException {
        context = new InitialContext();
        ds = (DataSource) context.lookup("java:comp/env/jdbc/shmusic");
        return (conn == null) ? conn = ds.getConnection() : conn;
    }
    
}
