package com.phx.corex;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.button.MaterialButton;

public class GamesFragment extends Fragment {
    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_games, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        
        setupGameButton(v.findViewById(R.id.btn_ff), "com.dts.freefireth", "Free Fire");
        setupGameButton(v.findViewById(R.id.btn_ffmax), "com.dts.freefiremax", "Free Fire MAX");
    }

    private void setupGameButton(MaterialButton btn, String packageName, String gameName) {
        boolean installed = false;
        try {
            requireContext().getPackageManager().getPackageInfo(packageName, 0);
            installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            installed = false;
        }

        btn.setText(gameName + (installed ? " (Instalado)" : " (No instalado)"));
        btn.setEnabled(installed);
        
        if (installed) {
            btn.setOnClickListener(view -> {
                Intent launchIntent = requireContext().getPackageManager().getLaunchIntentForPackage(packageName);
                if (launchIntent != null) startActivity(launchIntent);
            });
        } else {
            btn.setOnClickListener(view -> Toast.makeText(requireContext(), "Instala " + gameName + " primero", Toast.LENGTH_SHORT).show());
        }
    }
}
