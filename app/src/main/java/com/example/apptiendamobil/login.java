package com.example.apptiendamobil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class login extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button otro;
        TextView Ir_Registro;
        otro = (Button)findViewById(R.id.btn2);
        otro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otro = new Intent(login.this,Principal.class);
                startActivity(otro);
            }
        });

        Ir_Registro = (TextView) findViewById(R.id.registrate);
        Ir_Registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Ir_Registro = new Intent(login.this,Principal.class);
                startActivity(Ir_Registro);
            }
        });

    }
}
