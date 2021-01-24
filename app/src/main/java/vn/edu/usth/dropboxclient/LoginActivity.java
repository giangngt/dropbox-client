package vn.edu.usth.dropboxclient;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.dropbox.core.v2.users.FullAccount;

import java.util.Arrays;

import vn.edu.usth.dropboxclient.api.DropboxAPI;
import vn.edu.usth.dropboxclient.api.DropboxClientFactory;
import vn.edu.usth.dropboxclient.api.GetCurrentAccountTask;


/**
 * Activity that shows information about the currently logged in user
 */
public class LoginActivity extends DropboxAPI {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
//        setSupportActionBar(toolbar);

        Button loginButton = (Button)findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DropboxAPI.startOAuth2Authentication(LoginActivity.this, getString(R.string.app_key), Arrays.asList("account_info.read", "files.content.write"));
            }
        });

//        Button filesButton = (Button)findViewById(R.id.files_button);
//        filesButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(FilesActivity.getIntent(UserActivity.this, ""));
//            }
//        });
//
//        Button openWithButton = (Button)findViewById(R.id.open_with);
//        openWithButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent openWithIntent = new Intent(UserActivity.this, OpenWithActivity.class);
//                startActivity(openWithIntent);
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (hasToken()) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
//            findViewById(R.id.login_button).setVisibility(View.GONE);
//            findViewById(R.id.email_text).setVisibility(View.VISIBLE);
//            findViewById(R.id.name_text).setVisibility(View.VISIBLE);
//            findViewById(R.id.type_text).setVisibility(View.VISIBLE);
//            findViewById(R.id.files_button).setEnabled(true);
//            findViewById(R.id.open_with).setEnabled(true);
//        } else {
//            findViewById(R.id.login_button).setVisibility(View.VISIBLE);
//            findViewById(R.id.email_text).setVisibility(View.GONE);
//            findViewById(R.id.name_text).setVisibility(View.GONE);
//            findViewById(R.id.type_text).setVisibility(View.GONE);
//            findViewById(R.id.files_button).setEnabled(false);
//            findViewById(R.id.open_with).setEnabled(false);
//        }
    }

    @Override
    protected void loadData() {
        new GetCurrentAccountTask(DropboxClientFactory.getClient(), new GetCurrentAccountTask.Callback() {
            @Override
            public void onComplete(FullAccount result) {
//                ((TextView) findViewById(R.id.email_text)).setText(result.getEmail());
//                ((TextView) findViewById(R.id.name_text)).setText(result.getName().getDisplayName());
//                ((TextView) findViewById(R.id.type_text)).setText(result.getAccountType().name());
            }
            @Override
            public void onError(Exception e) {
                Log.e(getClass().getName(), "Failed to get account details.", e);
            }
        }).execute();
    }

}
