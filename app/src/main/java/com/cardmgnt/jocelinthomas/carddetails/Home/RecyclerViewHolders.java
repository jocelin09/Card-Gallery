package com.cardmgnt.jocelinthomas.carddetails.Home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cardmgnt.jocelinthomas.carddetails.Aadhaar.AadhaarCardDetails;
import com.cardmgnt.jocelinthomas.carddetails.CreditCard.CreditCardActivity;
import com.cardmgnt.jocelinthomas.carddetails.DebitCard.DebitCardActivity;
import com.cardmgnt.jocelinthomas.carddetails.DrivingLicense.DrivingLicense_Details;
import com.cardmgnt.jocelinthomas.carddetails.Mediclaim.MediclaimDetailsActivity;
import com.cardmgnt.jocelinthomas.carddetails.Pan.PanCardDetails;
import com.cardmgnt.jocelinthomas.carddetails.Passport.PassportDetailsActivity;
import com.cardmgnt.jocelinthomas.carddetails.R;
import com.cardmgnt.jocelinthomas.carddetails.VoterID.VoterID_Details;


public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView countryName;
    public ImageView countryPhoto;
    private final Context context;
    RelativeLayout relativeLayout;


    public RecyclerViewHolders(View itemView) {
        super(itemView);
        context = itemView.getContext();

        itemView.setOnClickListener(this);
        countryName = (TextView) itemView.findViewById(R.id.country_name);
        countryPhoto = (ImageView) itemView.findViewById(R.id.country_photo);
        relativeLayout = (RelativeLayout) itemView.findViewById(R.id.layout1);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (getAdapterPosition()) {
            case 0:
                // Check if we're running on Android 5.0 or higher
               /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // Apply activity transition
                    intent = new Intent(context, AadhaarCardDetails.class);
                    ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, relativeLayout, ViewCompat.getTransitionName(relativeLayout));
                    context.startActivity(intent, optionsCompat.toBundle());
                } else {*/
                // Swap without transition

                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, view, "transition");
                int revealX = (int) (view.getX() + view.getWidth() / 2);
                int revealY = (int) (view.getY() + view.getHeight() / 2);

                intent = new Intent(context, AadhaarCardDetails.class);
                intent.putExtra(AadhaarCardDetails.EXTRA_CIRCULAR_REVEAL_X, revealX);
                intent.putExtra(AadhaarCardDetails.EXTRA_CIRCULAR_REVEAL_Y, revealY);

                ActivityCompat.startActivity(context, intent, options.toBundle());
                break;

            // intent = new Intent(context, AadhaarCardDetails.class);
            //break;
            // }


            case 1:
                intent = new Intent(context, PanCardDetails.class);
                break;

            case 2:
                intent = new Intent(context, PassportDetailsActivity.class);
                break;

            case 3:
                intent = new Intent(context, VoterID_Details.class);
                break;

            case 4:
                intent = new Intent(context, DrivingLicense_Details.class);
                break;

            case 5:
                intent = new Intent(context, MediclaimDetailsActivity.class);
                break;

            case 6:
                intent = new Intent(context, CreditCardActivity.class);
                break;

            case 7:
                intent = new Intent(context, DebitCardActivity.class);
                break;

            default:
                return;
        }
        context.startActivity(intent);
    }
}