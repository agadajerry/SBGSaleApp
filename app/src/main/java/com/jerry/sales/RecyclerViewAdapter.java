package com.jerry.sales;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.StockViewHolder>{

    ArrayList<Stock> newList;

    public RecyclerViewAdapter(ArrayList<Stock> newList2) {
        this.newList = newList2;
    }


    public static class StockViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView productText;
        TextView quantity;
        TextView unitPrice, totalPrice;
        View view;

        public StockViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            cardView = itemView.findViewById(R.id.cardView);
            productText = itemView.findViewById(R.id.productName);
            quantity = itemView.findViewById(R.id.quantit);
            unitPrice =itemView.findViewById(R.id.unitPrice1);
            totalPrice =itemView.findViewById(R.id.totalPrice);
        }
    }
    @NonNull
    @Override
    public StockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout,parent,false);

        return new StockViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull StockViewHolder holder, int position) {


        Stock myStock = newList.get(position);
        holder.productText.setText("Product Name: "+myStock.getItemName());
        holder.quantity.setText("Quantity:  "+myStock.getQuantity());
        holder.unitPrice.setText("Unit Price:  "+myStock.getUnitPrice());
        holder.totalPrice.setText("Total Price:  "+myStock.getSumTotal());
    }






    @Override
    public int getItemCount() {
        if(newList!=null){
            return newList.size();
        }else{
            return 0;
        }
    }
}
