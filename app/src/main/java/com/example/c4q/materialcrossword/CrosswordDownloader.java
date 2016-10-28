package com.example.c4q.materialcrossword;


import android.content.Context;
import android.util.Log;

import com.example.c4q.materialcrossword.crossword.model.Crossword;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Created by C4Q on 10/21/16.
 */

public class CrosswordDownloader
{

    private static final String TAG = CrosswordDownloader.class.getSimpleName();
    public static String source = "http://www.xwordinfo.com/JSON/Data.aspx?format=text";

    public Crossword downloadCrossword(){
        Log.d(TAG, "Downlaoding from source");
        return downloadCrossword(source);

    }

    private Crossword downloadCrossword(String source){
        try {
            Document document = Jsoup.connect(source).get();
            String body = document.html();
            Log.d(TAG, body);
            return new Gson().fromJson(body, Crossword.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public Crossword loadLocalCrossword(Context context, int rawFile){
        InputStream inputStream = context.getResources().openRawResource(rawFile);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String line;
        StringBuilder stringBuilder = new StringBuilder();
        Crossword crossword = null;
        try {
            while ((line = bufferedReader.readLine()) != null )
            {
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            String json= stringBuilder.toString();
            Log.d(TAG + " local ", json);
            crossword = new Gson().fromJson(json, Crossword.class);
            JSONObject obj = new JSONObject(json);
            JSONArray gridJson = obj.getJSONArray("gridnums");
            List<Integer> gridNums = new ArrayList<>();
            Log.d(TAG, crossword.toString());
            if(crossword.getGridNums() == null) {
                for (int i = 0; i < gridJson.length(); i++) {
                    int num = Integer.parseInt((String) gridJson.get(i));
                    gridNums.add(num);
                }
                crossword.setGridNums(gridNums);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return crossword;

    }

    public String getJSON(String endpoint){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(endpoint).build();
        try {
            Response response = client.newCall(request).execute();
            String body = response.body().string();
            Log.d(TAG, body);
            return body;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
