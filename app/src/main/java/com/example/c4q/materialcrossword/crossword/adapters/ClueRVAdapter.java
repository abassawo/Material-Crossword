package com.example.c4q.materialcrossword.crossword.adapters;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.c4q.materialcrossword.R;
import com.example.c4q.materialcrossword.crossword.model.Crossword;
import com.example.c4q.materialcrossword.crossword.view.TextViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by C4Q on 10/24/16.
 */

public class ClueRVAdapter extends RecyclerView.Adapter<TextViewHolder>{

    private List<String> clues;

    public ClueRVAdapter(Crossword cross){
        List<String> across = cross.getClues().getAcross();
        List<String> down = cross.getClues().getDown();
        clues = new ArrayList<>(across.size() + down.size());
        int idx = 0;
        int min = Math.min(across.size(), down.size());
        while(idx < min){
            clues.add(across.get(idx));
            clues.add(down.get(idx));
            idx++;
        }
        List<String> other = down.size() == min ? across : down;
        for (int i = idx; i < other.size(); i++) {
            clues.add(other.get(i));
        }
        //still have to add the views for the max

    }
    @Override
    public TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.text_item, parent, false);
        return new TextViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TextViewHolder holder, int position) {
        String clue =  clues.get(position);
        holder.bindClue(clue);
    }

    @Override
    public int getItemCount() {
        return clues.size();
    }
}
