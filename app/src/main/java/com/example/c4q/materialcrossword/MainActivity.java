package com.example.c4q.materialcrossword;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.c4q.materialcrossword.crossword.model.Crossword;
import com.example.c4q.materialcrossword.crossword.presenter.CrosswordFragment;


public class MainActivity extends AppCompatActivity implements PuzzleListFragment.ListFragmentInteraction {
    private static final String TAG = MainActivity.class.getSimpleName();
    private boolean listVisible;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        hostFragment(new PuzzleListFragment(), PuzzleListFragment.TAG);
    }

    @Override
    public void onBackPressed() {
        if(listVisible){
            super.onBackPressed();
        }else{
            hostFragment(new PuzzleListFragment(), PuzzleListFragment.TAG);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater infl = getMenuInflater();
        infl.inflate(R.menu.activity_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch(item.getItemId()){
//            case R.id.load_todays_nyt_puzzle:
//                Toast.makeText(this, "Not yet Implemented", Toast.LENGTH_SHORT).show();
//
//        }
//        return true;
//    }

    public void hostFragment(Fragment fragment, String tag){
        switch (tag){
            case "PuzzleListFragment": listVisible = true;
                break;
            default:
                listVisible = false;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).commit();
    }

    @Override
    public void onPuzzleSelected(Puzzle puzzle) {
        toolbar.setSubtitle(puzzle.getName());
        String cwJson = new CrosswordDownloader().downloadLocalJSON(this, puzzle.getRawRes());
        if(cwJson != null)
            hostFragment(CrosswordFragment.newInstance(cwJson), CrosswordFragment.TAG);
    }

}


