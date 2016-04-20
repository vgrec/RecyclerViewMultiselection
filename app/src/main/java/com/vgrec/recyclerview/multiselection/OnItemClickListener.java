package com.vgrec.recyclerview.multiselection;

import android.view.View;

/**
 * A click listener for items.
 */
public interface OnItemClickListener {

    void onItemClick(View view, int position);

    void onItemLongPress(View view, int position);
}