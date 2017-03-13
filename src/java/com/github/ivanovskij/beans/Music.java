/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.ivanovskij.beans;

/**
 *
 * @author IOAdmin
 */
public class Music {
    
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
}
