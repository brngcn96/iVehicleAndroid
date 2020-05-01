package com.example.vehicle;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private Fragment fragment;

    String token;
    int userid;
    int response;
    Bundle bundle=new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        token = getIntent().getExtras().getString("token");
       userid = getIntent().getExtras().getInt("userid");


        navigationView = findViewById(R.id.navigationView);
        drawer = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);
        //toolbar.setTitle("")
        setSupportActionBar(toolbar);

        fragment = new FragmentRent(); //hiçbirine basılmamışken arkada olacak olan

        bundle.putString("token",token);
        bundle.putInt("userid", userid);
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_tutucu, fragment).commit();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, 0, 0);
//acılma ve kapanma için desc resc değerleri olur gerekyok

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        View baslik = navigationView.inflateHeaderView(R.layout.navigation_baslik);

        navigationView.setNavigationItemSelectedListener(this); //navDra bu interface tetiklicek
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) { //hangi itema tıklandıgını sylick
        if(menuItem.getItemId() == R.id.nav_createchild){
            fragment = new FragmentCreateChild();
        }
        if(menuItem.getItemId() == R.id.nav_loadcredit){
            fragment = new FragmentLoadCredit();
        }
        if(menuItem.getItemId() == R.id.nav_bookavehicle){
            fragment = new FragmentBookaVehicle();
            bundle.putString("token",token);
            bundle.putInt("userid", userid);
            fragment.setArguments(bundle);

        }
        if(menuItem.getItemId() == R.id.nav_rent){
            fragment = new FragmentRent();
            bundle.putString("token",token);
            bundle.putInt("userid", userid);
            fragment.setArguments(bundle);

        }
        if(menuItem.getItemId() == R.id.nav_rides){
            fragment = new FragmentRides();
        }
        if(menuItem.getItemId() == R.id.nav_displayride){
            fragment = new FragmentDisplayRide();
            bundle.putString("token",token);
            bundle.putInt("userid", userid);
            fragment.setArguments(bundle);
        }

        if(menuItem.getItemId() == R.id.nav_gifts){
            fragment = new FragmentGifts();
        }




        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_tutucu, fragment).commit();

        drawer.closeDrawer(GravityCompat.START); //birini seçince kaoanıcak
        return true;
    }

    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){ //drawer acık durumda ise
            drawer.closeDrawer(GravityCompat.START);
        }else{ //kapalı durumda ise uygulamadan cıkar
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}
