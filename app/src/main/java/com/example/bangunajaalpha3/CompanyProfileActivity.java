package com.example.bangunajaalpha3;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class CompanyProfileActivity extends AppCompatActivity {

    TextView companyName_tv;
    TextView companyAddress_tv;
    TextView companyDesc_tv;
    ImageView companyImage;
    String companyName;
    String companyDesc;
    String companyAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_profile);

        companyName_tv = findViewById(R.id.compProfile_name_tv);
        companyAddress_tv = findViewById(R.id.compProfile_address_tv);
        companyDesc_tv = findViewById(R.id.compProfile_desc_tv);
        companyImage = findViewById(R.id.compProfile_imageView);

        Intent intent = getIntent();
        companyName = intent.getStringExtra("companyName");
        companyName_tv.setText(companyName);
        getCompanyImage(companyName);
        getCompanyInfo();
        companyDesc_tv.setText(companyDesc);
        companyAddress_tv.setText(companyAddress);
    }

    public void getCompanyImage(String companyName) {
        switch(companyName){
            case "PT. Taman Indah Perkasa":
                companyImage.setImageResource(R.drawable.company1);
                break;
            case "PT. Harman Intimarin":
                companyImage.setImageResource(R.drawable.company2);
                break;
            case "PT. Intan Bina Jaya":
                companyImage.setImageResource(R.drawable.company3);
                break;
        }
    }

    public void getCompanyInfo() {
        try {
            SQLiteDatabase myDatabase = this.openOrCreateDatabase("bangunAjaDB", MODE_PRIVATE, null);

            Cursor c = myDatabase.rawQuery("SELECT * FROM company", null);

            int compNameIndex = c.getColumnIndex("companyName");
            int compAddressIndex = c.getColumnIndex("companyAddress");
            int compDescIndex = c.getColumnIndex("companyDesc");

            c.moveToFirst();

            while(c != null) {
                if(companyName.equals(c.getString(compNameIndex))) {
                    companyDesc = c.getString(compDescIndex);
                    companyAddress = c.getString(compAddressIndex);
                    Log.i("FOUND", "Company Data Found!");
                    break;
                }
                else {
                    c.moveToNext();
                }
            }
            myDatabase.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
