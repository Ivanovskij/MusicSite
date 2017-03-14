/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.ivanovskij.beans;

import java.io.Serializable;

/**
 *
 * @author IOAdmin
 */
public class Music implements Serializable {
    
    private long idMusic;
    private String name;
    private String albums;
    private String genre;

    public Music() {
    }

    public long getIdMusic() {
        return idMusic;
    }

    public void setIdMusic(long idMusic) {
        this.idMusic = idMusic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlbums() {
        return albums;
    }

    public void setAlbums(String albums) {
        this.albums = albums;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Music{" + "idMusic=" + idMusic + ", name=" + name + ", albums=" + albums + ", genre=" + genre + '}';
    }
}
