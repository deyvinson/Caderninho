package com.example.aluno.caderninho;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private EditText editTextNome;
    private EditText editTextLogin;
    private EditText editTextSenha;
    private EditText editTextReSenha;
    private Button buttonCadastrar;

    private Login app;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cadastro_usuario);

        editTextNome    = (EditText) findViewById(R.id.editTextNome);
        editTextLogin   = (EditText) findViewById(R.id.editTextLogin);
        editTextSenha   = (EditText) findViewById(R.id.editTextSenha);
        editTextReSenha = (EditText) findViewById(R.id.editTextReSenha);
        buttonCadastrar = (Button) findViewById(R.id.buttonCadastrar);

        app = new Login();
        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ret = app.cadastrar( CadastroUsuarioActivity.this
                        ,editTextNome.getText().toString()
                        ,editTextLogin.getText().toString()
                        ,editTextSenha.getText().toString()
                        ,editTextReSenha.getText().toString());

                if (ret.equals("OK")){
                    Toast.makeText(CadastroUsuarioActivity.this, "Cadastro Realizado Com Sucesso",Toast.LENGTH_SHORT).show();
                    CadastroUsuarioActivity.this.finish();
                }else {
                    Toast.makeText(CadastroUsuarioActivity.this, ret,Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
