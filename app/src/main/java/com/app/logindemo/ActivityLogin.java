package com.app.logindemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityLogin extends AppCompatActivity implements Validator.ValidationListener
{

    @NotEmpty
    @BindView(R.id.et_username)
    EditText etUsername;

    @NotEmpty(message = "password cannot be empty")
    @Password(min = 6, message = "password must be of 6 character or long")
    @BindView(R.id.et_password)
    EditText etPassword;

    @BindView(R.id.btn_login)
    Button btnLogin;

    Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        validator = new Validator(this);
        validator.setValidationListener(this);

    }

    @OnClick(R.id.btn_login)
    public void validateCredentials()
    {
        validator.validate();
    }

    public void tryLogin()
    {
        Toast.makeText(this, "CALL API TO CHECK CREDENTIALS", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onValidationSucceeded()
    {
        tryLogin();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors)
    {
        for (ValidationError error : errors)
        {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText)
            {
                ((EditText) view).setError(message);
            } else
            {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}
