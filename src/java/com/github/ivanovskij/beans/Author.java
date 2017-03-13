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
public class Author {
    
    private long idAuthor;
    private String fio;
    private String groups;

    public Author() {
    }

    public Author(long idAuthor, String fio, String groups) {
        this.idAuthor = idAuthor;
        this.fio = fio;
        this.groups = groups;
    }

    public long getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(long idAuthor) {
        this.idAuthor = idAuthor;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getIdGroups() {
        return groups;
    }

    public void setIdGroups(String groups) {
        this.groups = groups;
    }
}
