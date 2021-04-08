package com.bank.izbank.MainScreen;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bank.izbank.R;
import com.bank.izbank.Sign.CreditCard;

import java.util.ArrayList;

public class MyCreditCardAdapter extends RecyclerView.Adapter<MyCreditCardAdapter.ViewHolder> {

    ArrayList<CreditCard> MyCreditCardData;
    Activity context;

    public MyCreditCardAdapter(ArrayList<CreditCard> myCreditCardData,Activity activity) {
        this.MyCreditCardData = myCreditCardData;
        this.context = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.credit_car_cardview,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @NonNull


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final CreditCard CreditCard = MyCreditCardData.get(position);
        holder.textCreditCardNo.setText(CreditCard.getCreditCardNo());
        holder.textCreditCardLimit.setText(String.valueOf(CreditCard.getLimit()));



    }

    @Override
    public int getItemCount() {
        return MyCreditCardData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageCreditCard;
        TextView textCreditCardLimit;
        TextView textCreditCardNo;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageCreditCard = itemView.findViewById(R.id.image_view_add_credit_card);
            textCreditCardLimit = itemView.findViewById(R.id.text_view_credit_card_limit);
            textCreditCardNo = itemView.findViewById(R.id.text_view_credit_card_no);
            cardView = itemView.findViewById(R.id.card_view_credit_card);

        }
    }

}
