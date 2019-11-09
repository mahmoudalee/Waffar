package com.dell.waffar;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.dell.waffar.fragments.HomeFragment;
import com.dell.waffar.fragments.RoomsFragment;
import com.dell.waffar.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav;
//    private FrameLayout mMainFrame;

//    private HomeFragment homeFragment;
//    private ExploreFragment dashboardFragment;
//    private DoenlaodsFragment doenlaodsFragment;
//    private ProfileFragment settingsFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mMainNav = findViewById(R.id.bottom_navigation);


        mMainNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container , new HomeFragment() ).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;
            switch (menuItem.getItemId()){
                case R.id.nav_favorites:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.nav_inbox:
                    selectedFragment = new RoomsFragment();
                    break;
                case R.id.nav_profile:
                    selectedFragment = new ProfileFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container , selectedFragment ).commit();
            return true;
        }
    };


//    public void setData(String title) {
//        FragmentManager fm = getSupportFragmentManager();
//        HomeFragment.setData(title);
//    }
}
