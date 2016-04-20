package com.vgrec.recyclerview.multiselection;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String IS_ACTION_MODE = "IS_ACTION_MODE";
    private Multiselector multiselector;
    private ActionMode actionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        multiselector = new Multiselector(recyclerView);
        multiselector.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null) {
            boolean isInActionMode = savedInstanceState.getBoolean(IS_ACTION_MODE);
            if (isInActionMode) {
                startActionMode();
                updateActionModeTitle();
            }
        }

        SimpleItemAdapter adapter = new SimpleItemAdapter(generateDummyItems(), multiselector, itemClickListener);
        recyclerView.setAdapter(adapter);
    }

    private void updateActionModeTitle() {
        actionMode.setTitle(String.valueOf(multiselector.getCount()));
    }

    private void startActionMode() {
        actionMode = startSupportActionMode(actionModeCallback);
    }

    private String[] generateDummyItems() {
        int totalNumberOfItems = 30;
        String[] items = new String[totalNumberOfItems];
        for (int i = 0; i < items.length; i++) {
            items[i] = "Item " + i;
        }
        return items;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        multiselector.onSaveInstanceState(outState);
        outState.putBoolean(IS_ACTION_MODE, actionMode != null);
    }

    private OnItemClickListener itemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            if (isMultiselectionEnabled()) {
                multiselector.checkView(view, position);
                updateActionModeTitle();
            }
        }

        @Override
        public void onItemLongPress(View view, int position) {
            if (!isMultiselectionEnabled()) {
                startActionMode();
            }
            multiselector.checkView(view, position);
            updateActionModeTitle();
        }
    };

    private boolean isMultiselectionEnabled() {
        return actionMode != null;
    }

    private ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.menu_action_mode, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.delete:
                    Toast.makeText(MainActivity.this, "Delete Action", Toast.LENGTH_LONG).show();
                    multiselector.clearAll();
                    mode.finish();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;
        }
    };
}
