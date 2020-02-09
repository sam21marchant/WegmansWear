package com.kobra.wegmanswear;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends WearableActivity {

    private TextView mTextView;

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

        mTextView = (TextView) findViewById(R.id.text);

        //placeholder code, replace timer with waiting for connection then running
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                // this code will be executed after 5 seconds
                startActivity(new Intent(MainActivity.this, ViewAllListsActivity.class));
            }
        }, 5000);

        // Enables Always-on
        setAmbientEnabled();
    }


}
