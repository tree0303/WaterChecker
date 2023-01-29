package tree0303.github.io.waterchecker.home;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.nfc.Tag;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.stream.Collectors;

import tree0303.github.io.waterchecker.PlantViewModel;
import tree0303.github.io.waterchecker.database.Plant;
import tree0303.github.io.waterchecker.databinding.FragmentHomeBinding;
import tree0303.github.io.waterchecker.ui.AssortmentOfDateTime;

public class HomeFragment extends Fragment implements AssortmentOfDateTime {

    private PlantViewModel mplantViewModel;
    private FragmentHomeBinding binding;
    private HomeAdapter homeAdapter;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mplantViewModel = new ViewModelProvider(this).get(PlantViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        homeAdapter = new HomeAdapter();

        RecyclerView recyclerView = binding.homePlantList;
        recyclerView.setAdapter(homeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mplantViewModel.getAllPlants().observe(getViewLifecycleOwner(), plants ->
                homeAdapter.setmPlants(plants));
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}