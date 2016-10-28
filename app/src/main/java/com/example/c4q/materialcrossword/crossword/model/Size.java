package com.example.c4q.materialcrossword.crossword.model;


/**
 * Created by C4Q on 10/21/16.
 */
public class Size {

    private Integer cols;
    private Integer rows;

    public Size(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }


    public Integer getCols() {
        return cols;
    }

    public void setCols(Integer cols) {
        this.cols = cols;
    }


    public Integer getRows() {
        return rows;
    }


    public void setRows(Integer rows) {
        this.rows = rows;
    }



}
