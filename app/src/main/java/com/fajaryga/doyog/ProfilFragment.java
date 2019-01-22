package com.fajaryga.doyog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.fajaryga.doyog.util.PreferencesHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilFragment extends Fragment {

    private TextView name, mail;
    public ProfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil, container, false);

    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        name = view.findViewById(R.id.tvNama);
        mail = view.findViewById(R.id.tvEmail);
        name.setText(PreferencesHelper.getInstance(getActivity().getApplicationContext()).getName());
        mail.setText(PreferencesHelper.getInstance(getActivity().getApplicationContext()).getEmail());

        Button info = (Button) view.findViewById(R.id.btn_ttg);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), info.class);
                getActivity().startActivity(intent);
            }
        });
    }

}
