package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText name_input,blood,phone;
    android.widget.Button add_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name_input=findViewById(R.id.name_input);
        blood=findViewById(R.id.blood);
        phone=findViewById(R.id.phone);
        add_button=findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               MyDatabaseHelper myDB=new MyDatabaseHelper(AddActivity.this);
               myDB.addBlood(name_input.getText().toString().trim(),phone.getText().toString().trim(),
                       blood.getText().toString().trim());
            }
        });
    }
}