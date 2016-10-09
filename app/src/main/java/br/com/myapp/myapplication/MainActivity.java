package br.com.myapp.myapplication;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.test.espresso.core.deps.guava.collect.Lists;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.Collection;

import br.com.myapp.myapplication.business.Atleta;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    protected WebView loginWebView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        AuthenticationWebViewClient authWebViewClient = AuthenticationWebViewClient.getInstance();

        loginWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = loginWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        loginWebView.setWebViewClient(authWebViewClient);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            loginWebView.loadUrl("https://login.globo.com/login/438");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_jogos) {
            loginWebView.loadUrl("https://api.cartolafc.globo.com/partidas");
        } else if (id == R.id.nav_gallery) {


            Gson gson = new Gson();
            Collection<Integer> ints = Lists.newArrayList(36856, 36940, 36943, 37281, 37604);

// Serialization
            String json = gson.toJson(ints);  // ==> json is [1,2,3,4,5]

// Deserialization
            Type collectionType = new TypeToken<Collection<Integer>>(){}.getType();
            Collection<Integer> ints2 = gson.fromJson(json, collectionType);
// ==> ints2 is same as ints
            final TextView mTextView = (TextView) findViewById(R.id.text);
            mTextView.setText(ints2.toString());


        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {
            final TextView mTextView = (TextView) findViewById(R.id.text);
            String url = "https://api.cartolafc.globo.com/mercado/status";

            JsonObjectRequest jsObjRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
//                            mTextView.setText("JSONObject: " + response.keys().toString());
                            mTextView.setText("JSONObject: " + response.toString());
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO Auto-generated method stub

                        }
                    });
            RequestQueueSingleton.getInstance(this).addToRequestQueue(jsObjRequest);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_parciais) {
            final TextView mTextView = (TextView) findViewById(R.id.text);

            String url = "https://api.cartolafc.globo.com/atletas/pontuados";
//            String url = "https://api.cartolafc.globo.com/mercado/status";

            GsonRequest gsonRequest = new GsonRequest(url, Atleta.class, null,
                    new Response.Listener<Atleta>() {
                        @Override
                        public void onResponse(Atleta response) {
                            mTextView.setText("JSON: " + response.toString());
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            mTextView.setText("That didn't work!");
                        }
                    }
            );


            // Add a request (in this example, called stringRequest) to your RequestQueue.
            RequestQueueSingleton.getInstance(this).addToRequestQueue(gsonRequest);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
