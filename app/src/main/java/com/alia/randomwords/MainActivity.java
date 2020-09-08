package com.alia.randomwords;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Intent startChooseNumberActivity;
    private Intent startShowWordsActivity;
    private Intent changeBase;
    private static String amount = "5";
    private TextView textViewNumber;
    public final static String MESSAGE_KEY = "com.alia.randomwords.message_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewNumber = findViewById(R.id.textViewNumber);
        Button getWordsButton = findViewById(R.id.showWordsButton);
        Button chooseAmountButton = findViewById(R.id.chooseAmountButton);
        Button changeBaseButton = findViewById(R.id.changeBaseButton);

        Intent intent = getIntent();
        if (intent.hasExtra(MESSAGE_KEY)) {
            amount = intent.getStringExtra(MESSAGE_KEY);
        }
        textViewNumber.setText(amount);

        startChooseNumberActivity = new Intent(this, ChooseNumberActivity.class);
        chooseAmountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(startChooseNumberActivity);
            }
        });

        startShowWordsActivity = new Intent(this, ShowWordsActivity.class);
        getWordsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startShowWordsActivity.putExtra(MESSAGE_KEY, textViewNumber.getText());
                startActivity(startShowWordsActivity);
            }
        });

        changeBase = new Intent(this, ChangeBaseActivity.class);
        changeBaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(changeBase);
            }
        });
    }
}