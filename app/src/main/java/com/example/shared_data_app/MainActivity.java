package com.example.shared_data_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText noteInput;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteInput = findViewById(R.id.noteInput);
        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String note = noteInput.getText().toString();

                SharedPreferences shrd = getSharedPreferences("notes", MODE_PRIVATE);
                SharedPreferences shrd2 = getSharedPreferences("count", MODE_PRIVATE);
                SharedPreferences.Editor editor = shrd.edit();
                SharedPreferences.Editor editor2 = shrd2.edit();

                int noteNum = shrd2.getInt("num", 1);
                Toast.makeText(MainActivity.this, "Note no. " + noteNum, Toast.LENGTH_SHORT).show();
                String noteKey = "note" + noteNum;
                editor.putString(noteKey.toString(), note);
                editor.apply();

                editor2.putInt("num", noteNum + 1);
                editor2.apply();

                Toast.makeText(MainActivity.this, "Saved!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void navToToggle(View view) {
        Intent intent = new Intent(this, NotesActivity.class);
        startActivity(intent);
    }
}