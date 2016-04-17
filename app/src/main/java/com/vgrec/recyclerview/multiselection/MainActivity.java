package com.vgrec.recyclerview.multiselection;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private Multiselector multiselector = new Multiselector();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        multiselector.onRestoreInstanceState(savedInstanceState);

        SimpleItemAdapter adapter = new SimpleItemAdapter(generateDummyItems(), multiselector);
        recyclerView.setAdapter(adapter);
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
    }
}
