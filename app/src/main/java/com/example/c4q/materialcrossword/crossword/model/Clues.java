package com.example.c4q.materialcrossword.crossword.model;

/**
 * Created by C4Q on 10/21/16.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Clues {

    private List<String> across = new ArrayList<String>();
    private List<String> down = new ArrayList<String>();
    private List<String> grid = new ArrayList<String>();


    public List<String> getAcross() {
        return across;
    }

    public void setAcross(List<String> across) {
        this.across = across;
    }


    public List<String> getDown() {
        return down;
    }


    public void setDown(List<String> down) {
        this.down = down;
    }


    public List<String> getGrid() {
        return grid;
    }


    public void setGrid(List<String> grid) {
        this.grid = grid;
    }

    @Override
    public String toString() {
        return " Across size : " + across.size() + "Down " + down +  "Grid " + grid;
    }
}