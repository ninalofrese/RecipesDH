package com.example.recipes.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.recipes.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    private TextInputLayout inputEmail;
    private TextInputLayout inputPassword;
    private MaterialButton buttonLogin;
    private MaterialButton buttonRegister;

    private String email, password;
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateInputs();
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    public void initViews() {
        inputEmail = findViewById(R.id.input_login_email);
        inputPassword = findViewById(R.id.input_login_password);
        buttonLogin = findViewById(R.id.button_login_login);
        buttonRegister = findViewById(R.id.button_login_register);
    }

    public void validateInputs() {
        email = inputEmail.getEditText().getText().toString().trim();
        password = inputPassword.getEditText().getText().toString().trim();

        if (!validateEmail(email) && !validatePassword(password)) {
            inputEmail.setError(getString(R.string.valid_email));
            inputPassword.setError(getString(R.string.valid_password));
        } else if (!validateEmail(email)) {
            inputEmail.setError(getString(R.string.valid_email));
            inputPassword.setErrorEnabled(false);
        } else if (!validatePassword(password)) {
            inputEmail.setErrorEnabled(false);
            inputPassword.setError(getString(R.string.valid_password));
        } else {
            inputEmail.setErrorEnabled(false);
            inputPassword.setErrorEnabled(false);

            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
    }

    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean validatePassword(String senha) {
        return senha.length() > 5;
    }
}
