package com.cardmgnt.jocelinthomas.carddetails.CreditCard;

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
import com.cardmgnt.jocelinthomas.carddetails.DrivingLicense.DrivinglicenseAdapter;
import com.cardmgnt.jocelinthomas.carddetails.DrivingLicense.DrivinglicenseModel;
import com.cardmgnt.jocelinthomas.carddetails.R;

import java.util.List;

/**
 * Created by jocelinthomas on 08/09/18.
 */

public class CreditCardAdapter extends RecyclerView.Adapter<CreditCardAdapter.Myholder>{

    Context context;
    List<CreditCardModel> dataModelArrayList;

    public CreditCardAdapter(Context context,List<CreditCardModel> dataModelArrayList) {
        this.context = context;
        this.dataModelArrayList = dataModelArrayList;
    }

    class Myholder extends RecyclerView.ViewHolder {
        TextView creditcard_name, creditcard_number;
        Button sharebtn,deletebtn;

        public Myholder(View itemView) {
            super(itemView);

            creditcard_name = (TextView) itemView.findViewById(R.id.name);
            creditcard_number = (TextView) itemView.findViewById(R.id.number);
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
        final CreditCardModel creditcardModel = dataModelArrayList.get(position);
        holder.creditcard_name.setText(creditcardModel.getcreditcard_name());
        holder.creditcard_number.setText(creditcardModel.getcreditcard_number());

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
                        deleteCreditcardData(holder.getAdapterPosition());

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
                    String creditcard_name = creditcardModel.getcreditcard_name();
                    String creditcard_num = creditcardModel.getcreditcard_number();

                    Intent intent = new Intent(Intent.ACTION_SEND);
                    StringBuilder sb = new StringBuilder();
                    sb.append("Hello,\n");
                    sb.append("Credit Card number of "+creditcard_name+" is '"+creditcard_num+"'");
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


    public void deleteCreditcardData(int pos) {
        //GET ID
        CreditCardModel p = dataModelArrayList.get(pos);
        int id1=p.getCreditcardid();
        System.out.println("id1:::::::::" +id1);

        //DELETE FROM DB
        DBHelper db = new DBHelper(context);
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();

        if (db.deletecreditcard(id1))
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
