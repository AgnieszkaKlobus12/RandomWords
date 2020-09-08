package com.alia.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.alia.database.ManageWords;

public class ChangeBaseActivity extends AppCompatActivity {

    private Context mContext;
    private Intent intent;
    private ManageWords manageWords;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_base);

        Button addWord = findViewById(R.id.addButton);
        Button deleteWord = findViewById(R.id.deleteButton);
        Button back = findViewById(R.id.back);

        editText = findViewById(R.id.getWord);
        intent = new Intent(this, MainActivity.class);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

        mContext = getApplicationContext();
        manageWords = new ManageWords(mContext);

        addWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean add = manageWords.addWord(editText.getText().toString().toLowerCase().trim());
                if (add) {
                    Toast.makeText(mContext, "Dodano", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "Już w bazie", Toast.LENGTH_SHORT).show();
                }
            }
        });

        deleteWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean delete = manageWords.delete(editText.getText().toString().toLowerCase().trim());
                if (delete) {
                    Toast.makeText(mContext, "Usunięto", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "Nie znaleziono", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}