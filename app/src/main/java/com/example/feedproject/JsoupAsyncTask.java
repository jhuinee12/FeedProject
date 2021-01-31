package com.example.feedproject;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import static org.jsoup.nodes.Document.OutputSettings.Syntax.html;

public class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Document document = Jsoup.connect(String.valueOf(html)).get();
            Log.d("TAG", "Msg : " + document);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}