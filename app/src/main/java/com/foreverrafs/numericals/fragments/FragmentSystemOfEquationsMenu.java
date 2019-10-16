package com.foreverrafs.numericals.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.foreverrafs.numericals.R;
import com.foreverrafs.numericals.adapter.OperationsMenuAdapter;
import com.foreverrafs.numericals.fragments.sys_of_equations.FragmentGaussSeidel;
import com.foreverrafs.numericals.fragments.sys_of_equations.FragmentGaussSeidelWithSOR;
import com.foreverrafs.numericals.fragments.sys_of_equations.FragmentGaussianComplete3x3;
import com.foreverrafs.numericals.fragments.sys_of_equations.FragmentGaussianComplete4x4;
import com.foreverrafs.numericals.fragments.sys_of_equations.FragmentGaussianPartial3x3;
import com.foreverrafs.numericals.fragments.sys_of_equations.FragmentGaussianPartial4x4;
import com.foreverrafs.numericals.fragments.sys_of_equations.FragmentJacobi;
import com.foreverrafs.numericals.model.OperationMenu;
import com.foreverrafs.numericals.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FragmentSystemOfEquationsMenu extends Fragment implements OperationsMenuAdapter.MenuItemClickListenener {

    @BindView(R.id.list_main_menu)
    RecyclerView mainMenuItems;

    @BindView(R.id.tvHeader)
    TextView header;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        mainMenuItems.setLayoutManager(new GridLayoutManager(getContext(), 2));

        List<OperationMenu> operations = new ArrayList<>();
        operations.add(new OperationMenu("Gaussian (Partial) 3 x 3", R.drawable.button_normal_system_of_eqns_gaussian3x3, Constants.SYS_OF_EQN_GAUSSIAN_PART_3X3));
        operations.add(new OperationMenu("Gaussian (Complete) 3 x 3", R.drawable.button_normal_system_of_eqns_gaussian3x3, Constants.SYS_OF_EQN_GAUSSIAN_COMPLETE_3X3));
        operations.add(new OperationMenu("Gaussian (Partial ) 4 x 4", R.drawable.button_normal_system_of_eqns4x4, Constants.SYS_OF_EQN_GAUSSIAN_PART_4X4));
        operations.add(new OperationMenu("Gaussian (Complete) 4 x 4", R.drawable.button_normal_system_of_eqns4x4, Constants.SYS_OF_EQN_GAUSSIAN_COMPLETE_4X4));
        operations.add(new OperationMenu("jacobi", R.drawable.button_normal_system_of_eqns_jacobi, Constants.SYS_OF_EQN_JACOBI));
        operations.add(new OperationMenu("Gauss Seidel", R.drawable.button_normal_system_of_eqns_gaussseidel, Constants.SYS_OF_EQN_GAUSS_SEIDEL));
        operations.add(new OperationMenu("Gauss Seidel (SOR)", R.drawable.button_normal_system_of_eqns_gaussseidel, Constants.SYS_OF_EQN_GAUSS_SEIDEL_SOR));


        header.setText(getString(R.string.system_of_equations));

        OperationsMenuAdapter adapter = new OperationsMenuAdapter(operations);

        adapter.setOnItemClickListenener(this);

        mainMenuItems.setHasFixedSize(true);
        mainMenuItems.setAdapter(adapter);
    }

    @Override
    public void OnMenuItemClicked(int menuItemType) {

        Fragment fragment = null;
        switch (menuItemType) {
            case Constants.SYS_OF_EQN_GAUSSIAN_PART_3X3:
                fragment = new FragmentGaussianPartial3x3();
                break;

            case Constants.SYS_OF_EQN_GAUSSIAN_COMPLETE_3X3:
                fragment = new FragmentGaussianComplete3x3();
                break;

            case Constants.SYS_OF_EQN_GAUSSIAN_PART_4X4:
                fragment = new FragmentGaussianPartial4x4();
                break;

            case Constants.SYS_OF_EQN_JACOBI:
                fragment = new FragmentJacobi();
                break;

            case Constants.SYS_OF_EQN_GAUSS_SEIDEL:
                fragment = new FragmentGaussSeidel();
                break;
            case Constants.SYS_OF_EQN_GAUSS_SEIDEL_SOR:
                fragment = new FragmentGaussSeidelWithSOR();
                break;
            case Constants.SYS_OF_EQN_GAUSSIAN_COMPLETE_4X4:
                fragment = new FragmentGaussianComplete4x4();
                break;
        }

//        if (fragment != null)
//            Utilities.replaceFragment(fragment, getSupportFragmentManager(), R.id.fragmentContainer);
    }
}
