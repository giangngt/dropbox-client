package vn.edu.usth.dropboxclient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button button = toolbar.findViewById(R.id.upload_file);
        setTitle("Home");

        PagerAdapter adapter = new HomeFragmentPagerAdapter(
                getSupportFragmentManager());
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setOffscreenPageLimit(3);
        pager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab);
        tabLayout.setupWithViewPager(pager);

        //Bottom nav
        BottomNavigationView botnav = findViewById(R.id.bottom_nav);
        botnav.setSelectedItemId(R.id.home_activity); //set

        botnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home_activity:
                        return true;
                    case R.id.photo_activity:
                        startActivity(new Intent(getApplicationContext(), PhotoActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.files_activity:
                        startActivity(new Intent(getApplicationContext(), FilesActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.account_activity:
                        startActivity(new Intent(getApplicationContext(), AccountActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.create_button:
                        PopupMenu popup = new PopupMenu(MainActivity.this, findViewById(R.id.create_button));
                        MenuInflater inflater = popup.getMenuInflater();
                        inflater.inflate(R.menu.create_menu, popup.getMenu());
                        popup.show();
                        break;
                }
                return false;
            }
        });

    }
    public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
        private final int PAGE_COUNT = 4;
        private String titles[] = new String[] { "Recent", "Shared", "Starred", "Offline" };
        public HomeFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return PAGE_COUNT; // number of pages for a ViewPager
        }

        @Override
        public Fragment getItem(int page) {
            switch (page) {
                case 0:
                    return new RecentFragment();
                case 1:
                    return new SharedFragment();
                case 2:
                    return new StarredFragment();
                case 3:
                    return new OfflineFragment();
            }
            return new Fragment();

        }
        @Override
        public CharSequence getPageTitle(int page){
            return titles[page];
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("main", "start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("main", "resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("main", "pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("main", "stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("main", "destroy");
    }
}