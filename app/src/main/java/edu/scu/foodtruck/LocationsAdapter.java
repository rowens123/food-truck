package edu.scu.foodtruck;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;
import java.util.List;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class LocationsAdapter extends
        RecyclerView.Adapter<LocationsAdapter.ViewHolder> {
    private List<ParkingSpace> mParkingSpaces;

    // Pass in the contact array into the constructor
    public LocationsAdapter(List<ParkingSpace> parkingSpaces) {
        mParkingSpaces = parkingSpaces;
    }
    @NonNull
    @Override
    public LocationsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.location_recomendation, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(LocationsAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        ParkingSpace parkingSpace = mParkingSpaces.get(position);

        // Set item views based on your views and data model
        TextView textView = holder.nameTextView;
        textView.setText(parkingSpace.getAddress());
        Button button = holder.messageButton;
        button.setText("Get Directions");
        button.setEnabled(true);
    }
    @Override
    public int getItemCount() {
        return mParkingSpaces.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public Button messageButton;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.address);
            messageButton = (Button) itemView.findViewById(R.id.message_button);
        }
    }
}
