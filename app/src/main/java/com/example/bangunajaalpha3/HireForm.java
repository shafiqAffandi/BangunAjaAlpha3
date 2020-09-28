package com.example.bangunajaalpha3;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HireForm extends AppCompatActivity {

    EditText projectNameEditText;
    EditText addressEditText;
    TextView companyTextView;
    String company;
    String address;
    String projectName;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hire_form);

        projectNameEditText = findViewById(R.id.projectNameEditText);
        addressEditText = findViewById(R.id.addressEditText);
        companyTextView = findViewById(R.id.companyTextView);
        checkBox = findViewById(R.id.checkBox);

        Intent intent = getIntent();
        company = intent.getStringExtra("contractor");
        companyTextView.setText(getString(R.string.companyHire) + "\n" + company);

    }

    public void HireContractor (View view) {
        projectName = projectNameEditText.getText().toString();
        address = addressEditText.getText().toString();
        String id = MainActivity.id;

        if(projectName.isEmpty()|| address.isEmpty()) {
            Toast.makeText(this, "Please Fill All Information", Toast.LENGTH_SHORT).show();
        }
        else if(!checkBox.isChecked()) {
            Toast.makeText(this, "Please Check Agreement", Toast.LENGTH_SHORT).show();
        }
        else {
            try {
                SQLiteDatabase myDatabase = this.openOrCreateDatabase("bangunAjaDB", MODE_PRIVATE, null);
//                myDatabase.execSQL("DROP TABLE IF EXISTS hireDetail");
                myDatabase.execSQL("CREATE TABLE IF NOT EXISTS hireDetail (hireId INTEGER PRIMARY KEY, userId INT(3), companyName VARCHAR, projectName VARCHAR, address VARCHAR)");
                myDatabase.execSQL("INSERT INTO hireDetail (userId, companyName, projectname, address) VALUES ('" + id + "', '" + company + "', '" + projectName + "', '" + address + "')");
                myDatabase.close();
                Toast.makeText(this, "Hire Success", Toast.LENGTH_SHORT).show();
                onBackPressed();
            } catch (Exception e) {
                Log.i("ERROR", "cannot insert into DB");
                e.printStackTrace();
            }


        }
    }
}
