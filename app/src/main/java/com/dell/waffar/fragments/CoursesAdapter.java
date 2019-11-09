package com.dell.waffar.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dell.waffar.R;
import com.dell.waffar.model.Courses;

import java.util.ArrayList;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.ViewHolder> {
    final private ListItemClickListener mOnClickListener;

    ArrayList<Courses> courses;

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public CoursesAdapter(ArrayList<Courses> course , ListItemClickListener listener){
        courses=course;
        mOnClickListener = listener;
    }

    @NonNull
    @Override
    public CoursesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.courses_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);

        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoursesAdapter.ViewHolder viewHolder, int i) {
//        viewHolder.imageView.setImageResource(courses.get(i).getImage());
        viewHolder.mName.setText(courses.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        ImageView imageView;
        TextView mName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.r_txt);
            imageView = itemView.findViewById(R.id.r_img);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickPosition);
        }
    }
}
