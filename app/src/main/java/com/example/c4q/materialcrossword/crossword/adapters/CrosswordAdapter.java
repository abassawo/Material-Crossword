package com.example.c4q.materialcrossword.crossword.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import com.example.c4q.materialcrossword.crossword.presenter.PuzzleInteractionable;
import com.example.c4q.materialcrossword.R;
import com.example.c4q.materialcrossword.crossword.model.Cell;
import com.example.c4q.materialcrossword.crossword.model.Crossword;
import com.example.c4q.materialcrossword.crossword.view.CellViewHolder;

import java.util.List;

/**
 * Created by C4Q on 10/24/16.
 */

public class CrosswordAdapter extends BaseAdapter implements PuzzleInteractionable{

    private int cols = 0, rows = 0;
    private Crossword crossword;
    private List<String> letters;
    private List<Integer> numbers;
    private Context context;
    private CellViewHolder viewHolder;
    private boolean reveal;
    private Cell currentCell;
    public Cell[] getCells() {
        return cells;
    }

    private Cell[] cells;

    public CrosswordAdapter(Context context, Crossword crossword) {
        this.context = context;
        this.crossword = crossword;
        try {
            cols = crossword.getSize().getCols();
            rows = crossword.getSize().getRows();
            letters = crossword.getGrid();
            numbers = crossword.getGridNums();
            cells = Cell.makeCells(letters, numbers);
        } catch (Exception e){

        }
    }

    public void cheat(){
        this.reveal = true;
    }



    @Override
    public int getCount() {
        return rows * cols;
    }

    @Override
    public Object getItem(int position) {
        return letters.get(position); //fixme
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.crossword_cell, null, false);
            viewHolder = new CellViewHolder(convertView, this);
            String c = letters.get(position);
            int num = numbers.get(position);
            viewHolder.bindCell(cells[position]);
            viewHolder.bindLetter(c);
            viewHolder.bindHint(crossword, num);
        }
        return convertView;
    }

    public void setCrossword(Crossword crossword) {
        this.crossword = crossword;
    }

    public Crossword getCrossword() {
        return crossword;
    }

    public void revealSolution() {
        for (int i = 0; i < cells.length; i++) {
            cells[i].reveal = true;
            viewHolder.revealLetter(cells[i]);
            viewHolder.bindCell(cells[i]);
        }
        notifyDataSetChanged();
    }

    @Override
    public void reset() {
        for (Cell cell : cells) {
            cell.reveal = false;
            viewHolder.restoreBackgroundColor();
            viewHolder.bindLetter(cell.getLetter());
            notifyDataSetChanged();
        }
    }

    @Override
    public void reveal() {
        for (String c : letters) {
            viewHolder.restoreBackgroundColor();
            viewHolder.bindLetter(c);
            notifyDataSetChanged();
        }
    }

    @Override
    public void highlightAcross(Cell cell) {

    }

    @Override
    public void highlightDown(Cell cell) {

    }

    public void solveCell(Cell cell) {
        if(cell != null) {
            cell.reveal = true;
            viewHolder.bindCell(cell);
        }

    }

    private Direction direction;

    public void setCurrentCell(Cell cell) {
        if(this.currentCell == cell){
            this.direction = Direction.DOWN;
        }else {
            this.direction = Direction.ACROSS;
        }
        this.currentCell = cell;
        viewHolder.setFocus(cell);
        notifyDataSetChanged();
        //presenter.showKeyboardForCell();
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    @Override
    public void onClick(Cell cell) {
        cell.toggleSelect();
        if(cell.isSelected()){
            setCurrentCell(cell);
        }
        viewHolder.bindCell(cell);
        notifyDataSetChanged();
    }

    private enum Direction{
        ACROSS, DOWN
    }
}
