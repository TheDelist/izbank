package com.bank.izbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainScreenActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Fragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new Fragment1()).commit();

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
}