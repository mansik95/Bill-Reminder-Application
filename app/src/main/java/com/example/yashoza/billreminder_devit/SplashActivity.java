package com.example.yashoza.billreminder_devit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {
    ConstraintLayout container;
    private AnimationDrawable animation;
    Button proceedBtn;
    TextView welcome;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        if (mAuth.getCurrentUser() != null) {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
        }

        container = findViewById(R.id.parentAnimLayout);
        proceedBtn = findViewById(R.id.proceedBtn);
        welcome = findViewById(R.id.welcome_text);

        final Animation welcomeAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_with_alpha);
        final Animation welcomeAnimForBtn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_with_alpha);
        welcomeAnimForBtn.setDuration(500);

        final SharedPreferences sharedPreferences = getSharedPreferences("MyPref", MODE_PRIVATE);
        if (sharedPreferences.getBoolean("FirstUse", true)){
            welcome.startAnimation(welcomeAnim);
        }
        else {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        welcomeAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                proceedBtn.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                proceedBtn.setVisibility(View.VISIBLE);
                proceedBtn.startAnimation(welcomeAnimForBtn);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        animation = (AnimationDrawable) container.getBackground();
        animation.setEnterFadeDuration(5000);
        animation.setExitFadeDuration(2000);

        proceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (animation != null && !animation.isRunning())
            animation.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (animation != null && animation.isRunning())
            animation.stop();
    }

    @Override
    protected void onStop() {
        finish();
        super.onStop();
    }
}

