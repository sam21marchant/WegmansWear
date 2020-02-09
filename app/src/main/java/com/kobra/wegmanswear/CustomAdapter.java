package com.kobra.wegmanswear;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.RecyclerViewHolder> {

    private ArrayList<ViewItem> dataSource = new ArrayList<ViewItem>();
    public interface AdapterCallback{
        void onItemClicked(Integer menuPosition);
    }
    private AdapterCallback callback;

    private Context context;


    public CustomAdapter(Context context, ArrayList<ViewItem> dataArgs, AdapterCallback callback){
        this.context = context;
        this.dataSource = dataArgs;
        this.callback = callback;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_view,parent,false);

        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);

        return recyclerViewHolder;
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder
    {
        RelativeLayout rv;
        TextView simpleItem;

        public RecyclerViewHolder(View view) {
            super(view);
            rv = view.findViewById(R.id.big_container);
            simpleItem = view.findViewById(R.id.justafuckingtext);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        ViewItem data_provider = dataSource.get(position);

        holder.simpleItem.setText(data_provider.getText());
        holder.rv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(callback != null) {
                    callback.onItemClicked(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }
}

class ViewItem {
    private String text;
    private boolean isList;

    public ViewItem(String text, boolean isList) {
        this.text = text;
        this.isList = isList;
    }

    public String getText() {
        return text;
    }

    public boolean isList() {
        return isList;
    }
}

