package com.bank.izbank.MainScreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bank.izbank.R;
import com.bank.izbank.Sign.BankAccount;
import com.bank.izbank.Sign.CreditCard;
import com.bank.izbank.Sign.SignIn;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.Date;

public class Fragment1 extends Fragment {

    RecyclerView recyclerView;
    RecyclerView recyclerViewbankaccount;
    TextView text_view_name, date;
    ArrayList<CreditCard> myCreditCard;
    ArrayList<BankAccount> myBankAccount;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_1,container,false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        define();
        setDate();

        text_view_name.setText("HELLO, " + SignIn.mainUser.getName().toUpperCase()+".");


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerViewbankaccount.setHasFixedSize(true);
        recyclerViewbankaccount.setLayoutManager(new LinearLayoutManager(getActivity()));


        myCreditCard = new ArrayList<>();
        myCreditCard.add(new CreditCard(2000 ));
        myCreditCard.add(new CreditCard(2000 ));
        myCreditCard.add(new CreditCard(2000 ));
        myCreditCard.add(new CreditCard(2000 ));
        myCreditCard.add(new CreditCard(2000 ));
        myCreditCard.add(new CreditCard(2000 ));
        myCreditCard.add(new CreditCard(2000 ));


        myBankAccount = new ArrayList<>();
        myBankAccount.add(new BankAccount(1234));
        myBankAccount.add(new BankAccount(1234));
        myBankAccount.add(new BankAccount(1234));
        myBankAccount.add(new BankAccount(1234));
        myBankAccount.add(new BankAccount(1234));


        MyCreditCardAdapter myCreditCardAdapter = new MyCreditCardAdapter(myCreditCard,getActivity() );
        recyclerView.setAdapter(myCreditCardAdapter);

        MyBankAccountAdapter myBankAccountAdapter = new MyBankAccountAdapter(myBankAccount,getActivity() );
        recyclerViewbankaccount.setAdapter(myBankAccountAdapter);

    }

    public void define(){


        text_view_name = getView().findViewById(R.id.text_view_name);
        date = getView().findViewById(R.id.text_view_date_main);
        recyclerView = getView().findViewById(R.id.recyclerview_credit_card);
        recyclerViewbankaccount = getView().findViewById(R.id.recyclerview_bank_account);


    }

    public void setDate(){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date currentTime = Calendar.getInstance().getTime();
        date.setText(format.format(currentTime));

    }
}
