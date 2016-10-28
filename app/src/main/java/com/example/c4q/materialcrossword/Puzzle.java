package com.example.c4q.materialcrossword;

/**
 * Created by C4Q on 10/26/16.
 */

public class Puzzle {

    private int rawRes;
    private String name;

    public Puzzle(String name, int rawRes) {
        this.rawRes = rawRes;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getRawRes() {
        return rawRes;
    }

    @Override
    public String toString() {
        return name;
    }
}
