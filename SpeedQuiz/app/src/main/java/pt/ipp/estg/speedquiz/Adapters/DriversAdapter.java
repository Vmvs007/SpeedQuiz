package pt.ipp.estg.speedquiz.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pt.ipp.estg.speedquiz.FireBaseAuth.MenuActivity;
import pt.ipp.estg.speedquiz.FragmentDetails;
import pt.ipp.estg.speedquiz.Models.DriversApi.Driver;
import pt.ipp.estg.speedquiz.R;

public class DriversAdapter
    extends RecyclerView.Adapter<DriversAdapter.DriversViewHolder> {
        private Context mContext;
        private List<Driver> driverList;

    public DriversAdapter(Context mContext, List<Driver> driverList) {
            this.mContext = mContext;
           this.driverList=driverList;
        }

        @Override
        public DriversViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //Get layout inflater from context
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            //Inflate layout
            View view = inflater.inflate(R.layout.item_driver, parent, false);

            //Return a new holder instance
            return new DriversViewHolder(view);
        }

    @Override
    public void onBindViewHolder (@NonNull DriversViewHolder holder, int position) {
        //Get the data model based on position
        Driver driver = driverList.get(position);

        //Set name
        TextView nameDriver = holder.nameDriver;

        nameDriver.setText(driver.getGivenName()+" "+driver.getFamilyName());

        ImageButton button = holder.button;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MenuActivity) mContext).sendTextToFragment(driver);
            }
        });
    }


        @Override
        public int getItemCount() {
            return driverList.size();
        }

        public class DriversViewHolder extends RecyclerView.ViewHolder {
            public TextView nameDriver;
            public ImageButton button;

            public DriversViewHolder(View itemView) {
                super(itemView);
                nameDriver = itemView.findViewById(R.id.name_Driver);
                button = itemView.findViewById(R.id.buttonInfo);
            }
        }

}
