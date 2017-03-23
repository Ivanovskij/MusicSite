/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.ivanovskij.dao.models;

import com.github.ivanovskij.beans.Genre;
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
public class GenresDAO extends BeanDao {

    private final List<Genre> genreList = new ArrayList<>();

    public GenresDAO() {
    }

    @Override
    public Object selectById(long id) throws NoSuchEntityException, DaoBusinessException {
        return null;
        // NOP
    }

    @Override
    public long selectIdByName(String name) throws NoSuchEntityException, DaoBusinessException {
        return getIdByName(name);
    }

    @Override
    public List selectExecute(String query) throws DaoBusinessException {
        return null;
        // NOP
    }

    @Override
    public List selectAll() throws DaoBusinessException {
        return getGenreList();
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

    private long getIdByName(String name)
            throws NoSuchEntityException, DaoBusinessException {
        long id;
        try {
            Connection conn = ConnectionDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select idGenre from shmusic.genre where name='" + name + "'");
            while (rs.next()) {
                id = rs.getLong("idGenre");
                return id;
            }
        } catch (SQLException | NamingException ex) {
            throw new DaoBusinessException("ERROR: Genres->getIdByName()", ex);
        }
        throw new NoSuchEntityException("No genre entity by name=" + name);
    }

    private List<Genre> getGenreList() {
        if (!genreList.isEmpty()) {
            return genreList;
        }
        return getAllGenres();
    }
}
