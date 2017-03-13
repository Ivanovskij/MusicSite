/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.ivanovskij.dao.models;

import com.github.ivanovskij.beans.Albums;
import com.github.ivanovskij.dao.ConnectionDAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author IOAdmin
 */
public class AlbumsDAO {
    
    private final List<Albums> albumsList = new ArrayList<>();
    
    private List<Albums> getAlbums(String query) {
        try {
            Connection conn = ConnectionDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Albums albums = new Albums();
                albums.setIdAlbums(rs.getLong("idAlbums"));
                albums.setName(rs.getString("name"));
                albums.setDescr(rs.getString("descr"));
                albums.setGroups(rs.getString("groups"));
                albumsList.add(albums);
            }
        } catch (SQLException | NamingException ex) {
            System.out.println("ERROR: AlbumsDAO->getAllAlbums()");
        }
        return albumsList;
    }
    
    public List<Albums> getAllAlbums() {
        return getAlbums("select alb.idAlbums, alb.name, alb.descr, gr.name as groups "
                           + "from albums as alb "
                           + "inner join groups as gr "
                           + "on alb.Group_idGroup = gr.idGroups "
                           + "order by alb.name");
    }
}
