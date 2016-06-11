package com.example.adarsh.notificationapplication;

/**
 * Created by adarsh on 11/6/16.
 */
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    Context mContext;
    ArrayList<Contact> mItems;



    public ContactsAdapter(Context context, ArrayList<Contact> items) {
        mContext = context;
        mItems = items;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView name,email,phone, buttoncont;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.itemname);
            phone = (TextView)itemView.findViewById(R.id.itemphone);
            email = (TextView)itemView.findViewById(R.id.itemusername);
            buttoncont = (TextView)itemView.findViewById(R.id.buttoncont);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout;
        layout = R.layout.item_contacts;
        View view = LayoutInflater.from(parent.getContext())
                .inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(mItems.get(position).getName());
        holder.phone.setText(mItems.get(position).getPhone());
        if(mItems.get(position).getCategory().equals("1")) {
            holder.buttoncont.setText("X");
        }
        else if(mItems.get(position).getCategory().equals("2")) {
            holder.buttoncont.setText(">>");
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(mContext, MapActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(i);
                }
            });
        }
    }



    @Override
    public int getItemCount() {
        return mItems.size();
    }


}