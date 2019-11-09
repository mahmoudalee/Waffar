package com.dell.waffar.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dell.waffar.R;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
//
//        // Inflate the layout for this fragment

       return rootView;
    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


    }

//    public static void setData(String title) {
//        flatsAdapter.add(title);
//    }
}
