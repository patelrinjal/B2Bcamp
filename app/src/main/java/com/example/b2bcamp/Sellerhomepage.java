package com.example.b2bcamp;

import android.content.Intent;
import android.os.Bundle;

import com.example.b2bcamp.Utility.AllSharedPrefernces;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.TextView;

import org.json.JSONObject;

public class Sellerhomepage extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    AllSharedPrefernces allSharedPrefernces = null;

    NavigationView navigationView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellerhomepage);

        allSharedPrefernces = new AllSharedPrefernces(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_myproduct, R.id.nav_mycategory, R.id.nav_myorders, R.id.nav_newinquiries,
                R.id.nav_complain, R.id.nav_profile, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        loadData();
    }

    public void loadData() {

        try {

            View vh = navigationView.getHeaderView(0);

            TextView txt_name = (TextView) vh.findViewById(R.id.txt_name);
            TextView txt_mobile = (TextView) vh.findViewById(R.id.txt_mobile);

            JSONObject jsonObject = new JSONObject(allSharedPrefernces.getCustomerData());

            txt_name.setText(jsonObject.getString("user_name"));
            txt_mobile.setText(jsonObject.getString("contact_num"));

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sellerhomepage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.action_logout) {

            allSharedPrefernces.ClearAllData();

            Intent i = new Intent(Sellerhomepage.this,splash.class);
            startActivity(i);

            finishAffinity();

            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
