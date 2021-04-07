package com.bank.izbank.MainScreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bank.izbank.MainScreen.Fragment1;
import com.bank.izbank.MainScreen.Fragment2;
import com.bank.izbank.MainScreen.Fragment3;
import com.bank.izbank.MainScreen.Fragment4;
import com.bank.izbank.MainScreen.Fragment5;
import com.bank.izbank.R;
import com.bank.izbank.Sign.Address;
import com.bank.izbank.Sign.MainActivity;
import com.bank.izbank.Sign.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.FindCallback;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class MainScreenActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Fragment tempFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);


        bottomNavigationView = findViewById(R.id.bottom_navigation);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new Fragment1()).commit();
            }
        }, 1000);   //0,25 seconds






        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.menu1:
                        tempFragment = new Fragment1();
                        break;
                    case R.id.menu2:
                        tempFragment = new Fragment2();
                        break;
                    case R.id.menu3:
                        tempFragment= new Fragment3();
                        break;
                    case R.id.menu4:
                        tempFragment = new Fragment4();
                        break;
                    case R.id.menu5:
                        tempFragment = new Fragment5();
                        break;

                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,tempFragment).commit();


                return true;
            }
        });


    }





    public void logOut(View view){
        ParseUser.logOutInBackground(new LogOutCallback() {
            @Override
            public void done(ParseException e) {
                if(e !=null){
                    Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }else{
                    Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}