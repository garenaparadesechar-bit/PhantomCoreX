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
        v.findViewById(R.id.btn_profile).setOnClickListener(view -> Toast.makeText(requireContext(), "Perfil Equilibrado aplicado", Toast.LENGTH_SHORT).show());
    }
    private void setupGame(MaterialButton btn, String pkg, String name) {
        boolean installed = false;
        try { requireContext().getPackageManager().getPackageInfo(pkg, 0); installed = true; } catch (Exception e) {}
        btn.setText(name + (installed ? " ✅" : " ❌"));
        btn.setEnabled(installed);
        if (installed) btn.setOnClickListener(view -> startActivity(requireContext().getPackageManager().getLaunchIntentForPackage(pkg)));
        else btn.setOnClickListener(view -> Toast.makeText(requireContext(), "Instala " + name + " primero", Toast.LENGTH_SHORT).show());
    }
}
