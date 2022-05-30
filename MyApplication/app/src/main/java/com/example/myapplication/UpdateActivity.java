package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {


    EditText name_input,blood_input,phone_input;
    Button update_button,delete_button;

    String id,name,phone,blood;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name_input=findViewById(R.id.name_input2);
        blood_input=findViewById(R.id.blood2);
        phone_input=findViewById(R.id.phone2);
        update_button=findViewById(R.id.update_button);
        delete_button=findViewById(R.id.delete_button);

        getAndSetIntentData();

        update_button.setOnClickListener((view) -> {


            name=name_input.getText().toString().trim();
            phone=phone_input.getText().toString().trim();
            blood=blood_input.getText().toString().trim();
                MyDatabaseHelper myDB=new MyDatabaseHelper(UpdateActivity.this);
                myDB.updateData(id,name,phone,blood);

        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                confirmDialog();
            }
        });

        getAndSetIntentData();
//set actionbar title after getandsetintent
        androidx.appcompat.app.ActionBar ab=getSupportActionBar();
        if(ab!=null) {
            ab.setTitle(blood);
        }
    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("phone") &&
                getIntent().hasExtra("bloodgroup")) {

            id=getIntent().getStringExtra("id");
            name=getIntent().getStringExtra("name");
            phone=getIntent().getStringExtra("phone");
            blood=getIntent().getStringExtra("bloodgroup");


            name_input.setText(name);
            blood_input.setText(blood);
            phone_input.setText(phone);

        }else
        {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }
    void confirmDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Delete "+blood+" ?");
        builder.setMessage("Are you sure you want to delete "+blood+" ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper myDB=new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}