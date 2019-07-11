package com.example.apptiendamobil;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
//package com.example.apptiendamobil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;


import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.apptiendamobil.retrofit.Myservice;
import com.example.apptiendamobil.retrofit.retroFitClient;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.rengwuxian.materialedittext.MaterialEditText;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    TextView txt_register;
    MaterialEditText edt_login_user, edt_login_password;
    Button btn_login;

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    Myservice myService;

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio2);

        Retrofit retrofit = retroFitClient.getInstance();
        myService = retrofit.create(Myservice.class);

        edt_login_user = (MaterialEditText)findViewById(R.id.editText);
        edt_login_password = (MaterialEditText)findViewById(R.id.editText2);
        btn_login = (Button)findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser(edt_login_user.getText().toString(), edt_login_password.getText().toString());
            }
        });

        txt_register = (TextView)findViewById(R.id.registrate);
        txt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View register_layout = LayoutInflater.from(MainActivity.this)
                        .inflate(R.layout.activity_register, null);
                new MaterialStyledDialog.Builder(MainActivity.this)
                        .setIcon(R.drawable.ic_menu_camera)
                        .setTitle("REGISTRO")
                        .setDescription("Por favor llene todos los campos")
                        .setCustomView(register_layout)
                        .setNegativeText("CANCEL")
                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveText("REGISTER")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                MaterialEditText edt_register_email = (MaterialEditText)register_layout.findViewById(R.id.edt_email);
                                MaterialEditText edt_register_user = (MaterialEditText)register_layout.findViewById(R.id.edt_username);
                                MaterialEditText edt_register_password = (MaterialEditText)register_layout.findViewById(R.id.edt_password);
                                setContentView(R.layout.activity_main);

                                if(TextUtils.isEmpty(edt_register_email.getText().toString())){
                                    Toast.makeText(MainActivity.this, "El usuario no puede ser nulo o vacio",Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                if(TextUtils.isEmpty(edt_register_user.getText().toString())){
                                    Toast.makeText(MainActivity.this, "El email no puede ser nulo o vacio",Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                if(TextUtils.isEmpty(edt_register_password.getText().toString())){
                                    Toast.makeText(MainActivity.this, "La constrasena no puede ser nulo o vacio",Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                registerUser(edt_register_email.getText().toString(),
                                        edt_register_user.getText().toString(), edt_register_password.getText().toString());

                            }
                        }).show();
            }
        });




    }

    private void registerUser(String email, String user, String password) {
        compositeDisposable.add(myService.registerUser(email,user,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String response) throws Exception {
                        Toast.makeText(MainActivity.this
                                ,""+response, Toast.LENGTH_SHORT).show();
                    }
                }));
    }

    private void loginUser(String username, String password) {
        if(TextUtils.isEmpty(username)){
            Toast.makeText(this, "El usuario no puede ser nulo o vacio",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Password nulo", Toast.LENGTH_SHORT).show();
            return;
        }
        compositeDisposable.add(myService.loginUser(username,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String response) throws Exception {
                        Toast.makeText(MainActivity.this
                                ,""+response, Toast.LENGTH_SHORT).show();
                    }
                }));
    }

    public void pagina5(View view){
        //Intent cinco = new Intent( this,cinrcoActivity.class);
        //startActivity(cinco);

    }



}
