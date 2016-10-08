package dcsoft.ciaomondo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
//import android.webkit.JavascriptInterface;
//import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;

public class MyWebView extends AppCompatActivity {

    private static final String TAG = "MyWebView ";
    private WebView wvSito;
    private ImageButton ibWebIndietro;
    private ImageButton ibWebAvanti;
    private ImageButton ibWebRicarica;
    private ImageButton ibWebStop;
    private ImageButton ibWebOpen;
    private ProgressBar progressBarWeb;
    public static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        wvSito = (WebView) findViewById(R.id.wvSito);
        if (wvSito != null) {
            wvSito.setWebViewClient(new WebViewClient() {
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);

                    ibWebIndietro.setEnabled(false);
                    ibWebIndietro.setAlpha((float) .25);
                    ibWebAvanti.setEnabled(false);
                    ibWebAvanti.setAlpha((float) .25);
                    ibWebRicarica.setEnabled(false);
                    ibWebRicarica.setAlpha((float) .25);
                    ibWebStop.setEnabled(true);
                    ibWebStop.setAlpha((float) 1);
                    ibWebOpen.setEnabled(false);
                    ibWebOpen.setAlpha((float) .25);

                    progressBarWeb.setVisibility(View.VISIBLE);
                }

                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);

                    if (wvSito.canGoBack()) {
                        ibWebIndietro.setEnabled(true);
                        ibWebIndietro.setAlpha((float) 1);
                    } else {
                        ibWebIndietro.setEnabled(false);
                        ibWebIndietro.setAlpha((float) .25);
                    }
                    if (wvSito.canGoForward()) {
                        ibWebAvanti.setEnabled(true);
                        ibWebAvanti.setAlpha((float) 1);
                    } else {
                        ibWebAvanti.setEnabled(false);
                        ibWebAvanti.setAlpha((float) .25);
                    }
                    ibWebRicarica.setEnabled(true);
                    ibWebRicarica.setAlpha((float) 1);
                    ibWebStop.setEnabled(false);
                    ibWebStop.setAlpha((float) .25);
                    ibWebOpen.setEnabled(true);
                    ibWebOpen.setAlpha((float) 1);

                    progressBarWeb.setVisibility(View.INVISIBLE);
                }
            }); // per navigare all'interno della webview stessa

            ibWebIndietro = (ImageButton) findViewById(R.id.ibWebIndietro);
            if (ibWebIndietro != null) {
                ibWebIndietro.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                wvSito.goBack();
                                Log.d("DEBUGAPP", TAG + "ibWebIndietro");
                            }
                        }
                );
            }

            ibWebAvanti = (ImageButton) findViewById(R.id.ibWebAvanti);
            if (ibWebAvanti != null) {
                ibWebAvanti.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                wvSito.goForward();
                                Log.d("DEBUGAPP", TAG + "ibWebAvanti");
                            }
                        }
                );
            }

            ibWebRicarica = (ImageButton) findViewById(R.id.ibWebRicarica);
            if (ibWebRicarica != null) {
                ibWebRicarica.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                wvSito.reload();
                                Log.d("DEBUGAPP", TAG + "ibWebRicarica");
                            }
                        }
                );
            }

            ibWebStop = (ImageButton) findViewById(R.id.ibWebStop);
            if (ibWebStop != null) {
                ibWebStop.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                wvSito.stopLoading();
                                Log.d("DEBUGAPP", TAG + "ibWebStop");
                            }
                        }
                );
            }

            ibWebOpen = (ImageButton) findViewById(R.id.ibWebOpen);
            if (ibWebOpen != null) {
                ibWebOpen.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(wvSito.getUrl()));
                                startActivity(browserIntent);
                                Log.d("DEBUGAPP", TAG + "ibWebOpen");
                            }
                        }
                );
            }

            progressBarWeb = (ProgressBar) findViewById(R.id.progressBarWeb);

//            WebSettings webSettings = wvSito.getSettings();
//            webSettings.setJavaScriptEnabled(true);
//            wvSito.addJavascriptInterface(new WebAppInterface(), "AndroidObject");

            /*
            Dopo questi passi, da Javascript potremo invocare il metodo executeJavaCode sull’oggetto
            Java che sarà stato registrato nella WebView con l’etichetta AndroidObject.
            Ad esempio, nella pagina web che richiameremo dalla WebView potremo usare il seguente
            snippet Javascript:

            <script type="text/javascript">
                    function interactWithAndroid()
            {
                AndroidObject.executeJavaCode();
            }
            </script>
            */

            
            wvSito.loadUrl("http://www.openstreetmap.org/#map=14/46.1822/12.9452");
        }

        // prove permessi...
        int statoPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        Log.d("DEBUGAPP", TAG + "statoPermission: " + statoPermission);

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                Log.d("DEBUGAPP", TAG + "Show an expanation to the user *asynchronously* -- don't block\n" +
                        "this thread waiting for the user's response! After the user\n" +
                        "sees the explanation, try again to request the permission.");

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

                // MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION is an
                // app-defined int constant. The callback method gets the
                // result of the request.

                Log.d("DEBUGAPP", TAG + "No explanation needed, we can request the permission.");
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        Log.d("DEBUGAPP", TAG + "onRequestPermissionsResult");
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                Log.d("DEBUGAPP", TAG + "If request is cancelled, the result arrays are empty.");
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    Log.d("DEBUGAPP", TAG + "permission was granted, yay! Do the\n" +
                            "contacts-related task you need to do.");

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Log.d("DEBUGAPP", TAG + "permission denied, boo! Disable the\n" +
                            "functionality that depends on this permission.");
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}

//class WebAppInterface
//{
//    @JavascriptInterface
//    public void executeJavaCode()
//    {
//        // Codice da attivare DA JAVASCRIPT
//    }
//}