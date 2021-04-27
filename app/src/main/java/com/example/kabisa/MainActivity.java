package com.example.kabisa;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.kabisa.donnees.BaseDonneFragment;
import com.example.kabisa.menu.Autre_lit;
import com.example.kabisa.menu.MenuFragment;
import com.example.kabisa.registre.Login;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    MenuItem menuItem;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string._menu);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new MenuFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_galery);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        menuItem = menu.findItem(R.id.action_langues);
        return true;
        //return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int langue = item.getItemId();
        if (langue == R.id.fr){
            menuItem.setIcon(getDrawable(R.drawable.ic_more));
            //return true;
        }else if (langue == R.id.egl){
            menuItem.setIcon(getDrawable(R.drawable.ic_more));
        }else if (langue == R.id.nd){
            menuItem.setIcon(getDrawable(R.drawable.ic_more));
        }else if (langue == R.id.Ds){
            menuItem.setIcon(getDrawable(R.drawable.ic_more));
        }else if (langue == R.id.prt){
            menuItem.setIcon(getDrawable(R.drawable.ic_more));
            menuItem.setIcon(getDrawable(R.drawable.ic_more));
        }else if (langue == R.id.spg){
            menuItem.setIcon(getDrawable(R.drawable.ic_more));
        }else if (langue == R.id.jpn){
            menuItem.setIcon(getDrawable(R.drawable.ic_more));
        }else if (langue == R.id.arb){
            menuItem.setIcon(getDrawable(R.drawable.ic_more));
        }else if (langue == R.id.pak){
            menuItem.setIcon(getDrawable(R.drawable.ic_more));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MenuFragment()).commit();
                break;
            case R.id.nav_ajout:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Autre_lit()).commit();
                //Toolbar toolbar = findViewById(R.id.toolbar);
                toolbar.setTitle(R.string._overlist);
               // setSupportActionBar(toolbar);
                break;
            case R.id.nav_login:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Login()).commit();
                break;
            case R.id.nav_galery:
                Toast.makeText(this, R.string._gallery, Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_suggestion:
                Toast.makeText(this, R.string._suggestion, Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_helper:
                Toast.makeText(this, R.string._helper, Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_parametre:
                Toast.makeText(this, R.string._about_app, Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_exit:
                Toast.makeText(this, R.string._sortir, Toast.LENGTH_SHORT).show();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}

