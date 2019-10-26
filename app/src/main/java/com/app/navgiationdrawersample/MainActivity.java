package com.app.navgiationdrawersample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageView imageViewDrawerToggle;
    private TextView textViewHome;
    private TextView textViewFavorites;
    private TextView textViewGallery;
    private TextView textViewTitle;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        findViews();
        setListeners();
        setInitialState();
    }

    private void setInitialState() {
        setHeader(textViewHome.getText());

        HomeFragment homeFragment = new HomeFragment();
        pushFragment(homeFragment, homeFragment.getTag());
    }

    private void setListeners() {
        textViewHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setHeader(textViewHome.getText());

                HomeFragment homeFragment = new HomeFragment();
                pushFragment(homeFragment, homeFragment.getTag());
            }
        });

        textViewFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setHeader(textViewFavorites.getText());

                FavoritesFragment favoritesFragment = new FavoritesFragment();
                pushFragment(favoritesFragment, favoritesFragment.getTag());
            }
        });

        textViewGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setHeader(textViewGallery.getText());

                GalleryFragment galleryFragment = new GalleryFragment();
                pushFragment(galleryFragment, galleryFragment.getTag());
            }
        });

        imageViewDrawerToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void findViews() {
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        imageViewDrawerToggle = findViewById(R.id.imageView_drawerToggle);
        textViewHome = navigationView.findViewById(R.id.textView_home);
        textViewFavorites = navigationView.findViewById(R.id.textView_favorites);
        textViewGallery = navigationView.findViewById(R.id.textView_gallery);
        textViewTitle = findViewById(R.id.textView_title);
    }

    public void setHeader(CharSequence title) {
        textViewTitle.setText(title);
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    public void pushFragment(Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, fragment, tag);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);

        } else if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStackImmediate();

        } else {
            super.onBackPressed();

        }
    }
}
