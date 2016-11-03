package com.example.c4q.materialcrossword.game;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.c4q.materialcrossword.R;
import com.example.c4q.materialcrossword.crossword.model.Cell;
import com.example.c4q.materialcrossword.crossword.model.Crossword;

/**
 * Created by C4Q on 10/28/16.
 */

public class GameFragment extends Fragment implements GamePresenter {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_crossword, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void startGame(Crossword crossword) {

    }

    @Override
    public void saveGame() {

    }

    @Override
    public void resetGame() {

    }

    @Override
    public void revealLetter(Cell cell) {

    }

    @Override
    public void revealWord(Cell... cells) {

    }
}
