package com.app.olaradio;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    public VideoView myvideov;
    private FloatingActionButton actionButton;
    private String uri = "http://cdns840stu0010.multistream.net/riosdevida2live/amlst:live/playlist.m3u8";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        myvideov = (VideoView) findViewById(R.id.myvideov);
        myvideov.setVideoURI(Uri.parse(uri));

        myvideov.setMediaController(new MediaController(this));

        try {
            myvideov.requestFocus();
            myvideov.start();
        } catch (Exception e) {
            System.out.println(e.getCause() + " >>>>>>>>>>>>>>>>>>>>>>>>>>>> " + e.getMessage());
        }


        LeadsFragment leadsFragment = (LeadsFragment)
                getSupportFragmentManager().findFragmentById(R.id.leads_container);

        if (leadsFragment == null) {
            leadsFragment = LeadsFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.leads_container, leadsFragment)
                    .commit();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myvideov.isPlaying()) {
                    try {
                        myvideov.requestFocus();
                        myvideov.pause();
                    } catch (Exception e) {
                        Snackbar.make(view, "Problemas de conexión, verifique su conexión a internet.", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    }
                    //fab.setImageResource(android.R.drawable.ic_media_play);
                    Snackbar.make(view, "Reproducción pausada!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                } else {
                    try {
                        myvideov.requestFocus();
                        myvideov.start();
                    } catch (Exception e) {
                        Snackbar.make(view, "Problemas de conexión, verifique su acceso a internet.", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    }
                    //fab.setImageResource(android.R.drawable.ic_media_pause);
                    Snackbar.make(view, "Reproducción iniciada!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }

            }
        });

        if (!verificaConexion(this)) {
            Toast.makeText(getBaseContext(), "Comprueba tu conexión a Internet. Saliendo ... ", Toast.LENGTH_LONG).show();
            this.finish();
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public static boolean verificaConexion(Context ctx) {
        boolean bConectado = false;
        ConnectivityManager connec = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        // No sólo wifi, también GPRS
        NetworkInfo[] redes = connec.getAllNetworkInfo();
        // este bucle debería no ser tan ñapa
        for (int i = 0; i < 2; i++) {
            // ¿Tenemos conexión? ponemos a true
            if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
                bConectado = true;
            }
        }
        return bConectado;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
        Intent i = new Intent(this, RunBackground.class);
        startService(i);
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onRestart() {
        super.onRestart();

    }
}
