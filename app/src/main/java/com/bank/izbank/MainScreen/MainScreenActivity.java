package com.bank.izbank.MainScreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bank.izbank.R;
import com.bank.izbank.Sign.SignIn;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class MainScreenActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    final Fragment fragment1 = new Fragment1();
    final Fragment fragment2 = new Fragment2();
    final Fragment fragment3 = new Fragment3();
    final Fragment fragment4 = new Fragment4();
    final Fragment fragment5 = new Fragment5();
    private Fragment tempFragment=fragment1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,fragment2,"2").hide(fragment2).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,fragment3,"3").hide(fragment3).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,fragment4,"4").hide(fragment4).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,fragment5,"5").hide(fragment5).commit();

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,fragment1).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.menu1:
                        getSupportFragmentManager().beginTransaction().hide(tempFragment).show(fragment1).commit();
                        tempFragment=fragment1;
                        break;
                    case R.id.menu2:
                        getSupportFragmentManager().beginTransaction().hide(tempFragment).show(fragment2).commit();
                        tempFragment=fragment2;
                        break;
                    case R.id.menu3:
                        getSupportFragmentManager().beginTransaction().hide(tempFragment).show(fragment3).commit();
                        tempFragment=fragment3;
                        break;
                    case R.id.menu4:
                        getSupportFragmentManager().beginTransaction().hide(tempFragment).show(fragment4).commit();
                        tempFragment=fragment4;
                        break;
                    case R.id.menu5:
                        getSupportFragmentManager().beginTransaction().hide(tempFragment).show(fragment5).commit();
                        tempFragment=fragment5;
                        break;

                }




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
                    Intent intent=new Intent(getApplicationContext(), SignIn.class);
                    startActivity(intent);
                }
            }
        });
    }
}