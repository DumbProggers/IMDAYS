package com.example.imdaysv2java;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.imdaysv2java.R;

import java.util.List;

public class TagAdapterArrayList extends ArrayAdapter<String> {

    List<String> items_list;
    int custom_layout_id;

    public TagAdapterArrayList(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
        items_list = objects;
        custom_layout_id = resource;
    }

    @Override public int getCount() {
        return items_list.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            // getting reference to the main layout and initializing
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(custom_layout_id, null);
        }

        // initializing the imageview and textview and setting data
        TextView textView = v.findViewById(R.id.textViewTag);

        // get the item using the  position param
        String item = items_list.get(position);

        textView.setText(item);
        return v;
    }
}