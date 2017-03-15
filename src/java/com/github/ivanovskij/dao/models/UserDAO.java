/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.ivanovskij.dao.models;

import com.github.ivanovskij.beans.User;
import com.github.ivanovskij.dao.ConnectionDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author IOAdmin
 */
public class UserDAO {

    public UserDAO() {
    }

    public User login(String username, String userpass) {
        try {
            Connection conn = ConnectionDAO.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select * from user where name=? and pass=?");

            pstmt.setString(1, username);
            pstmt.setString(2, userpass);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getLong("idUser"));
                user.setName(rs.getString("name"));
                user.setPass(rs.getString("pass"));
                user.setType(rs.getString("type"));
                user.setLang(rs.getString("lang"));
                return user;
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("ERROR: UserDAO->login()" + ex);
        }

        return null;
    }

    public boolean logout() {
        return false;
    }
}
