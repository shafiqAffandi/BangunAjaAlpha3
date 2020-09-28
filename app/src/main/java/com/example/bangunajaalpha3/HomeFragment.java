package com.example.bangunajaalpha3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    TextView headerTextView;
    ListView newsListView;
    ArrayList<String> titles = new ArrayList<>();
    ArrayList<String> url = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Bundle bundle = getArguments();
        String username = bundle.getString("username");
        headerTextView = view.findViewById(R.id.headerTextView);
        headerTextView.setText("Hello, " + username);

        newsListView = view.findViewById(R.id.newsListView);
        // add static data into news stream
        titles.add("Lima Arsitek Asia Ciptakan Desain Rumah untuk Situasi Pandemi");
        titles.add("Atidesa, Rumah Pandemi Karya Arsitek Indonesia Terbaik di Asia");
        titles.add("'Basement' Bisa Dipermak Jadi Apartemen Minimals SuperCozy");
        titles.add("Tips Tepat Memilih Arsitek untuk Rumah Idaman");
        titles.add("Futuristik, 6 Desain Bangunan Ini Seperti dari Masa Depan");

        url.add("https://properti.kompas.com/read/2020/05/26/215237321/lima-arsitek-asia-ciptakan-desain-rumah-untuk-situasi-pandemi");
        url.add("https://properti.kompas.com/read/2020/05/28/181433021/atidesa-rumah-pandemi-karya-arsitek-indonesia-terbaik-di-asia");
        url.add("https://properti.kompas.com/read/2020/05/31/125020321/basement-bisa-dipermak-jadi-apartemen-minimalis-supercozy");
        url.add("https://www.liputan6.com/on-off/read/4166198/tips-tepat-memilih-arsitek-untuk-rumah-idaman");
        url.add("https://hot.liputan6.com/read/4008227/futuristik-6-desain-bangunan-ini-seperti-dari-masa-depan");


        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, titles);
        newsListView.setAdapter(arrayAdapter);

        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity().getApplicationContext(), NewsActivity.class);
                intent.putExtra("urls", url.get(position));
                startActivity(intent);
            }
        });

        return view;
    }



}
