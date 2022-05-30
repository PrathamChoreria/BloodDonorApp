package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView empty_imageview;

    MyDatabaseHelper myDB;
    ArrayList<String> blood_id,name,phone,blood_group;
    CustomAdaptor customAdaptor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recycleView);
        add_button=findViewById(R.id.add_button);
        empty_imageview=findViewById(R.id.empty_imageview);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent=new Intent(MainActivity.this,AddActivity.class);
               startActivity(intent);
            }
        });

        myDB=new MyDatabaseHelper(MainActivity.this);
        blood_id=new ArrayList<>();
        name=new ArrayList<>();
        phone=new ArrayList<>();
        blood_group=new ArrayList<>();

        storeDataInArray();

        customAdaptor=new CustomAdaptor(MainActivity.this,this,blood_id,name,phone,blood_group);
        recyclerView.setAdapter(customAdaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1)
            recreate();
    }

    void storeDataInArray(){
        android.database.Cursor cursor=myDB.readAllData();
        if(cursor.getCount() ==0){
           empty_imageview.setVisibility(View.VISIBLE);
        }
        else
        {
            while(cursor.moveToNext()){
                blood_id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                phone.add(cursor.getString(2));
                blood_group.add(cursor.getString(3));
            }
            empty_imageview.setVisibility(View.GONE);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() ==R.id.delete_all){
           confirmDialog();


        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Delete All");
        builder.setMessage("Are you sure you want to delete all?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper myDB=new MyDatabaseHelper(MainActivity.this);

                myDB.deleteAllData();
                Intent intent=new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
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