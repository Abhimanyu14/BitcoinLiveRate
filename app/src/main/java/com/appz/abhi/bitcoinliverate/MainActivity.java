package com.appz.abhi.bitcoinliverate;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ArrayList<HashMap> arrayList;
    private MyListAdapter myListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);

        arrayList = new ArrayList<>();

        myListAdapter = new MyListAdapter(this, arrayList);
        listView.setAdapter(myListAdapter);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    SystemClock.sleep(100);
                    new getBitcoinRate().execute();
                }
            }
        };
        new Thread(runnable).start();
    }

    private class getBitcoinRate extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            HttpHandler httpHandler = new HttpHandler();

            // Making a request to url and getting response
            String url = "https://blockchain.info/ticker";
            String jsonStr = httpHandler.makeServiceCall(url);

            if (jsonStr != null) {
                try {

                    arrayList.clear();
                    JSONObject jsonObject = new JSONObject(jsonStr);

                    //  USD
                    JSONObject usd = jsonObject.getJSONObject("USD");
                    String buy1 = usd.getString("buy");
                    String sell1 = usd.getString("sell");
                    String last1 = usd.getString("last");
                    String symbol1 = usd.getString("symbol");
                    String m1 = usd.getString("15m");
                    HashMap<String, String> hashMap1 = new HashMap<>();
                    hashMap1.put("currency_name", "USD");
                    hashMap1.put("buy", buy1);
                    hashMap1.put("sell", sell1);
                    hashMap1.put("symbol", symbol1);
                    hashMap1.put("last", last1);
                    hashMap1.put("m", m1);
                    arrayList.add(hashMap1);

                    //  INR
                    JSONObject inr = jsonObject.getJSONObject("INR");
                    String buy2 = inr.getString("buy");
                    String sell2 = inr.getString("sell");
                    String last2 = inr.getString("last");
                    String symbol2 = inr.getString("symbol");
                    String m2 = inr.getString("15m");
                    HashMap<String, String> hashMap2 = new HashMap<>();
                    hashMap2.put("currency_name", "INR");
                    hashMap2.put("buy", buy2);
                    hashMap2.put("sell", sell2);
                    hashMap2.put("symbol", symbol2);
                    hashMap2.put("last", last2);
                    hashMap2.put("m", m2);
                    arrayList.add(hashMap2);

                    //  EUR
                    JSONObject eur = jsonObject.getJSONObject("EUR");
                    String buy3 = eur.getString("buy");
                    String sell3 = eur.getString("sell");
                    String last3 = eur.getString("last");
                    String symbol3 = eur.getString("symbol");
                    String m3 = eur.getString("15m");
                    HashMap<String, String> hashMap3 = new HashMap<>();
                    hashMap3.put("currency_name", "EUR");
                    hashMap3.put("buy", buy3);
                    hashMap3.put("sell", sell3);
                    hashMap3.put("symbol", symbol3);
                    hashMap3.put("last", last3);
                    hashMap3.put("m", m3);
                    arrayList.add(hashMap3);

                    //  JPY
                    JSONObject jpy = jsonObject.getJSONObject("JPY");
                    String buy4 = jpy.getString("buy");
                    String sell4 = jpy.getString("sell");
                    String last4 = jpy.getString("last");
                    String symbol4 = jpy.getString("symbol");
                    String m4 = jpy.getString("15m");
                    HashMap<String, String> hashMap4 = new HashMap<>();
                    hashMap4.put("currency_name", "JPY");
                    hashMap4.put("buy", buy4);
                    hashMap4.put("sell", sell4);
                    hashMap4.put("symbol", symbol4);
                    hashMap4.put("last", last4);
                    hashMap4.put("m", m4);
                    arrayList.add(hashMap4);

                } catch (final JSONException e) {
                    Log.e(getLocalClassName(), "JSON parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "JSON parsing error: " +
                                    e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            } else {
                Log.e(getLocalClassName(), "Couldn't get JSON from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Couldn't get JSON from server",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            myListAdapter.notifyDataSetChanged();
        }
    }
}
