package com.example.c4q.materialcrossword.crossword.view;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

import com.example.c4q.materialcrossword.crossword.adapters.CrosswordAdapter;
import com.example.c4q.materialcrossword.crossword.model.Cell;
import com.example.c4q.materialcrossword.crossword.model.Crossword;


/**
 * Created by C4Q on 10/17/16.
 */
public class CrosswordView extends GridView{


    public CrosswordView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    private Crossword crossword;
    private CrosswordAdapter adapter;



    public void setAdapter(CrosswordAdapter adapter) {
        super.setAdapter(adapter);
        this.adapter = adapter;
        this.crossword = adapter.getCrossword();
        setNumColumns(crossword.getSize().getCols());
    }

    public void reset() {
        CrosswordAdapter adapter = (CrosswordAdapter) getAdapter();
        adapter.reset();
        for(Cell cell : adapter.getCells()){
            cell.setSelected(false);
        }
        setAdapter(adapter);

    }

    public void solveCrossword() {
        adapter.revealSolution();
        setAdapter(adapter);
    }

}
