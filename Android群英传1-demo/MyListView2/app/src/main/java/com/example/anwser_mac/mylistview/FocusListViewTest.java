package com.example.anwser_mac.mylistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FocusListViewTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.focus);
        ListView listView = (ListView) findViewById(R.id.focus_listview);
        List<String> data = new ArrayList<>();
        data.add("item 1");
        data.add("item 2");
        data.add("item 3");
        data.add("item 4");
        data.add("item 5");
        final FocusListViewAdapter adapter = new FocusListViewAdapter(this, data);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setmCurrentItem(position);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
