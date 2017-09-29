package com.lapremavera.androidatccourses;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.StringTokenizer;

import static android.R.attr.id;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    private static ArrayList<DataModel> data;
    public static View.OnClickListener myOnClickListener;
    private static ArrayList<Integer> removedItems;
    private String[] nameArray = {
            "Java Fundamentals for Android Development",
            "Android Application Developmen",
            "Android Security Essentials",
            "Monetize Android Application",
            "Java Fundamentals for Android Development",
            "Android Application Development",
            "Android Security Essentials",
            "Monetize Android Applications"};
    private Integer[] id = {0, 1, 2, 3, 4, 5, 6, 7};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton btn_add = (FloatingActionButton) findViewById(R.id.btn_add);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        myOnClickListener = new MyOnClickListener();
        btn_add.setOnClickListener(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = new ArrayList<>();
        for (int i = 0; i < nameArray.length; i++) {
            data.add(new DataModel(nameArray[i], id[i]

            ));

        }
        removedItems = new ArrayList<>();
        adapter = new CustomAdapter(data);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if (removedItems.size() !=0) {
            addRemovedItemToList();
        }else {
            Toast.makeText(this, "Nothing to add", Toast.LENGTH_SHORT).show();
        }
    }

    private void addRemovedItemToList() {
        int addItemAtListPosition = 0;
        data.add(addItemAtListPosition, new DataModel(nameArray[removedItems.get(0)], id [removedItems.get(0)]
        ));
        adapter.notifyItemInserted(addItemAtListPosition);
        removedItems.remove(0);
    }


    private class MyOnClickListener implements View.OnClickListener {

    public void onClick(View view) {
        removeItem(view);
    }

    private void removeItem(View v) {
        int selectedItemPosition = recyclerView.getChildLayoutPosition(v);
        RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(selectedItemPosition);
        TextView textViewName = (TextView) viewHolder.itemView.findViewById(R.id.textViewName);
        String selectedName = (String) textViewName.getText();
        int selectedItemId = -1;
        for (int i = 0; i < nameArray.length; i++) {
            if (selectedName.equals(nameArray[i])) {
                selectedItemId = id[i];
            }
        }
        removedItems.add(selectedItemId);
        data.remove(selectedItemPosition);
        adapter.notifyItemRemoved(selectedItemPosition);

    }

}
}


