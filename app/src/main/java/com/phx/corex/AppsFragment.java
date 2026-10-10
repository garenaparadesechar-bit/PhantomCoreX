package com.phx.corex;
import android.content.Intent; import android.content.pm.ApplicationInfo; import android.content.pm.PackageManager; import android.net.Uri;
import android.os.Bundle; import android.provider.Settings; import android.view.LayoutInflater; import android.view.View; import android.view.ViewGroup;
import android.widget.Button; import android.widget.ImageView; import android.widget.TextView; import androidx.annotation.NonNull; import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment; import androidx.recyclerview.widget.LinearLayoutManager; import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList; import java.util.Collections; import java.util.List;
public class AppsFragment extends Fragment {
    @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_apps, container, false);
    }
    @Override public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        RecyclerView rv = v.findViewById(R.id.rv_apps);
        rv.setLayoutManager(new LinearLayoutManager(requireContext()));
        List<AppItem> apps = new ArrayList<>();
        PackageManager pm = requireContext().getPackageManager();
        for (ApplicationInfo ai : pm.getInstalledApplications(0)) {
            apps.add(new AppItem(pm.getApplicationLabel(ai).toString(), ai.packageName, ai.loadIcon(pm)));
        }
        Collections.sort(apps, (a, b) -> a.name.compareToIgnoreCase(b.name));
        rv.setAdapter(new AppAdapter(apps));
    }
    static class AppItem { String name, pkg; android.graphics.drawable.Drawable icon; AppItem(String n, String p, android.graphics.drawable.Drawable i) { name=n; pkg=p; icon=i; } }
    class AppAdapter extends RecyclerView.Adapter<AppAdapter.VH> {
        List<AppItem> items; AppAdapter(List<AppItem> i) { items = i; }
        @NonNull @Override public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_app, parent, false));
        }
        @Override public void onBindViewHolder(@NonNull VH holder, int position) {
            AppItem app = items.get(position);
            holder.name.setText(app.name); holder.pkg.setText(app.pkg); holder.icon.setImageDrawable(app.icon);
            holder.btn.setOnClickListener(v -> {
                LogManager.add("Gestionando app: " + app.name);
                Intent i = new Intent(Intent.ACTION_DELETE, Uri.parse("package:" + app.pkg));
                startActivity(i);
            });
        }
        @Override public int getItemCount() { return items.size(); }
        class VH extends RecyclerView.ViewHolder { TextView name, pkg; ImageView icon; Button btn;
            VH(View v) { super(v); name=v.findViewById(R.id.tv_name); pkg=v.findViewById(R.id.tv_pkg); icon=v.findViewById(R.id.iv_icon); btn=v.findViewById(R.id.btn_action); }
        }
    }
}
