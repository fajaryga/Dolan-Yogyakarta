package com.fajaryga.doyog;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fajaryga.doyog.activity.BantulActivity;
import com.fajaryga.doyog.activity.GundulActivity;
import com.fajaryga.doyog.activity.KulonprogoActivity;
import com.fajaryga.doyog.activity.PilihanActivity;
import com.fajaryga.doyog.activity.SlemanActivity;
import com.fajaryga.doyog.activity.YogyakartaActivity;
import com.fajaryga.doyog.util.PreferencesHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private TextView name;
    public HomeFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        name = view.findViewById(R.id.welcome);
        name.setText("Selamat datang, "+ PreferencesHelper.getInstance(getActivity().getApplicationContext()).getName());

        CardView pilihan = (CardView) view.findViewById(R.id.pilihan);
        pilihan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PilihanActivity.class);
                getActivity().startActivity(intent);
            }
        });

        CardView sleman = (CardView) view.findViewById(R.id.sleman);
        sleman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SlemanActivity.class);
                getActivity().startActivity(intent);
            }
        });

        CardView jogja = (CardView) view.findViewById(R.id.yogya);
        jogja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), YogyakartaActivity.class);
                getActivity().startActivity(intent);
            }
        });

        CardView kp = (CardView) view.findViewById(R.id.kp);
        kp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), KulonprogoActivity.class);
                getActivity().startActivity(intent);
            }
        });

        CardView gunkid = (CardView) view.findViewById(R.id.gk);
        gunkid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GundulActivity.class);
                getActivity().startActivity(intent);
            }
        });

        CardView bantul = (CardView) view.findViewById(R.id.bantul);
        bantul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BantulActivity.class);
                getActivity().startActivity(intent);
            }
        });

    }

}
