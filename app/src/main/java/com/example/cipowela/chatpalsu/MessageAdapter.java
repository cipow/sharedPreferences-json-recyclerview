package com.example.cipowela.chatpalsu;

import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cipowela on 01/11/17.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MAdapter> {

    List<JSONObject> dataJson = new ArrayList<>();
    JSONArray jsonArray;

    public MessageAdapter(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
        for (int i=0;i<this.jsonArray.length();i++){
            try {
                dataJson.add(this.jsonArray.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public MAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_message,parent,false);
        return new MAdapter(view);
    }

    @Override
    public void onBindViewHolder(MAdapter holder, int position) {
        JSONObject jsonObject = dataJson.get(position);
        try {
            holder.foto.setImageResource(jsonObject.getInt("foto"));
            holder.nama.setText(jsonObject.getString("nama"));
            holder.pesan.setText(jsonObject.getString("pesan"));
            holder.tanggal.setText(jsonObject.getString("tanggal"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return dataJson.size();
    }

    public class MAdapter extends RecyclerView.ViewHolder {
        ImageView foto;
        TextView nama, pesan, tanggal;
        public MAdapter(View itemView) {
            super(itemView);
            foto = (ImageView) itemView.findViewById(R.id.gambar);
            nama = (TextView) itemView.findViewById(R.id.nama);
            pesan = (TextView) itemView.findViewById(R.id.pesan);
            tanggal = (TextView) itemView.findViewById(R.id.tanggal);
        }
    }
}
