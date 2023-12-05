package com.example.imdaysv2java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.imdaysv2java.mainFragments.daysFragments.DaysFragments;
import com.example.imdaysv2java.structedDescriptionDay.ChoiceNameDayActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    ArrayList<Day> days = new ArrayList<>();
    FloatingActionButton fab;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        replaceFragment(new DaysFragments());




        bottomNavigationView.setBackground(null);
        bottomNavigationView.setSelectedItemId(R.id.listDays);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.analitics) {
//                replaceFragment(new HomeFragment());
            } else if (item.getItemId() == R.id.listDays) {
                replaceFragment(new DaysFragments());
            }
            return true;
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomDialog();
            }
        });
    }
    private void showBottomDialog() {
        Calendar calendar = Calendar.getInstance();
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomshitlayout);
        RelativeLayout addDayButton = dialog.findViewById(R.id.layoutAddButton);
        ImageView cancelButton = dialog.findViewById(R.id.cancelButton);
        CalendarView calendarView = dialog.findViewById(R.id.calendar_bottom);
        Day day = new Day(
                calendar.get(Calendar.DAY_OF_MONTH) + "/"
                + (calendar.get(Calendar.MONTH)+1) + "/"
                + calendar.get(Calendar.YEAR));

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                day.setDateTime(dayOfMonth+"/"+month+1+"/"+year);

            }
        });

        addDayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ChoiceNameDayActivity.class);
                intent.putExtra("day",day);
                v.getContext().startActivity(intent);
                dialog.dismiss();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }
    public void init(){
        initDays();
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        fab = findViewById(R.id.fab);
    }
    private  void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle args = new Bundle();
        args.putSerializable("days", days);
        fragment.setArguments(args);
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
    public void initDays(){
        Day dayTest = new Day("test_date");
        dayTest.setName("NAME TEST");
        dayTest.setEmotionalScore(1);
        days.add(dayTest);


        @SuppressLint("SdCardPath") String path = "/data/data/com.example.imdaysv2java/files";
        File directory = new File(path);
        File[] files = directory.listFiles();
        if(files!=null){
            for(File file:files){
                FileInputStream fin;
                try {
                    if(file.isFile()&&file.getName().endsWith(".ser")){
                        fin = new FileInputStream(file);
                        ObjectInputStream ois = new ObjectInputStream(fin);
                        Day day = (Day)ois.readObject();
                        System.out.println(day.getDateTime()+ "DATE");
                        days.add(day);
                    }
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}