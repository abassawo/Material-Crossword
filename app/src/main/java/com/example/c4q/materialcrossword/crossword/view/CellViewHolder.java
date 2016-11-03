package com.example.c4q.materialcrossword.crossword.view;


import android.text.Editable;
import android.text.TextWatcher;
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
    public TextView cellViewText;
    public TextView numberTV;
    private String letter;
    private CrosswordAdapter crosswordAdapter;
    private Cell cell;
    private String acrossHint;
    private String downHint;

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

    }

    public void bindHint(Crossword crossword, int number) {
        if (number != 0) {
            numberTV.setText("" + number);
            List<String> acrosses = crossword.getClues().getAcross();
            this.acrossHint = getHint(number, acrosses);
            List<String> downs = crossword.getClues().getDown();
            this.downHint = getHint(number, downs);
        }
    }

    private String getHint(int number, List<String> acrossOrDown) {
        for (String hint : acrossOrDown) {
            if (number == getNumber(hint)) {
                return hint;
            }
        }
        return null;
    }

    private int getNumber(String hint) {
        int idx = hint.indexOf(".");
        String num = "";
        for (int i = 0; i < idx; i++) {
            num += hint.charAt(i);
        }
        return Integer.parseInt(num);
    }

    public void setFocus(Cell cell){
        cellViewText.setBackgroundResource(R.color.yellow);
    }


    public void bindCell(Cell cell) {
        if(cell == null) return;
        this.cell = cell;
        if(cell.isSelected()){
            cellViewText.setBackgroundResource(R.color.yellow);
        }else{
            restoreBackgroundColor();
        }
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
}