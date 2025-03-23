package com.example.sunnygym;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Toolbar extends Fragment {

    // Parámetros de argumentos
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // Variables de instancia
    private String mParam1;
    private String mParam2;

    public Toolbar() {
        // Constructor público vacío requerido
    }

    // Método de fábrica para crear una nueva instancia del fragmento
    public static Toolbar newInstance(String param1, String param2) {
        Toolbar fragment = new Toolbar();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_toolbar, container, false);

        // Verifica si el botón de menú existe antes de asignar el listener
        ImageView menuButton = view.findViewById(R.id.menuButton);
        if (menuButton != null) {
            menuButton.setOnClickListener(this::showPopupMenu);
        }

        return view;
    }

    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(requireContext(), view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.profile_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(this::onMenuItemClick);
        popupMenu.show();
    }

    private boolean onMenuItemClick(MenuItem item) {
        int itemId = item.getItemId();

        if (getContext() == null) return false;

        if (itemId == R.id.home) {
            startActivity(new Intent(getContext(), MyProfileActivity.class));
            return true;
        } else if (itemId == R.id.my_profile) {
            startActivity(new Intent(getContext(), MyProfileActivity.class));
            return true;
        } else if (itemId == R.id.guided_activities) {
            startActivity(new Intent(getContext(), GuidedActivitiesActivity.class));
            return true;
        } else if (itemId == R.id.view_clubs) {
            startActivity(new Intent(getContext(), ViewClubsActivity.class));
            return true;
        } else if (itemId == R.id.support) {
            startActivity(new Intent(getContext(), SupportActivity.class));
            return true;
        } else if (itemId == R.id.lista_activities) {
            startActivity(new Intent(getContext(), ReservasActivty.class));
            return true;
        } else if (itemId == R.id.logout) {
            startActivity(new Intent(getContext(), MainActivity.class));
            requireActivity().finish();
            return true;
        } else {
            return false;
        }
    }
}