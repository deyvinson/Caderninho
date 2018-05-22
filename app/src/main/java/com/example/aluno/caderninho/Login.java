package com.example.aluno.caderninho;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

/**
 * Created by aluno on 16/04/18.
 */

public class Login {

    public boolean login(Context contexto, String usuario, String senha){
        boolean validation = true;

        SharedPreferences sharedPref = contexto.getSharedPreferences("AppLoginExemplo", Context.MODE_PRIVATE);
        String loginDB = sharedPref.getString("login","^7j*^$89");
        String senhaDB = sharedPref.getString("senha","^7j*^$89");

        if (!usuario.equals(loginDB) || !senha.equals(senhaDB)) {
            validation = false;
        }
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("validation", validation);
        editor.commit();

        return validation;
    }


    public String cadastrar(Context contexto, String nome, String login, String senha, String resenha){

        if (nome.length() <= 5){
            return "Nome deve ser maior que 5 caracteres.";
        }

        if (login.length() <= 5){
            return "Login deve ser maior que 5 caracteres.";
        }

        if (senha.length() <= 4){
            return "senha deve ser maior que 4 caracteres.";
        }

        if (!senha.equals(resenha)){
            return "senha incorreta, tente novamente.";
        }

        //salvar no banco
        salvarNoDB(contexto, nome, login, senha);

        return "OK";


    }

    private void salvarNoDB(Context contexto, String nome,String login,String senha) {

        SharedPreferences sharedPref = contexto.getSharedPreferences("AppLoginExemplo", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("nome", nome);
        editor.putString("login", login);
        editor.putString("senha", senha);
        editor.commit();
    }

    public boolean LoginAutomatico(Context contexto){
        SharedPreferences sharedPref = contexto.getSharedPreferences("AppLoginExemplo", Context.MODE_PRIVATE);
        boolean validation = sharedPref.getBoolean("validation", false);

        if (validation){
            return true;
        }
        else{
            return false;
        }
    }

}
