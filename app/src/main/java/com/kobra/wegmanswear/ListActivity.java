package com.kobra.wegmanswear;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;

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
        ArrayList<ViewItem_Produce> items = new ArrayList<ViewItem_Produce>();
        items.add(new ViewItem_Produce("Apple"));
        items.add(new ViewItem_Produce("Bees"));
        //

        //add lists to screen
        rv.setLayoutManager(new WearableLinearLayoutManager(this));
        rv.setAdapter(new CustomAdapterProduce(this, items, new CustomAdapterProduce.AdapterCallback() {
            @Override
            public void onItemClicked(final Integer listPosition) {
                //activate checkmark!

                System.out.println("Check it!");
            }
        }));



    }

}
