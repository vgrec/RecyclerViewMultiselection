package com.vgrec.recyclerview.multiselection;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Author vgrec, on 17.04.16.
 */
public class SimpleItemAdapter extends RecyclerView.Adapter<SimpleItemAdapter.ItemViewHolder> {

    private String[] items;

    public SimpleItemAdapter(String[] items) {
        this.items = items;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.itemTextView.setText(items[position]);
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView itemTextView;

        public ItemViewHolder(View view) {
            super(view);
            itemTextView = (TextView) view.findViewById(R.id.item);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
