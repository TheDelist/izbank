package com.bank.izbank.splashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bank.izbank.Bill.Bill;
import com.bank.izbank.MainScreen.AdminPanelActivity;
import com.bank.izbank.MainScreen.MainScreenActivity;
import com.bank.izbank.R;
import com.bank.izbank.Sign.SignIn;
import com.bank.izbank.UserInfo.BankAccount;
import com.felipecsl.gifimageview.library.GifImageView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class splashScreen extends AppCompatActivity {

    private GifImageView gifImageView;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        gifImageView = findViewById(R.id.GifImageView);
        progressBar = findViewById(R.id.progress_barr);
        progressBar.setVisibility(progressBar.VISIBLE);
        //deneme
        try {
            InputStream inputStream = getAssets().open("deneme2.gif");
            byte[] bytes = IOUtils.toByteArray(inputStream);
            gifImageView.setBytes(bytes);
            gifImageView.startAnimation();
        }
        catch (IOException ex){

        }





        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (SignIn.mainUser.getId().equals("9999") && SignIn.mainUser.getName().equals("admin")){
                    Intent intent = new Intent( splashScreen.this, AdminPanelActivity.class);
                    startActivity(intent);
                }
                else{
                    Intent splashIntent = new Intent( splashScreen.this, MainScreenActivity.class);
                    splashScreen.this.startActivity(splashIntent);
                }


                //splashIntent.putExtra("billList",bills);

                //splashScreen.this.startActivity(new Intent( splashScreen.this, MainScreenActivity.class));
                splashScreen.this.finish();
            }
        },5000);


    }


}