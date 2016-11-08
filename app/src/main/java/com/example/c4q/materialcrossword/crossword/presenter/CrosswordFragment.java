package com.example.c4q.materialcrossword.crossword.presenter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.c4q.materialcrossword.CrosswordDownloader;
import com.example.c4q.materialcrossword.R;
import com.example.c4q.materialcrossword.crossword.adapters.ClueRVAdapter;
import com.example.c4q.materialcrossword.crossword.adapters.CrosswordAdapter;
import com.example.c4q.materialcrossword.crossword.model.Cell;
import com.example.c4q.materialcrossword.crossword.model.Crossword;
import com.example.c4q.materialcrossword.crossword.view.CrosswordView;


public class CrosswordFragment extends Fragment {
    private Crossword crossword;
    private CrosswordView crosswordView;
    private CrosswordAdapter crosswordAdapter;
    private RecyclerView cluesRV;
    private ClueRVAdapter clueAdapter;
    public static final String TAG = CrosswordFragment.class.getSimpleName();
    private static String PUZZLE_SOURCE = "puzzle_web_source";


    public static CrosswordFragment newInstance(String crosswordJson){
        CrosswordFragment fragment = new CrosswordFragment();
        Bundle args = new Bundle();
        args.putString(PUZZLE_SOURCE, crosswordJson);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        String crosswordJson = getArguments().getString(PUZZLE_SOURCE);
        crossword = new CrosswordDownloader().makeCrossword(crosswordJson);
        crosswordAdapter = new CrosswordAdapter(getActivity(), crossword);
        clueAdapter = new ClueRVAdapter(crossword);
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_crossword, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        crosswordView = (CrosswordView) view.findViewById(R.id.crossword_view);
        crosswordView.setAdapter(crosswordAdapter);
        cluesRV = (RecyclerView) view.findViewById(R.id.list_recycler);
        cluesRV.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        cluesRV.setAdapter(clueAdapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_restart:
                crosswordAdapter.reset();
                crosswordAdapter.notifyDataSetChanged();
                return true;
            case R.id.menu_solve_puzzle:
                showCheatDialog();
                return true;
            case R.id.menu_solve_cell:
               Cell cell = crosswordAdapter.getCurrentCell();
                if(cell != null) {
                    crosswordAdapter.viewHolder.revealLetter(cell);
                    crosswordAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.menu_solve_word:
                for(Cell c : crosswordAdapter.getCurrentSelectedCells()){
                    crosswordAdapter.revealLetter(c);
                }

            //To-do - Implement Solve Cell, Solve word, and Select Puzzle.
        }

        return super.onOptionsItemSelected(item);
    }


    public void showCheatDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Are you sure you want to reveal all the answers");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               crosswordAdapter.revealSolution();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }


}
