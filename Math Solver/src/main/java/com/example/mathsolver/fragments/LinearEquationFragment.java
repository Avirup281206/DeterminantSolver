package com.example.mathsolver.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mathsolver.R;
import com.example.mathsolver.fragments.linearEquationFragments.LinearEquation2v;
import com.example.mathsolver.fragments.linearEquationFragments.LinearEquation3v;
import com.example.mathsolver.fragments.linearEquationFragments.LinearEquation4v;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LinearEquationFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_linear_equation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getParentFragmentManager().beginTransaction().replace(R.id.fragment_container_linear_equation, new LinearEquation2v()).commit();

        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottom_nav_linear_equation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.bottom_nav_2v_le -> selectedFragment = new LinearEquation2v();
                case R.id.bottom_nav_3v_le -> selectedFragment = new LinearEquation3v();
                case R.id.bottom_nav_4v_le -> selectedFragment = new LinearEquation4v();

            }
            assert selectedFragment != null;
            getParentFragmentManager().beginTransaction().replace(R.id.fragment_container_linear_equation, selectedFragment).commit();
            return true;
        });

    }


}
