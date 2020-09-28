package com.example.bangunajaalpha3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class HireActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hire);
    }

    public void hireComp1 (View view) {
//        Toast.makeText(this, "You Hired " + tv.getText().toString(), Toast.LENGTH_SHORT).show();
        TextView tv = findViewById(R.id.comp1TextView);
        Log.i("Hire", tv.getText().toString());
        Intent intent = new Intent(this, HireForm.class);
        intent.putExtra("contractor", tv.getText().toString());
        startActivity(intent);
    }

    public void hireComp2 (View view) {
        TextView tv = findViewById(R.id.comp2TextView);
        Log.i("Hire", tv.getText().toString());
        Intent intent = new Intent(this, HireForm.class);
        intent.putExtra("contractor", tv.getText().toString());
        startActivity(intent);
        Toast.makeText(this, "You Hired " + tv.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    public void hireComp3 (View view) {
        TextView tv = findViewById(R.id.comp3TextView);
        Log.i("Hire", tv.getText().toString());
        Intent intent = new Intent(this, HireForm.class);
        intent.putExtra("contractor", tv.getText().toString());
        startActivity(intent);
        Toast.makeText(this, "You Hired " + tv.getText().toString(), Toast.LENGTH_SHORT).show();
    }


}
