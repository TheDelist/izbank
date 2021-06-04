package com.bank.izbank.MainScreen.FinanceScreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bank.izbank.Adapters.CryptoPostAdapter;
import com.bank.izbank.R;
import com.bank.izbank.Sign.SignIn;

import java.util.ArrayList;

public class FinanceFragment extends Fragment {
        //exchange https://nomics.com/docs/#tag/Exchange-Rates
        //https://api.nomics.com/v1/exchange-rates?key=c5d0683b83dc6e2dbd00841b72f7c86c
    private ArrayList<CryptoModel> cryptoModels;
    private RecyclerView recyclerView;
    private CryptoPostAdapter cryptoPostAdapter;

    public FinanceFragment(ArrayList<CryptoModel> list){
        this.cryptoModels=list;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "selam " + SignIn.mainUser.getName(), Toast.LENGTH_LONG).show();
        return inflater.inflate(R.layout.finance_fragment,container,false);


    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        recyclerView=view.findViewById(R.id.recyclerView_finance);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        cryptoPostAdapter = new CryptoPostAdapter(cryptoModels,getActivity());
        recyclerView.setAdapter(cryptoPostAdapter);
        cryptoPostAdapter.notifyDataSetChanged();

    }

}
