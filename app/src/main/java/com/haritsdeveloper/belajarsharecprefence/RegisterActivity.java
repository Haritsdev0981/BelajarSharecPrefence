package com.haritsdeveloper.belajarsharecprefence;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    TextInputLayout tInputName, tInputEmail, eInputPass, tInputConfrimPass;
    AutoCompleteTextView etName, etEmail, etPass, etConfrimPass;
    RadioButton rbMale, rbFemale;
    RadioGroup rgGender;
    Button btRgister;
    CheckBox cbAggre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.edtUsername);
        etEmail = findViewById(R.id.edtEmail);
        etPass = findViewById(R.id.edtPass);
        etConfrimPass = findViewById(R.id.edtPassConfrim);
        rgGender = findViewById(R.id.rgGender);
        rbMale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);
        btRgister = findViewById(R.id.btRegister);
        cbAggre = findViewById(R.id.cbAggre);

        btRgister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btRegister){
            String nama = etName.getText().toString();
            String email = etEmail.getText().toString();
            String password = etPass.getText().toString();
            String kelamin = String.valueOf(rgGender.getCheckedRadioButtonId());
            String agree = cbAggre.getText().toString();
            String Confrim = etConfrimPass.getText().toString();


            if (TextUtils.isEmpty(nama)){
                etName.setError("Usenam Can't be empty");
                return;
            }

            if (TextUtils.isEmpty(email)){
                etEmail.setError("email can't be empty");
                return;
            }
            if (TextUtils.isEmpty(password)){
                etPass.setError("The password must not be blank");
                return;
            }
            if (TextUtils.isEmpty(Confrim)){
                etConfrimPass.setError("Confirm Passwords cannot be empty");
                return;
            }
            
            if (!password.equals(Confrim)){
                Toast.makeText(this, "passwords are not the same", Toast.LENGTH_SHORT).show();

                return;
            }

               invalidUser(nama, email, password,kelamin,agree );

        }
    }

    private void invalidUser( String nama, String email, String password, String kelamin, String aggre){
        SaveShared userShared =new SaveShared(this);
        UserModel userModel = userShared.getUser();

        String saveEmail = userModel.getEmai();
        if (email.equals(saveEmail)){
            Toast.makeText(this, "Email sudah terdaftar,silahkan gunakan email lain", Toast.LENGTH_SHORT).show();
        }else {
            saveUser(nama, email, password, kelamin, aggre);
        }
    }


    private void saveUser(String nama, String email, String password, String kelamin, String aggre){
        SaveShared userShared = new SaveShared(this);
        UserModel userModel = new UserModel();
        userModel.setNama(nama);
        userModel.setEmai(email);
        userModel.setPassword(password);
        userModel.setKelamin(kelamin);
        userModel.setAggre(aggre);

        userShared.setUser(userModel);
//        Toast.makeText(this, getString(R.string.succesRegister), Toast.LENGTH_SHORT).show();
        AlertDialog.Builder alert = new AlertDialog.Builder(RegisterActivity.this);
        alert.setTitle(getString(R.string.succesRegister));
        alert.setTitle(getString(R.string.cautionSuscces));
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
            }
        });
        alert.show();
    }
}
