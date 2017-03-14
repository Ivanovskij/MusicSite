/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.ivanovskij.dao.models;

import com.github.ivanovskij.beans.Music;
import com.github.ivanovskij.dao.ConnectionDAO;
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
public class MusicsDAO {

    private final List<Music> musicList = new ArrayList<>();

    public MusicsDAO() {
    }

    private List<Music> getMusics(String query) {
        try {
            Connection conn = ConnectionDAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Music music = new Music();
                music.setIdMusic(rs.getLong("idMusic"));
                music.setName(rs.getString("name"));
                music.setGenre(rs.getString("genre"));
                music.setAlbums(rs.getString("albums"));
                musicList.add(music);
            }
        } catch (SQLException | NamingException ex) {
            System.out.println("ERROR: MusicsDAO->getAllMusics()\n" + ex);
        }
        return musicList;
    }

    public List<Music> getAllMusics() {
        return getMusics("select m.idMusic, m.name, alb.name as albums, g.name as genre "
                + "from music as m "
                + "inner join albums alb "
                + "on alb.idAlbums = m.Albums_idAlbums "
                + "inner join genre g "
                + "on g.idGenre = m.Genre_idGenre "
                + "order by m.name");
    }

    public List<Music> getMusicsByGenre(long genre_id) {
        if (genre_id == -1) {
            return getAllMusics();
        }
        return getMusics("select m.idMusic, m.name, alb.name as albums, g.name as genre from music as m, albums as alb, genre as g "
                + "where m.Albums_idAlbums = alb.idAlbums and "
                + "m.Genre_idGenre = g.idGenre and "
                + "m.Genre_idGenre = " + genre_id + " "
                + "order by m.name");
    }

    public List<Music> getMusicsByLetter(String letter) {
        if (letter == null || letter.equals("")) {
            return getAllMusics();
        }

        return getMusics("select m.idMusic, m.name, alb.name as albums, g.name as genre from music as m, albums as alb, genre as g "
                + "where m.Albums_idAlbums = alb.idAlbums and "
                + "m.Genre_idGenre = g.idGenre and "
                + "substr(m.name,1,1) = '" + letter + "' "
                + "order by m.name");
    }

    public List<Music> getMusicsBySearch(String search, SearchType sType) {
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

        return getMusics(generalQuery.toString());
    }

}
