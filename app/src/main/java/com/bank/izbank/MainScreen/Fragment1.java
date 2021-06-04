package com.bank.izbank.MainScreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bank.izbank.Adapters.MyBankAccountAdapter;
import com.bank.izbank.Adapters.MyCreditCardAdapter;
import com.bank.izbank.R;
import com.bank.izbank.UserInfo.BankAccount;
import com.bank.izbank.UserInfo.CreditCard;
import com.bank.izbank.Sign.SignIn;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.Date;

public class Fragment1 extends Fragment {
    ImageView add_bank_account, add_credit_card;
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
        myCreditCard = new ArrayList<>();
        myBankAccount = new ArrayList<>();
        define();
        setDate();
        click();

        text_view_name.setText("HELLO, " + SignIn.mainUser.getName().toUpperCase()+".");


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerViewbankaccount.setHasFixedSize(true);
        recyclerViewbankaccount.setLayoutManager(new LinearLayoutManager(getActivity()));






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
        add_bank_account = getView().findViewById(R.id.image_view_add_bank_account);
        add_credit_card = getView().findViewById(R.id.image_view_add_credit_card);


    }

    public void click(){
        add_bank_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myBankAccount.add(new BankAccount(1234));
                MyBankAccountAdapter myBankAccountAdapter = new MyBankAccountAdapter(myBankAccount,getActivity() );
                recyclerViewbankaccount.setAdapter(myBankAccountAdapter);
            }
        });
        add_credit_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCreditCard.add(new CreditCard(9999));
                MyCreditCardAdapter myCreditCardAdapter = new MyCreditCardAdapter(myCreditCard,getActivity() );
                recyclerView.setAdapter(myCreditCardAdapter);
            }
        });
    }

    public void setDate(){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date currentTime = Calendar.getInstance().getTime();
        date.setText(format.format(currentTime));

    }
}
