package tn.esprit.lfcovoiturage.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Covoiturage {
    @PrimaryKey(autoGenerate = true)
    private int idC ;
    @ColumnInfo
    private String dep ;
    @ColumnInfo
    private String dest ;
    @ColumnInfo
    private String price ;
    @ColumnInfo
    private String date ;
    @ColumnInfo
    private String nbrP ;

    public Covoiturage() {
    }

    public Covoiturage(String dep, String dest, String price, String date, String nbrP) {
        this.dep = dep;
        this.dest = dest;
        this.price = price;
        this.date = date;
        this.nbrP = nbrP;
    }

    public int getIdC() {
        return idC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNbrP() {
        return nbrP;
    }

    public void setNbrP(String nbrP) {
        this.nbrP = nbrP;
    }

    @Override
    public String toString() {
        return "Covoiturage{" +
                "idC=" + idC +
                ", dep='" + dep + '\'' +
                ", dest='" + dest + '\'' +
                ", price='" + price + '\'' +
                ", date='" + date + '\'' +
                ", nbrP='" + nbrP + '\'' +
                '}';
    }
}
