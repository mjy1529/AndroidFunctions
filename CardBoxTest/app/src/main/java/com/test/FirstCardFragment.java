package com.test;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class FirstCardFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    GridLayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_card, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        ArrayList<Item> list = new ArrayList<>();
        for(int i = 0; i<10; i++) {
            list.add(new Item("image", R.drawable.image));
        }
        Log.d("dd", list.size() + "");

        adapter = new RecyclerViewAdapter(getContext(), list);
        layoutManager = new GridLayoutManager(getContext(), 3);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }

}
