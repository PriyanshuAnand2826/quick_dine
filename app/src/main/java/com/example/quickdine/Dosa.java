package com.example.quickdine;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quickdine.Adapter.food_adpter;
import com.example.quickdine.data_class.food_data;

import java.util.ArrayList;
import java.util.List;


public class Dosa extends Fragment {
    RecyclerView recyclerView;
    private Context contextNullSafe;
    List<food_data> list;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_dosa, container, false);
        list=new ArrayList<>();
        recyclerView=view.findViewById(R.id.rv1);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContextNullSafety());
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setLayoutManager(linearLayoutManager);
        food_adpter adapter=new food_adpter(getContextNullSafety(),list);
        recyclerView.setAdapter(adapter);


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