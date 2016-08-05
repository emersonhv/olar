package com.app.olaradio;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class LeadsFragment extends Fragment {


    ListView mLeadsList;
    ArrayAdapter<String> mLeadsAdapter;

    public LeadsFragment() {
        // Required empty public constructor
    }

    public static LeadsFragment newInstance(/*parámetros*/) {
        LeadsFragment fragment = new LeadsFragment();
        // Setup parámetros
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // Gets parámetros
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_leads, container, false);
        // Instancia del ListView.
        mLeadsList = (ListView) root.findViewById(R.id.leads_list);

        String[] leadsNames = {
                "Tweet de Alexander Pierrot",
                "Tweet Carlos Lopez",
                "Tweet Sara Bonz",
                "Tweet Liliana Clarence",
                "Tweet Benito Peralta",
                "Tweet Juan Jaramillo",
                "Tweet Christian Steps",
                "Tweet Alexa Giraldo",
                "Tweet Linda Murillo",
                "Tweet Lizeth Astrada"
        };

        mLeadsAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, leadsNames);

        mLeadsList.setAdapter(mLeadsAdapter);

        return root;
    }

}
