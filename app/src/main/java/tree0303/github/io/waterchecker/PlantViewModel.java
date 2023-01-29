package tree0303.github.io.waterchecker;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import static androidx.lifecycle.SavedStateHandleSupport.createSavedStateHandle;
import static androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewmodel.ViewModelInitializer;

import java.util.List;

import tree0303.github.io.waterchecker.database.Plant;

public class PlantViewModel extends AndroidViewModel {

    private final PlantRepository mplantRepository;
    private final LiveData<List<Plant>> mAllPlants;

    public PlantViewModel(@NonNull Application application) {
        super(application);
        this.mplantRepository = new PlantRepository(application);
        this.mAllPlants = mplantRepository.getAllPlants();
    }

    public LiveData<List<Plant>> getAllPlants() {
        return mAllPlants;
    }

    public void insert(String name, int num, String date) {
        Plant plant = new Plant(name, num, date);
        mplantRepository.insert(plant);
    }
    public void update(Plant plant) {
//        Plant plant = new Plant(id, name, num, date);
        mplantRepository.update(plant);
    }
    public void delete(Plant plant) {
        mplantRepository.delete(plant);
    }
}
