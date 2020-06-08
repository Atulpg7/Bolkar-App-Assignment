package com.example.assignment_atul.Adapters.Model;

import java.util.ArrayList;


// Model to store Title of videos and List of videos
public class VerticalView {

    String title;
    ArrayList<HorizontalView> arrayList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<HorizontalView> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<HorizontalView> arrayList) {
        this.arrayList = arrayList;
    }
}
