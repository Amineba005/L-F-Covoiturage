package tn.esprit.lfcovoiturage.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LostFound {
    @PrimaryKey(autoGenerate = true)
    private int idLF;
    @ColumnInfo
    private String dep;
    @ColumnInfo
    private String dest;
    @ColumnInfo
    private String price;
    @ColumnInfo
    private String date;
    @ColumnInfo
    private String nbrP;

}
