package com.example.splitac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class Registro extends AppCompatActivity implements View.OnClickListener {

    //defining view objects
    private EditText TextUsername;
    private EditText TextEmail;
    private EditText TextPassword;
    private Button btnRegistrar;
    private ProgressDialog progressDialog;

    //Declaramos un objeto FirebaseAuth
    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //inicializamos el objeto firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        //referenciamos los views
        TextUsername = (EditText)findViewById(R.id.txt_username);
        TextEmail= (EditText) findViewById(R.id.txt_email);
        TextPassword = (EditText)findViewById(R.id.txt_contra);

        btnRegistrar=(Button)findViewById(R.id.botonRegistrar);

        progressDialog= new ProgressDialog(this);

        //attaching listener to button
        btnRegistrar.setOnClickListener(this);
    }

    private void registrarUsuario(){
        //obtenemos el email y la contaseña desde los edittext
        String email = TextEmail.getText().toString().trim();
        String password = TextPassword.getText().toString().trim();

        //varificamos que las cajas de texto no esten vacias
        if(TextUtils.isEmpty(email)){ //(precio.equals(""))
            Toast.makeText(this, "Se debe ingresar un Email", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)){ //(precio.equals(""))
            Toast.makeText(this, "Se debe ingresar la contraseña", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Realizando registro en linea...");
        progressDialog.show();

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){
                            Toast.makeText(Registro.this,"Se ha registrado el usuario con el email " + TextEmail.getText(),Toast.LENGTH_LONG).show();

                            Intent in = new Intent(Registro.this, Login.class);
                            startActivity(in);
                        }else {
                            if(task.getException() instanceof FirebaseAuthUserCollisionException){//si se presenta una colision
                                Toast.makeText(Registro.this,"Ese usuario ya existe",Toast.LENGTH_SHORT).show();

                            }else {
                                Toast.makeText(Registro.this, "No se pudo registrar el usuario", Toast.LENGTH_LONG).show();
                            }
                        }
                        progressDialog.dismiss();
                    }
                });





    }
    @Override
    public void onClick(View view){
        //invocamos al metodo
        registrarUsuario();
    }


}
