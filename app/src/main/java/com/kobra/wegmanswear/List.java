package com.kobra.wegmanswear;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class List extends RecyclerView.ViewHolder {
    private TextView simpleTextView;
    public List(final View itemView) {
        super(itemView);
        simpleTextView = (TextView) itemView.findViewById(R.id.wearable_support_nav_drawer_text);
    }

}
