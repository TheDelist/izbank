package com.bank.izbank.Adapters;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.ahmadrosid.svgloader.SvgLoader;
import com.bank.izbank.MainScreen.FinanceScreen.CryptoModel;
import com.bank.izbank.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CryptoPostAdapter  extends RecyclerView.Adapter<CryptoPostAdapter.PostHolder> {
    class PostHolder extends RecyclerView.ViewHolder{
        private TextView cryptoNameText,cryptoSymbolText,cryptoPriceText;
        private ImageView cryptoImageView;


        public PostHolder(@NonNull View itemView) {
            super(itemView);

            cryptoNameText=itemView.findViewById(R.id.crypto_name_text);
            cryptoSymbolText=itemView.findViewById(R.id.crypto_symbol_text);
            cryptoPriceText=itemView.findViewById(R.id.crypto_price_text_view);
            cryptoImageView=itemView.findViewById(R.id.crypto_imageview);
        }
    }



    private ArrayList<CryptoModel> models;
    private Activity activity;

    public CryptoPostAdapter(ArrayList<CryptoModel> models, Activity activity) {
        this.models = models;
        this.activity=activity;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.custom_view,parent,false);


        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        holder.cryptoNameText.setText(models.get(position).getCurrencyName());
        holder.cryptoSymbolText.setText(models.get(position).getCurrencySymbol());
        holder.cryptoPriceText.setText(models.get(position).getPrice());
        holder.cryptoImageView.setImageBitmap(models.get(position).getImage());

        if(models.get(position).getLogoUrl().substring(models.get(position).getLogoUrl().length()-3).equalsIgnoreCase("svg")){
            SvgLoader.pluck()
                    .with(activity)
                    .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                    .load(models.get(position).getLogoUrl(),holder.cryptoImageView);
        }else{
            Picasso.get().load(models.get(position).getLogoUrl()).into(holder.cryptoImageView);
        }




    }

    @Override
    public int getItemCount() {
        return models.size();
    }



}
