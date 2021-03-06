package churt.a12explorers.Login;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import churt.a12explorers.Main.MainActivity;
import churt.a12explorers.R;
import churt.a12explorers.Register.RegisterActivity;

/**
 * Created by Christopher Hurt on 9/8/2017.
 *
 * Implementation of the Login View.
 *
 * TODO: Create frontend
 */

public class LoginActivity extends Activity implements LoginView,
        View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();
    private EditText inputEmail;
    private EditText inputPassword;
    private TextView unhidePass;
    private TextView toRegister;
    private Button button;
    private LoginPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Get UI Components
        inputEmail = (EditText) findViewById(R.id.loginEmailInput);
        inputPassword = (EditText) findViewById(R.id.loginPasswordInput);
        unhidePass = (TextView) findViewById(R.id.loginUnhidePassword);
        toRegister = (TextView) findViewById(R.id.loginAlreadyUser);
        button = (Button) findViewById(R.id.loginBtn);

        //Set UI Design
        button.setBackgroundColor(Color.WHITE);
        button.setTextColor(Color.rgb(29, 54, 108));

        //Initialize Presenter
        presenter = new LoginPresenterImpl(this);

        //Set onClick Listeners
        button.setOnClickListener(this);
        unhidePass.setOnClickListener(this);
        toRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn:
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().toString();
                presenter.validateCredentials(email, password);
                break;
            case R.id.loginUnhidePassword:
                presenter.changePassVisibility(inputPassword, unhidePass);
                break;
            case R.id.loginAlreadyUser:
                navigateToRegister();
                break;
        }
    }

    @Override
    public void navigateToRegister() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @Override
    public void navigateToHome() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void setEmailError() {
        Toast.makeText(LoginActivity.this, R.string.email_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPasswordError() {
        Toast.makeText(LoginActivity.this, R.string.password_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setAuthError() {
        Toast.makeText(LoginActivity.this, R.string.auth_failed, Toast.LENGTH_SHORT).show();
    }
}