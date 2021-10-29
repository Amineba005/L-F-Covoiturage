package tn.esprit.lfcovoiturage.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import tn.esprit.lfcovoiturage.entities.User;

@Dao
public interface UserdDAO {

    @Insert
    void insertUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("select * from User where username=(:username) and password=(:password)")
    User login(String username , String password );

    @Query("select * from User where email=(:email) or username=(:username)")
    User register(String username , String email );

    



}
