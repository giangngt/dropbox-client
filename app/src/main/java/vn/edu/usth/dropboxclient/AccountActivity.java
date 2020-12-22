package vn.edu.usth.dropboxclient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.PopupMenu;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AccountActivity extends AppCompatActivity {

    private static final int GET_FROM_GALLERY = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Bottom nav
        BottomNavigationView botnav = findViewById(R.id.bottom_nav);
        botnav.setSelectedItemId(R.id.account_activity); //set

        botnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home_activity:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
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
                        return true;
                    case R.id.create_button:
                        PopupMenu popup = new PopupMenu(AccountActivity.this, findViewById(R.id.create_button));
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
                        break;
                }
                return false;
            }
        });
    }
}