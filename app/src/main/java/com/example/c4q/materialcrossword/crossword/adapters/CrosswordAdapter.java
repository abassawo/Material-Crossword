package com.example.c4q.materialcrossword.crossword.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;


import com.example.c4q.materialcrossword.crossword.model.Answers;
import com.example.c4q.materialcrossword.crossword.model.CellFactory;
import com.example.c4q.materialcrossword.crossword.model.Direction;
import com.example.c4q.materialcrossword.crossword.presenter.PuzzleInteractionable;
import com.example.c4q.materialcrossword.R;
import com.example.c4q.materialcrossword.crossword.model.Cell;
import com.example.c4q.materialcrossword.crossword.model.Crossword;
import com.example.c4q.materialcrossword.crossword.view.CellViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * Created by C4Q on 10/24/16.
 */

public class CrosswordAdapter extends BaseAdapter implements PuzzleInteractionable {

    private static final String TAG = CrosswordAdapter.class.getSimpleName();
    private int cols = 0, rows = 0;
    private Crossword crossword;
    private List<String> letters;
    private List<Integer> numbers;
    private Context context;
    public CellViewHolder viewHolder;
    private boolean reveal;

    public List<Cell> getCells() {
        return cells;
    }

    private Stack<Cell> clickedCells = new Stack<>();
    private List<String> acrAnswers;
    private List<String> downAnswers;
    Map<Cell, String> cellMap;

    private List<Cell> cells;

    public Set<Cell> getCurrentSelectedCells() {
        return currentSelectedCells;
    }

    private Set<Cell> currentSelectedCells;

    public CrosswordAdapter(Context context, Crossword crossword) {
        this.context = context;
        this.crossword = crossword;
        readCrossword(crossword);
        cellMap = new HashMap<>();

    }

    public void readCrossword(Crossword crossword) {
        try {
            cols = crossword.getSize().getCols();
            rows = crossword.getSize().getRows();
            letters = crossword.getGrid();
            numbers = crossword.getGridNums();
            cells = CellFactory.makeCells(letters, numbers, crossword.getClues());
            Answers answers = crossword.getAnswers();
            acrAnswers = answers.getAcross();
            downAnswers = answers.getDown();
        } catch (Exception e) {

        }

    }

    public String findAnswerAtCell(Cell cell) {
        int hintNum = cell.getNumber();
        for (String word : acrAnswers) {
            if (word.startsWith("" + hintNum)) {
                return word;
            }
        }
        return "";
    }

//    public String findAnswerAtCell(Cell cell) {
//        //solving for across
//        if (cell.getLetter().equals(".")) return "";
//        Cell pointer = cell;
//        StringBuilder sb = new StringBuilder();
//        int idx = cell.getPositionInCW();
//        while (!pointer.getLetter().equals(".") && idx < cells.size() && idx % cols != 0) {
//            Cell c = cells.get(idx);
//            sb.append(c.getLetter());
//            idx++;
//        }
////        if(acrAnswers.contains(sb.toString()))
//        return sb.toString();
////        return "";
//    }


    public void cheat() {
        this.reveal = true;
    }


    @Override
    public int getCount() {
        return rows * cols;
    }

    @Override
    public Cell getItem(int position) {
        return cells.get(position); //fixme
    }

    public Cell getCurrentCell() {
        if (!clickedCells.isEmpty()) {
            return clickedCells.peek();
        }
        return null;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Cell cell = cells.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.crossword_cell, null, false);
        viewHolder = new CellViewHolder(view, this);
        viewHolder.bindCell(cell);
        return view;
    }

    public void setCrossword(Crossword crossword) {
        this.crossword = crossword;
    }

    public Crossword getCrossword() {
        return crossword;
    }

    public void revealSolution() {
        for (int i = 0; i < cells.size(); i++) {
            cells.get(i).reveal = true;
            viewHolder.revealLetter(cells.get(i));
            viewHolder.bindCell(cells.get(i));
        }
        notifyDataSetChanged();
    }

    @Override
    public void reset() {
        readCrossword(crossword);
        for (Cell cell : cells) {
            cell.reveal = false;
            viewHolder.conceal();
            viewHolder.bindCell(cell);
            notifyDataSetChanged();

        }
        notifyDataSetChanged();
    }

    @Override
    public void reveal() {
        for (String c : letters) {
            viewHolder.restoreBackgroundColor();
            notifyDataSetChanged();
        }
    }

    public String getAnswer(int hintNUm, Direction dir) {
        String answer = null;
        List<String> ansArray;
        switch (dir) {
            case ACROSS:
                ansArray = acrAnswers;
            case DOWN:
                ansArray = downAnswers;

        }
        for (String ans : acrAnswers) {
            if (ans.startsWith(String.valueOf(hintNUm))) {
                answer = ans;
            }
        }
        return answer;
    }


    @Override
    public void highlightNeighbors(Cell cell, final Direction dir) {
        Set<Cell> cellSet = new HashSet<>();
        switch (dir) {
            case ACROSS:
                Cell pointer = cell;
                int idx = cell.getPositionInCW();
                while (!pointer.getLetter().equals(".") && idx < cells.size()) {
                    pointer = cells.get(idx);
                    highlightCell(pointer);
                    cellSet.add(pointer);
                    idx++;
                }
                currentSelectedCells = cellSet;
//                return cellSet;
//            case DOWN:
//                return null;
//            default: return null;
        }
    }

    private void highlightCell(Cell c) {
        c.setSelected(true);
        viewHolder.bindCell(c);

        notifyDataSetChanged();
    }


    public void solveCell(Cell cell) {
        if (cell != null) {
            cell.reveal = true;
            viewHolder.bindCell(cell);
        }

    }

    private Direction direction;

    public boolean isCurrentCell(Cell cell) {
        return clickedCells.isEmpty() ? false : clickedCells.peek() == cell;
    }

    public void setCurrentCell(Cell cell) {
//        Toast.makeText(context, findAnswerAtCell(cell), Toast.LENGTH_SHORT).show();
        Toast.makeText(context, cell.acrossPlaceInEnum.toString(), Toast.LENGTH_SHORT).show();
        if (isCurrentCell(cell)) {
            cell.setSelected(false);
            viewHolder.restoreBackgroundColor();
        } else {
//            while (!clickedCells.isEmpty()) {
//                Cell last = clickedCells.pop();
//                last.setSelected(false);
//                cell.toggleSelect();
//
//            }
        }
//        clickedCells.push(cell);


        if (isCurrentCell(cell)) {
            this.direction = Direction.DOWN;
        } else {
            this.direction = Direction.ACROSS;
        }
        highlightNeighbors(cell, direction);
        viewHolder.bindCell(cell);
        notifyDataSetChanged();
        //presenter.showKeyboardForCell();

    }

    @Override
    public void onClick(Cell cell) {
        setCurrentCell(cell);
    }


    public void revealLetter(Cell c) {
        viewHolder.revealLetter(c);
    }
}
