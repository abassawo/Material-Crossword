package com.example.c4q.materialcrossword;

/**
 * Created by C4Q on 10/27/16.
 */

import com.example.c4q.materialcrossword.crossword.model.Cell;

/**
 * Created by C4Q on 10/24/16.
 */

public class Mvp {

    public interface Model{

    }

    public interface View{

    }

    public interface Presenter{
        void showKeyboard(Cell cell, boolean goAcross);
    }
}
