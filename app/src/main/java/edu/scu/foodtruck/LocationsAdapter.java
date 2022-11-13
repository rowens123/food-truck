package edu.scu.foodtruck;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LocationsAdapter extends
        RecyclerView.Adapter<LocationsAdapter.ViewHolder> {
    private List<ParkingSpace> mParkingSpaces;

    public LocationsAdapter(List<ParkingSpace> parkingSpaces) {
        mParkingSpaces = parkingSpaces;
    }
    @NonNull
    @Override
    public LocationsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.location_recomendation, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(LocationsAdapter.ViewHolder holder, int position) {

        ParkingSpace parkingSpace = mParkingSpaces.get(position);

        TextView textView = holder.nameTextView;
        textView.setText(parkingSpace.getAddress());
        Button button = holder.messageButton;
        button.setText(parkingSpace.getRadius());
        button.setEnabled(true);

    }
    @Override
    public int getItemCount() {
        return mParkingSpaces.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;
        public Button messageButton;

        public ViewHolder(View itemView) {

            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.address);
            messageButton = (Button) itemView.findViewById(R.id.message_button);
        }
    }
}
