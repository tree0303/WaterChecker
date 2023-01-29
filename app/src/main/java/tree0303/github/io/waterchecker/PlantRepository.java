package tree0303.github.io.waterchecker;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.List;

import tree0303.github.io.waterchecker.database.AppDatabase;
import tree0303.github.io.waterchecker.database.Plant;
import tree0303.github.io.waterchecker.database.PlantDAO;

public class PlantRepository {
    private final PlantDAO mplantDAO;
    private final LiveData<List<Plant>> mAllPlants;

    PlantRepository(@NonNull Application application){
        AppDatabase database = AppDatabase.getInstance(application);
        mplantDAO = database.plantDAO();
        mAllPlants = mplantDAO.getAllPlants();
    }

    LiveData<List<Plant>> getAllPlants() {
        return mAllPlants;
    }

    void insert(Plant plant) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mplantDAO.insert(plant);
        });
    }
    void update(Plant plant) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mplantDAO.update(plant);
        });
    }
    void delete(Plant plant) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mplantDAO.delete(plant);
        });
    }

}
