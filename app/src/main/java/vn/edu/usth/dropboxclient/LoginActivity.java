package vn.edu.usth.dropboxclient;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dropbox.core.v2.users.FullAccount;

import java.util.Arrays;

import vn.edu.usth.dropboxclient.api.DropboxAPI;
import vn.edu.usth.dropboxclient.api.DropboxClientFactory;
import vn.edu.usth.dropboxclient.api.GetCurrentAccountTask;
import vn.edu.usth.dropboxclient.api.DownloadImageTask;


/**
 * Activity that shows information about the currently logged in user
 */
public class LoginActivity extends DropboxAPI {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        Button loginButton = (Button)findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DropboxAPI.startOAuth2Authentication(LoginActivity.this, getString(R.string.app_key), Arrays.asList("account_info.read", "files.content.write", "files.content.read"));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (hasToken()) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }

    @Override
    protected void loadData() {
        new GetCurrentAccountTask(DropboxClientFactory.getClient(), new GetCurrentAccountTask.Callback() {
            @Override
            public void onComplete(FullAccount result) {
                AsyncTask<String, Void, Void> download = new DownloadImageTask();
                download.execute(result.getProfilePhotoUrl());
                SharedPreferences prefs = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("name", result.getName().getDisplayName());
                editor.putString("email", result.getEmail());
                editor.putString("type",result.getAccountType().name());
                editor.putString("country",result.getCountry());
                editor.apply();
            }
            @Override
            public void onError(Exception e) {
                Log.e(getClass().getName(), "Failed to get account details.", e);
            }
        }).execute();
    }

}
