package com.cardmgnt.jocelinthomas.carddetails.VoterID;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cardmgnt.jocelinthomas.carddetails.Aadhaar.DataModel;
import com.cardmgnt.jocelinthomas.carddetails.DBHelper;
import com.cardmgnt.jocelinthomas.carddetails.R;

import java.util.List;

/**
 * Created by jocelinthomas on 11/05/18.
 */

public class VoterAdapter extends RecyclerView.Adapter<VoterAdapter.Myholder> {
    Context context;
    VotersModel votersModel;
    List<VotersModel> dataModelArrayList;

    public VoterAdapter(Context context,List<VotersModel> dataModelArrayList) {
        this.context = context;
        this.dataModelArrayList = dataModelArrayList;
    }

    class Myholder extends RecyclerView.ViewHolder {
        TextView voter_name, voter_number;
        Button sharebtn,deletebtn;

        public Myholder(View itemView) {
            super(itemView);

            voter_name = (TextView) itemView.findViewById(R.id.name);
            voter_number = (TextView) itemView.findViewById(R.id.number);
            sharebtn = (Button) itemView.findViewById(R.id.sharebtn);
            deletebtn = (Button) itemView.findViewById(R.id.deletebtn);


        }
    }

    @Override
    public Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview, null);
        return new Myholder(view);

    }

    @Override
    public void onBindViewHolder(final Myholder holder, int position) {
        final VotersModel voterModel = dataModelArrayList.get(position);
        holder.voter_name.setText(voterModel.getvoter_name());
        holder.voter_number.setText(voterModel.getvoter_number());

        holder.deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                // alertDialogBuilder.setTitle("Exit");
                alertDialogBuilder.setMessage("Are you sure you want to delete?");
                //alertDialogBuilder.setIcon(R.drawable.pwd);
                alertDialogBuilder.setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        // delete the item from recycler view and db.
                        deleteVotersData(holder.getAdapterPosition());

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                // create alert dialog
                final AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });

        holder.sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    String voters_name = voterModel.getvoter_name();
                    String voters_num = voterModel.getvoter_number();

                    Intent intent = new Intent(Intent.ACTION_SEND);
                    StringBuilder sb = new StringBuilder();
                    sb.append("Hello,\n");
                    sb.append("Voter's ID number of "+voters_name+" is '"+voters_num+"'");
                    intent.putExtra(Intent.EXTRA_TEXT, sb.toString());
                    intent.setType("text/plain");
                    context.startActivity(Intent.createChooser(intent, "Send To"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }


    public void deleteVotersData(int pos) {
        //GET ID
        VotersModel p = dataModelArrayList.get(pos);
        int id1=p.getVotersid();
        System.out.println("id1:::::::::" +id1);

        //DELETE FROM DB
        DBHelper db = new DBHelper(context);
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();

        if (db.deletevoter(id1))
        {
            dataModelArrayList.remove(pos);
            Toast.makeText(context, "Deleted Successfully!", Toast.LENGTH_SHORT).show();


        }
        else
        {
            Toast.makeText(context,"Unable To Delete",Toast.LENGTH_SHORT).show();
        }

        db.close();

        this.notifyItemRemoved(pos);

    }

}
