package tree0303.github.io.waterchecker.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public
interface PlantDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Plant plant);

    @Update
    void update(Plant plant);

    @Delete
    void delete(Plant plant);

    @Query("SELECT * FROM plants")
    LiveData<List<Plant>> getAllPlants();

}
