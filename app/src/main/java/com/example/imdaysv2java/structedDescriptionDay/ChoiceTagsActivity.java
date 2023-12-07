package com.example.imdaysv2java.structedDescriptionDay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.imdaysv2java.Day;
import com.example.imdaysv2java.DefaultFuncion;
import com.example.imdaysv2java.MainActivity;
import com.example.imdaysv2java.R;
import com.example.imdaysv2java.TagAdapterArrayList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class ChoiceTagsActivity extends AppCompatActivity implements DefaultFuncion {
    Day day = new Day();
    List<String> tags = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_tags);
        acceptDay();
        GridView countriesList = findViewById(R.id.gridview);
        // создаем адаптер
        TagAdapterArrayList adapter = new TagAdapterArrayList(this, R.layout.list_item_tag, tags);

        countriesList.setAdapter(adapter);

        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"Вы выбрали "
                                + parent.getItemAtPosition(position).toString(),
                        Toast.LENGTH_SHORT).show();
            }
        };
        countriesList.setOnItemClickListener(itemListener);

        EditText tagEditText = findViewById(R.id.day_character__name);
        FloatingActionButton addButton = findViewById(R.id.addTag);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tagEditText.getText().toString().length()>=40){
                    tagEditText.setError("Длина тега не может превыщать 40 символов");
                }
                else {
                    tags.add(tagEditText.getText().toString());
                    adapter.notifyDataSetChanged();
                    tagEditText.setText("");
                }
            }
        });
    }
    private void acceptDay(){
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            day = (Day) getIntent().getSerializableExtra("day");
            System.out.print(day.getInfo()+"INFO");
        }
    }

    @Override
    public void cloze(View view) throws IOException {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void next(View view) throws IOException, URISyntaxException {
        day.setTags(tags);
        Intent intent = new Intent(this, ChoiceTagsActivity.class);
        intent.putExtra("day",day);
        startActivity(intent);
    }
}