package com.example.bangunajaalpha3;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectFragment extends Fragment {

    ListView listView;
    ArrayList<String> projectName = new ArrayList<String>();
    ArrayList<String> companyName = new ArrayList<String>();
    ArrayList<String> address = new ArrayList<String>();
    ArrayList<String> hireId = new ArrayList<String>();
    String id = MainActivity.id;

    public ProjectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_project, container, false);

        getProjectList();

        listView = view.findViewById(R.id.listView);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1,projectName);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity().getApplicationContext(), DetailProjectActivity.class);
                intent.putExtra("projectName", projectName.get(position));
                intent.putExtra("companyName", companyName.get(position));
                intent.putExtra("address", address.get(position));
                intent.putExtra("hireId", hireId.get(position));

                startActivity(intent);
            }
        });

        return view;
    }

    public void getProjectList() {
        try {
            SQLiteDatabase myDatabase = getActivity().getApplicationContext().openOrCreateDatabase("bangunAjaDB", getActivity().getApplicationContext().MODE_PRIVATE, null);
            Cursor c = myDatabase.rawQuery("SELECT * FROM hireDetail WHERE userId =" + id, null);

            int projectNameIndex = c.getColumnIndex("projectName");
            int companyNameIndex = c.getColumnIndex("companyName");
            int addressIndex = c.getColumnIndex("address");
            int hireIdIndex = c.getColumnIndex("hireId");

            c.moveToFirst();

            while(c != null) {
                projectName.add(c.getString(projectNameIndex));
                companyName.add(c.getString(companyNameIndex));
                address.add(c.getString(addressIndex));
                hireId.add(Integer.toString(c.getInt(hireIdIndex)));
                c.moveToNext();
            }
            myDatabase.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
