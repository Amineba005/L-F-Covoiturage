package tn.esprit.lfcovoiturage.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import tn.esprit.lfcovoiturage.dao.UserdDAO;
import tn.esprit.lfcovoiturage.entities.User;

@Database(entities = {User.class},version = 1,exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

    private static MyDatabase instance ;
    public abstract UserdDAO userdDAO();

    public static MyDatabase getDatabase(Context context){
        if (instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),MyDatabase.class,"my_db")
                    .allowMainThreadQueries()
                    .build();

        }
        return instance ;
    }
}
