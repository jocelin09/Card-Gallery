package com.cardmgnt.jocelinthomas.carddetails.Mediclaim;

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
 * Created by jocelinthomas on 03/05/18.
 */

public class MediclaimAdapter extends RecyclerView.Adapter<MediclaimAdapter.Myholder> {
    Context context;
    List<MediclaimModel> dataModelArrayList;

    public MediclaimAdapter(Context context,List<MediclaimModel> dataModelArrayList) {
        this.context = context;
        this.dataModelArrayList = dataModelArrayList;
    }

    class Myholder extends RecyclerView.ViewHolder {
        TextView mediclaim_name, mediclaim_number;
        Button sharebtn,deletebtn;

        public Myholder(View itemView) {
            super(itemView);

            mediclaim_name = (TextView) itemView.findViewById(R.id.name);
            mediclaim_number = (TextView) itemView.findViewById(R.id.number);
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
        final MediclaimModel mediclaimModel = dataModelArrayList.get(position);
        holder.mediclaim_name.setText(mediclaimModel.getmediclaim_name());
        holder.mediclaim_number.setText(mediclaimModel.getmediclaim_number());

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
                        deletemediclaimData(holder.getAdapterPosition());

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
                    String mediclaim_name = mediclaimModel.getmediclaim_name();
                    String mediclaim_num = mediclaimModel.getmediclaim_number();

                    Intent intent = new Intent(Intent.ACTION_SEND);
                    StringBuilder sb = new StringBuilder();
                    sb.append("Hello,\n");
                    sb.append("Mediclaim number of "+mediclaim_name+" is '"+mediclaim_num+"'");
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


    public void deletemediclaimData(int pos) {
        //GET ID
        MediclaimModel p = dataModelArrayList.get(pos);
        int id1=p.getmediclaimid();
        System.out.println("id1:::::::::" +id1);

        //DELETE FROM DB
        DBHelper db = new DBHelper(context);
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();

        if (db.deletemediclaim(id1))
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
