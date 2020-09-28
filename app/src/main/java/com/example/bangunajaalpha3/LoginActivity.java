package com.example.bangunajaalpha3;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {


    public void userLogin (View view) {
        EditText username_et = findViewById(R.id.username_et);
        EditText password_et = findViewById(R.id.password_et);

        String username = username_et.getText().toString();
        String password = password_et.getText().toString();

        if(username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all information", Toast.LENGTH_SHORT).show();
        }
        else {
            try {
                SQLiteDatabase myDatabase = this.openOrCreateDatabase("bangunAjaDB", MODE_PRIVATE, null);

                Cursor c = myDatabase.rawQuery("SELECT * FROM users", null);

                int idIndex = c.getColumnIndex("userId");
                int usernameIndex = c.getColumnIndex("username");
                int passwordIndex = c.getColumnIndex("password");

                c.moveToFirst();

                while(c != null) {
                    if(username.equals(c.getString(usernameIndex)) && password.equals(c.getString(passwordIndex))) {
                        Log.i("ID", Integer.toString(c.getInt(idIndex)));
                        Log.i("username", c.getString(usernameIndex));
                        Log.i("password", c.getString(passwordIndex));

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("userId", Integer.toString(c.getInt(idIndex)));
                        startActivity(intent);
                        break;
                    }
                    else {
                        c.moveToNext();
                    }
                }
                myDatabase.close();
            } catch (Exception e) {
                Log.i("ERORR", "failed, error occured");
                Toast.makeText(this, "user not found", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void signupButton (View view) {
        Intent intent = new Intent(getApplicationContext(), signupActivity.class);
        startActivity(intent);
    }
}
