package com.example.c4q.materialcrossword.crossword.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by C4Q on 10/24/16.
 */
public class Cell {

    public AcrossPlaceInWord acrossPlaceInEnum;
    public DownPlaceInWord downPlaceInEnum;

    public boolean reveal = false;

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
    public int positioninCW;
    private String letter;
    private boolean selected;

    private String acrossAnswer;
    private String downAnswer;


    private String guess;

    public Cell() {
        //default constructor
    }

    public Cell(String letter, int number) {
        this.letter = letter;
        this.number = number;
    }



    public void toggleSelect() {
        selected = !selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }


    public void setAcrossAnswer(String acrossAnswer) {
        this.acrossAnswer = acrossAnswer;
    }

    public void setDownAnser(String downAnswer) {
        this.downAnswer = downAnswer;
    }


    public String getAcrossAnswer() {
        return acrossAnswer;
    }

    public boolean isActiveCell() {
        return !letter.equals(".");
    }

    public boolean isHintNumbered(){
        return number != 0;
    }

    public int getPositionInCW() {
        return positioninCW;
    }

    public enum AcrossPlaceInWord{
        START, MID, END, NIL
    }

    public enum DownPlaceInWord{
        START, MID, END, NIL
    }
}
