package tn.esprit.lfcovoiturage.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import tn.esprit.lfcovoiturage.entities.Covoiturage;
import tn.esprit.lfcovoiturage.entities.User;

@Dao
public interface CovoiturageDAO {

    @Insert
    void insertCov(Covoiturage covoiturage);

    @Delete
    void deleteCov(Covoiturage covoiturage);

    @Query("select * from Covoiturage")
    List<Covoiturage> getAllCov();
}
