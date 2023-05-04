package com.example.quickdine;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class tap_food extends Fragment {
    View view ;
    private Context contextNullSafe;
    LinearLayout half,full;
    ImageView increase,decrease,cart;
    TextView price,amount,order,quantity;
    Boolean half_bool=false,full_bool=false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.fragment_tap_food, container, false);
        half=view.findViewById(R.id.linearLayout3);
        full=view.findViewById(R.id.linearlayout4);
        increase=view.findViewById(R.id.imageView11);
        decrease=view.findViewById(R.id.imageView19);
        price=view.findViewById(R.id.textView11);
        amount=view.findViewById(R.id.textView12);
        order=view.findViewById(R.id.textView13);
        quantity=view.findViewById(R.id.textView15);
        cart=view.findViewById(R.id.imageView10);




        half.setOnClickListener(v->{
            half.setBackgroundResource(R.drawable.bg_tap_half_full);
            full.setBackgroundResource(R.drawable.bg_half_full);
            amount.setText("Rs 149");
            increase.setVisibility(View.VISIBLE);
            decrease.setVisibility(View.VISIBLE);
            price.setVisibility(View.VISIBLE);
            amount.setVisibility(View.VISIBLE);
            order.setVisibility(View.VISIBLE);
            quantity.setVisibility(View.VISIBLE);
            quantity.setText("1");
            half_bool=true;
            full_bool=false;
        });

        full.setOnClickListener(v->{
            full.setBackgroundResource(R.drawable.bg_tap_half_full);
            half.setBackgroundResource(R.drawable.bg_half_full);
            amount.setText("Rs 249");
            increase.setVisibility(View.VISIBLE);
            decrease.setVisibility(View.VISIBLE);
            price.setVisibility(View.VISIBLE);
            amount.setVisibility(View.VISIBLE);
            order.setVisibility(View.VISIBLE);
            quantity.setVisibility(View.VISIBLE);
            quantity.setText("1");
            half_bool=false;
            full_bool=true;
        });
        increase.setOnClickListener(v->{
            int quantity_=Integer.parseInt(String.valueOf(quantity.getText()));
            quantity_=quantity_+1;
            String quantity_1=Integer.toString(quantity_);
            quantity.setText(quantity_1);
        });
        decrease.setOnClickListener(v->{
            int quantity_=Integer.parseInt(String.valueOf(quantity.getText()));
            if ((quantity_>1)){
                quantity_=quantity_-1;
                String quantity_1=Integer.toString(quantity_);
                quantity.setText(quantity_1);
            }
            else {
                Toast.makeText(contextNullSafe, "Quantity can't be less than one !!", Toast.LENGTH_SHORT).show();
            }
        });
        order.setOnClickListener(v->{
            Toast.makeText(contextNullSafe, "Your Order is Placed !!", Toast.LENGTH_SHORT).show();
        });

        cart.setOnClickListener(v->{
            if (half_bool==false && full_bool==false){
                Toast.makeText(contextNullSafe, "Please select the quantity !!", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(contextNullSafe, "Item added to cart !!", Toast.LENGTH_SHORT).show();
            }

        });

        if (contextNullSafe == null) getContextNullSafety();
        return  view;
    }
    public Context getContextNullSafety() {
        if (getContext() != null) return getContext();
        if (getActivity() != null) return getActivity();
        if (contextNullSafe != null) return contextNullSafe;
        if (getView() != null && getView().getContext() != null) return getView().getContext();
        if (requireContext() != null) return requireContext();
        if (requireActivity() != null) return requireActivity();
        if (requireView() != null && requireView().getContext() != null)
            return requireView().getContext();

        return null;

    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        contextNullSafe = context;
    }
}