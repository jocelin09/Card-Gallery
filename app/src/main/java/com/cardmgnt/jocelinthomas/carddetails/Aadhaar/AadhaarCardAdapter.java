package com.cardmgnt.jocelinthomas.carddetails.Aadhaar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cardmgnt.jocelinthomas.carddetails.DBHelper;
import com.cardmgnt.jocelinthomas.carddetails.R;

import java.util.List;

/**
 * Created by jocelinthomas on 03/05/18.
 */


public class AadhaarCardAdapter extends RecyclerView.Adapter<AadhaarCardAdapter.Myholder> {
    Context context;
    List<DataModel> dataModelArrayList;
   // private final RecyclerView recyclerView;
   private final FloatingActionButton fab;

    public AadhaarCardAdapter(Context context, List<DataModel> dataModelArrayList, FloatingActionButton fab) {
        this.context = context;
        this.dataModelArrayList = dataModelArrayList;
       // this.recyclerView = recyclerView;
        this.fab = fab;
    }

    class Myholder extends RecyclerView.ViewHolder {
        TextView name, number;
        Button sharebtn, deletebtn;
        public LinearLayout viewForeground;
        public RelativeLayout viewBackground;
        FloatingActionButton fab;

        public Myholder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            number = (TextView) itemView.findViewById(R.id.number);
            sharebtn = (Button) itemView.findViewById(R.id.sharebtn);
            deletebtn = (Button) itemView.findViewById(R.id.deletebtn);
            fab = (FloatingActionButton) itemView.findViewById(R.id.fab);
        }
    }


    @Override
    public Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview, null);
        return new Myholder(view);

    }

    @Override
    public void onBindViewHolder(final Myholder holder, final int position) {
        final DataModel dataModel = dataModelArrayList.get(position);
        holder.name.setText(dataModel.getName());
        holder.number.setText(dataModel.getNumber());
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
                        deleteData(holder.getAdapterPosition());

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
                    String aadhaar_name = dataModel.getName();
                    String aadhaar_num = dataModel.getNumber();

                    Intent intent = new Intent(Intent.ACTION_SEND);
                    StringBuilder sb = new StringBuilder();
                    sb.append("Hello,\n");
                    sb.append("Aadhaar Card number of " + aadhaar_name + " is '" + aadhaar_num + "'");
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


    public void deleteData(int pos) {
        //GET ID
        DataModel p = dataModelArrayList.get(pos);
        int id1 = p.getId();
        System.out.println("id1:::::::::" + id1);

        //DELETE FROM DB
        DBHelper db = new DBHelper(context);
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();

        if (db.deleteaadhaar(id1)) {
            dataModelArrayList.remove(pos);
            //Toast.makeText(context, "Deleted Successfully!", Toast.LENGTH_SHORT).show();

            Snackbar snackbar = Snackbar.make(fab, "Deleted Successfully!", Snackbar.LENGTH_LONG);

            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.YELLOW);
          //  textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 55f);

/*
            View view = snackbar.getView();
            CoordinatorLayout.LayoutParams params =(CoordinatorLayout.LayoutParams)view.getLayoutParams();
            params.gravity = Gravity.BOTTOM;
            view.setLayoutParams(params);*/
            snackbar.show();

        } else {
            // Toast.makeText(context, "Unable To Delete", Toast.LENGTH_SHORT).show();

            Snackbar snackbar = Snackbar.make(fab, "Unable To Delete", Snackbar.LENGTH_LONG);
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.YELLOW);
            snackbar.show();

        }

        db.close();

        this.notifyItemRemoved(pos);

    }

}
