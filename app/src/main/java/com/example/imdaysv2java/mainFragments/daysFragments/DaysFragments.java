package com.example.imdaysv2java.mainFragments.daysFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.imdaysv2java.Day;
import com.example.imdaysv2java.R;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DaysFragments#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DaysFragments extends Fragment implements RecyclerViewInterface{

    ArrayList<Day> days = new ArrayList<>();



    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public DaysFragments() {
        // Required empty public constructor
    }

    public static DaysFragments newInstance(String param1, String param2) {
        DaysFragments fragment = new DaysFragments();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list_days, container, false);
        acceptDays();


        RecyclerView recyclerView = view.findViewById(R.id.list);
        DayAdapterRecycler dayAdapterRecycler = new DayAdapterRecycler(view.getContext(), days, this);
        recyclerView.setAdapter(dayAdapterRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        return view;

    }
    @Override
    public void onItemClick(int position, View view) {
        System.out.println(days.get(position).getName()+" CLICK");
    }
    private void acceptDays(){
        days = (ArrayList<Day>) getArguments().getSerializable("days");
    }
}