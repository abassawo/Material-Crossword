package com.example.c4q.materialcrossword.crossword.view;


import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.c4q.materialcrossword.Mvp;
import com.example.c4q.materialcrossword.R;
import com.example.c4q.materialcrossword.crossword.adapters.CrosswordAdapter;
import com.example.c4q.materialcrossword.crossword.model.Cell;
import com.example.c4q.materialcrossword.crossword.model.Crossword;

import java.util.List;

/**
 * Created by C4Q on 10/21/16.
 */


public class CellViewHolder implements Mvp.View{
    private static final String TAG = CellViewHolder.class.getSimpleName() ;
    public TextView cellViewText;
    public TextView numberTV;
    private String letter;
    private CrosswordAdapter crosswordAdapter;
    private Cell cell;


    public TextView currentCellView;

    public CellViewHolder(View itemView, CrosswordAdapter crosswordAdapter) {
        this.crosswordAdapter = crosswordAdapter;
        cellViewText = (TextView) itemView.findViewById(R.id.text_view);
        numberTV = (TextView) itemView.findViewById(R.id.number_tv);
        itemView.setOnClickListener(this);
        cellViewText.setOnClickListener(this);
    }



    public void bindLetter(String c) {
        if (c == null) return;
        if (c.equals(".")) {
            currentCellView = cellViewText;
            cellViewText.setBackgroundResource(android.R.color.black);
        }
        this.letter = c;
    }

    public void revealLetter(Cell cell) {
        cell.reveal = true;
        cellViewText.setText(cell.getLetter());
        crosswordAdapter.notifyDataSetChanged();
    }


    public void bindCell(Cell cell) {
        if(cell == null) return;
        String letter = cell.getLetter();
        this.cell = cell;
        if(cell.isSelected() || cell.equals(crosswordAdapter.getCurrentCell())){
            highlightCell();
        }else{
            restoreBackgroundColor();
        }
        bindLetter(letter);
        if(cell.getNumber() != 0)
            numberTV.setText("" + cell.getNumber());
        if(cell.reveal){
            cellViewText.setText(cell.getLetter());
        }
    }



    public void restoreBackgroundColor() {
        cellViewText.setBackgroundResource(android.R.color.transparent);
    }



    @Override
    public void onClick(View v) {
        crosswordAdapter.onClick(cell);
    }

    public void conceal() {
        cellViewText.setText("");
    }

    private void highlightCell() {
        cellViewText.setBackgroundResource(R.color.yellow);
    }
}