package tree0303.github.io.waterchecker;

import android.app.Application;

import junit.framework.TestCase;

import tree0303.github.io.waterchecker.database.Plant;

public class PlantRepositoryTest extends TestCase {

    public void testGetAllPlants() throws Exception{
        PlantRepository plantRepository = new PlantRepository(new Application());
        Plant plant = new Plant("test", 0 , "2022");
        plantRepository.insert(plant);
        assertEquals(plantRepository.getAllPlants(),plant);
    }

}