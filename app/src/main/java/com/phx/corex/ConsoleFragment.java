package com.phx.corex;
import android.os.Bundle; import android.view.LayoutInflater; import android.view.View; import android.view.ViewGroup;
import android.widget.TextView; import androidx.annotation.NonNull; import androidx.annotation.Nullable; import androidx.fragment.app.Fragment;
public class ConsoleFragment extends Fragment {
    @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_console, container, false);
    }
    @Override public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        TextView log = v.findViewById(R.id.tv_log);
        log.setText(LogManager.get());
    }
    @Override public void onResume() {
        super.onResume();
        TextView log = getView() != null ? getView().findViewById(R.id.tv_log) : null;
        if (log != null) log.setText(LogManager.get());
    }
}
