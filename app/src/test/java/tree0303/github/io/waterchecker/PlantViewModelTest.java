package tree0303.github.io.waterchecker;

import android.app.Application;

import androidx.lifecycle.ViewModelProvider;

import junit.framework.TestCase;

import tree0303.github.io.waterchecker.add.AddFragment;
import tree0303.github.io.waterchecker.database.Plant;

public class PlantViewModelTest extends TestCase {

    public void testGetmPlants() {
        PlantRepository plantRepository = new PlantRepository(new Application());
        PlantViewModel plantViewModel = new ViewModelProvider(AddFragment.newInstance()).get(PlantViewModel.class);
        Plant plant = new Plant("test", 0 , "2022");
        plantViewModel.insert("test", 0 , "2022");
        assertEquals(plantViewModel.getAllPlants(), plant);
    }
}