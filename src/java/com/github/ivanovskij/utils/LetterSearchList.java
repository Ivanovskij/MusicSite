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

    private static final Character[] lettersRus = new Character[29];
    private static final Character[] lettersEng = new Character[26];

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

        return lettersRus;
    }
    
    public Character[] getEngLetters() {
        lettersEng[0] = 'A';
        lettersEng[1] = 'B';
        lettersEng[2] = 'C';
        lettersEng[3] = 'D';
        lettersEng[4] = 'E';
        lettersEng[5] = 'F';
        lettersEng[6] = 'G';
        lettersEng[7] = 'H';
        lettersEng[8] = 'I';
        lettersEng[9] = 'J';
        lettersEng[10] = 'K';
        lettersEng[11] = 'L';
        lettersEng[12] = 'M';
        lettersEng[13] = 'N';
        lettersEng[14] = 'O';
        lettersEng[15] = 'P';
        lettersEng[16] = 'Q';
        lettersEng[17] = 'R';
        lettersEng[18] = 'S';
        lettersEng[19] = 'T';
        lettersEng[20] = 'U';
        lettersEng[21] = 'V';
        lettersEng[22] = 'W';
        lettersEng[23] = 'X';
        lettersEng[24] = 'Y';
        lettersEng[25] = 'Z';
        
        return lettersEng;
    }
}
