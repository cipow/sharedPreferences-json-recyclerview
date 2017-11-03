package com.example.cipowela.chatpalsu.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cipowela.chatpalsu.R;

/**
 * Created by cipowela on 03/11/17.
 */

public class ImageSpinnerAdapter extends BaseAdapter {
    private Context context;
    private int img[];
    private String img_name[];
    private LayoutInflater inflater;

    public ImageSpinnerAdapter(Context context, int[] img, String[] img_name) {
        this.context = context;
        this.img = img;
        this.img_name = img_name;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return img.length;
    }

    @Override
    public Object getItem(int position) {
        return img[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.image_spinner,null);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.spinner_image);
        TextView textView = (TextView) convertView.findViewById(R.id.spinner_text);

        imageView.setImageResource(img[position]);
        textView.setText(img_name[position]);

        return convertView;
    }
}
