package com.example.shared_data_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Map;

public class NotesActivity extends AppCompatActivity {

    TextView noteText;
    Button clearButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        noteText = findViewById(R.id.noteText);
        clearButton = findViewById(R.id.clearButton);

        SharedPreferences shr1 = getSharedPreferences("notes", MODE_PRIVATE);
        String note = shr1.getString("note1", "Please create a note!");
        StringBuilder allNotesString = new StringBuilder("<ul>");
        Map<String, ?> allNotes = shr1.getAll();

        for(Map.Entry<String, ?> entry: allNotes.entrySet()) {
                String key = entry.getKey();
                String value = (String) entry.getValue();
                allNotesString.append("<li> ").append(key + " * ").append(value).append(" </li>");
        }

        allNotesString.append("</ul>");

        noteText.setText(Html.fromHtml(allNotesString.toString()));

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noteText.setText("");
                SharedPreferences.Editor editorNotes = shr1.edit();
                editorNotes.clear();
                editorNotes.apply();

                SharedPreferences countShrd = getSharedPreferences("count", MODE_PRIVATE);
                SharedPreferences.Editor numEditor = countShrd.edit();

                numEditor.putInt("num", 1);
                numEditor.apply();
            }
        });
    }
}