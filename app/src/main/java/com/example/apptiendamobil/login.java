package com.example.apptiendamobil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class login extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button otro;
        otro = (Button)findViewById(R.id.btn2);
        otro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otro = new Intent(login.this,menu.class);
                startActivity(otro);
            }
        });
    }
}
