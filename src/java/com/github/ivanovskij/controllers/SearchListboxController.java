/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.ivanovskij.controllers;

import com.github.ivanovskij.enums.SearchType;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

/**
 *
 * @author IOAdmin
 */
public class SearchListboxController {
    
    private static final Map<String, SearchType> searchListbox = new HashMap<>();

    public SearchListboxController() {
        ResourceBundle bundle = ResourceBundle.getBundle(
                "com.github.ivanovskij.nls.messages");
        searchListbox.clear();
        searchListbox.put(bundle.getString("search_music_name"), SearchType.MUSIC_NAME);
        searchListbox.put(bundle.getString("search_albums_name"), SearchType.ALBUMS);
    }

    public static Set<String> getSearchListbox() {
        return searchListbox.keySet();
    }
}
