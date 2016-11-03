package com.example.c4q.materialcrossword.crossword.presenter;

import com.example.c4q.materialcrossword.crossword.model.Cell;
import com.example.c4q.materialcrossword.crossword.model.Crossword;

/**
 * Created by C4Q on 11/3/16.
 */

public interface PuzzleInteractionable {

    public void onClick(Cell cell);

    public void reset();

    public void reveal();

    public void highlightAcross(Cell cell);

    public void highlightDown(Cell cell);

}