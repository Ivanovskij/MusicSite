/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.ivanovskij.dao.models;

import com.github.ivanovskij.beans.Albums;
import com.github.ivanovskij.dao.ConnectionDAO;
import com.github.ivanovskij.dao.exception.DaoBusinessException;
import com.github.ivanovskij.dao.exception.NoSuchEntityException;
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
public class AlbumsDAO extends BeanDao {
    
    private final List<Albums> albumsList = new ArrayList<>();

    @Override
    public Object selectById(long id) throws NoSuchEntityException, DaoBusinessException {
        // NOP
        return null;
    }
    
    @Override
    public long selectIdByName(String name) throws NoSuchEntityException, DaoBusinessException {
        return getIdByName(name);
    }

    @Override
    public List selectAll() throws DaoBusinessException {
        return getAllAlbums();
    }

    @Override
    public List selectExecute(String query) throws DaoBusinessException {
        try {
            Connection conn = ConnectionDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            albumsList.clear();
            while (rs.next()) {
                Albums albums = new Albums();
                albums.setIdAlbums(rs.getLong("idAlbums"));
                albums.setName(rs.getString("name"));
                albums.setDescr(rs.getString("descr"));
                albums.setGroups(rs.getString("groups"));
                albumsList.add(albums);
            }     
        } catch (SQLException | NamingException ex) {
            throw new DaoBusinessException("Error: AlbumsDAO->selectExecute()", ex);
        }
        return albumsList;
    }
    
    private long getIdByName(String name) throws NoSuchEntityException, DaoBusinessException {
        long id;
        try {
            Connection conn = ConnectionDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select idAlbums from shmusic.albums where name='" + name + "'");
            while (rs.next()) {
                id = rs.getLong("idAlbums");
                return id;
            }
        } catch (SQLException | NamingException ex) {
            throw new DaoBusinessException("ERROR: AlbumsDAO->getIdByName()", ex);
        }
        throw new NoSuchEntityException("No album entity by name=" + name);
    }
    
    private List<Albums> getAllAlbums() throws DaoBusinessException {
        return selectExecute("select alb.idAlbums, alb.name, alb.descr, gr.name as groups "
                           + "from albums as alb "
                           + "inner join groups as gr "
                           + "on alb.Group_idGroup = gr.idGroups "
                           + "order by alb.name");
    }
    
    public int getCountAllAlbums() {
        int col_row = 0;
        try {
            Connection conn = ConnectionDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select idAlbums from albums");
            rs.last();
            col_row = rs.getRow();
        } catch (SQLException | NamingException ex) {
            System.out.println("ERROR: AlbumsDAO->getCountAllAlbums()\n" + ex);
        }
        return col_row;
    }
}
