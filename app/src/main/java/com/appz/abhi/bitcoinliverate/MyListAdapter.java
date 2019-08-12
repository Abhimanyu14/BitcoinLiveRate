package com.appz.abhi.bitcoinliverate;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class MyListAdapter extends ArrayAdapter<HashMap> {

    private ArrayList<HashMap> arrayList;
    private Context context;

    MyListAdapter(Context context, ArrayList<HashMap> arrayList) {
        super(context, -1, arrayList);
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context
                .LAYOUT_INFLATER_SERVICE);

        View itemView = null;
        if (layoutInflater != null && (position <= arrayList.size())) {
            itemView = layoutInflater.inflate(R.layout.list_item, parent, false);

            final TextView currency_name_tv = itemView.findViewById(R.id.currency_name_tv_id);
            final TextView m_tv = itemView.findViewById(R.id.m_tv_id);
            final TextView buy_tv = itemView.findViewById(R.id.buy_tv_id);
            final TextView sell_tv = itemView.findViewById(R.id.sell_tv_id);
            final TextView last_tv = itemView.findViewById(R.id.last_tv_id);
            final TextView symbol_tv1 = itemView.findViewById(R.id.symbol_tv1_id);
            final TextView symbol_tv2 = itemView.findViewById(R.id.symbol_tv2_id);
            final TextView symbol_tv3 = itemView.findViewById(R.id.symbol_tv3_id);
            final TextView symbol_tv4 = itemView.findViewById(R.id.symbol_tv4_id);

            currency_name_tv.setText(arrayList.get(position).get("currency_name").toString());
            m_tv.setText(new DecimalFormat("##.##").format(
                    Float.parseFloat(arrayList.get(position).get("m").toString())));
            buy_tv.setText(new DecimalFormat("##.##").format(
                    Float.parseFloat(arrayList.get(position).get("buy").toString())));
            sell_tv.setText(new DecimalFormat("##.##").format(
                    Float.parseFloat(arrayList.get(position).get("sell").toString())));
            last_tv.setText(new DecimalFormat("##.##").format(
                    Float.parseFloat(arrayList.get(position).get("last").toString())));
            symbol_tv1.setText(arrayList.get(position).get("symbol").toString());
            symbol_tv2.setText(arrayList.get(position).get("symbol").toString());
            symbol_tv3.setText(arrayList.get(position).get("symbol").toString());
            symbol_tv4.setText(arrayList.get(position).get("symbol").toString());
        }

        return itemView;
    }
}
