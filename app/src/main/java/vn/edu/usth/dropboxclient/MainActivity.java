package vn.edu.usth.dropboxclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button button = toolbar.findViewById(R.id.upload_file);
        setTitle("Home");
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