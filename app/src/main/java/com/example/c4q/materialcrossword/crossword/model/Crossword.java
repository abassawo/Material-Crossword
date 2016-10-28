package com.example.c4q.materialcrossword.crossword.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by C4Q on 10/21/16.
 */

public class Crossword implements Serializable{

    private Size size;
    private Answers answers;
    private String date, author, editor;
    private Clues clues;
    private List<String> grid = new ArrayList<>();
    private List<Integer> gridnums = new ArrayList<>();


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public Clues getClues() {
        return clues;
    }

    public void setClues(Clues clues) {
        this.clues = clues;
    }

    public Answers getAnswers() {
        return answers;
    }

    public void setAnswers(Answers answers) {
        this.answers = answers;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }


    public List<String> getGrid() {
        return grid;
    }

    public void setGrid(List<String> grid) {
        this.grid = grid;
    }

    public List<Integer> getGridNums() {
        return gridnums;
    }

    public void setGridNums(List<Integer> gridNums) {
        this.gridnums = gridNums;
    }

    @Override
    public String toString() {
        return clues.toString() + date + author;
    }
}

