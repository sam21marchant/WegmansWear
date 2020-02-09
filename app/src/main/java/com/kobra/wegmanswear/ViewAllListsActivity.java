package com.kobra.wegmanswear;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.view.View;

import android.view.ViewGroup;

import androidx.wear.widget.WearableLinearLayoutManager;
import androidx.wear.widget.WearableRecyclerView;

import com.kobra.wegmanswear.dataobjects.ShoppingList;
import com.kobra.wegmanswear.dataobjects.ShoppingListMeta;

import java.util.ArrayList;

public class ViewAllListsActivity extends WearableActivity {

    private ArrayList<ShoppingListMeta> shoppingLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        shoppingLists = (ArrayList<ShoppingListMeta>) bundle.getSerializable("shoppingLists");
        Log.i("ViewAllListsActivity", (new Integer(shoppingLists.size())).toString());

        setContentView(R.layout.activity_viewlists);

        WearableRecyclerView rv = findViewById(R.id.listslist);
        rv.setEdgeItemsCenteringEnabled(true);
        //TODO: Get Lists in Arraylist
        final ArrayList<ViewItem> lists = new ArrayList<ViewItem>(); //possibly change string to custom class List
        lists.add(new ViewItem("Family list", true));
        lists.add(new ViewItem("Work list", false));
        //

        //add lists to screen
        rv.setLayoutManager(new WearableLinearLayoutManager(this));
        rv.setAdapter(new CustomAdapter(this, lists, new CustomAdapter.AdapterCallback() {
            @Override
            public void onItemClicked(final Integer listPosition) {
               //switchActivity to the list at listPosition
                ViewItem item = lists.get(listPosition);
                if(item.isList()){
                    Log.i("OnItemClicked", "LIST BUTTON");
                }else{
                    Log.i("OnItemClicked", "RIBBON");
                }
//                startActivity(new Intent(ViewAllListsActivity.this, ListActivity.class));
            }
        }));
    }


}
