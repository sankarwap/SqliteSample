package com.example.cds121.sqlitesample;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    SQLiteDatabase mydb;
    Button save,view;
    EditText phone,Name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        save=findViewById(R.id.save);
        view=findViewById(R.id.view);
        phone=findViewById(R.id.phone);
        Name=findViewById(R.id.Name);
        save.setOnClickListener(this);
        view.setOnClickListener(this);
        createTable();
    }

    public void createTable() {
        try {
            mydb = getApplicationContext().openOrCreateDatabase(Appconstant.DATABASE, Context.MODE_PRIVATE, null);
            //mydb.execSQL("CREATE TABLE IF  NOT EXISTS " + Appconstant.DATATABLES + "(Name TEXT,Phone TEXT);");
            mydb.execSQL("CREATE TABLE IF  NOT EXISTS " + Appconstant.ORDERTABLE + " (Name TEXT,Phone TEXT);");


            // mydb.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error in creating table", Toast.LENGTH_LONG).show();
        }
    }
public void insertTable(){
    try {
        mydb = getApplicationContext().openOrCreateDatabase(Appconstant.DATABASE, Context.MODE_PRIVATE, null);
        mydb.execSQL("INSERT INTO "
                + Appconstant.ORDERTABLE
                + " VALUES ('" + Name.getText().toString() + "','" +  phone.getText().toString()+ "');");

        //mydb.close();
    } catch (Exception e) {
        // Toast.makeText(getActivity(), ""+e, Toast.LENGTH_SHORT).show();
    }
}
    @Override
    public void onClick(View v) {

        if(v==save){
            insertTable();
        }else if(v==view){
            Intent vv=new Intent(getApplicationContext(),ViewActivity.class);
            startActivity(vv);
        }
    }
}
