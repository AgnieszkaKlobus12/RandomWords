package com.alia.randomwords;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class ChooseNumberActivity extends AppCompatActivity {

    private Context mContext;
    private Integer item;
    private Intent changeToMainActivityIntent;
    public final static String MESSAGE_KEY = "com.alia.randomwords.message_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_number);

        changeToMainActivityIntent = new Intent(this, MainActivity.class);
        Activity mActivity = ChooseNumberActivity.this;
        mContext = mActivity.getApplicationContext();
        ListView mListView = findViewById(R.id.list);

        List<Integer> trees = Arrays.asList(
                1, 3, 5, 6, 7, 8, 9, 10, 15
        );

        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(
                mContext,
                android.R.layout.simple_list_item_1,
                trees
        );

        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                item = (Integer) adapterView.getItemAtPosition(i);
                Toast.makeText(mContext, "Wybrano: " + item, Toast.LENGTH_SHORT).show();
                changeToMainActivityIntent.putExtra(MESSAGE_KEY, item.toString());
                startActivity(changeToMainActivityIntent);
            }
        });

    }
}