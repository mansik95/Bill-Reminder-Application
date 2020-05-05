package com.example.yashoza.billreminder_devit;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yashoza on 02/04/18.
 */

public class BillsAdapter extends RecyclerView.Adapter<BillsAdapter.MyViewHolder>{

    List<Bill> bills=new ArrayList<>();
    BillsAdapterListener listener;
    Context context;

    public BillsAdapter(Context context, List<Bill> bills, BillsAdapterListener listener){
        this.context = context;
        this.bills = bills;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bills, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Bill bill = bills.get(position);
        holder.name.setText(bill.getName());
        holder.type.setText(bill.getType());
        holder.date.setText(bill.getDate());
        holder.amount.setText(bill.getAmount());


        String type = bill.getType();
        holder.type.setText(type);

        switch (type){
            case "Food":
                Glide.with(context).load(R.drawable.home).into(holder.image);
                holder.type.setTextColor(Color.parseColor("#FFDA44"));
                break;
            case "Shopping":
                Glide.with(context).load(R.drawable.icon_camera).into(holder.image);
                holder.type.setTextColor(Color.parseColor("#E64C3C"));
                break;
            case "Transport":
                Glide.with(context).load(R.drawable.food).into(holder.image);
                holder.type.setTextColor(Color.parseColor("#87D7FF"));
                break;
            case "Debt":
                Glide.with(context).load(R.drawable.shopping_cart_loaded).into(holder.image);
                holder.type.setTextColor(Color.parseColor("#EAEAD7"));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return bills.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name, date,amount,type;
        ImageView image;

        public MyViewHolder(final View itemView) {
            super(itemView);
            image= itemView.findViewById(R.id.type_image);
            name =  itemView.findViewById(R.id.bill_name);
            date =  itemView.findViewById(R.id.bill_date);
            amount= itemView.findViewById(R.id.bill_amount);
            type= itemView.findViewById(R.id.bill_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onBillClicked(bills.get(getAdapterPosition()), itemView);
                }
            });
        }
    }
    interface BillsAdapterListener {
        void onBillClicked(Bill bill, View view);
    }
}
