package com.bank.izbank.Sign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bank.izbank.MainScreen.MainScreenActivity;
import com.bank.izbank.R;
import com.bank.izbank.UserInfo.Address;
import com.bank.izbank.UserInfo.User;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {

    private EditText userNameText , userPassText ,userIdText,userPhoneText,userAddressText,userProfText;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        userNameText=findViewById(R.id.edittext_id_name_sign_up);
        userPassText=findViewById(R.id.edittext_user_password_sign_up);
        userIdText=findViewById(R.id.edittext_id_number_sign_up);
        userPhoneText=findViewById(R.id.edittext_phone_sign_up);
        userAddressText=findViewById(R.id.edittext_address_sign_up);
        userProfText=findViewById(R.id.edittext_profession_sign_up);





    }

    public void signUp(View view){
        user=new User(userNameText.getText().toString(),
                userIdText.getText().toString(),
                userPassText.getText().toString(),
                userPhoneText.getText().toString(),
                new Address("TPAO","ss",13,2,4,"Kırklareli","Lüleburgaz","Turkey"),
                userProfText.getText().toString());

        ParseUser parseUser=new ParseUser();
        parseUser.setUsername(user.getId());
        parseUser.setPassword(user.getPass());

        ParseObject object=new ParseObject("UserInfo");
        object.put("userRealName",user.getName());
        object.put("phone",user.getPhoneNumber());
        object.put("address",user.addressWrite());
        object.put("profession",user.getProfession());
        object.put("username", user.getId());

        object.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e != null){
                    Toast.makeText(getApplicationContext(),e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
                }
                else{
                  //  Toast.makeText(getApplicationContext(),"oldu galiba",Toast.LENGTH_LONG).show();

                }
            }
        });

       parseUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e != null){
                    Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"User Created" ,Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(getApplicationContext(), MainScreenActivity.class);
                    startActivity(intent);

                    //intent
                }
            }
        });




    }
}