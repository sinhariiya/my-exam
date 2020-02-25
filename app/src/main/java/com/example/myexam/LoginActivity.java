package com.example.myexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText username,password;
    Button login;
    TextView gotosignup;
    String Username,Password;
    LocalDatabase ld;
    Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ld = new LocalDatabase(LoginActivity.this);
        username = (EditText) findViewById(R.id.user_name);
        password = (EditText) findViewById(R.id.pass);
        login = (Button) findViewById(R.id.login);
        gotosignup = (TextView)findViewById(R.id.gotosignup);
        session=new Session(LoginActivity.this);

        if (session.checkLoggedIn()==true){
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }

        validation();
    }
 public void validation() {
        login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Username = username.getText().toString();
                        Password = password.getText().toString();
                        if (Username.isEmpty()){
                            username.setError("please provide username");
                        }
                        else if(Password.isEmpty()) {
                            password.setError("please enter password");
                        }
                        else {
                            getpassword(Username,Password);
                        }
                    }
                }
        );

 }
 public void getpassword(String username,String password){
     if(password.equals(ld.getPassword(username)) && !password.equals("NOT FOUND")) {
         String name=ld.getName(username);
         session.setName(name);
         session.setUsername(username);
         Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
         session.setLoggedIn(true);
         Intent inten = new Intent(LoginActivity.this,MainActivity.class);

         startActivity(inten);
     }
     else{
         Toast.makeText(getApplicationContext(),"Login failed",Toast.LENGTH_SHORT).show();
     }
 }
    public void gotoSignup(View view) {
        Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
        startActivity(intent);

    }


}
