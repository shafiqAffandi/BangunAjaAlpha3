package com.example.bangunajaalpha3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DetailProjectActivity extends AppCompatActivity {

    TextView projectName_tv;
    TextView companyName_tv;
    TextView address_tv;
    String companyName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_project);

        projectName_tv = findViewById(R.id.project_tv);
        companyName_tv = findViewById(R.id.company_tv);
        address_tv = findViewById(R.id.address_tv);

        Intent intent = getIntent();
        String projectName = intent.getStringExtra("projectName");
        companyName = intent.getStringExtra("companyName");
        String address = intent.getStringExtra("address");

        projectName_tv.setText(projectName);
        companyName_tv.setText(companyName);
        address_tv.setText(address);

    }

    public void teamProfile (View view) {
        Toast.makeText(this, "Team's Profile pressed", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, TeamProfileActivity.class);
        startActivity(intent);
    }

    public void companyProfile (View view) {
        Toast.makeText(this, "Company Profile pressed", Toast.LENGTH_SHORT).show();
        Intent intent= new Intent(this, CompanyProfileActivity.class);
        intent.putExtra("companyName", companyName);

        startActivity(intent);
    }

}
