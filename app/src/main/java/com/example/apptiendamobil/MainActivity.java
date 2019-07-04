package com.example.apptiendamobil;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

   // private TextView texto1;

    //private Typeface kaibon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //String fuente1 = "fuentes/kaibon.otf";
        //this.kaibon= Typeface.createFromAsset(getAssets(),fuente1);
        //texto1 = (TextView) findViewById(R.id.txt2);
        //texto1.setTypeface(kaibon);

        Button siguiente;

        siguiente = (Button)findViewById(R.id.btn1);
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent siguiente = new Intent(MainActivity.this,login.class);
                startActivity(siguiente);
            }
        });

    }


}
