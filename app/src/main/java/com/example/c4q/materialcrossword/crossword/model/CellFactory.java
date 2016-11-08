package com.example.c4q.materialcrossword.crossword.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by C4Q on 11/8/16.
 */

public class CellFactory {

    public static List<Cell> makeCells(List<String> letters, List<Integer> numbers, Clues clues) {
        if (letters.size() != numbers.size()) {
            try {
                throw new Exception("ArrayList sized must be the same");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        List<Cell> cells = new ArrayList<>();
        int min = Math.min(numbers.size(), letters.size());
        for (int i = 0; i < min; i++) {
            String letter = letters.get(i);
            int number = numbers.get(i);
            Cell cell = new Cell(letter, number);
            cell.positioninCW = i;
            Log.d("Cell", letter + cell.positioninCW);
            cells.add(i, cell);
        }
        return cells;
    }
}
