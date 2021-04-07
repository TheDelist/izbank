package com.bank.izbank.MainScreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bank.izbank.R;
import com.bank.izbank.Sign.MainActivity;

public class Fragment3 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "selam " + MainActivity.mainUser.getName(), Toast.LENGTH_LONG).show();
        return inflater.inflate(R.layout.fragment_3,container,false);


    }
}
