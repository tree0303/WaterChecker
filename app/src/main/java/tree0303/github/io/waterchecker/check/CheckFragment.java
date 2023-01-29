package tree0303.github.io.waterchecker.check;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import tree0303.github.io.waterchecker.PlantViewModel;
import tree0303.github.io.waterchecker.database.Plant;
import tree0303.github.io.waterchecker.databinding.FragmentCheckBinding;
import tree0303.github.io.waterchecker.ui.AssortmentOfDateTime;
import tree0303.github.io.waterchecker.ui.DeletePlantListener;
import tree0303.github.io.waterchecker.ui.UpDatePlantListener;

public class CheckFragment extends Fragment implements UpDatePlantListener, DeletePlantListener, AssortmentOfDateTime {

    private PlantViewModel mplantViewModel;
    private FragmentCheckBinding binding;
    private CheckAdapter checkAdapter;

    public static CheckFragment newInstance() {
        return new CheckFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mplantViewModel = new ViewModelProvider(this).get(PlantViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCheckBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        checkAdapter = new CheckAdapter();
        checkAdapter.setUpDatePlantListener(this);
        checkAdapter.setDeletePlantListener(this);
        RecyclerView recyclerView = binding.checkPlantList;
        recyclerView.setAdapter(checkAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mplantViewModel.getAllPlants().observe(getViewLifecycleOwner(), plants ->
                checkAdapter.setmPlants(plants));
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

    @Override
    public void onClickUpdatePlant(Plant plant) {
        int nnum = plant.getWater_num();
        if (isThisDay(plant.getDate())) {
            nnum = nnum + 1;
        } else {
            nnum = 1;
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter datetimeformatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        String dateTime = datetimeformatter.format(localDateTime);
        Plant nplant = new Plant(plant.getPlant_id(), plant.getPlant_name(), nnum, dateTime);
        mplantViewModel.update(nplant);
    }

    @Override
    public void onClickDeletePlant(Plant plant) {
        mplantViewModel.delete(plant);
    }
}