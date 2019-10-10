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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class Login extends AppCompatActivity implements View.OnClickListener {

    //defining view objects

    private EditText TextEmail;
    private EditText TextPassword;
    private Button btnRegistrar;
    private Button btnLogin;
    private ProgressDialog progressDialog;

    //Declaramos un objeto FirebaseAuth
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //inicializamos el objeto firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        //referenciamos los views

        TextEmail = (EditText) findViewById(R.id.txt_email);
        TextPassword = (EditText) findViewById(R.id.txt_contra);
        btnLogin = (Button) findViewById(R.id.botonLogin);
        btnRegistrar = (Button) findViewById(R.id.botonRegistrar);

        progressDialog = new ProgressDialog(this);

        //attaching listener to button

        btnRegistrar.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }
        private void LoginUsuario(){
            //obtenemos el email y la contaseña desde los edittext
            final String email = TextEmail.getText().toString().trim();
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

            progressDialog.setMessage("Comparando datos en linea...");
            progressDialog.show();

            //consultar a  user
            firebaseAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //checking if success
                            if(task.isSuccessful()){
                                Toast.makeText(Login.this,"Bienvenido " + TextEmail.getText(),Toast.LENGTH_LONG).show();
                                Intent on= new Intent(Login.this,Counter.class);
                                on.putExtra(Counter.user,email);
                                startActivity(on);

                            }else {
                                if(task.getException() instanceof FirebaseAuthUserCollisionException){//si se presenta una colision
                                    Toast.makeText(Login.this,"Ese usuario ya existe",Toast.LENGTH_SHORT).show();

                                }else {
                                    Toast.makeText(Login.this, "No se pudo registrar el usuario", Toast.LENGTH_LONG).show();
                                }
                            }
                            progressDialog.dismiss();
                        }
                    });



    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(Login.this,Registro.class);

        switch (view.getId()){

            case R.id.botonLogin:
                LoginUsuario();
                break;

            case R.id.botonRegistrar:
                startActivity(i);

                break;

        }


    }
}
