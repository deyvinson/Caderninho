package com.example.aluno.caderninho;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText txtEmail;
    private EditText txtSenha;
    private Button btnCadastrar;
    private Button btnLogin;

    private Login app;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtEmail = findViewById(R.id.txtEmail);
        txtSenha = findViewById(R.id.txtSenha);
        btnLogin = findViewById(R.id.btnLogin);
        btnCadastrar = findViewById(R.id.btnCadastrar);

        app = new Login();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean validate = app.login(LoginActivity.this,
                        txtEmail.getText().toString(),
                        txtSenha.getText().toString());

                if (validate){
                    Intent i = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(i);
                    LoginActivity.this.finish();
                }else{
                    Toast.makeText(LoginActivity.this,"Login invalido",Toast.LENGTH_SHORT).show();
                }
            }
        });



        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this,CadastroUsuarioActivity.class);
                startActivity(i);
            }
        });



    }





}
