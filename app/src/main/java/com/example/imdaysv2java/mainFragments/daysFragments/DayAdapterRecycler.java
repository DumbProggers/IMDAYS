package com.example.imdaysv2java.mainFragments.daysFragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imdaysv2java.Day;
import com.example.imdaysv2java.R;

import java.util.ArrayList;

public class DayAdapterRecycler extends RecyclerView.Adapter<DayAdapterRecycler.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    ArrayList<Day> listDay = new ArrayList<>();
    Context context;
    public DayAdapterRecycler(Context context, ArrayList<Day> listDay,RecyclerViewInterface recyclerViewInterface){
        this.listDay = listDay;
        System.out.println(listDay.size()+"HELLO FROM KONSTRUKTOR");
        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //раздуваем макет и придаем вид каждому из наших строк
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.list_item,parent,false);
        return new DayAdapterRecycler.MyViewHolder(view,recyclerViewInterface);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //назначаем значения для каждых из наших строк
        holder.descr.setText(listDay.get(position).getName());
        holder.date.setText(listDay.get(position).getDateTime());
        int emocionalScore = listDay.get(position).getEmotionalScore();
        switch (emocionalScore){
            case 0:
                holder.imageView.setImageResource(R.drawable.emocional_score4);
                break;
            case 1:
                holder.imageView.setImageResource(R.drawable.emocional_score4);
                break;
            case 2:
                holder.imageView.setImageResource(R.drawable.emocional_score4);
                break;
            case 3:
                holder.imageView.setImageResource(R.drawable.emocional_score4);
                break;
            case 4:
                holder.imageView.setImageResource(R.drawable.emocional_score4);
                break;
            case 5:
                holder.imageView.setImageResource(R.drawable.emocional_score4);
                break;
            case 6:
                holder.imageView.setImageResource(R.drawable.emocional_score4);
                break;
            case 7:
                holder.imageView.setImageResource(R.drawable.emocional_score4);
                break;
        }
    }
    @Override
    public int getItemCount() {
        //узнает сколько всего элементов имеется
        return listDay.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView descr;
        TextView date;
        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            imageView = itemView.findViewById(R.id.listImage);
            descr = itemView.findViewById(R.id.textDayDescrTextView);
            date = itemView.findViewById(R.id.dateTextView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface!=null){
                        int pos = getAdapterPosition();
                        if(pos!=RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos, view);
                        }
                    }
                }
            });
        }
    }
}
