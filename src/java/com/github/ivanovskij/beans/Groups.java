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
public class Groups {
    
    private long idGroups;
    private String name;

    public Groups() {
    }

    public Groups(long idGroups, String name) {
        this.idGroups = idGroups;
        this.name = name;
    }
    
    public long getIdGroups() {
        return idGroups;
    }

    public void setIdGroups(long idGroups) {
        this.idGroups = idGroups;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }  
    
}
