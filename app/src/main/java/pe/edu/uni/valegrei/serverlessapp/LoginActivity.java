package pe.edu.uni.valegrei.serverlessapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import pe.edu.uni.valegrei.serverlessapp.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding binding = ActivityLoginBinding.inflate(getLayoutInflater());

        binding.btnLogin.setOnClickListener(v -> login());
    }

    private void login() {

    }
}