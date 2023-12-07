package com.example.imdaysv2java;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Day implements Serializable{
    private String description_most_important,
            name,
            dateTime;
    private int imageRecurse,
            emotionalScore;



    private List<String> tags = new ArrayList<>();

    public String getInfo(){
        return "Day date:"+dateTime+"\n"+
                "Name: "+name+"\n";
    }

    public Day(){}
    public Day(String dateTime) {
        this.dateTime = dateTime;
    }



    public String getDateTime() {
        return dateTime;
    }
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }


    public List<String> getTags() {
        return tags;
    }
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }



    public String getDescription_most_important() {
        return description_most_important;
    }
    public void setDescription_most_important(String description_most_important) {
        this.description_most_important = description_most_important;
    }


    public int getImageRecurse() {
        return imageRecurse;
    }
    public void setImageRecurse(int imageRecurse) {
        this.imageRecurse = imageRecurse;
    }


    public int getEmotionalScore() {
        return emotionalScore;
    }
    public void setEmotionalScore(int emotionalScore) {
        this.emotionalScore = emotionalScore;
    }

}
