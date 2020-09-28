package com.example.bangunajaalpha3;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    TextView welcomeTextView;
    TextView headerTextView;
    BottomNavigationView bottomNavigationView;
    static String id;
    String username;
    Bundle b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        welcomeTextView = findViewById(R.id.welcomeTextView);
        headerTextView = findViewById(R.id.headerTextView);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        afterLogin();

//        create company table (dummy) (first-time ony)
//        createCompanyDB();

        loadFragment(new HomeFragment());
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener  = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.homeFragment:
//                    toolbar.setTitle("Home");
                    fragment = new HomeFragment();
                    break;
                case R.id.projectFragment:
//                    toolbar.setTitle("Project");
                    fragment = new ProjectFragment();
                    break;
                case R.id.chatFragment:
                    fragment = new ChatFragment();
                    break;
                case R.id.userFragment:
//                    toolbar.setTitle("Profile");
                    fragment = new UserFragment();
                    break;
            }
            return loadFragment(fragment);
        }
    };

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            b = new Bundle();
            b.putString("username", username);
            fragment.setArguments(b);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    public void afterLogin() {
        try {
            SQLiteDatabase myDatabase = this.openOrCreateDatabase("bangunAjaDB", MODE_PRIVATE, null);
            Intent intent = getIntent();
            id = intent.getStringExtra("userId");

            Cursor c = myDatabase.rawQuery("SELECT * FROM users", null);

            int idIndex = c.getColumnIndex("userId");
            int usernameIndex = c.getColumnIndex("username");

            c.moveToFirst();

//            if(c.moveToFirst()) {
//                do {
//                    if (id.equals(Integer.toString(c.getInt(idIndex)))) {
////                        username += c.getString(usernameIndex);
//                        headerTextView.setText("Hello, " + c.getString(usernameIndex));
//                        return;
//                    }
//                } while (c.moveToNext());
//            }

            while (c != null) {
                if (id.equals(Integer.toString(c.getInt(idIndex)))) {
                    username = c.getString(usernameIndex);
//                    welcomeTextView.setText("Welcome, " + username);
//                    headerTextView.setText("Hello, " + c.getString(usernameIndex));
//                    myDatabase.close();
                    Log.i("found", "data found");
                    Log.i("usernameLogin", username);
                    break;
//                    return;
                } else {
                    Log.i("moveToNext", "next data");
                    c.moveToNext();
                }
            }
            myDatabase.close();
        } catch(Exception e) {
            Log.i("Error", "failed, ERORR occured");
            e.printStackTrace();
        }
    }

    public void hireButton (View view) {
//        Toast.makeText(getApplicationContext(), "Hire pressed", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, HireActivity.class);
        startActivity(intent);
    }

    public void projectButton (final View view) {
        Toast.makeText(getApplicationContext(), "Project pressed", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(this, ProjectActivity.class);
        Fragment fragment = null;
        fragment = new ProjectFragment();
        bottomNavigationView.setSelectedItemId(R.id.projectFragment);
        loadFragment(fragment);
//        startActivity(intent);

    }

    public void donationButton (View view) {
        Toast.makeText(getApplicationContext(), "Donation pressed", Toast.LENGTH_SHORT).show();
    }

    public void moreButton (View view) {
        Toast.makeText(getApplicationContext(), "More pressed", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MoreActivity.class);
        startActivity(intent);
    }

    public void createCompanyDB() {
        try {
            SQLiteDatabase myDatabase = this.openOrCreateDatabase("bangunAjaDB", MODE_PRIVATE, null);
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS company (companyId INTEGER PRIMARY KEY, companyName VARCHAR, companyDesc VARCHAR, companyAddress VARCAHR)");

            //insert data (first-run only)
            String compDesc1 = "PT. Taman Indah Perkasa adalah sebuah perusahaan yang bergerak di bidang pembangunan, berdiri sejak tahun 1982.";
            String compName1 = "PT. Taman Indah Perkasa";
            String compAddress1 = "Jl. Merdeka TImur KAV 12-14\n" + "help.center@sukajaya.co.id\n" + "(021) 435 1223";
            myDatabase.execSQL("INSERT INTO company (companyName, companyDesc, companyAddress) VALUES ('" + compName1 + "', '" + compDesc1 + "', '" + compAddress1 + "')");
            String compDesc2 = "PT. Harman Intimarin adalah perusahaan konstruksi ternama di 5 negara asean. Berdiri sejak tahun 1978 dan mempunyai prestasi gemilang";
            String compName2 = "PT. Harman Intimarin";
            String compAddress2 = "Jl. Thamrin no .43\n" + "construction.care@hintimarin.com\n" + "(021) 233 9832";
            myDatabase.execSQL("INSERT INTO company (companyName, companyDesc, companyAddress) VALUES ('" + compName2 + "', '" + compDesc2 + "', '" + compAddress2 + "')");
            String compDesc3 = "PT. Intan Bina Jaya adalah anak perusahaan dari perusahaan ternama PT. Intan Bumi. Berdiri sejak tahun 2012";
            String compName3 = "PT. Intan Bina Jaya";
            String compAddress3 = "Jl. Selatan Maranata No.11\n" + "kit.help@intanbinajaya.com\n" + "(021) 652 3489";
            myDatabase.execSQL("INSERT INTO company (companyName, companyDesc, companyAddress) VALUES ('" + compName3 + "', '" + compDesc3 + "', '" + compAddress3 + "')");

            myDatabase.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
