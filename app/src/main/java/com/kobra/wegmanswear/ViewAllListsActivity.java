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
import com.kobra.wegmanswear.network.ApiManager;
import com.kobra.wegmanswear.network.ApiService;

import java.util.ArrayList;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ViewAllListsActivity extends WearableActivity {

    private ArrayList<ShoppingListMeta> shoppingLists;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.apiService = ApiManager.getInstance().getService();

        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        shoppingLists = (ArrayList<ShoppingListMeta>) bundle.getSerializable("shoppingLists");
        Log.i("ViewAllListsActivity", (new Integer(shoppingLists.size())).toString());

        setContentView(R.layout.activity_viewlists);

        WearableRecyclerView rv = findViewById(R.id.listslist);
        rv.setEdgeItemsCenteringEnabled(true);


        final ArrayList<ViewItem> lists = new ArrayList<ViewItem>();

        lists.add(new ViewItem("Shopping Lists", null));
        for(ShoppingListMeta meta : shoppingLists){
            lists.add(new ViewItem(meta));
        }

        //add lists to screen
        rv.setLayoutManager(new WearableLinearLayoutManager(this));
        rv.setAdapter(new CustomAdapter(this, lists, new CustomAdapter.AdapterCallback() {
            @Override
            public void onItemClicked(final Integer listPosition) {
               //switchActivity to the list at listPosition
                ViewItem item = lists.get(listPosition);
                if(item.isList()){
                    Log.i("OnItemClicked", "LIST BUTTON " + item.getText());

                    int listId = item.getShoppingLists().getListId();
                    int userId = item.getShoppingLists().getUserId();

                    Single<Response<ShoppingList>> responseObservable = apiService.getShoppingList(listId,userId);
                    responseObservable.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new SingleObserver<Response<ShoppingList>>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                    Log.i("ViewAllListsActivity", "Subscribed");
                                }

                                @Override
                                public void onSuccess(Response<ShoppingList> arrayListResponse) {
                                    Log.i("ViewAllListsActivity", "Success");
                                    openListsActivity(arrayListResponse.body());
                                }

                                @Override
                                public void onError(Throwable e) {
                                    Log.e("ViewAllListsActivity", "Could not get Shopping List");
                                    Log.e("ViewAllListsActivity", e.getMessage());

                                }
                            });
                }else{
                    Log.i("OnItemClicked", "RIBBON " + item.getText());
                }
//                startActivity(new Intent(ViewAllListsActivity.this, ListActivity.class));
            }
        }));
    }


    private void openListsActivity(ShoppingList shoppingList){
        Intent allListsActivityIntent = new Intent(this, ListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("products", shoppingList);
        allListsActivityIntent.putExtras(bundle);
        startActivity(allListsActivityIntent);
    }


}
