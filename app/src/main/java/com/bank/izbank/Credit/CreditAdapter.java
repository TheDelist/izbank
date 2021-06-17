package com.bank.izbank.Credit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.bank.izbank.R;

import java.util.List;

public class CreditAdapter extends RecyclerView.Adapter<CreditAdapter.CardViewObjectHolder> {

    private Context mContext;
    private List<Credit> list;

    public CreditAdapter(Context mContext, List<Credit> list) {
        this.mContext = mContext;
        this.list = list;
    }



    public class CardViewObjectHolder extends RecyclerView.ViewHolder{

        public TextView textViewInstallment;
        public TextView textViewInterestRate;
        public TextView textViewAmount;
        public Button buttonCreditPay;
        public CardView cardViewCredit;

        public CardViewObjectHolder(View view){

            super(view);
            textViewInstallment = view.findViewById(R.id.textView_installment);
            textViewInterestRate = view.findViewById(R.id.textView_interest_rate);
            textViewAmount = view.findViewById(R.id.textView_credit_amount);
            buttonCreditPay = view.findViewById(R.id.buttonCreditPay);
            cardViewCredit = view.findViewById(R.id.cardView_credit);


        }


    }

    @NonNull
    @Override
    public CreditAdapter.CardViewObjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.credit_cardview,parent,false);
        return new CreditAdapter.CardViewObjectHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CreditAdapter.CardViewObjectHolder holder, int position) {

        Credit credit = list.get(position);

        holder.textViewInstallment.setText(String.valueOf(credit.getInstallment())+" months");
        holder.textViewInterestRate.setText("%"+credit.getInterestRate());
        holder.textViewAmount.setText(credit.getPayAmount()+" TL");

        holder.buttonCreditPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // ödeme yapılcak

            }
        });




    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
