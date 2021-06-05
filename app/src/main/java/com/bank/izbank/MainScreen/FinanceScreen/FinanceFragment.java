package com.bank.izbank.MainScreen.FinanceScreen;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
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

import static com.parse.Parse.getApplicationContext;

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

        cryptoPostAdapter = new CryptoPostAdapter(cryptoModels, getActivity(), getContext(), new ItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView.ViewHolder vh, CryptoModel item, int pos) {
                //popup menu


                AlertDialog.Builder buyCryptoPopup=new AlertDialog.Builder(getContext());
                buyCryptoPopup.setTitle("Crypto Currency Buy");
               Drawable d = item.getImage().getDrawable();
               buyCryptoPopup.setIcon(d);

                buyCryptoPopup.setView(R.layout.finance_screen_cryto_buy_popup);
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View dialogView= inflater.inflate(R.layout.finance_screen_cryto_buy_popup, null);
                buyCryptoPopup.setView(dialogView);
                TextView name= dialogView.findViewById(R.id.CrytoNameTextView);
                TextView value= dialogView.findViewById(R.id.CryptovalueTextView);
                TextView amount= dialogView.findViewById(R.id.CryptoAmountTextView);
                EditText buyAmount= dialogView.findViewById(R.id.CyrptoBuyAmountEditText);
                name.setText(item.getCurrencyName());
                value.setText(item.getPrice());


                buyAmount.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if(s.toString().equals("")){
                            amount.setText("0.0000");
                        }else{
                            amount.setText(String.valueOf((double) (Integer.parseInt(s.toString()) / Double.parseDouble(item.getPrice()))));
                        }

                    }
                });

                buyCryptoPopup.setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "buyed", Toast.LENGTH_SHORT).show();
                    }
                });
                buyCryptoPopup.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Canceled", Toast.LENGTH_SHORT).show();
                    }
                });
                buyCryptoPopup.create().show();
             //   Toast.makeText(getActivity(), "Item clicked: " + pos+" item"+item.getCurrencyName(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(cryptoPostAdapter);
        cryptoPostAdapter.notifyDataSetChanged();


    }

}
