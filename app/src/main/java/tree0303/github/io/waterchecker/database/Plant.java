package tree0303.github.io.waterchecker.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "plants")
public class Plant {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "plant_id")
    private int plant_id;
    @ColumnInfo(name = "plant_name")
    private String plant_name;
    @ColumnInfo(name = "water_num")
    private int water_num;
    @ColumnInfo(name = "date")
    private String date;

    public Plant(String plant_name, int water_num, String date){
        this.plant_name = plant_name;
        this.water_num = water_num;
        this.date = date;
    }
    public Plant(int id, String plant_name, int water_num, String date){
        this.plant_id = id;
        this.plant_name = plant_name;
        this.water_num = water_num;
        this.date = date;
    }


    public int getPlant_id() {
        return plant_id;
    }

    public int getWater_num() {
        return water_num;
    }

    public String getDate() {
        return date;
    }

    public String getPlant_name() {
        return plant_name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPlant_id(int plant_id) {
        this.plant_id = plant_id;
    }

    public void setPlant_name() {
        this.plant_name = plant_name;
    }

    public void setWater_num(int water_num) {
        this.water_num = water_num;
    }
}
