package com.example.c4q.materialcrossword;

/**
 * Created by C4Q on 10/26/16.
 */

public class PuzzleFactory {

    public static Puzzle[] makePuzzles(){
        Puzzle[] puzzles = new Puzzle[]{
                new Puzzle("NYT 10_28_16", R.raw.nyt_10_28_2016),
                new Puzzle("azed_2170", R.raw.azed_2170),
                new Puzzle("crossword1",R.raw.crossword1),
                new Puzzle("crossword2",R.raw.crossword2),
                new Puzzle("crossword3",R.raw.crossword3),
                new Puzzle("crossword4",R.raw.crossword4),
                new Puzzle("crossword5",R.raw.crossword5),
                new Puzzle("cryptic26171",R.raw.cryptic26171),
                new Puzzle("cryptic26179",R.raw.cryptic26179),
                new Puzzle("nyt1",R.raw.nyt1),
                new Puzzle("nyt2",R.raw.nyt2),
                new Puzzle("nyt3",R.raw.nyt3),
                new Puzzle("nyt4",R.raw.nyt4),
                new Puzzle("nyt5",R.raw.nyt5),
                new Puzzle("nyt6",R.raw.nyt6),
                new Puzzle("nyt7",R.raw.nyt7),
                new Puzzle("nyt8",R.raw.nyt9),
                new Puzzle("nyt_2014_02_21",R.raw.nyt_2014_02_21),
                new Puzzle("nyt_2014_02_24",R.raw.nyt_2014_02_24),
                new Puzzle("nyt_2014_02_25",R.raw.nyt_2014_02_25),
                new Puzzle("nyt_2014_02_26",R.raw.nyt_2014_02_26),
                new Puzzle("nyt_2014_02_27",R.raw.nyt_2014_02_27),
                new Puzzle("nyt_2014_02_28",R.raw.nyt_2014_02_28),
                new Puzzle("nyt_2014_03_01",R.raw.nyt_2014_03_01),
                new Puzzle("nyt_2014_03_03",R.raw.nyt_2014_03_03),
                new Puzzle("nyt_2014_03_04",R.raw.nyt_2014_03_04),
                new Puzzle("nyt_2014_03_06",R.raw.nyt_2014_03_06),
                new Puzzle("nyt_2014_03_07",R.raw.nyt_2014_03_07),
                new Puzzle("nyt_2014_03_08",R.raw.nyt_2014_03_08)

        };
        return puzzles;

    }
}
