package tn.esprit.lfcovoiturage.entities;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UserWithCov {
    @Embedded public User user ;
    @Relation(
            parentColumn = "id",
            entityColumn = "idUser"
    )
    public List<Covoiturage> covoiturages ;

    public UserWithCov(User user, List<Covoiturage> covoiturages) {
        this.user = user;
        this.covoiturages = covoiturages;
    }

    public UserWithCov() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Covoiturage> getCovoiturages() {
        return covoiturages;
    }

    public void setCovoiturages(List<Covoiturage> covoiturages) {
        this.covoiturages = covoiturages;
    }

    @Override
    public String toString() {
        return "UserWithCov{" +
                "user=" + user +
                ", covoiturages=" + covoiturages +
                '}';
    }
}
