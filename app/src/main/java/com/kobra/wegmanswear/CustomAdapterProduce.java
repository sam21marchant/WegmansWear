package com.kobra.wegmanswear;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.wear.widget.BoxInsetLayout;


import com.kobra.wegmanswear.dataobjects.WegmansProduct;

import java.util.ArrayList;

public class CustomAdapterProduce extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<ViewItem_Produce> dataSource;
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


    /**
     * For the checkboxes
     */
    public static class RecyclerViewHolder0 extends RecyclerView.ViewHolder
    {
        BoxInsetLayout rv;
        CheckedTextView simpleItem;

        public RecyclerViewHolder0(View view) {
            super(view);
            rv = view.findViewById(R.id.big_container_produce);
            simpleItem = view.findViewById(R.id.justafuckingtext_produce);
        }
    }


    /**
     * For the Ribbons
     */
    public static class RecyclerViewHolder2 extends RecyclerView.ViewHolder{
        RelativeLayout rv;
        TextView tv;

        public RecyclerViewHolder2(@NonNull View itemView) {
            super(itemView);
            rv = itemView.findViewById(R.id.big_container);
            tv = itemView.findViewById(R.id.justafuckingtext);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;

        switch (viewType){
            case 0:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_view_checkbox,parent,false);
                holder = new RecyclerViewHolder0(view);
                break;
            case 2:
                View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_view, parent, false);
                holder = new RecyclerViewHolder2(view1);
                break;

        }

        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        ViewItem_Produce item = dataSource.get(position);
        if(item.getProd() == null){
            return 2;
        }

        return 0;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewItem_Produce data_provider = dataSource.get(position);

        switch (holder.getItemViewType()){
            case 0:
                ((RecyclerViewHolder0) holder).simpleItem.setText(data_provider.getText());
                ((RecyclerViewHolder0) holder).rv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        if(callback != null) {
                            callback.onItemClicked(position);
                        }
                    }
                });
                break;
            case 2:
                ((RecyclerViewHolder2) holder).tv.setText(data_provider.getText());

        }

    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }
}

class ViewItem_Produce {
    private String text;
    private WegmansProduct prod;

    public ViewItem_Produce(String text, WegmansProduct pro) {
        this.text = text;
        prod = pro;
    }

    public ViewItem_Produce(WegmansProduct pro) {
        this.text = pro.getName() + "\tqty: " + pro.getQty();
        prod = pro;
    }

    public String getText() {
        return text;
    }

    public WegmansProduct getProd() {
        return prod;
    }

    public void setProd(WegmansProduct prod) {
        this.prod = prod;
    }

}