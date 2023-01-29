package tree0303.github.io.waterchecker.home;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import tree0303.github.io.waterchecker.R;
import tree0303.github.io.waterchecker.database.Plant;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    private List<Plant> mPlants;

    public HomeAdapter(){
        this.mPlants = new ArrayList<>();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setmPlants(List<Plant> mPlants) {
        this.mPlants = mPlants;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return mPlants.get(position).getPlant_id();
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_plant_item, parent, false);
        return new HomeAdapter.HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.HomeViewHolder holder, int position) {
        holder.getPlantName().setText(mPlants.get(position).getPlant_name());
        holder.getPlantDate().setText(mPlants.get(position).getDate());
        int num = mPlants.get(position).getWater_num();
        holder.getPlantNum().setText(String.valueOf(num));
    }

    @Override
    public int getItemCount() {
        return mPlants.size();
    }

//  viewholder
    public static class HomeViewHolder extends RecyclerView.ViewHolder {
        private final TextView plantName;
        private final TextView plantDate;
        private final TextView plantNum;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            plantName = itemView.findViewById(R.id.home_plant_name);
            plantDate = itemView.findViewById(R.id.home_plant_date);
            plantNum = itemView.findViewById(R.id.home_plant_num);
        }

        public TextView getPlantName() {
            return plantName;
        }

        public TextView getPlantDate() {
            return plantDate;
        }

        public TextView getPlantNum() {
            return plantNum;
        }
    }

}
