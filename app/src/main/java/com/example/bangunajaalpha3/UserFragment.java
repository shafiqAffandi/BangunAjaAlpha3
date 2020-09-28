package com.example.bangunajaalpha3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {

    ListView profileListView;
    ArrayList<String> profileListAct = new ArrayList<String>();


    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        profileListAct.add("Account Info");
        profileListAct.add("Privacy Policy");
        profileListAct.add("About BangunAja");

        profileListView = view.findViewById(R.id.profile_listView);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, profileListAct);
        profileListView.setAdapter(arrayAdapter);

        return view;
    }
}
