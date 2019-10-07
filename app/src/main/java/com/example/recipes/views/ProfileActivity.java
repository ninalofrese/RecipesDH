package com.example.recipes.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.recipes.R;
import com.example.recipes.models.Usuario;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.recipes.views.RegisterActivity.NEW_USER;

public class ProfileActivity extends AppCompatActivity {
    private TextInputLayout inputName;
    private TextInputLayout inputEmail;
    private TextInputLayout inputPassword;
    private TextInputLayout inputConfirmPassword;
    private MaterialButton buttonSave;

    private String name, email, password, confirmPassword;
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initViews();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (getIntent() != null && getIntent().getExtras() != null) {
            Usuario usuario = getIntent().getExtras().getParcelable(NEW_USER);

            inputName.getEditText().setText(usuario.getNome());
            inputEmail.getEditText().setText(usuario.getEmail());
        }

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateInputs();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public void initViews() {
        inputName = findViewById(R.id.input_profile_name);
        inputEmail = findViewById(R.id.input_profile_email);
        inputPassword = findViewById(R.id.input_profile_password);
        inputConfirmPassword = findViewById(R.id.input_profile_confirm);
        buttonSave = findViewById(R.id.button_profile_save);
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
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
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

}
