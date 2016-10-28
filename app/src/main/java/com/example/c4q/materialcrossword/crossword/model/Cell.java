package com.example.c4q.materialcrossword.crossword.model;

import java.util.List;

/**
 * Created by C4Q on 10/24/16.
 */
public class Cell {

    public int getNumber() {
        return number;
    }

    public String getLetter() {
        return letter;
    }

    public boolean isSelected() {
        return selected;
    }

    private int number;
    private String letter;
    private boolean selected;

    private String guess;

    public Cell(String letter, int number){
        this.letter = letter;
        this.number = number;
    }

    public static Cell[] makeCells(List<String> letters, List<Integer> numbers) {
        if(letters.size() != numbers.size()){
            try {
                throw new Exception("ArrayList sized must be the same");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Cell[] cells = new Cell[letters.size()];
        int min = Math.min(numbers.size(), letters.size());
        for (int i = 0; i < min; i++) {
            String letter = letters.get(i);
            int number = numbers.get(i);
            cells[i] = new Cell(letter, number);
        }
        return cells;
    }

    public void toggleSelect() {
        selected = !selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }


}
