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
public class Albums {
    
    private long idAlbums;
    private String name;
    private String descr;
    private String groups;

    public Albums() {
    }

    public long getIdAlbums() {
        return idAlbums;
    }

    public void setIdAlbums(long idAlbums) {
        this.idAlbums = idAlbums;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }
}
