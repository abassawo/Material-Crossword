package com.example.c4q.materialcrossword.crossword.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.c4q.materialcrossword.R;


/**
 * Created by C4Q on 10/24/16.
 */
public class TextViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;

    public TextViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.clue_text);
    }

    public void bindClue(String clue) {
        textView.setText(clue);
    }
}
