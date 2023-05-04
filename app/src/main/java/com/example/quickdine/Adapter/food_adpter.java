package com.example.quickdine.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quickdine.R;
import com.example.quickdine.data_class.food_data;

import java.util.List;

public class food_adpter extends RecyclerView.Adapter<food_adpter.ViewHolder>{
    Context context;
    List<food_data> list;

    public food_adpter(Context context, List<food_data> list) {
        this.context= context;
        this.list=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.food_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.food_name.setText(list.get(position).getFood_name());
        holder.restaurant_name.setText(list.get(position).getRestaursant_name());
        holder.price.setText(list.get(position).getPrice());
        holder.food_name.setOnClickListener(v->{
            Toast.makeText(context, ""+list.get(position).getFood_name(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView food_name,restaurant_name,price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            food_name=itemView.findViewById(R.id.textView);
            restaurant_name=itemView.findViewById(R.id.textView2);
            price=itemView.findViewById(R.id.textView3);
        }
    }
}

