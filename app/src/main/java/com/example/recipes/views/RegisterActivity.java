package com.example.recipes.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.example.recipes.R;
import com.example.recipes.models.Usuario;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    private TextInputLayout inputName;
    private TextInputLayout inputEmail;
    private TextInputLayout inputPassword;
    private TextInputLayout inputConfirmPassword;
    private MaterialButton buttonRegister;

    private String name, email, password, confirmPassword;
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    public static final String NEW_USER = "usuario";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateInputs();
            }
        });
    }

    public void initViews() {
        inputName = findViewById(R.id.input_register_name);
        inputEmail = findViewById(R.id.input_register_email);
        inputPassword = findViewById(R.id.input_register_password);
        inputConfirmPassword = findViewById(R.id.input_register_confirm);
        buttonRegister = findViewById(R.id.button_register_register);
    }

    public void validateInputs() {
        name = inputName.getEditText().getText().toString();
        email = inputEmail.getEditText().getText().toString().trim();
        password = inputPassword.getEditText().getText().toString().trim();
        confirmPassword = inputConfirmPassword.getEditText().getText().toString().trim();
        boolean isValid = true;

        if (name.isEmpty()) {
            inputName.setError(getString(R.string.empty_name));
            isValid = false;
        } else {
            inputName.setErrorEnabled(false);
            isValid = true;
        }

        if (email.isEmpty() || !validateEmail(email)) {
            inputEmail.setError(getString(R.string.valid_email));
            isValid = false;
        } else {
            inputEmail.setErrorEnabled(false);
            isValid = true;
        }

        if (!validatePassword(password)) {
            inputPassword.setError(getString(R.string.valid_password));
            isValid = false;
        } else {
            inputPassword.setErrorEnabled(false);
            isValid = true;
        }

        if (!validatePassword(confirmPassword)) {
            inputConfirmPassword.setError(getString(R.string.valid_password));
            isValid = false;
        } else {
            inputConfirmPassword.setErrorEnabled(false);
            isValid = true;
        }

        if (isValid) {
            if (password.equals(confirmPassword)) {
                Usuario usuario = new Usuario(name, email, password);
                setToBundle(usuario);
            } else {
                inputPassword.setError(getString(R.string.matches_password));
                inputConfirmPassword.setError(getString(R.string.matches_password));

                inputName.setErrorEnabled(false);
                inputEmail.setErrorEnabled(false);
            }
        }
    }

    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean validatePassword(String senha) {
        return senha.length() > 5;
    }

    public void setToBundle(Usuario usuario) {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(NEW_USER, usuario);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
