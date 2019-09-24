package com.haritsdeveloper.belajarsharecprefence;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{


    AutoCompleteTextView etUsername, etPassword;
    TextView tvRegister, tvForgot;
    Button btLogin;
    SaveShared saveShared;
    UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.edtUsername);
        etPassword = findViewById(R.id.edtPass);
        tvRegister = findViewById(R.id.tvRegister);
        tvForgot = findViewById(R.id.tvForgot);
        btLogin = findViewById(R.id.btLogin);

        btLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        tvForgot.setOnClickListener(this);

        saveShared = new SaveShared(this);
        checkLogin();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btLogin:
                String userName = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                if (TextUtils.isEmpty(userName)){
                    etUsername.setError("username tidak boleh kosong");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    etPassword.setError("password tidak boleh kosong");
                    return;
                }
                if (!isValidEmail(userName)){
                    Toast.makeText(this, "email tidak falid", Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(this, "email tidak terdaftar, silahkan Register", Toast.LENGTH_SHORT).show();
                validateLogin(userName, password);
                break;
            case R.id.tvRegister:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
            case R.id.tvForgot:
                validateForgot();
                break;
        }
    }

    private void validateForgot(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        userModel = saveShared.getUser();
        String showPasword = userModel.getPassword();
        alert.setTitle("password kamu adalah :"+showPasword);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.show();
    }

    private void validateLogin(String email, String password){
       userModel = saveShared.getUser();
       String saveEmail = userModel.getEmai();
       String savepassword = userModel.getPassword();
       if (email.equals(saveEmail)&& password.equals(savepassword)){
           startActivity(new Intent(LoginActivity.this,MainActivity.class));
           userModel.setStatusLogin(true);
           saveShared.setUser(userModel);
           finish();
       }else {
           AlertDialog.Builder alert = new AlertDialog.Builder(this);
           alert.setTitle(getString(R.string.acountNotRegist));
           alert.setTitle(getString(R.string.pleaseRegister));
           alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int which) {
                   startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                   finish();
               }
           });
           alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int which) {

               }
           });
           alert.show();
       }
    }

    private void checkLogin(){
        userModel = saveShared.getUser();
        boolean statusLogin = userModel.isStatusLogin();
        if (statusLogin==true){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

    private boolean isValidEmail(CharSequence email){
        return !TextUtils.isEmpty(email)&& Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
