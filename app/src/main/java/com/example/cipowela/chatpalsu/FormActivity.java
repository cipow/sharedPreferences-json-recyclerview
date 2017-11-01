package com.example.cipowela.chatpalsu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FormActivity extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    EditText form_nama, form_pesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        form_nama = (EditText) findViewById(R.id.form_nama);
        form_pesan = (EditText) findViewById(R.id.form_pesan);
        preferences = getSharedPreferences(MainActivity.mainPrers,0);
        editor = preferences.edit();
    }

    public void send(View view) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("nama",form_nama.getText().toString());
            jsonObject.put("pesan",form_pesan.getText().toString());
            jsonObject.put("tanggal",new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
            jsonObject.put("foto",R.drawable.gambar_1);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(preferences.contains("message")) {
            String dataMessage = preferences.getString("message","");

            try {
                JSONArray jsonArray = new JSONArray(dataMessage);
                jsonArray.put(jsonObject);
                editor.putString("message", jsonArray.toString());
                editor.apply();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(jsonObject);
            editor.putString("message", jsonArray.toString());
            editor.apply();
        }

        finish();
    }
}
