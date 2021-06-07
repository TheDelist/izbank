package com.bank.izbank.MainScreen;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bank.izbank.Adapters.MyBankAccountAdapter;
import com.bank.izbank.Adapters.MyCreditCardAdapter;
import com.bank.izbank.Bill.Bill;
import com.bank.izbank.Bill.BillAdapter;
import com.bank.izbank.Bill.PhoneBill;
import com.bank.izbank.R;
import com.bank.izbank.UserInfo.BankAccount;
import com.bank.izbank.UserInfo.CreditCard;
import com.bank.izbank.Sign.SignIn;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.Date;
import java.util.List;

import static com.parse.Parse.getApplicationContext;

public class Fragment1 extends Fragment {
    LinearLayout linear_layout_request_money;
    ImageView add_bank_account, add_credit_card;
    RecyclerView recyclerView;
    RecyclerView recyclerViewbankaccount;
    TextView text_view_name, date,text_view_total_money;
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
        myCreditCard = SignIn.mainUser.getCreditcards();
        myBankAccount = SignIn.mainUser.getBankAccounts();

        define();
        setDate();
        click();
        setTotalMoney(myBankAccount);

        text_view_name.setText("HELLO, " + SignIn.mainUser.getName().toUpperCase()+".");


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerViewbankaccount.setHasFixedSize(true);
        recyclerViewbankaccount.setLayoutManager(new LinearLayoutManager(getActivity()));

