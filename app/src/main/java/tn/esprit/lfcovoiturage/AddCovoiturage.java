package tn.esprit.lfcovoiturage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddCovoiturage extends AppCompatActivity {

    FloatingActionButton addCovFab , addLfFab ;
    ExtendedFloatingActionButton addActionFav ;
    TextView addCovTv , addLfTv ;
    Boolean isAllFabVisible ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_covoiturage);
    }

}