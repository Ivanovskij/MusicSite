/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.ivanovskij.dao.models;

import com.github.ivanovskij.beans.Music;
import com.github.ivanovskij.dao.ConnectionDAO;
import com.github.ivanovskij.dao.exception.DaoBusinessException;
import com.github.ivanovskij.dao.exception.NoSuchEntityException;
import com.github.ivanovskij.enums.SearchType;
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
public class MusicsDAO extends BeanDao {

    private final List<Music> musicList = new ArrayList<>();

    public MusicsDAO() {
    }

    @Override
    public Object selectById(long id) 
            throws NoSuchEntityException, DaoBusinessException {
        List<Music> OneMusic = selectExecute("select m.idMusic, m.name, alb.name as albums, g.name as genre from music as m, albums as alb, genre as g "
                + "where m.Albums_idAlbums = alb.idAlbums and "
                + "m.Genre_idGenre = g.idGenre and "
                + "m.idMusic=" + id + "");

        if (OneMusic.get(0) == null) {
            throw new NoSuchEntityException("No music entity in list OneMusic");
        }

        return OneMusic.get(0);
    }

    @Override
    public List selectAll() throws DaoBusinessException {
        return getAllMusics();
    }

    @Override
    public List selectExecute(String query) throws DaoBusinessException {
        try {
            Connection conn = ConnectionDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            musicList.clear();
            while (rs.next()) {
                Music music = new Music();
                music.setIdMusic(rs.getLong("idMusic"));
                music.setName(rs.getString("name"));
                music.setGenre(rs.getString("genre"));
                music.setAlbums(rs.getString("albums"));
                musicList.add(music);
            }
        } catch (SQLException | NamingException ex) {
            throw new DaoBusinessException("ERROR: MusicsDAO->getMusics()\n" + ex);
        }
        return musicList;
    }

    @Override
    public long selectIdByName(String name) throws NoSuchEntityException {
        return 0;
        // NOP
    }

    private List<Music> getAllMusics() throws DaoBusinessException {
        return selectExecute("select m.idMusic, m.name, alb.name as albums, g.name as genre "
                + "from music as m "
                + "inner join albums alb "
                + "on alb.idAlbums = m.Albums_idAlbums "
                + "inner join genre g "
                + "on g.idGenre = m.Genre_idGenre "
                + "order by m.name");
    }

    public List<Music> getMusicsByLimit(int limitFrom, int limitTo) throws DaoBusinessException {
        return selectExecute("select m.idMusic, m.name, alb.name as albums, g.name as genre "
                + "from music as m "
                + "inner join albums alb "
                + "on alb.idAlbums = m.Albums_idAlbums "
                + "inner join genre g "
                + "on g.idGenre = m.Genre_idGenre "
                + "order by idMusic desc limit " + limitFrom + ", " + limitTo);
    }

    public List<Music> getMusicsByGenre(long genre_id) throws DaoBusinessException {
        if (genre_id == -1) {
            return getAllMusics();
        }
        return selectExecute("select m.idMusic, m.name, alb.name as albums, g.name as genre from music as m, albums as alb, genre as g "
                + "where m.Albums_idAlbums = alb.idAlbums and "
                + "m.Genre_idGenre = g.idGenre and "
                + "m.Genre_idGenre = " + genre_id + " "
                + "order by m.name");
    }

    public List<Music> getMusicsByLetter(String letter) throws DaoBusinessException {
        if (letter == null || letter.equals("")) {
            return getAllMusics();
        }

        return selectExecute("select m.idMusic, m.name, alb.name as albums, g.name as genre from music as m, albums as alb, genre as g "
                + "where m.Albums_idAlbums = alb.idAlbums and "
                + "m.Genre_idGenre = g.idGenre and "
                + "substr(m.name,1,1) = '" + letter + "' "
                + "order by m.name");
    }

    public List<Music> getMusicsBySearch(String search, SearchType sType) throws DaoBusinessException {
        String searchStr = search.toLowerCase();

        if (searchStr.equals("")) {
            return getAllMusics();
        }

        StringBuilder generalQuery = new StringBuilder();
        generalQuery.append("select m.idMusic, m.name, alb.name as albums, g.name as genre "
                + "from music as m "
                + "inner join albums alb "
                + "on alb.idAlbums = m.Albums_idAlbums "
                + "inner join genre g "
                + "on g.idGenre = m.Genre_idGenre ");

        if (sType == SearchType.MUSIC_NAME) {
            generalQuery.append(" where lower(m.name) like '%").append(searchStr.toLowerCase()).append("%' order by m.name");
        } else {
            generalQuery.append(" where lower(alb.name) like '%").append(searchStr.toLowerCase()).append("%' order by alb.name");
        }

        return selectExecute(generalQuery.toString());
    }
    
    public void delMusic(long id) throws DaoBusinessException {
        try {
            Connection conn = ConnectionDAO.getConnection();
            Statement stmt = conn.createStatement();
            int col_row = stmt.executeUpdate("DELETE FROM `shmusic`.`music` WHERE idMusic=" + id);

            if (col_row == 0) {
                throw new DaoBusinessException("Error del music entity with id=" + id);
            }
        } catch (SQLException | NamingException ex) {
            System.out.println("ERROR: MusicsDAO->delMusic()\n" + ex);
        }
    }

    public void addMusic(String name, long idAlbum, long idGenre) throws DaoBusinessException {
        try {
            Connection conn = ConnectionDAO.getConnection();
            Statement stmt = conn.createStatement();
            int col_row = stmt.executeUpdate("INSERT INTO `shmusic`.`music` (`name`, `Albums_idAlbums`, `Genre_idGenre`) VALUES "
                    + "('" + name + "', "
                    + "'" + idAlbum + "', "
                    + "'" + idGenre + "');");

            if (col_row == 0) {
                throw new DaoBusinessException("Not add music entity with data = {name=" + name + ", idAlbum=" + idAlbum + ", idGenre=" + idGenre + "}");
            }
        } catch (SQLException | NamingException ex) {
            System.out.println("ERROR: MusicsDAO->addMusic()\n" + ex);
        }
    }

    public void updateMusic(long idMusic, String name, long idAlbum, long idGenre) throws DaoBusinessException {
        try {
            Connection conn = ConnectionDAO.getConnection();
            Statement stmt = conn.createStatement();
            int col_row = stmt.executeUpdate("UPDATE `shmusic`.`music` SET `name`='" + name + "', "
                    + "`Albums_idAlbums`=" + idAlbum + ", "
                    + "`Genre_idGenre`=" + idGenre + " "
                    + "WHERE idMusic=" + idMusic);

            if (col_row == 0) {
                throw new DaoBusinessException("Not update music entity with data = {name=" + name + ", idAlbum=" + idAlbum + ", idGenre=" + idGenre + "}");
            }
        } catch (SQLException | NamingException ex) {
            System.out.println("ERROR: MusicsDAO->updateMusic()\n" + ex);
        }
    }

    public int getCountAllMusics() {
        int col_row = 0;
        try {
            Connection conn = ConnectionDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select idMusic from music");
            rs.last();
            col_row = rs.getRow();
        } catch (SQLException | NamingException ex) {
            System.out.println("ERROR: MusicsDAO->getCountAllMusics()\n" + ex);
        }
        return col_row;
    }
}
