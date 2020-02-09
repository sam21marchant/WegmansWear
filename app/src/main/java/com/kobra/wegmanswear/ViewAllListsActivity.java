package com.kobra.wegmanswear;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;

import androidx.wear.widget.WearableLinearLayoutManager;
import androidx.wear.widget.WearableRecyclerView;

import java.util.ArrayList;

public class ViewAllListsActivity extends WearableActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewlists);

        WearableRecyclerView rv = findViewById(R.id.listslist);
        rv.setEdgeItemsCenteringEnabled(true);
        //TODO: Get Lists in Arraylist
        ArrayList<ViewItem> lists = new ArrayList<ViewItem>(); //possibly change string to custom class List
        lists.add(new ViewItem("Family list"));
        lists.add(new ViewItem("Work list"));
        //

        //add lists to screen
        rv.setLayoutManager(new WearableLinearLayoutManager(this));
        rv.setAdapter(new CustomAdapter(this, lists, new CustomAdapter.AdapterCallback() {
            @Override
            public void onItemClicked(final Integer listPosition) {
               //switchActivity to the list at listPosition
                startActivity(new Intent(ViewAllListsActivity.this, ListActivity.class));
            }
        }));
    }

}
