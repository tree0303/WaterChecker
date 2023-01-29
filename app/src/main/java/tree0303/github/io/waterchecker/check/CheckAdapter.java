package tree0303.github.io.waterchecker.check;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import tree0303.github.io.waterchecker.R;
import tree0303.github.io.waterchecker.database.Plant;
import tree0303.github.io.waterchecker.ui.AssortmentOfDateTime;
import tree0303.github.io.waterchecker.ui.DeletePlantListener;
import tree0303.github.io.waterchecker.ui.UpDatePlantListener;

public class CheckAdapter extends RecyclerView.Adapter<CheckAdapter.CheckViewHolder> implements AssortmentOfDateTime {

    private List<Plant> mPlants;
    private UpDatePlantListener upDatePlantListener;
    private DeletePlantListener deletePlantListener;

    public CheckAdapter(){
        this.mPlants = new ArrayList<>();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setmPlants(List<Plant> mPlants) {
        this.mPlants = mPlants;
        notifyDataSetChanged();
    }

    public void setUpDatePlantListener(UpDatePlantListener upDatePlantListener) {
        this.upDatePlantListener = upDatePlantListener;
    }

    public void setDeletePlantListener(DeletePlantListener deletePlantListener) {
        this.deletePlantListener = deletePlantListener;
    }

    @Override
    public int getItemViewType(int position) {
        return mPlants.get(position).getPlant_id();
    }

    @NonNull
    @Override
    public CheckAdapter.CheckViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.check_plant_item, parent, false);
        return new CheckAdapter.CheckViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckAdapter.CheckViewHolder holder, int position) {
        holder.getPlantName().setText(mPlants.get(position).getPlant_name());
        holder.getPlantDate().setText(mPlants.get(position).getDate());
        holder.getFinbtn().setOnClickListener(view -> {
            if (upDatePlantListener!=null) {
                upDatePlantListener.onClickUpdatePlant(mPlants.get(position));
            }
        });
        holder.getFinbtn().setOnLongClickListener(view -> {
            if (deletePlantListener!=null) {
                deletePlantListener.onClickDeletePlant(mPlants.get(position));
            }
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return mPlants.size();
    }
    //  viewholder
    public static class CheckViewHolder extends RecyclerView.ViewHolder {
        private final TextView plantName;
        private final TextView plantDate;
        private final Button finbtn;

        public CheckViewHolder(@NonNull View itemView) {
            super(itemView);
            plantName = itemView.findViewById(R.id.check_plant_name);
            plantDate = itemView.findViewById(R.id.check_plant_date);
            finbtn = itemView.findViewById(R.id.check_plant_num);
        }

        public TextView getPlantName() {
            return plantName;
        }

        public TextView getPlantDate() {
            return plantDate;
        }

        public Button getFinbtn() {
            return finbtn;
        }

    }
}
