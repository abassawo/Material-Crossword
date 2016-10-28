package com.example.c4q.materialcrossword.crossword.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import com.example.c4q.materialcrossword.R;
import com.example.c4q.materialcrossword.crossword.model.Cell;
import com.example.c4q.materialcrossword.crossword.model.Crossword;
import com.example.c4q.materialcrossword.crossword.view.CellViewHolder;

import java.util.List;

/**
 * Created by C4Q on 10/24/16.
 */

public class CrosswordAdapter extends BaseAdapter {

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

    public CrosswordAdapter(Context context, Crossword crossword, boolean reveal) {
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
        this.reveal = reveal;
    }

    public CrosswordAdapter(Context context) {
        this(context, null, false);

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
            viewHolder.bindLetter(c, reveal);
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
        for (Cell cell : cells) {
            viewHolder.cellViewText.setText(cell.getLetter());
        }
    }

    public void reset() {
        for (String c : letters) {
            viewHolder.restoreBackgroundColor();
            viewHolder.bindLetter(c, false);
            notifyDataSetChanged();
        }
    }

    public void solveCell(Cell cell) {
        if(cell != null) {
            viewHolder.bindCell(cell);
            viewHolder.bindLetter(cell.getLetter(), true);
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
        //presenter.showKeyboardForCell();
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    private enum Direction{
        ACROSS, DOWN
    }
}
