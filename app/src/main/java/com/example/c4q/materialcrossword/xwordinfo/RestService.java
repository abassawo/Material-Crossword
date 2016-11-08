package com.example.c4q.materialcrossword.xwordinfo;

import com.example.c4q.materialcrossword.crossword.model.Crossword;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by C4Q on 11/7/16.
 */

public interface RestService {

    @GET()
    Call<Crossword> loadCrossword();
}
