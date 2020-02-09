package com.kobra.wegmanswear;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CheckedTextView;

import androidx.wear.widget.WearableLinearLayoutManager;
import androidx.wear.widget.WearableRecyclerView;

import java.util.ArrayList;

public class ListActivity extends WearableActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        WearableRecyclerView rv = findViewById(R.id.listslistproduce);
        rv.setEdgeItemsCenteringEnabled(true);
        //TODO: Get list
        //TODO: Find a way to get list ID passed over from ViewAllListsActivity to get this data
        final ArrayList<ViewItem_Produce> items = new ArrayList<ViewItem_Produce>();
        items.add(new ViewItem_Produce("Apple"));
        items.add(new ViewItem_Produce("Bees"));
        //

        //add items to screen
        rv.setLayoutManager(new WearableLinearLayoutManager(this));
        rv.setAdapter(new CustomAdapterProduce(this, items, new CustomAdapterProduce.AdapterCallback() {
            @Override
            public void onItemClicked(final Integer listPosition) {
                //activate checkmark!

                CheckedTextView ctv = (CheckedTextView) findViewById(R.id.justafuckingtext_produce);
                ViewItem_Produce itemSelected = items.get(listPosition);
                if(!itemSelected.selected) {
                    ctv.setCheckMarkDrawable(R.drawable.checked);
                    ctv.setChecked(true);
                }
                else {
                    ctv.setChecked(false);
                    ctv.setCheckMarkDrawable(null);
                }
            }
        }));



    }

}
