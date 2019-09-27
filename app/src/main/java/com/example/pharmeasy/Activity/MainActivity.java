package com.example.pharmeasy.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pharmeasy.Database.DBHelper;
import com.example.pharmeasy.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button button;
    TextView navheadertitle;
    String x;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                sendEmail();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View hView =  navigationView.getHeaderView(0);

        CardView cardOrders = findViewById(R.id.cardOrders);
        cardOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent orders_intent = new Intent( MainActivity.this, OrdersActivity.class);
                startActivity(orders_intent);
            }
        });

        CardView cardMedicine = findViewById(R.id.cardMedicine);
        cardMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent medicine_intent = new Intent( MainActivity.this, MedicineActivity.class);
                startActivity(medicine_intent);
            }
        });

        CardView cardDelivery = findViewById(R.id.cardDelivery);
        cardDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent delivery_intent = new Intent( MainActivity.this, DeliveryActivity.class);
                startActivity(delivery_intent);
            }
        });

        CardView cardfeed = findViewById(R.id.cardFeedback);
        cardfeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent delivery_intent = new Intent( MainActivity.this, pharmFeedback.class);
                startActivity(delivery_intent);
            }
        });

        navheadertitle =  (TextView)  hView.findViewById(R.id.user_nav);
        x = "Welcome "+ dbHelper.getUsername() + "!";
        navheadertitle.setText(x);
        Toast.makeText(getApplicationContext(),x,Toast.LENGTH_LONG);

    }

    protected void sendEmail() {
        Log.i("Send email", "");
        String[] TO = {"info@pharmeasy.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(MainActivity.this, settingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_orders) {
            Intent orders_intent = new Intent( MainActivity.this, OrdersActivity.class);
            startActivity(orders_intent);
        } else if (id == R.id.nav_medicine) {
            Intent medicine_intent = new Intent( MainActivity.this, MedicineActivity.class);
            startActivity(medicine_intent);
        } else if (id == R.id.nav_delivery) {
            Intent delivery_intent = new Intent( MainActivity.this, DeliveryActivity.class);
            startActivity(delivery_intent);
        } else if (id == R.id.nav_settings) {
            Intent settingsIntent = new Intent(MainActivity.this, settingsActivity.class);
            startActivity(settingsIntent);

        } else if (id == R.id.nav_logout) {
            dbHelper.changeuser();
            Intent logIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(logIntent);
            Toast.makeText(getApplicationContext(),"Successfully Logged Out",Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
