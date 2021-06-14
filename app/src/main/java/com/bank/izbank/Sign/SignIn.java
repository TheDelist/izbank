package com.bank.izbank.Sign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bank.izbank.Bill.Bill;
import com.bank.izbank.Bill.Date;
import com.bank.izbank.Job.Job;
import com.bank.izbank.R;
import com.bank.izbank.UserInfo.Address;
import com.bank.izbank.UserInfo.BankAccount;
import com.bank.izbank.UserInfo.CreditCard;
import com.bank.izbank.UserInfo.History;
import com.bank.izbank.UserInfo.User;
import com.bank.izbank.splashScreen.splashScreen;
import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SignIn extends AppCompatActivity {
    private EditText userName,userPass;
    public static User mainUser;
    public static String name;
    public String billType;
    public String billAmount;
    public String billDate;
    public ArrayList<Bill> bills;
    public ArrayList<BankAccount> bankAccounts;
    private Stack<History> history;
    public ArrayList<CreditCard> creditCards;
    String bankCash,bankAccountNo;
    String cardNo, cardLimit;
    public Intent intent ;
    public Job userJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remember
        ParseUser parseUser =ParseUser.getCurrentUser();
       if(parseUser !=null ){
          //loading screen
           getUser(parseUser);



       }else{
           setContentView(R.layout.activity_sign_in);//load screen
           userName=findViewById(R.id.edittext_id_number_sign_in);
           userPass=findViewById(R.id.edittext_user_password_sign_in);
       }


    }
    public void signUp(View view){
        Intent signUp=new Intent(SignIn.this, SignUpActivity.class);
        startActivity(signUp);

    }
    public void getUser(ParseUser parseUser)  {

        ParseQuery<ParseObject>  query=ParseQuery.getQuery("UserInfo");
        query.whereEqualTo("username",parseUser.getUsername().toString());

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e!=null){
                    e.printStackTrace();
                }else{
                    if(objects.size()>0){
                        for(ParseObject object:objects){
                            String name=object.getString("userRealName");
                            String phone=object.getString("phone");
                            String address_string= object.getString("address");
                            String[] str = address_string.split(" ");
                            Address address = new Address(str[0],str[1],Integer.parseInt(str[2]),Integer.parseInt(str[3]),Integer.parseInt(str[4]),str[5],str[6],str[7]);
                            String jobName = object.getString("job");
                            String maxCreditAmount = object.getString("maxCreditAmount");
                            String maxCreditInstallment = object.getString("maxCreditInstallment");
                            Job tempJob = new Job(jobName,maxCreditAmount,maxCreditInstallment);

                            Toast.makeText(getApplicationContext(),"Welcome "+name,Toast.LENGTH_LONG).show();
                            mainUser = new User(name, parseUser.getUsername(), phone,address,tempJob);

                            getBankAccounts();
                            getCreditCards();
                            getHistory();

                            getUserBills();

                        }


                    }

                    intent = new Intent(SignIn.this, splashScreen.class);
                    startActivity(intent);
                }

            }
        });



    }

    public void getUserBills(){
        ParseQuery<ParseObject> queryBill=ParseQuery.getQuery("Bill");
        queryBill.whereEqualTo("username",SignIn.mainUser.getId());
        queryBill.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e!=null){
                    e.printStackTrace();
                }else{
                    bills = new ArrayList<>();
                    if(objects.size()>0){
                        for(ParseObject object:objects){

                            billType=object.getString("type");
                            billAmount=object.getString("amount");
                            billDate=object.getString("date");

                            String [] date = billDate.split("/");

                            Date tempdate = new Date(date[0],date[1],date[2]);

                            Bill tempBill = new Bill(billType,Integer.parseInt(billAmount),tempdate);

                            bills.add(tempBill);


                        }


                    }
                    SignIn.mainUser.setUserbills(bills);

                }

            }
        });



    }


    public void getCreditCards(){
        ParseQuery<ParseObject> queryBankAccount=ParseQuery.getQuery("CreditCard");
        queryBankAccount.whereEqualTo("userId", SignIn.mainUser.getId());
        queryBankAccount.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e!=null){
                    e.printStackTrace();
                }else{
                    creditCards = new ArrayList<>();
                    if(objects.size()>0){
                        for(ParseObject object:objects){

                            cardNo=object.getString("creditCardNo");
                            cardLimit=object.getString("limit");

                            creditCards.add(new CreditCard(cardNo,Integer.parseInt(cardLimit)));


                        }


                    }
                    SignIn.mainUser.setCreditcards(creditCards);
                }


            }
        });
    }




    public void getBankAccounts(){
        ParseQuery<ParseObject> queryBankAccount=ParseQuery.getQuery("BankAccount");
        queryBankAccount.whereEqualTo("userId", SignIn.mainUser.getId());
        queryBankAccount.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e!=null){
                    e.printStackTrace();
                }else{
                    bankAccounts = new ArrayList<>();
                    if(objects.size()>0){
                        for(ParseObject object:objects){

                            bankAccountNo=object.getString("accountNo");
                            bankCash=object.getString("cash");

                            bankAccounts.add(new BankAccount(bankAccountNo,Integer.parseInt(bankCash)));


                        }


                    }
                    SignIn.mainUser.setBankAccounts(bankAccounts);
                }


            }
        });
    }
    public void getHistory(){
        ParseQuery<ParseObject> queryBankAccount=ParseQuery.getQuery("History");
        queryBankAccount.whereEqualTo("userId", SignIn.mainUser.getId());
        queryBankAccount.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e!=null){
                    e.printStackTrace();
                }else{
                    history = new Stack<>();
                    if(objects.size()>0){
                        for(ParseObject object:objects){


                            String id = object.getString("userId");
                            String process = object.getString("process");
                            java.util.Date date = object.getDate("date");
                            history.push(new History(id,process,date));



                        }


                    }
                    SignIn.mainUser.setHistory(history);
                    Toast.makeText(getApplicationContext(),"history alındı",Toast.LENGTH_LONG).show();
                }


            }
        });
    }



    public void signIn(View view){
        //loading screen



        ParseUser.logInInBackground(userName.getText().toString(), userPass.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e !=null){
                    Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }else{

                    ParseQuery<ParseObject> query=ParseQuery.getQuery("UserInfo");
                    query.whereEqualTo("username",userName.getText().toString());
                    query.findInBackground(new FindCallback<ParseObject>() {
                        @Override
                        public void done(List<ParseObject> objects, ParseException e) {
                            if(e!=null){
                                e.printStackTrace();
                            }else{

                                if(objects.size()>0){
                                    for(ParseObject object:objects){
                                      //  name=object.getString("userRealName");
                                        String name=object.getString("userRealName");
                                        String phone=object.getString("phone");
                                        String userId=object.getString("username");
                                        String address_string= object.getString("address");
                                        String[] str = address_string.split(" ");
                                        Address address = new Address(str[0],str[1],Integer.parseInt(str[2]),Integer.parseInt(str[3]),Integer.parseInt(str[4]),str[5],str[6],str[7]);
                                        String jobName = object.getString("job");
                                        String maxCreditAmount = object.getString("maxCreditAmount");
                                        String maxCreditInstallment = object.getString("maxCreditInstallment");
                                        Job tempJob = new Job(jobName,maxCreditAmount,maxCreditInstallment);

                                        Toast.makeText(getApplicationContext(),"Welcome "+name,Toast.LENGTH_LONG).show();

                                        Toast.makeText(getApplicationContext(),"Welcome "+name,Toast.LENGTH_LONG).show();
                                        mainUser = new User(name,userId, phone,address,tempJob);

                                        getBankAccounts();
                                        getCreditCards();
                                        getHistory();
                                        getUserBills();



                                        Toast.makeText(getApplicationContext(),"Welcome "+name,Toast.LENGTH_LONG).show();
                                    }


                                }
                            }

                        }
                    });


                    intent = new Intent(SignIn.this, splashScreen.class);
                    startActivity(intent);



                }
            }
        });
    }

}