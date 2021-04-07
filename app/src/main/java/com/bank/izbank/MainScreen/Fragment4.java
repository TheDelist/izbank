package com.bank.izbank.MainScreen;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.bank.izbank.R;

public class Fragment4 extends Fragment implements SearchView.OnQueryTextListener{

    private Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_4,container,false);




    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toolbar = getView().findViewById(R.id.toolbar_bill);
        toolbar.setTitle("deneme");
        toolbar.setLogo(R.drawable.icon_bill);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        setHasOptionsMenu(true);


    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        inflater.inflate(R.menu.bill_toolbar_menu,menu);

        MenuItem item = menu.findItem(R.id.toolbar_bill_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(this);


        super.onCreateOptionsMenu(menu, inflater);



    }



    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.e("oluyor",query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.e("oluyor",newText);
        return true;
    }
}
