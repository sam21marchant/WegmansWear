package com.kobra.wegmanswear;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kobra.wegmanswear.dataobjects.ShoppingListMeta;
import com.kobra.wegmanswear.network.ApiManager;
import com.kobra.wegmanswear.network.ApiService;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class MainActivity extends WearableActivity {

    private TextView mTextView;
    private ApiService apiService;

    /* WearableRecyclerView with curvedLayout support - creates a list
        1. Create list functionality (prioritize touch input first if relavant)
        2. Connect server list to watch
        3. Wegmans header, side menu for different screens?
        4. Always-on functionality
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiService = ApiManager.getInstance().getService();

        mTextView = (TextView) findViewById(R.id.text);

        // Enables Always-on
        setAmbientEnabled();

        //TODO: Get unique user id
        //get data
        Single<Response<ArrayList<ShoppingListMeta>>> responseObservable = apiService.getShoppingLists(1);
        responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Response<ArrayList<ShoppingListMeta>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i("MainActivity", "Subscribed");
                    }

                    @Override
                    public void onSuccess(final Response<ArrayList<ShoppingListMeta>> arrayListResponse) {
                        Log.i("MainActivity", "Success");
                        Button startButton = ((Button) findViewById(R.id.startButton));
                        startButton.setVisibility(View.VISIBLE);

                        startButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                openAllListsActivity(arrayListResponse.body());
                            }
                        });

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("MainActivity", "Could not get Shopping Lists");

                    }
                });

    }


    private void openAllListsActivity(ArrayList<ShoppingListMeta> shoppingLists){
        Intent allListsActivityIntent = new Intent(this, ViewAllListsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("shoppingLists", shoppingLists);
        allListsActivityIntent.putExtras(bundle);
        startActivity(allListsActivityIntent);

    }


    private void reportError(){
        mTextView.setText(R.string.main_activity_error);
    }
}
