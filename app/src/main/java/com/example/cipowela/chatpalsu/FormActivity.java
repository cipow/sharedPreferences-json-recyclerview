package com.example.cipowela.chatpalsu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.cipowela.chatpalsu.Adapter.ImageSpinnerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FormActivity extends AppCompatActivity{

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    EditText form_nama, form_pesan;
    Spinner spinner;

    String[] image_name = {"Ruby", "Preman", "Mafia"};
    int[] image_picture = {R.drawable.gambar_1,R.drawable.gambar_3,R.drawable.gambar_4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        getSupportActionBar().setTitle("Tambah Pesan");

        form_nama = (EditText) findViewById(R.id.form_nama);
        form_pesan = (EditText) findViewById(R.id.form_pesan);
        spinner = (Spinner) findViewById(R.id.foto_image);
        preferences = getSharedPreferences(MainActivity.mainPrers,0);
        editor = preferences.edit();

        ImageSpinnerAdapter spinnerAdapter = new ImageSpinnerAdapter(this,image_picture,image_name);
        spinner.setAdapter(spinnerAdapter);
    }

    public void send(View view) {
        int image = (int) spinner.getSelectedItem();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Pengirim",form_nama.getText().toString());
            jsonObject.put("Content",form_pesan.getText().toString());
            jsonObject.put("Waktu",new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
            jsonObject.put("Foto",image);
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
