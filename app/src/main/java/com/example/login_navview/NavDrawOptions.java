package com.example.login_navview;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.login_navview.databinding.ActivityNavDrawOptionsBinding;
import com.squareup.picasso.Picasso;

public class NavDrawOptions extends AppCompatActivity {
    ImageView imagen;
    TextView name,rol;
    String g_img,g_name,g_rol;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityNavDrawOptionsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        g_img = getIntent().getStringExtra("img");
        g_name = getIntent().getStringExtra("name");
        g_rol = getIntent().getStringExtra("rol");


        binding = ActivityNavDrawOptionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarNavDrawOptions.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        View headerView = navigationView.getHeaderView(0);
        imagen = (ImageView) headerView.findViewById(R.id.img);
        name = (TextView) headerView.findViewById(R.id.txtName);
        rol = (TextView) headerView.findViewById(R.id.txtRol);

        Picasso.get().load(g_img).into(imagen);
        name.setText(g_name);
        rol.setText(g_rol);

        mAppBarConfiguration = new
                AppBarConfiguration.Builder(R.id.main_menu, R.id.manage_users, R.id.general_settings)
                    .setDrawerLayout(drawer).build();
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_nav_draw_options);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
            NavigationUI.setupWithNavController(navigationView, navController);

        Menu menuNav=navigationView.getMenu();
        MenuItem [] nav_items = new MenuItem[7];
        nav_items [0] = menuNav.findItem(R.id.main_menu);
        nav_items [1] = menuNav.findItem(R.id.general_settings);
        nav_items [2] = menuNav.findItem(R.id.manage_users);
        nav_items [3] = menuNav.findItem(R.id.my_posts);
        nav_items [4] = menuNav.findItem(R.id.new_release);
        nav_items [5] = menuNav.findItem(R.id.shop);
        nav_items [6] = menuNav.findItem(R.id.favorites);
        if(rol.equals("Admin") )
            for (int i = 3; i<nav_items.length; i++ )
                nav_items[i].setVisible(false);
        else if(rol.equals("Vendedor"))
        {
            for (int i = 0; i< nav_items.length-4; i++)
                nav_items[i].setVisible(false);
                for (int j = 5; j< nav_items.length; j++)
                    nav_items[j].setVisible(false);
        }else if(rol.equals("Cliente"))
            for (int i = 0; i<nav_items.length-2; i++)
                nav_items[i].setVisible(false);

        binding.appBarNavDrawOptions.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_draw_options, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_nav_draw_options);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}