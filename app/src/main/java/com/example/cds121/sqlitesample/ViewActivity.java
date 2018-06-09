package com.example.cds121.sqlitesample;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ViewActivity extends AppCompatActivity {
    SQLiteDatabase mydb;
    TextView viewtext;
    Button drop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        viewtext=findViewById(R.id.viewdetials);
        drop=findViewById(R.id.drop);
        drop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropTable();
            }
        });
        try {
            mydb = getApplicationContext().openOrCreateDatabase(Appconstant.DATABASE, Context.MODE_PRIVATE, null);
            Cursor allrows = mydb.rawQuery("SELECT * FROM " + Appconstant.ORDERTABLE, null);
            //  Toast.makeText(getActivity(), "Size" + allrows.getCount(), Toast.LENGTH_SHORT).show();
            allrows.moveToFirst();
            if (allrows != null) {
                do {
                    Log.d("Success", "check1" + allrows.getString(0));
////
                    Log.d("Success", "SYNC Activity" + (allrows.getString(0) + allrows.getString(1)));
                    viewtext.setText((allrows.getString(0) + allrows.getString(1)) );

                }
                while (allrows.moveToNext());
                Log.d("Success", "SYNC Comes on End");

            }else {
                Log.d("Fialed ", "No Data");
            }

        } catch (Exception ex) {
            Log.d("ERRPR", "" + ex);
        }
    }
    public void dropTable(){
        try{
            mydb = getApplicationContext().openOrCreateDatabase(Appconstant.DATABASE, Context.MODE_PRIVATE,null);
            mydb.execSQL("DROP TABLE " + Appconstant.ORDERTABLE);
            mydb.close();
        }catch(Exception e){
            Toast.makeText(getApplicationContext(), "Error encountered while dropping.", Toast.LENGTH_LONG);
        }
    }
}
