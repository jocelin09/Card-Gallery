package com.cardmgnt.jocelinthomas.carddetails.DebitCard;

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
import android.widget.TextView;
import android.widget.Toast;

import com.cardmgnt.jocelinthomas.carddetails.DBHelper;
import com.cardmgnt.jocelinthomas.carddetails.R;

import java.util.List;

/**
 * Created by jocelinthomas on 10/09/18.
 */

public class DebitCardAdapter extends RecyclerView.Adapter<DebitCardAdapter.Myholder> {

    Context context;
    List<DebitCardModel> dataModelArrayList;

    public DebitCardAdapter(Context context,List<DebitCardModel> dataModelArrayList) {
        this.context = context;
        this.dataModelArrayList = dataModelArrayList;
    }

    class Myholder extends RecyclerView.ViewHolder {
        TextView debitcard_name, debitcard_number;
        Button sharebtn,deletebtn;

        public Myholder(View itemView) {
            super(itemView);

            debitcard_name = (TextView) itemView.findViewById(R.id.name);
            debitcard_number = (TextView) itemView.findViewById(R.id.number);
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
        final DebitCardModel debitcardModel = dataModelArrayList.get(position);
        holder.debitcard_name.setText(debitcardModel.getdebitcard_name());
        holder.debitcard_number.setText(debitcardModel.getdebitcard_number());

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
                        deleteDebitData(holder.getAdapterPosition());

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
                    String debitcard_name = debitcardModel.getdebitcard_name();
                    String debitcard_num = debitcardModel.getdebitcard_number();

                    Intent intent = new Intent(Intent.ACTION_SEND);
                    StringBuilder sb = new StringBuilder();
                    sb.append("Hello,\n");
                    sb.append("DebitCard Number of "+debitcard_name+" is '"+debitcard_num+"'");
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


    public void deleteDebitData(int pos) {
        //GET ID
        DebitCardModel p = dataModelArrayList.get(pos);
        int id1=p.getdebitcardid();
        System.out.println("id1:::::::::" +id1);

        //DELETE FROM DB
        DBHelper db = new DBHelper(context);
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();

        if (db.deletedebitcard(id1))
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
