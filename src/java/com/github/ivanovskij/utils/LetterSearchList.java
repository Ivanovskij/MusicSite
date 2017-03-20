/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.ivanovskij.utils;

/**
 *
 * @author IOAdmin
 */
public class LetterSearchList {

    private static final Character[] lettersRus = new Character[30];

    public Character[] getRusLetters() {
        lettersRus[0] = 'А';
        lettersRus[1] = 'Б';
        lettersRus[2] = 'В';
        lettersRus[3] = 'Г';
        lettersRus[4] = 'Д';
        lettersRus[5] = 'Е';
        lettersRus[6] = 'Ё';
        lettersRus[7] = 'Ж';
        lettersRus[8] = 'З';
        lettersRus[9] = 'И';
        lettersRus[10] = 'К';
        lettersRus[11] = 'Л';
        lettersRus[12] = 'М';
        lettersRus[13] = 'Н';
        lettersRus[14] = 'О';
        lettersRus[15] = 'П';
        lettersRus[16] = 'Р';
        lettersRus[17] = 'С';
        lettersRus[18] = 'Т';
        lettersRus[19] = 'У';
        lettersRus[20] = 'Ф';
        lettersRus[21] = 'Х';
        lettersRus[22] = 'Ц';
        lettersRus[23] = 'Ч';
        lettersRus[24] = 'Ш';
        lettersRus[25] = 'Щ';
        lettersRus[26] = 'Э';
        lettersRus[27] = 'Ю';
        lettersRus[28] = 'Я';
        lettersRus[29] = '?';

        return lettersRus;
    }
}
