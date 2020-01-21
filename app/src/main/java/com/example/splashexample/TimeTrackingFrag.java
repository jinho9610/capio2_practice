package com.example.splashexample;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class TimeTrackingFrag extends Fragment {

    MainActivity activity;
    Button btn_stop;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup timeFrag_container, @Nullable Bundle savedInstance) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_time_tracking_frag, timeFrag_container, false);
        btn_stop = (Button) rootView.findViewById(R.id.btn_stop);
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().remove(TimeTrackingFrag.this).commit();
                fragmentManager.popBackStack();
            }
        });
        return rootView;
    }
}
