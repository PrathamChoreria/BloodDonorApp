package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    public static final String DATABASE_NAME="BloodBank.db";
    public static final int DATABASE_VERSION=1;

    private static final String  TABLE_NAME="my_bank";
    private static final String COLUMN_ID="_id";
    private static final String COLUMN_NAME="name";
    private static final String COLUMN_PHONE="phone";
    private static final String COLUMN_BLOOD="blood";



     MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE "+TABLE_NAME+
                " ("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_NAME+" TEXT, "+
                COLUMN_PHONE+" TEXT, "+
                COLUMN_BLOOD+" TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    void addBlood(String name,String phone,String blood){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(COLUMN_NAME,name);
        cv.put(COLUMN_PHONE,phone);
        cv.put(COLUMN_BLOOD,blood);

        long result=db.insert(TABLE_NAME,null,cv);
        if(result==-1){
            Toast.makeText(context, "Failed to request", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Request success", Toast.LENGTH_SHORT).show();
        }

    }

    android.database.Cursor readAllData(){
        String query ="SELECT * FROM "+ TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();

        android.database.Cursor cursor=null;
        if(db!=null){
            cursor=db.rawQuery(query,null);
        }
        return cursor;
    }

    void updateData(String row_id,String name,String phone,String blood){
         SQLiteDatabase db=this.getWritableDatabase();
         ContentValues cv=new ContentValues();
         cv.put(COLUMN_NAME,name);
         cv.put(COLUMN_PHONE,phone);
         cv.put(COLUMN_BLOOD,blood);

        long result=  db.update(TABLE_NAME,cv,"_id=?",new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to update. ", Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneRow(String row_id){
         SQLiteDatabase db=this.getWritableDatabase();
         long result= db.delete(TABLE_NAME, "_id=?",new String[]{row_id});

         if(result==-1){
             Toast.makeText(context, "OOPS!looks like record wants to stay here", Toast.LENGTH_SHORT).show();
         }else
         {
             Toast.makeText(context, "Record deleted", Toast.LENGTH_SHORT).show();
         }
    }
    void deleteAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME);
    }
}
