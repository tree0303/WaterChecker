package tree0303.github.io.waterchecker.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@androidx.room.Database(entities = {Plant.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract PlantDAO plantDAO();
    private static AppDatabase INSTANCE;
    private static final int MEMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(MEMBER_OF_THREADS);
    private static final String DATABASE_NAME = "plants";

    public static AppDatabase getInstance(final Context context) {
        if(INSTANCE == null){
            synchronized (AppDatabase.class){
                INSTANCE = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).build();
            }
        }
        return INSTANCE;
    }
}
