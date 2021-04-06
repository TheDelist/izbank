package com.bank.izbank.MainScreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bank.izbank.R;
import com.bank.izbank.Sign.MainActivity;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class Fragment5 extends Fragment {
   private  Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_5,container,false);
    }


}
