package com.example.u93.detailproduct.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.u93.detailproduct.R;
import com.example.u93.detailproduct.models.Product;

import java.util.ArrayList;

public class AdapterProducts extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Product> productsArrayList;
    private Context context;
    public AdapterProducts(ArrayList<Product> productsArrayList){
        this.productsArrayList = productsArrayList;

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_products,parent,false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CustomViewHolder customViewHolder = (CustomViewHolder) holder;
        final Product producto = productsArrayList.get(position);
        customViewHolder.productoName.setText(producto.getName());
        customViewHolder.productoDescription.setText(producto.getDescription());
        /*customViewHolder.cardViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("product",producto);
                context.startActivity(intent);
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return productsArrayList.size();
    }

    private class CustomViewHolder extends RecyclerView.ViewHolder{
        private TextView productoName;
        private TextView productoDescription;
        private CardView cardViewItem;

        public CustomViewHolder(View itemView) {
            super(itemView);
            productoName = itemView.findViewById(R.id.tvProducto);
            productoDescription = itemView.findViewById(R.id.tvProductoDes);
            cardViewItem = itemView.findViewById(R.id.cardViewItem);
        }
    }


}
