package vn.edu.usth.dropboxclient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PhotoActivity extends AppCompatActivity {
    private ImageView avatar1, avatar2,avatar3,avatar4,avatar5,avatar6,avatar7,avatar8,avatar9,avatar10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Bottom nav
        BottomNavigationView botnav = findViewById(R.id.bottom_nav);
        botnav.setSelectedItemId(R.id.photo_activity); //set

        botnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home_activity:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.photo_activity:
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
                        PopupMenu popup = new PopupMenu(PhotoActivity.this, findViewById(R.id.create_button));
                        MenuInflater inflater = popup.getMenuInflater();
                        inflater.inflate(R.menu.create_menu, popup.getMenu());
                        popup.show();
                        break;
                }
                return false;
            }
        });
        avatar1 = (ImageView)findViewById(R.id.imageView1);
        avatar2 = (ImageView)findViewById(R.id.imageView2);
        avatar3 = (ImageView)findViewById(R.id.imageView3);
        avatar4 = (ImageView)findViewById(R.id.imageView4);
        avatar5 = (ImageView)findViewById(R.id.imageView5);
        avatar6 = (ImageView)findViewById(R.id.imageView6);
        avatar7 = (ImageView)findViewById(R.id.imageView7);
        avatar8 = (ImageView)findViewById(R.id.imageView8);
        avatar9 = (ImageView)findViewById(R.id.imageView9);
        avatar10 = (ImageView)findViewById(R.id.imageView10);
        avatar1.setOnClickListener(mClick);
        avatar2.setOnClickListener(mClick);
        avatar3.setOnClickListener(mClick);
        avatar4.setOnClickListener(mClick);
        avatar5.setOnClickListener(mClick);
        avatar6.setOnClickListener(mClick);
        avatar7.setOnClickListener(mClick);
        avatar8.setOnClickListener(mClick);
        avatar9.setOnClickListener(mClick);
        avatar10.setOnClickListener(mClick);
    }
    private View.OnClickListener mClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Dialog builder = new Dialog(PhotoActivity.this);
            builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
            builder.getWindow().setBackgroundDrawable(
                    new ColorDrawable(android.graphics.Color.TRANSPARENT));
            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    //nothing;
                }
            });
            int a = v.getId();
            Uri path;
            if (R.id.imageView1 == a) {
                ImageView imageView = new ImageView(PhotoActivity.this);
                imageView.setImageResource(R.drawable.avatar1);
                builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
            }
            else if(R.id.imageView2 == a) {
                ImageView imageView = new ImageView(PhotoActivity.this);
                imageView.setImageResource(R.drawable.avatar2);
                builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
            }
            else if(R.id.imageView3 == a) {
                ImageView imageView = new ImageView(PhotoActivity.this);
                imageView.setImageResource(R.drawable.avatar3);
                builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
            }
            else if(R.id.imageView4 == a) {
                ImageView imageView = new ImageView(PhotoActivity.this);
                imageView.setImageResource(R.drawable.avatar4);
                builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
            }
            else if(R.id.imageView5 == a) {
                ImageView imageView = new ImageView(PhotoActivity.this);
                imageView.setImageResource(R.drawable.avatar5);
                builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
            }
            else if(R.id.imageView6 == a) {
                ImageView imageView = new ImageView(PhotoActivity.this);
                imageView.setImageResource(R.drawable.avatar6);
                builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
            }
            else if(R.id.imageView7 == a) {
                ImageView imageView = new ImageView(PhotoActivity.this);
                imageView.setImageResource(R.drawable.avatar7);
                builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
            }
            else if(R.id.imageView8 == a) {
                ImageView imageView = new ImageView(PhotoActivity.this);
                imageView.setImageResource(R.drawable.avatar8);
                builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
            }
            else if(R.id.imageView9 == a) {
                ImageView imageView = new ImageView(PhotoActivity.this);
                imageView.setImageResource(R.drawable.avatar9);
                builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
            }
            else {
                ImageView imageView = new ImageView(PhotoActivity.this);
                imageView.setImageResource(R.drawable.avatar10);
                builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
            }
            builder.show();
        }
    };
}