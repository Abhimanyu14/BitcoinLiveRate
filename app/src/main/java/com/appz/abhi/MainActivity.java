package com.appz.abhi.bitcoinliverate;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    // URL to get contacts JSON
    private static String url = "https://blockchain.info/ticker";
    private String TAG = MainActivity.class.getSimpleName();
    private ListView listView;
    private ArrayList<HashMap<String, String>> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayList = new ArrayList<>();

        listView = findViewById(R.id.list);
        startProgress();
        new GetContacts().execute();
    }

    public void startProgress() {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    SystemClock.sleep(100);
                    new GetContacts().execute();
                }
            }
        };
        new Thread(runnable).start();
    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler httpHandler = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = httpHandler.makeServiceCall(url);

//            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObject = new JSONObject(jsonStr);

                    // tmp hash map for single hashMap
                    HashMap<String, String> hashMap = new HashMap<>();

                    JSONObject code1 = jsonObject.getJSONObject("USD");
                    String buy1 = code1.getString("buy");
                    String sell1 = code1.getString("sell");
                    String last1 = code1.getString("last");
                    String symbol1 = code1.getString("symbol");
                    String m1 = code1.getString("15m");

                    JSONObject code2 = jsonObject.getJSONObject("INR");
                    String buy2 = code2.getString("buy");
                    String sell2 = code2.getString("sell");
                    String last2 = code2.getString("last");
                    String symbol2 = code2.getString("symbol");
                    String m2 = code2.getString("15m");

                    // adding each child node to HashMap key => value
                    hashMap.put("buy1", buy1);
                    hashMap.put("sell1", sell1);
                    hashMap.put("symbol1", symbol1);
                    hashMap.put("last1", last1);
                    hashMap.put("m1", m1);

                    hashMap.put("buy2", buy2);
                    hashMap.put("sell2", sell2);
                    hashMap.put("symbol2", symbol2);
                    hashMap.put("last2", last2);
                    hashMap.put("m2", m2);

                    // adding hashMap to hashMap list
                    arrayList.clear();
                    arrayList.add(hashMap);
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Json parsing error: " +
                                    e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Couldn't get json from server",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this,
                    arrayList,
                    R.layout.list_item, new String[]{
                    "m1", "buy1", "sell1", "last1", "symbol1", "symbol1", "symbol1", "symbol1",
                    "m2", "buy2", "sell2", "last2", "symbol2", "symbol2", "symbol2", "symbol2"
            },
                    new int[]{
                            R.id.m1_tv_id, R.id.buy1_tv_id, R.id.sell1_tv_id, R.id.last1_tv_id,
                            R.id.symbol1_tv1_id, R.id.symbol1_tv2_id, R.id.symbol1_tv3_id, R.id.symbol1_tv4_id,
                            R.id.m2_tv_id, R.id.buy2_tv_id, R.id.sell2_tv_id, R.id.last2_tv_id,
                            R.id.symbol2_tv1_id, R.id.symbol2_tv2_id, R.id.symbol2_tv3_id, R.id.symbol2_tv4_id
                    }
            );
            listView.setAdapter(adapter);
        }
    }
}
