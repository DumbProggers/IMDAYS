package com.example.imdaysv2java.structedDescriptionDay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.imdaysv2java.Day;
import com.example.imdaysv2java.DefaultFuncion;
import com.example.imdaysv2java.MainActivity;
import com.example.imdaysv2java.R;

import java.io.IOException;
import java.net.URISyntaxException;

public class ChoiceNameDayActivity extends AppCompatActivity implements DefaultFuncion {
    Day day = new Day();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_name_day);
        acceptDay();

    }
    @Override
    public void cloze(View view) throws IOException {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    @Override
    public void next(View view) throws IOException, URISyntaxException {
        EditText editTextName = findViewById(R.id.day_character__name);
        day.setName(editTextName.getText().toString());
    }
    private void acceptDay(){
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            day = (Day) getIntent().getSerializableExtra("day");
            System.out.print(day.getInfo());
        }
    }
}
