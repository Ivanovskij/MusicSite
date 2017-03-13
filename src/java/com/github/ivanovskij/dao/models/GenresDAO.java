/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.ivanovskij.dao.models;

import com.github.ivanovskij.beans.Genre;
import com.github.ivanovskij.dao.ConnectionDAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author IOAdmin
 */
public class GenresDAO {
    
    private final List<Genre> genreList = new ArrayList<>();

    public GenresDAO() {
    }
    
    private List<Genre> getAllGenres() {
        try {
            Connection conn = ConnectionDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from shmusic.genre order by name");
            while (rs.next()) {
                Genre genre = new Genre(rs.getLong("idGenre"), rs.getString("name"));
                genreList.add(genre);
            }
        } catch (SQLException | NamingException ex) {
            System.out.println("ERROR: Genres->getAllGenres()");
        }
        return genreList;
    }
    
    public List<Genre> getGenreList() {
        if (!genreList.isEmpty()) {
            return genreList;
        }
        return getAllGenres();
    }
}
