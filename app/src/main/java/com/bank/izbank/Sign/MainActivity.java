package com.bank.izbank.Sign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bank.izbank.MainScreen.MainScreenActivity;
import com.bank.izbank.R;
import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText userName,userPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName=findViewById(R.id.edittext_id_number_sign_in);
        userPass=findViewById(R.id.edittext_user_password_sign_in);

        //remember
        ParseUser parseUser=ParseUser.getCurrentUser();
       if(parseUser !=null ){
           Intent intent=new Intent(MainActivity.this, MainScreenActivity.class);
           startActivity(intent);
       }

      // startActivity(new Intent(MainActivity.this, MainScreenActivity.class));
    }
    public void signUp(View view){
        Intent signUp=new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(signUp);

    }
    public void signIn(View view){



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
                                        String name=object.getString("userRealName");

                                        Toast.makeText(getApplicationContext(),"Welcome "+name,Toast.LENGTH_LONG).show();
                                    }


                                }
                            }

                        }
                    });




                    //intent
                    Intent intent=new Intent(MainActivity.this,MainScreenActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

}