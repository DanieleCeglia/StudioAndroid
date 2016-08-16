package dcsoft.ciaomondo;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class AreaRiservata extends AppCompatActivity {

    private static final String TAG = "AppCompatActivity ";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_riservata);

        if (savedInstanceState != null) {
            Log.d("DEBUGAPP", TAG + "onCreate: " + savedInstanceState.toString());
        }

        Log.d("DEBUGAPP", TAG + "onCreate");

        Intent i = getIntent();
        String username = i.getStringExtra("username");
        String password = i.getStringExtra("password");

        TextView tvUsernameInserita = (TextView) findViewById(R.id.tvUsernameInserita);
        if (tvUsernameInserita != null) {
            tvUsernameInserita.setText(username);
        }

        TextView tvPasswordInserita = (TextView) findViewById(R.id.tvPasswordInserita);
        if (tvPasswordInserita != null) {
            tvPasswordInserita.setText(password);
        }

        Button btContextButton = (Button) findViewById(R.id.btContextButton);
        registerForContextMenu(btContextButton);

        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();


        Button bListView = (Button) findViewById(R.id.bListView);
        if (bListView != null) {
            bListView.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(AreaRiservata.this, ListViewActivity.class);
                            startActivity(i);
                        }
                    }
            );
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();

        Log.d("DEBUGAPP", TAG + "onStart");
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "AreaRiservata Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://dcsoft.ciaomondo/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("DEBUGAPP", TAG + "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d("DEBUGAPP", TAG + "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "AreaRiservata Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://dcsoft.ciaomondo/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);

        Log.d("DEBUGAPP", TAG + "onStop");
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.disconnect();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d("DEBUGAPP", TAG + "onDestroy");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("DEBUGAPP", TAG + "onCreateOptionsMenu");

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.MENU_1: {
                Log.d("DEBUGAPP", TAG + "MENU_1");
            }
            break;
            case R.id.MENU_2: {
                Log.d("DEBUGAPP", TAG + "MENU_2");

                AlertDialog.Builder builder1 = new AlertDialog.Builder(AreaRiservata.this);
                builder1.setMessage("Ma ciao!");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Log.d("DEBUGAPP", TAG + "Ok");
                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "-_-",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Log.d("DEBUGAPP", TAG + "-_-");
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("Menu contestuale");
        menu.add(0, v.getId(), 0, "Azione 1");
        menu.add(0, v.getId(), 0, "Azione 2");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AreaRiservata.this);
        alertDialogBuilder.setTitle("ASD");
        alertDialogBuilder.setMessage("Holaz!");

        if (item.getTitle() == "Azione 1") {
            Log.d("DEBUGAPP", TAG + "Azione 1");

            alertDialogBuilder.setNeutralButton(
                    "Ok 1",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Log.d("DEBUGAPP", TAG + "Ok 1");
                            dialog.cancel();
                        }
                    });
        } else {
            Log.d("DEBUGAPP", TAG + "Azione 2");

            alertDialogBuilder.setNeutralButton(
                    "Ok 2",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Log.d("DEBUGAPP", TAG + "Ok 2");
                            dialog.cancel();
                        }
                    });
        }

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        return super.onContextItemSelected(item);
    }
}