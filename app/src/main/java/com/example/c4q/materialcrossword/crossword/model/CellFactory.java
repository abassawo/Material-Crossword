package com.example.c4q.materialcrossword.crossword.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.c4q.materialcrossword.crossword.model.Cell.AcrossPlaceInWord.END;
import static com.example.c4q.materialcrossword.crossword.model.Cell.AcrossPlaceInWord.MID;
import static com.example.c4q.materialcrossword.crossword.model.Cell.AcrossPlaceInWord.NIL;
import static com.example.c4q.materialcrossword.crossword.model.Cell.AcrossPlaceInWord.START;

/**
 * Created by C4Q on 11/8/16.
 */

public class CellFactory {
    private static List<String> acrossAnswers;
    private static List<String> downAnswers;
    private static Clues clues;
    private static List<String> letters;
    private static List<Integer> numbers;
    private static Size size;

    private static Map<Cell, String> cellStringMap = new HashMap<>();

    public static List<Cell> makeCells(Crossword crossword){
        acrossAnswers = crossword.getAnswers().getAcross();
        downAnswers = crossword.getAnswers().getDown();
        clues = crossword.getClues();
        numbers = crossword.getGridNums();
        letters = crossword.getGrid();
        size = crossword.getSize();
        return makeCells(letters, numbers, clues);
    }


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
        StringBuilder sb = new StringBuilder();

        boolean startOfWord = true;

        boolean endOfWord = false;

        for (int i = 0; i < min; i++) {
            String letter = letters.get(i);
            int number = numbers.get(i);
            Cell cell = new Cell(letter, number);
            cell.positioninCW = i;
            Log.d("Cell", letter + cell.positioninCW);
            if(letter.equals(".")){
                cell.acrossPlaceInEnum = NIL;
                cell.downPlaceInEnum = Cell.DownPlaceInWord.NIL;
                startOfWord = true;
            }
            else{
                sb.append(letter);
                cell.acrossPlaceInEnum = MID;
                startOfWord = false;
            }
            if(startOfWord) {
                cell.acrossPlaceInEnum = START;
            }
            cells.add(i, cell);
        }
        return cells;
    }
}
