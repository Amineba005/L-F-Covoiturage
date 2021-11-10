package tn.esprit.lfcovoiturage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

import tn.esprit.lfcovoiturage.dao.CovoiturageDAO;
import tn.esprit.lfcovoiturage.dao.UserdDAO;
import tn.esprit.lfcovoiturage.database.MyDatabase;
import tn.esprit.lfcovoiturage.entities.Covoiturage;

public class RecyclerViewCov extends RecyclerView.Adapter<RecyclerViewCov.MyViewHolder> {
    Context context ;
    List<Covoiturage> covoiturages ;
    MyDatabase myDb ;
    String phoneuser ;

    public RecyclerViewCov(Context context, List<Covoiturage> covoiturages) {
        this.context = context;
        this.covoiturages = covoiturages;
    }


    @Override
    public RecyclerViewCov.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coviturage_view,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewCov.MyViewHolder holder, int position) {
        myDb = MyDatabase.getDatabase(context);

        holder.depC.setText(covoiturages.get(position).getDep());
        holder.destC.setText(covoiturages.get(position).getDest());
        holder.dateC.setText(covoiturages.get(position).getDate());
        holder.prixC.setText(covoiturages.get(position).getPrice());
        holder.nbrP.setText(covoiturages.get(position).getNbrP());


        MyDatabase myDB = Room.databaseBuilder(context,MyDatabase.class,"my_db")
                .allowMainThreadQueries().build();
                Log.d("position","p"+position);
                 phoneuser = myDB.userdDAO().getPhoneByIdUser(covoiturages.get(position).getIdUser());

        holder.phone.setText(phoneuser);






    }

    @Override
    public int getItemCount() {
        return covoiturages.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView depC , destC , dateC , prixC , phone,nbrP ;
        public MyViewHolder( View itemView) {
            super(itemView);
            nbrP = itemView.findViewById(R.id.nbrP);
            depC = itemView.findViewById(R.id.depTv);
            destC = itemView.findViewById(R.id.destCov);
            dateC = itemView.findViewById(R.id.dateTv);
            prixC = itemView.findViewById(R.id.coinTv);
            phone = itemView.findViewById(R.id.phoneTv);
        }
    }
}
