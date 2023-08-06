package com.avirup.mathsolver.fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.avirup.mathsolver.R;
import com.avirup.mathsolver.fragments.determinantFragments.Determinant2D;
import com.avirup.mathsolver.fragments.determinantFragments.Determinant3D;
import com.avirup.mathsolver.fragments.determinantFragments.Determinant4D;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DeterminantFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_determinant, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getParentFragmentManager().beginTransaction().replace(R.id.fragment_container_determinant, new Determinant3D()).commit();

        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottom_nav_determinant);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.bottom_nav_2d -> selectedFragment = new Determinant2D();
                case R.id.bottom_nav_3d -> selectedFragment = new Determinant3D();
                case R.id.bottom_nav_4d -> selectedFragment = new Determinant4D();

            }
            assert selectedFragment != null;
            getParentFragmentManager().beginTransaction().replace(R.id.fragment_container_determinant, selectedFragment).commit();
            return true;
        });

        bottomNavigationView.setBackground(new ColorDrawable(Color.WHITE));

    }


}
