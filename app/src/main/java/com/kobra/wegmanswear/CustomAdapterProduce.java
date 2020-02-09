package com.kobra.wegmanswear;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.wear.widget.BoxInsetLayout;


import java.util.ArrayList;

public class CustomAdapterProduce extends RecyclerView.Adapter<CustomAdapterProduce.RecyclerViewHolder> {

    private ArrayList<ViewItem_Produce> dataSource = new ArrayList<ViewItem_Produce>();
    public interface AdapterCallback{
        void onItemClicked(Integer menuPosition);
    }
    private AdapterCallback callback;

    private Context context;


    public CustomAdapterProduce(Context context, ArrayList<ViewItem_Produce> dataArgs, AdapterCallback callback){
        this.context = context;
        this.dataSource = dataArgs;
        this.callback = callback;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_view_checkbox,parent,false);

        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);

        return recyclerViewHolder;
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder
    {
        BoxInsetLayout rv;
        CheckedTextView simpleItem;

        public RecyclerViewHolder(View view) {
            super(view);
            rv = view.findViewById(R.id.big_container_produce);
            simpleItem = view.findViewById(R.id.justafuckingtext_produce);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        ViewItem_Produce data_provider = dataSource.get(position);

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

class ViewItem_Produce {
    private String text;
    public ViewItem_Produce(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}