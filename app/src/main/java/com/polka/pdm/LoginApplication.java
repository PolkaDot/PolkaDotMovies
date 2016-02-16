package com.polka.pdm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginApplication extends AppCompatActivity {

    public void onLoginCancelPressed(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onLoginButtonPressed(View v) {
        Log.d("LOGIN ACTIVITY", "Login Button Pressed");
        EditText nameBox = (EditText) findViewById(R.id.usernameEdit);
        EditText passBox = (EditText) findViewById(R.id.passwordEdit);
        CharSequence text;

        UserRepo repo = new UserRepo(this);
        User user = repo.getUserByUsername(nameBox.getText().toString());

        if (passBox.getText().toString().equals(user.password)) {
            text = "Login Success!";
        } else {
            text = "Login Failure!";
        }


        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast t = Toast.makeText(context, text, duration);
        t.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_application);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        Button btCancel = (Button) findViewById(R.id.cancelButton);
//
//        btCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setResult(RESULT_OK);
//                finish();
//            }
//        });

    }

}
