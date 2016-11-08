package com.example.c4q.materialcrossword.crossword.presenter;

import com.example.c4q.materialcrossword.crossword.model.Cell;
import com.example.c4q.materialcrossword.crossword.model.Crossword;
import com.example.c4q.materialcrossword.crossword.model.Direction;

import java.util.Set;

/**
 * Created by C4Q on 11/3/16.
 */

public interface PuzzleInteractionable {

    public void onClick(Cell cell);

    public void reset();

    public void reveal();

    public void highlightNeighbors(Cell cell, Direction dir);



}
