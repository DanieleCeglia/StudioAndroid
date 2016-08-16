package dcsoft.ciaomondo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity ";

    private EditText etUsername;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            Log.d("DEBUGAPP", TAG + "onCreate: " + savedInstanceState.toString());
        }

        Log.d("DEBUGAPP", TAG + "onCreate");

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);

        Button bLogin = (Button) findViewById(R.id.bLogin);
        if (bLogin != null) {
            bLogin.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(MainActivity.this, AreaRiservata.class);
                            i.putExtra("username", etUsername != null ? etUsername.getText().toString() : null);
                            i.putExtra("password", etPassword != null ? etPassword.getText().toString() : null);
                            startActivity(i);
                        }
                    }
            );
        }

        Button bNotifica = (Button) findViewById(R.id.bNotifica);
        if (bNotifica != null) {
            bNotifica.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(v.getContext(), Messaggi.class);
                            PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, i, 0);

                            Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

                            NotificationCompat.Builder n = (NotificationCompat.Builder) new NotificationCompat.Builder(v.getContext())
                                    .setContentTitle("Arrivato nuovo messaggio!!")
                                    .setContentText("Autore: Nicola Rossi")
                                    .setSmallIcon(android.R.drawable.ic_dialog_email)
                                    .setContentIntent(pi)
                                    .setAutoCancel(true)
                                    .setSound(sound);

                            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                            notificationManager.notify(0, n.build());

                            Log.d("DEBUGAPP", TAG + notificationManager.toString());
                        }
                    }
            );
        }

        Button bToast = (Button) findViewById(R.id.bToast);
        if (bToast != null) {
            bToast.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(MainActivity.this, "Notifica toast!", Toast.LENGTH_SHORT).show();
                        }
                    }
            );
        }

        final Button bDialog = (Button) findViewById(R.id.bDialog);
        if (bDialog != null) {
            bDialog.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (bDialog.getText().equals("DIALOG SEMPLICE")) {
                                final Handler handler = new Handler();
                                Timer t = new Timer();
                                t.schedule(new TimerTask() {
                                    public void run() {
                                        handler.post(new Runnable() {
                                            public void run() {
                                                DialogActivity da = new DialogActivity(MainActivity.this);
                                                da.show();
                                                bDialog.setText("PROGRESS DIALOG");
                                            }
                                        });
                                    }
                                }, 2000);
                            } else {
                                final ProgressDialog progress = ProgressDialog.show(MainActivity.this, "Attendere", "Scaricamento in corso... (3)", true);

                                new Thread(new Runnable() {
                                    @Override
                                    public void run()
                                    {
                                        SystemClock.sleep(1000);
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run()
                                            {
                                                progress.setMessage("Scaricamento in corso... (2)");
                                            }
                                        });
                                        SystemClock.sleep(1000);
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run()
                                            {
                                                progress.setMessage("Scaricamento in corso... (1)");
                                            }
                                        });
                                        SystemClock.sleep(1000);
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run()
                                            {
                                                progress.dismiss();
                                                bDialog.setText("DIALOG SEMPLICE");
                                            }
                                        });
                                    }
                                }).start();
                            }
                        }
                    }
            );
        }

        Button bWebView = (Button) findViewById(R.id.bWebView);
        if (bWebView != null) {
            bWebView.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(MainActivity.this, MyWebView.class);
                            startActivity(i);
                        }
                    }
            );
        }
    }

    public void reset(View v) {
        if (etUsername != null) {
            etUsername.setText("");
        }
        if (etPassword != null) {
            etPassword.setText("");
        }
    }

    @Override
    protected void onStart(){
        super.onStart();

        Log.d("DEBUGAPP", TAG + "onStart");
    }

    @Override
    protected void onResume(){
        super.onResume();

        Log.d("DEBUGAPP", TAG + "onResume");
    }

    @Override
    protected void onPause(){
        super.onPause();

        Log.d("DEBUGAPP", TAG + "onPause");
    }

    @Override
    protected void onStop(){
        super.onStop();

        Log.d("DEBUGAPP", TAG + "onStop");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        Log.d("DEBUGAPP", TAG + "onDestroy");
    }
}
