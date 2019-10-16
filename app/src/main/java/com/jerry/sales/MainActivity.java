package com.jerry.sales;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;

    ActionBarDrawerToggle actionToggle;
    NavigationView navigationView;
    TextView textView;
    Button addB, listB;
    TextInputEditText productField, quantityField, unitPriceField;

    public static ArrayList<Stock>stocks = new ArrayList<Stock>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //tool bar implementation
        toolbar =  findViewById(R.id.toolbar);
        collapsingToolbarLayout = findViewById(R.id.collapseBar);
        setSupportActionBar(toolbar);

        textView = (TextView)findViewById(R.id.textchange);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView)findViewById(R.id.navigate);
        addB = (Button)findViewById(R.id.addId);
        listB = (Button)findViewById(R.id.listId);
        productField =(TextInputEditText)findViewById(R.id.productId);
        quantityField =(TextInputEditText)findViewById(R.id.quantityId);
        unitPriceField =(TextInputEditText)findViewById(R.id.unitPrice);
        //**********************************************************************************
        addB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem();
            }
        });
        listB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listIntent = new Intent(MainActivity.this,ItemListsClass.class);
                ActivityOptionsCompat compat = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(MainActivity.this,null);

                startActivity(listIntent ,compat.toBundle());

            }
        });
        //transaction of this activity to next
        TransitionInflater tf = TransitionInflater.from(this);
        Transition t = tf.inflateTransition(R.transition.my_transition);





        actionToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(actionToggle);
        actionToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void addItem() {

        if(productField.getText().toString().isEmpty()||quantityField.getText().toString().isEmpty()
                ||unitPriceField.getText().toString().isEmpty()) {


            if(productField.getText().toString().isEmpty()){
                productField.setError("Product field Is Empty");

            }
            if(quantityField.getText().toString().isEmpty()) {
                quantityField.setError("Quantity field Is Empty");
            }
            if(unitPriceField.getText().toString().isEmpty()){
                unitPriceField.setError("Price field Is Empty");

            }

        }  else {
            String productName = productField.getText().toString().trim();

            int quantity = Integer.parseInt(quantityField.getText().toString().trim());

            double unitPrice = Double.parseDouble(unitPriceField.getText().toString().trim());
            //double totalAmout =(quantity*unitPrice);

            stocks.add(new Stock(productName, quantity, unitPrice));
            quantityField.setText("");
            productField.setText("");
            unitPriceField.setText("");
            productField.requestFocus();

        }





    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()){

            case R.id.about:

                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("About me:");
                alertDialog.setMessage("My name is Idoko Agada Jerry. I am the developer of this App." +
                        " You can contact me for desktop and android app developments of any type.\n" +
                        "08160332264\nidokoidoko4@gmail.com");
                alertDialog.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create().show();
                break;
            case R.id.Exit:

                break;
        }

        return true;
    }



    //back button click notification for exit
    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if(doubleBackToExitPressedOnce){
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, " Click again to Exit",Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable(){
            public void run(){
                doubleBackToExitPressedOnce = false;
            }
        },2000);



    }

}
