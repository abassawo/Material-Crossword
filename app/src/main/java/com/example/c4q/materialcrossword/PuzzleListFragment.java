package com.example.c4q.materialcrossword;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import java.lang.reflect.Field;

/**
 * Created by C4Q on 10/26/16.
 */

public class PuzzleListFragment extends Fragment {
    private RecyclerView rv;
    public static String TAG = PuzzleListFragment.class.getSimpleName();
    Puzzle[] puzzles;

    public interface ListFragmentInteraction{
        void onPuzzleSelected(Puzzle puzzle);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_puzzle, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv = (RecyclerView) view.findViewById(R.id.list_recycler);
        puzzles = PuzzleFactory.makePuzzles();
        rv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rv.setAdapter(new PuzzlesAdapter(puzzles));
    }

    private class PuzzlesAdapter extends RecyclerView.Adapter<PuzzleViewHolder>{
        private Puzzle[] puzzles;

        public PuzzlesAdapter(Puzzle[] puzzles){
            this.puzzles = puzzles;
        }
        @Override
        public PuzzleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.puzzle_item, parent, false);
            return new PuzzleViewHolder(view);
        }

        @Override
        public void onBindViewHolder(PuzzleViewHolder holder, int position) {
            holder.bindPuzzle(puzzles[position]);
            holder.button.setText(puzzles[position].getName());
        }

        @Override
        public int getItemCount() {
            return puzzles.length;
        }
    }

    public class PuzzleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Puzzle puzzle;
        private Button button;

        public PuzzleViewHolder(View itemView) {
            super(itemView);
            this.button =(Button) itemView.findViewById(R.id.puzzle_button);
            button.setBackgroundResource(android.R.drawable.btn_default);
            button.setOnClickListener(this);
        }

        public void bindPuzzle(Puzzle puzzle){
            this.puzzle = puzzle;

        }

        @Override
        public void onClick(View v) {
            MainActivity activity = (MainActivity) getActivity();
            activity.onPuzzleSelected(puzzle);

        }
    }

}