        MyCreditCardAdapter myCreditCardAdapter = new MyCreditCardAdapter(myCreditCard,getActivity(),myBankAccount ,recyclerViewbankaccount);
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
        linear_layout_request_money = getView().findViewById(R.id.linear_layout_request_money);
        text_view_total_money = getView().findViewById(R.id.text_view_total_money);

    }
    public void setTotalMoney(ArrayList<BankAccount> MyBankAccounts){
        int totalmoney = 0;
        for (int i = 0; i<MyBankAccounts.size();i++){
            totalmoney += MyBankAccounts.get(i).getCash();
        }
        text_view_total_money.setText(Integer.toString(totalmoney));
    }
    public void accountsToDatabase(BankAccount bankAc){
        ParseObject object=new ParseObject("BankAccount");
        object.put("accountNo",bankAc.getAccountno());
        object.put("userId", SignIn.mainUser.getId());

        object.put("cash",String.valueOf(bankAc.getCash()));


        object.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e != null){
                    Toast.makeText(getApplicationContext(),e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
                }
                else{
                      Toast.makeText(getApplicationContext(),"banka datada",Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    public void cardsToDatabase(CreditCard card){
        ParseObject object=new ParseObject("CreditCard");
        object.put("creditCardNo",card.getCreditCardNo());
        object.put("userId", SignIn.mainUser.getId());

        object.put("limit",String.valueOf(card.getLimit()));


        object.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e != null){
                    Toast.makeText(getApplicationContext(),e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"kart datada",Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    public void updateBankAccount(BankAccount bankac){
        ParseQuery<ParseObject> queryBankAccount=ParseQuery.getQuery("BankAccount");
        queryBankAccount.whereEqualTo("accountNo", bankac.getAccountno());
        queryBankAccount.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e!=null){
                    e.printStackTrace();
                }else{

                    if(objects.size()>0){
                        for(ParseObject object:objects){
                            object.deleteInBackground();
                            Toast.makeText(getApplicationContext(),"sildi",Toast.LENGTH_LONG).show();
                            accountsToDatabase(bankac);





                        }


                    }

                }


            }
        });
    }

    public void click(){
        add_bank_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myBankAccount.size()>=5){
                    Toast.makeText(getContext(), "YOU CANT ADD MORE THAN 5 BANK ACCOUNT", Toast.LENGTH_LONG).show();
                }
                else{
                    final EditText editText = new EditText(getContext());
                    editText.setHint("0");
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    AlertDialog.Builder ad = new AlertDialog.Builder(getContext());
                    ad.setTitle("How Much Money Do You Want?");
                    ad.setIcon(R.drawable.icon_save_money);
                    ad.setView(editText);
                    ad.setNegativeButton("ADD", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            try {

                                myBankAccount.add(new BankAccount(Integer.parseInt(editText.getText().toString())));
                                MyBankAccountAdapter myBankAccountAdapter = new MyBankAccountAdapter(myBankAccount,getActivity() );
                                recyclerViewbankaccount.setAdapter(myBankAccountAdapter);
                                setTotalMoney(myBankAccount);


                            }catch (NumberFormatException e){
                                myBankAccount.add(new BankAccount(0));
                                MyBankAccountAdapter myBankAccountAdapter = new MyBankAccountAdapter(myBankAccount,getActivity() );
                                recyclerViewbankaccount.setAdapter(myBankAccountAdapter);
                                setTotalMoney(myBankAccount);

                            }
                            accountsToDatabase(myBankAccount.get(myBankAccount.size()-1));




                        }
                    });
                    ad.create().show();

                }

            }
        });
        add_credit_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myCreditCard.size()>=5){
                    Toast.makeText(getContext(), "YOU CANT ADD MORE THAN 5 CREDIT CARD", Toast.LENGTH_LONG).show();
                }
                else{


                    final EditText editText = new EditText(getContext());
                    editText.setHint("0");
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    AlertDialog.Builder ad = new AlertDialog.Builder(getContext());
                    ad.setTitle("How Much Credit Card Limit Do You Want?");
                    ad.setIcon(R.drawable.icon_credit_card);
                    ad.setView(editText);
                    ad.setNegativeButton("ADD", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            try {
                                myCreditCard.add(new CreditCard(Integer.parseInt(editText.getText().toString())));
                                MyCreditCardAdapter myCreditCardAdapter = new MyCreditCardAdapter(myCreditCard,getActivity(),myBankAccount ,recyclerViewbankaccount);
                                recyclerView.setAdapter(myCreditCardAdapter);


                            }catch (NumberFormatException e){
                                myCreditCard.add(new CreditCard(0));
                                MyCreditCardAdapter myCreditCardAdapter = new MyCreditCardAdapter(myCreditCard,getActivity(),myBankAccount ,recyclerViewbankaccount);
                                recyclerView.setAdapter(myCreditCardAdapter);

                            }
                            cardsToDatabase(myCreditCard.get(myCreditCard.size()-1));

                        }
                    });
                    ad.create().show();

                }


            }
        });
        linear_layout_request_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myBankAccount.size()==0){
                    AlertDialog.Builder ad = new AlertDialog.Builder(getContext());
                    ad.setTitle("You dont have any bank account. Please add one before request.");
                    ad.setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i){

                        }
                    });
                    ad.create().show();
                }
                else{
                    final EditText editText = new EditText(getContext());
                    editText.setHint("How Much Do You Want to Request?");
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);

                    AlertDialog.Builder ad = new AlertDialog.Builder(getContext());

                    ad.setTitle("Which Bank Account Do You Want to Request?");
                    ad.setIcon(R.drawable.icon_credit_card);
                    ad.setView(editText);
                    String[] items = new String[myBankAccount.size()];
                    for (int i =0; i<myBankAccount.size();i++){
                        String data= myBankAccount.get(i).getAccountno() + "  $" + Integer.toString(myBankAccount.get(i).getCash());
                        items[i] = data;
                    }
                    final int[] checkedItem = {0};
                    ad.setSingleChoiceItems(items, checkedItem[0], new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            switch (i) {
                                case 0:
                                    checkedItem[0] =i;


                                    break;
                                case 1:

                                    checkedItem[0] =i;

                                    break;
                                case 2:

                                    checkedItem[0] =i;

                                    break;
                                case 3:

                                    checkedItem[0] =i;

                                    break;
                                case 4:

                                    checkedItem[0] =i;
                                    break;
                            }
                        }
                    });
                    ad.setNegativeButton("Request", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            i= checkedItem[0];
                            switch (i) {
                                case 0:

                                    try {
                                        myBankAccount.get(i).setCash(myBankAccount.get(i).getCash() + Integer.parseInt(editText.getText().toString()));
                                        updateBankAccount(myBankAccount.get(i));
                                        MyBankAccountAdapter myBankAccountAdapter = new MyBankAccountAdapter(myBankAccount,getActivity() );
                                        recyclerViewbankaccount.setAdapter(myBankAccountAdapter);
                                        setTotalMoney(myBankAccount);
                                    }catch (NumberFormatException e){

                                    }


                                    break;
                                case 1:


                                    try {
                                        myBankAccount.get(i).setCash(myBankAccount.get(i).getCash() + Integer.parseInt(editText.getText().toString()));
                                        updateBankAccount(myBankAccount.get(i));

                                        MyBankAccountAdapter myBankAccountAdapter = new MyBankAccountAdapter(myBankAccount,getActivity() );
                                        recyclerViewbankaccount.setAdapter(myBankAccountAdapter);
                                        setTotalMoney(myBankAccount);


                                    }catch (NumberFormatException e){

                                    }


                                    break;
                                case 2:

                                    try {
                                        myBankAccount.get(i).setCash(myBankAccount.get(i).getCash() + Integer.parseInt(editText.getText().toString()));
                                        updateBankAccount(myBankAccount.get(i));

                                        MyBankAccountAdapter myBankAccountAdapter = new MyBankAccountAdapter(myBankAccount,getActivity() );
                                        recyclerViewbankaccount.setAdapter(myBankAccountAdapter);
                                        setTotalMoney(myBankAccount);



                                    }catch (NumberFormatException e){

                                    }
                                    break;
                                case 3:

                                    try {

                                        myBankAccount.get(i).setCash(myBankAccount.get(i).getCash() + Integer.parseInt(editText.getText().toString()));
                                        updateBankAccount(myBankAccount.get(i));

                                        MyBankAccountAdapter myBankAccountAdapter = new MyBankAccountAdapter(myBankAccount,getActivity() );
                                        recyclerViewbankaccount.setAdapter(myBankAccountAdapter);
                                        setTotalMoney(myBankAccount);


                                    }catch (NumberFormatException e){

                                    }
                                    break;
                                case 4:
                                    try {
                                        myBankAccount.get(i).setCash(myBankAccount.get(i).getCash() + Integer.parseInt(editText.getText().toString()));
                                        updateBankAccount(myBankAccount.get(i));

                                        MyBankAccountAdapter myBankAccountAdapter = new MyBankAccountAdapter(myBankAccount,getActivity() );
                                        recyclerViewbankaccount.setAdapter(myBankAccountAdapter);
                                        setTotalMoney(myBankAccount);


                                    }catch (NumberFormatException e){

                                    }
                                    break;
                            }



                        }
                    });
                    ad.create().show();

                }

            }
        });


    }

    public void setDate(){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date currentTime = Calendar.getInstance().getTime();
        date.setText(format.format(currentTime));

    }


   /*
    @Override
    public void onStop() {
        super.onStop();
        shouldRefreshOnResume = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (shouldRefreshOnResume) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.setReorderingAllowed(false);
            transaction.detach(this).attach(this).commitAllowingStateLoss();
            shouldRefreshOnResume=false;
        }
    }

    */
}
