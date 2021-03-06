package com.example.healthystep2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ListAdapterForTrx extends BaseAdapter {

    Context c;
    int images[];

    ListAdapterForTrx(Context c, int[] images) {
        this.c = c;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return images[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater ly = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = ly.inflate(R.layout.custompagefortrx, parent, false);

        ImageView imageView = (ImageView) v.findViewById(R.id.customImageForTrx);

        imageView.setImageResource(images[position]);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c, TrxExercises.class);
                intent.putExtra("exercisefortrx", position);
                c.startActivity(intent);

            }
        });

        return v;
    }
}
