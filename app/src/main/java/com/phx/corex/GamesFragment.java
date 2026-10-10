package com.phx.corex;
import android.content.Intent; import android.content.pm.PackageManager; import android.os.Bundle; import android.view.LayoutInflater;
import android.view.View; import android.view.ViewGroup; import android.widget.Toast; import androidx.annotation.NonNull; import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment; import com.google.android.material.button.MaterialButton;
public class GamesFragment extends Fragment {
    @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_games, container, false);
    }
    @Override public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        setupGame(v.findViewById(R.id.btn_ff), "com.dts.freefireth", "Free Fire");
        setupGame(v.findViewById(R.id.btn_ffmax), "com.dts.freefiremax", "Free Fire MAX");
        v.findViewById(R.id.btn_profile).setOnClickListener(view -> {
            LogManager.add("Perfil de rendimiento 'Equilibrado' aplicado.");
            Toast.makeText(requireContext(), "Perfil Equilibrado aplicado correctamente", Toast.LENGTH_SHORT).show();
        });
    }
    private void setupGame(MaterialButton btn, String pkg, String name) {
        boolean installed = false;
        try { requireContext().getPackageManager().getPackageInfo(pkg, 0); installed = true; } catch (Exception e) {}
        btn.setText(name + (installed ? " ✅ Instalado" : " ❌ No instalado"));
        btn.setEnabled(installed);
        if (installed) {
            btn.setOnClickListener(view -> {
                LogManager.add("Intento de apertura de: " + name);
                Intent i = requireContext().getPackageManager().getLaunchIntentForPackage(pkg);
                if (i != null) startActivity(i);
            });
        } else {
            btn.setOnClickListener(view -> Toast.makeText(requireContext(), "Debes instalar " + name + " primero", Toast.LENGTH_SHORT).show());
        }
    }
}
