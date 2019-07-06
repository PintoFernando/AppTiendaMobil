package com.example.apptiendamobil;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import android.widget.Button;

import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        /*Button siguiente;

        siguiente = (Button)findViewById(R.id.btn2);
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent siguiente = new Intent(MainActivity.this,login.class);
                startActivity(siguiente);
            }
        });*/

    }
    public void pagina5(View view){
        Intent cinco = new Intent( this,cincoActivity.class);
        startActivity(cinco);

   }



}
