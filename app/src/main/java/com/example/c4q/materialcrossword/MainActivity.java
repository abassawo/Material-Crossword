package com.example.c4q.materialcrossword;


import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.load_todays_nyt_puzzle:
                Toast.makeText(this, "Not yet Implemented", Toast.LENGTH_SHORT).show();
//               new AsyncLoader().execute();
        }
        return true;
    }

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
        Crossword crossword  = new CrosswordDownloader().loadLocalCrossword(this, puzzle.getRawRes());
        if(crossword != null)
            hostFragment(CrosswordFragment.newInstance(crossword), CrosswordFragment.TAG);
    }

    private class AsyncLoader extends AsyncTask<Void, Void,Crossword> {

        @Override
        protected Crossword doInBackground(Void... params) {
            return new CrosswordDownloader().downloadCrossword();
        }

        @Override
        protected void onPostExecute(Crossword crossword) {
            super.onPostExecute(crossword);

            if(crossword != null) {
                Log.d(TAG, crossword.toString());
                hostFragment(CrosswordFragment.newInstance(crossword), CrosswordFragment.TAG);
            }else{
                Toast.makeText(MainActivity.this, "Unable to laod crossword", Toast.LENGTH_SHORT).show();
            }
        }
    }
}


