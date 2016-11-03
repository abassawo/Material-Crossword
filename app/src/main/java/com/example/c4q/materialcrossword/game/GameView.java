package com.example.c4q.materialcrossword.game;

import com.example.c4q.materialcrossword.crossword.model.Cell;

/**
 * Created by C4Q on 10/28/16.
 */

public interface GameView {

    public void onCellSelected(Cell cell);

    public void onAcrossSelect(Cell anyCell);

    public void onDownSelect(Cell anyCell);
}
