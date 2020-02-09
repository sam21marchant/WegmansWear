package com.kobra.wegmanswear;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CheckedTextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.wear.widget.BoxInsetLayout;
import androidx.wear.widget.WearableLinearLayoutManager;
import androidx.wear.widget.WearableRecyclerView;

import com.kobra.wegmanswear.dataobjects.ShoppingList;
import com.kobra.wegmanswear.dataobjects.ShoppingListMeta;
import com.kobra.wegmanswear.dataobjects.WegmansProduct;
import com.kobra.wegmanswear.network.ApiManager;

import java.util.ArrayList;

public class ListActivity extends WearableActivity {
    private ShoppingList shoppingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        shoppingList = (ShoppingList) bundle.getSerializable("products");
        //Log.i("ListActivity", (new Integer(shoppingList.getProducts().size())).toString());
        setContentView(R.layout.activity_list);

        final WearableRecyclerView rv = findViewById(R.id.listslistproduce);
        rv.setEdgeItemsCenteringEnabled(true);
        ArrayList<ViewItem_Produce> items = new ArrayList<ViewItem_Produce>();
       // items.add(new ViewItem_Produce("Apple"));
       // items.add(new ViewItem_Produce("Bees"));
        if(shoppingList == null) {
            items.add(new ViewItem_Produce("List: " + shoppingList.getName(), null));
            items.add(new ViewItem_Produce("No items in list", null));
        }
        else {
            items.add(new ViewItem_Produce("List: " + shoppingList.getName(), null));
            String location = "";

            for (WegmansProduct product : shoppingList.getProducts()) {
                if(!product.getLocation().equals(location)){
                    location = product.getLocation();
                    items.add(new ViewItem_Produce(location, null));
                }

                items.add(new ViewItem_Produce(product));
            }
        }
        //add items to screen
        rv.setLayoutManager(new WearableLinearLayoutManager(this));
        rv.setAdapter(new CustomAdapterProduce(this, items, new CustomAdapterProduce.AdapterCallback() {
            @Override
            public void onItemClicked(final Integer listPosition) {
                RecyclerView.ViewHolder item = rv.findViewHolderForAdapterPosition(listPosition);

                CheckedTextView itemSelected = (CheckedTextView) ((BoxInsetLayout)(item.itemView)).getChildAt(0); //requires simple_view_checkbox to only have one child
                if(!(itemSelected).isChecked()) {
                    itemSelected.setCheckMarkDrawable(R.drawable.checked);
                    itemSelected.setChecked(true);
                }
                else {
                    itemSelected.setChecked(false);
                    itemSelected.setCheckMarkDrawable(null);
                }
            }
        }));



    }

}
