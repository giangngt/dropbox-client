package vn.edu.usth.dropboxclient;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    private static final int GET_FROM_GALLERY = 10;

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
        pager.setOffscreenPageLimit(4);
        pager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab);
        tabLayout.setupWithViewPager(pager);

        Button clickButton = (Button) findViewById(R.id.upload_file);
        clickButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivityForResult(
                        new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI
                        ),
                        GET_FROM_GALLERY
                );
            }
        });

        Button clickButton2 = (Button) findViewById(R.id.upload_file);
        clickButton2.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(intent);
            }
        });

        //Bottom nav
        bottomNavi();


    }

    private void bottomNavi() {
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
                        finish();
                        return true;
                    case R.id.files_activity:
                        startActivity(new Intent(getApplicationContext(), FilesActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.account_activity:
                        startActivity(new Intent(getApplicationContext(), AccountActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.create_button:
                        botMenuPop();
                        finish();
                        break;
                }
                return false;
            }
        });
    }

    private void botMenuPop() {
        Context wrapper = new ContextThemeWrapper(MainActivity.this, R.style.MyPopupOtherStyle);
        PopupMenu popup = new PopupMenu(wrapper, findViewById(R.id.create_button));
        try {
            Field[] fields = popup.getClass().getDeclaredFields();
            for (Field field : fields) {
                if ("mPopup".equals(field.getName())) {
                    field.setAccessible(true);
                    Object menuPopupHelper = field.get(popup);
                    Class<?> classPopupHelper = Class.forName(menuPopupHelper.getClass().getName());
                    Method setForceIcons = classPopupHelper.getMethod("setForceShowIcon", boolean.class);
                    setForceIcons.invoke(menuPopupHelper, true);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.create_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.transfer_files:
                        return true;
                    case R.id.take_photo:
                        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                        startActivity(intent);
                        return true;
                    case R.id.upload_photos:
                        startActivityForResult(
                                new Intent(
                                        Intent.ACTION_PICK,
                                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI
                                ),
                                GET_FROM_GALLERY
                        );
                        return true;
                    case R.id.create_or_upload:
                        return true;
                    case R.id.create_folder:
                        return true;
                }
                return false;
            }
        });

        popup.show();
        return;
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