package com.bank.izbank.MainScreen.FinanceScreen;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bank.izbank.Adapters.CryptoPostAdapter;
import com.bank.izbank.Bill.Bill;
import com.bank.izbank.Bill.BillAdapter;
import com.bank.izbank.MainScreen.MainScreenActivity;
import com.bank.izbank.R;
import com.bank.izbank.Sign.SignIn;
import com.bank.izbank.UserInfo.BankAccount;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

import static com.parse.Parse.getApplicationContext;

public class FinanceFragment extends Fragment implements SearchView.OnQueryTextListener {
        //exchange https://nomics.com/docs/#tag/Exchange-Rates
        //https://api.nomics.com/v1/exchange-rates?key=c5d0683b83dc6e2dbd00841b72f7c86c
    private ArrayList<CryptoModel> cryptoModels, searchList;
    private RecyclerView recyclerView;
    private CryptoPostAdapter cryptoPostAdapter ,searchAdapter;
    private ItemClickListener itemClickListener;
    private Toolbar toolbar;
    private String decMoney;
    private String currentMoney;
    int index;

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
        toolbar = getView().findViewById(R.id.toolbar);
        toolbar.setTitle("Search ");
        toolbar.setLogo(R.drawable.icons_bitcoin);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

        recyclerView=view.findViewById(R.id.recyclerView_finance);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        itemClickListener=new ItemClickListener() {
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
                            decMoney=s.toString();
                        }

                    }
                });

                buyCryptoPopup.setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        buyCrypto(Integer.parseInt(decMoney));
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
        };

        cryptoPostAdapter = new CryptoPostAdapter(cryptoModels, getActivity(), getContext(), itemClickListener);

        recyclerView.setAdapter(cryptoPostAdapter);
        cryptoPostAdapter.notifyDataSetChanged();


    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
       inflater.inflate(R.menu.crypto_search,menu);
        MenuItem menuItem=menu.findItem(R.id.search_view);
        SearchView searchView=(SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
    }
    public void toolbarSearch(String searchWord){

         searchList = searchBill(cryptoModels, searchWord);

        searchAdapter = new CryptoPostAdapter(searchList,getActivity(),getContext(),itemClickListener);
        recyclerView.setAdapter(searchAdapter);

    }

    public ArrayList<CryptoModel> searchBill(ArrayList<CryptoModel> list, String string){

        ArrayList<CryptoModel> returnList = new ArrayList<>();

        if(string.equals("")){
            return list;
        }
        else {
            for(CryptoModel cryptoModel: list){



                if( cryptoModel.getCurrencyName().toLowerCase().contains(string.toLowerCase())
                        || String.valueOf(cryptoModel.getPrice()).toLowerCase().contains(string.toLowerCase()) || cryptoModel.getCurrencySymbol().toLowerCase().contains(string.toLowerCase())){
                    returnList.add(cryptoModel);
                }

            }
            return returnList;
        }




    }
        public void buyCrypto(int decMoney){
        index=0;
        for (BankAccount bankAc:SignIn.mainUser.getBankAccounts()){
            if(bankAc.getCash()> decMoney){
                index=SignIn.mainUser.getBankAccounts().indexOf(bankAc);
                break;
            }
        }

            ParseQuery<ParseObject> query = ParseQuery.getQuery("BankAccount");
            query.whereEqualTo("accountNo", SignIn.mainUser.getBankAccounts().get(index).getAccountno());
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    if(e!=null){
                        e.printStackTrace();
                    }else{
                        if(objects.size()>0){
                            for(ParseObject object:objects){
                                ParseObject cryptoBuy=objects.get(0);
                                currentMoney=cryptoBuy.getString("cash");
                               currentMoney= String.valueOf( Integer.parseInt(currentMoney)-decMoney);
                                cryptoBuy.put("cash",currentMoney);

                                object.saveInBackground(new SaveCallback() {
                                    @Override
                                    public void done(ParseException e) {
                                        if(e != null){
                                            Toast.makeText(getApplicationContext(),e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
                                        }
                                        else{
                                            Toast.makeText(getApplicationContext(),"crypto parası düşüldü",Toast.LENGTH_LONG).show();
                                            SignIn.mainUser.getBankAccounts().get(index).setCash(Integer.parseInt(currentMoney));


                                        }
                                    }
                                });




                            }


                        }

                    }
                }
            });
        }

    @Override
    public boolean onQueryTextSubmit(String query) {
        toolbarSearch(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        toolbarSearch(newText);
        return false;
    }
}
