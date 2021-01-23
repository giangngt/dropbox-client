package vn.edu.usth.dropboxclient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.PopupMenu;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class FilesActivity extends AppCompatActivity {

    private static final int GET_FROM_GALLERY = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Bottom nav
        bottomNavi();
    }

    private void bottomNavi() {
        BottomNavigationView botnav = findViewById(R.id.bottom_nav);
        botnav.setSelectedItemId(R.id.files_activity); //set

        botnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home_activity:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.photo_activity:
                        startActivity(new Intent(getApplicationContext(), PhotoActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.files_activity:
                        return true;
                    case R.id.account_activity:
                        startActivity(new Intent(getApplicationContext(), AccountActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.create_button:
                        botMenuPop();
                        break;
                }
                return false;
            }
        });
    }

    private void botMenuPop() {
        Context wrapper = new ContextThemeWrapper(FilesActivity.this, R.style.MyPopupOtherStyle);
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
}