package tn.esprit.lfcovoiturage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

import tn.esprit.lfcovoiturage.dao.CovoiturageDAO;
import tn.esprit.lfcovoiturage.database.MyDatabase;
import tn.esprit.lfcovoiturage.entities.Covoiturage;
import tn.esprit.lfcovoiturage.entities.User;

public class AddCovoiturage extends AppCompatActivity {
    private SharedPreferences mPreferences;
    public static final String sharedPrefFile = "tn.esprit.lfcovoiturage";
    EditText departET , destEt , dateEt , prixEt , nbr_pEt ;
    Button ajout_C ;
    MyDatabase myDb ;

    DatePickerDialog picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_covoiturage);
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);


        departET = findViewById(R.id.DepartEt);
        destEt = findViewById(R.id.DestEt);
        dateEt = findViewById(R.id.DateDep) ;
        dateEt.setInputType(InputType.TYPE_NULL);
        prixEt = findViewById(R.id.PrixEt) ;
        nbr_pEt = findViewById(R.id.NbrPlEt) ;
        ajout_C = findViewById(R.id.AjoutCoBtn) ;

        dateEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(AddCovoiturage.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                dateEt.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }

        });



        myDb = MyDatabase.getDatabase(this);

        ajout_C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String depart = departET.getText().toString();
                final String dest = destEt.getText().toString();
                final String date = dateEt.getText().toString();
                final String prix = prixEt.getText().toString();
                final String nbrP = nbr_pEt.getText().toString();
                final int idUser = mPreferences.getInt("idUser",0);

                Covoiturage covoiturage = new Covoiturage();
                covoiturage.setDep(depart);
                covoiturage.setDest(dest);
                covoiturage.setDate(date);
                covoiturage.setPrice(prix);
                covoiturage.setNbrP(nbrP);
                covoiturage.setIdUser(idUser);

                 if(validateInput(covoiturage)){
                    MyDatabase myDatabase = MyDatabase.getDatabase(getApplicationContext());
                    CovoiturageDAO covDAO = myDatabase.covoiturageDAO();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            covDAO.insertCov(covoiturage);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Log.d("TAG","idUser: "+idUser);
                                    Toast.makeText(getApplicationContext(),"cov Registered",Toast.LENGTH_SHORT).show();
                                   startActivity(new Intent(AddCovoiturage.this,HomeActivity.class));



                                }

                            });

                        }
                    }).start();
                }else
                    Toast.makeText(getApplicationContext(),"Fill all fields ", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private Boolean validateInput(Covoiturage covoiturage){
        if(covoiturage.getDep().isEmpty()||
                covoiturage.getDest().isEmpty()||
                covoiturage.getPrice().isEmpty()||
                covoiturage.getDate().isEmpty()||
                covoiturage.getNbrP().isEmpty()){
            return false ;

        };
        return true ;
    }



}