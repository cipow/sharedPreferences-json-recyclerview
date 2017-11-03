package com.example.cipowela.chatpalsu.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cipowela.chatpalsu.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by cipowela on 01/11/17.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MAdapter> {

    JSONArray jsonArray;

    public MessageAdapter(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    @Override
    public MAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_message,parent,false);
        return new MAdapter(view);
    }

    @Override
    public void onBindViewHolder(MAdapter holder, int position) {
        try {
            JSONObject jsonObject = jsonArray.getJSONObject(position);
            holder.foto.setImageResource(jsonObject.getInt("Foto"));
            holder.nama.setText(jsonObject.getString("Pengirim"));
            holder.pesan.setText(jsonObject.getString("Content"));
            holder.tanggal.setText(jsonObject.getString("Waktu"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return jsonArray.length();
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
