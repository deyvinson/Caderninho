package com.example.aluno.caderninho;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity {

    private TextView textViewSplash;
    private ProgressBar progressBarSplash;
    private Integer SPLASH_TIME_OUT = 0;
    private Login login;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);



        textViewSplash = (TextView) findViewById(R.id.txtBemVindo);
        progressBarSplash = (ProgressBar) findViewById(R.id.SplashBolinha);
        progressBarSplash.setProgress(SPLASH_TIME_OUT);
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
                                      @Override
                                      public void run() {
                                          SPLASH_TIME_OUT += 1;
                                          progressBarSplash.setProgress(SPLASH_TIME_OUT);
                                          if (SPLASH_TIME_OUT >= 4){

                                              login = new Login();


                                              if (login.LoginAutomatico(SplashScreenActivity.this)) {

                                                  Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                                                  startActivity(i);
                                                  overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                                              }
                                              else{
                                                  Intent i = new Intent(SplashScreenActivity.this, LoginActivity.class);
                                                  startActivity(i);
                                                  overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

                                              }

                                              this.cancel();
                                              SplashScreenActivity.this.finish();

                                          }

                                      }
                                  }
                ,100
                ,1000);








    }
}
