package com.androidatc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class DynamicListViewActivity extends AppCompatActivity {

    private EditText item;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_list_view);
        item = (EditText) findViewById(R.id.itemEditText);
        Button add = (Button) findViewById(R.id.addItemButton);
        ListView dynamicListView = (ListView) findViewById(R.id.itemsListView);

        //initialize the list and add item
        list = new ArrayList<>();
        //initialize the arrayAdapter
        adapter = new ArrayAdapter<>(DynamicListViewActivity.this, android.R.layout.simple_list_item_1, list);
        //setting the adapter to the listView
        dynamicListView.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todoItem = item.getText().toString();
                if (todoItem.length() > 0 ) {
                    //add editText Value to the list
                    list.add(item.getText().toString());
                    //apply Changes on the adapter to refresh the listView
                    adapter.notifyDataSetChanged();
                    item.setText("");
                }
            }
        });

        dynamicListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                //remove the item from the list
                list.remove(position);
                //apply changes on the adapter to refresh the listView
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}
