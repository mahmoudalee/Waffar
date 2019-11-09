package com.dell.waffar.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.dell.waffar.R;
import com.dell.waffar.RoomActivity;
import com.dell.waffar.model.Courses;
import com.dell.waffar.model.HomeCourses;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RoomsFragment extends Fragment
        implements CoursesAdapter.ListItemClickListener {

    RecyclerView mRecyclerView;
//    RecyclerView.LayoutManager mLayoutManager;
    GridLayoutManager gridLayoutManager;
    RecyclerView.Adapter mAdapter;
    HomeCourses homeCourses = new HomeCourses();
    ArrayList<Courses> courses;

    public RoomsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        courses = homeCourses.CreateCourses();

        return inflater.inflate(R.layout.fragment_rooms, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_rooms);
        mRecyclerView.setHasFixedSize(true);
        gridLayoutManager = new GridLayoutManager(getContext() ,2);
//        mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mAdapter = new CoursesAdapter(courses, this);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onListItemClick(int itemIndex) {
        Intent intent = new Intent(getActivity() , RoomActivity.class);
        startActivity(intent);
    }
}
