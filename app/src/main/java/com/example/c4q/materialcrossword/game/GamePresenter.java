package com.example.c4q.materialcrossword.game;

import com.example.c4q.materialcrossword.Puzzle;
import com.example.c4q.materialcrossword.crossword.model.Cell;
import com.example.c4q.materialcrossword.crossword.model.Crossword;

/**
 * Created by C4Q on 10/28/16.
 */

public interface GamePresenter {

    void startGame(Crossword crossword);
    void saveGame();
    void resetGame();
    void revealLetter(Cell cell);
    void revealWord(Cell...cells);
}
