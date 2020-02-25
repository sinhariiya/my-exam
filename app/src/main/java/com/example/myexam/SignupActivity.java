package com.example.myexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    EditText name, username, pass, cnfpass;
    TextView gotologin;
    Button signup;
    String Name, Username, password, confirmpassword;
    LocalDatabase ld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ld = new LocalDatabase(SignupActivity.this);
        name = (EditText) findViewById(R.id.name);
        username = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.password);
        cnfpass = (EditText) findViewById(R.id.confirmpassword);
        signup = (Button) findViewById(R.id.signup);
        gotologin = (TextView) findViewById(R.id.gotoLogin);

        validation();
    }

    public void validation() {
        signup.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Name = name.getText().toString();
                        Username = username.getText().toString();
                        password = pass.getText().toString();
                        confirmpassword = cnfpass.getText().toString();
                        if (Name.isEmpty()) {
                            name.setError("please provide name");
                        } else if (Username.isEmpty()) {
                            username.setError("please provide username");
                        } else if (password.isEmpty()) {
                            pass.setError("please provide password,it should not be more tha 14 characters");
                        } else if (confirmpassword.isEmpty()) {
                            cnfpass.setError("password doesn't match");
                        } else {
                            registeruser(Name, Username, password);
                        }
                    }
                }
        );
    }

    public void registeruser(String name, String username, String password) {
        boolean isInserted = ld.insertData(name, username, password);
        if (isInserted == false) {
            Toast.makeText(SignupActivity.this, "Error occured", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(SignupActivity.this, "user registered successfully", Toast.LENGTH_SHORT).show();
        }
    }

    public void gotologin(View view) {
        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
        startActivity(intent);

    }
}