package com.alia.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.alia.database.ManageWords;

public class ShowWordsActivity extends AppCompatActivity {
    private Intent startMainActivityIntent;
    public final static String MESSAGE_KEY = "com.alia.randomwords.message_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_words);

        Context mContext = getApplicationContext();
        Button backButton = findViewById(R.id.back);
        TextView yourWordsText = findViewById(R.id.yourWords);
        Intent intent = getIntent();

        startMainActivityIntent = new Intent(this, MainActivity.class);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(startMainActivityIntent);
            }
        });

        int amount = Integer.parseInt(intent.getStringExtra(MESSAGE_KEY));
        ManageWords manageWords = new ManageWords(mContext);
        String words = manageWords.getAmount(amount);
        yourWordsText.setText(words);
    }
}