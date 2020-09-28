package com.example.bangunajaalpha3;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class signupActivity extends AppCompatActivity {


    public void userSignup (View view) {
        EditText username_signup_et = findViewById(R.id.username_signup_et);
        EditText password_signup_et = findViewById(R.id.password_signup_et);
        EditText confirmPass_signup_et = findViewById(R.id.confirmPass_signup_et);

        String username = username_signup_et.getText().toString();
        String password = password_signup_et.getText().toString();
        String confirmPass = confirmPass_signup_et.getText().toString();

        if(username.isEmpty() || password.isEmpty() || confirmPass.isEmpty()) {
            Toast.makeText(this, "Please Fill All Information", Toast.LENGTH_SHORT).show();
        }
        else {
            try {
                SQLiteDatabase myDatabase = this.openOrCreateDatabase("bangunAjaDB", MODE_PRIVATE, null);

                //DELETE TABLE confirmPass !!!!
                myDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (userId INTEGER PRIMARY KEY, username VARCHAR, password VARCHAR, confirmPass VARCHAR)");

                //Insert data user to database
                myDatabase.execSQL("INSERT INTO users (username, password, confirmPass) VALUES ('" + username + "', '" + password + "', '" + confirmPass + "')");
                myDatabase.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            Toast.makeText(this, "Signup Success", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void backButton (View view) {
        onBackPressed();
    }

}
