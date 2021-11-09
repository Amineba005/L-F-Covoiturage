package tn.esprit.lfcovoiturage.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import tn.esprit.lfcovoiturage.dao.CovoiturageDAO;
import tn.esprit.lfcovoiturage.dao.UserdDAO;
import tn.esprit.lfcovoiturage.entities.Covoiturage;
import tn.esprit.lfcovoiturage.entities.User;

@Database(entities = {User.class, Covoiturage.class},version = 7,exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

    private static MyDatabase instance ;
    public abstract UserdDAO userdDAO();
    public abstract CovoiturageDAO covoiturageDAO();

    public static MyDatabase getDatabase(Context context){
        if (instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),MyDatabase.class,"my_db")
                    .fallbackToDestructiveMigration()
                    .build();

        }
        return instance ;
    }
}
