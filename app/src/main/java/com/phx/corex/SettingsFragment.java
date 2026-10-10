package com.phx.corex;
import android.os.Bundle; import android.view.LayoutInflater; import android.view.View; import android.view.ViewGroup;
import android.widget.TextView; import androidx.annotation.NonNull; import androidx.annotation.Nullable; import androidx.fragment.app.Fragment;
import com.google.android.material.button.MaterialButton; import com.google.android.material.dialog.MaterialAlertDialogBuilder;
public class SettingsFragment extends Fragment {
    @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }
    @Override public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        ((TextView) v.findViewById(R.id.tv_version)).setText("Versión: 1.0.0 (Compilación Estable)");
        v.findViewById(R.id.btn_privacy).setOnClickListener(view -> {
            new MaterialAlertDialogBuilder(requireContext())
                .setTitle("Privacidad y Seguridad")
                .setMessage("Phantom Core X NO recolecta datos personales, NO envía información a servidores externos y NO modifica archivos del sistema sin tu permiso explícito. Todas las lecturas (RAM, Batería, Temp) se procesan localmente en tu dispositivo.")
                .setPositiveButton("Entendido", null)
                .show();
        });
    }
}
