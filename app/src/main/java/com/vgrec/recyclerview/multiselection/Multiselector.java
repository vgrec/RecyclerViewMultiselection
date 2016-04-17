package com.vgrec.recyclerview.multiselection;

import android.os.Bundle;
import android.view.View;

/**
 * Helper class to keep track of the checked items.
 */
public class Multiselector {
    private static final String CHECKED_STATES = "checked_states";

    private ParcelableSparseBooleanArray checkedItems = new ParcelableSparseBooleanArray();


    public void onSaveInstanceState(Bundle state) {
        if (state != null) {
            state.putParcelable(CHECKED_STATES, checkedItems);
        }
    }

    public void onRestoreInstanceState(Bundle state) {
        if (state != null) {
            checkedItems = state.getParcelable(CHECKED_STATES);
        }
    }

    public boolean isChecked(int position) {
        return checkedItems.get(position, false);
    }

    public void onChecked(int position, boolean isChecked) {
        if (isChecked) {
            checkedItems.put(position, true);
        } else {
            checkedItems.delete(position);
        }
    }
}
