package gr.prisma.androidprisma;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;

import gr.prisma.androidprisma.Fragments.ArrayListFragment;
import gr.prisma.androidprisma.Utils.DialogUtils;
import gr.prisma.androidprisma.Utils.ServerUtils;

public class MainActivity extends ActionBarActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;
    private CharSequence mTitle;
    private CharSequence mDrawerTitle;
    private ArrayListFragment list;
    private SearchView searchView;
    private ServerUtils serverUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        serverUtils = new ServerUtils(this);
        initializeUI();
        if (savedInstanceState == null) {
            DialogUtils.showRateDialog(this);
        } else {
            if (getSupportFragmentManager().findFragmentById(R.id.content_frame)!=null)
            getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentById(R.id.content_frame)).commit();
        }
    }

    private void initializeUI() {
        setContentView(R.layout.activity_main);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mTitle = getTitle();
        mDrawerTitle = getString(R.string.navigation_drawer_title);
        searchWidgetInitialization();
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.open,
                R.string.close
        )

        {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
                getSupportActionBar().setTitle(mTitle);
                syncState();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
                getSupportActionBar().setTitle(mDrawerTitle);
                syncState();
            }
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        actionBarDrawerToggle.syncState();
    }

    private void searchWidgetInitialization(){
        searchView= (SearchView) findViewById(R.id.searchView);
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                if (getSupportFragmentManager().findFragmentById(R.id.content_frame)!=null) {
                    getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentById(R.id.content_frame)).commit();
                    list = null;
                }
                return false;
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                serverUtils.setCurQuery(s);
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(searchView.getWindowToken(),0);
                searchView.clearFocus();
                if (getSupportFragmentManager().findFragmentById(R.id.content_frame)==null) {
                    Bundle args = new Bundle();
                    args.putString("query",s);
                    list = new ArrayListFragment();
                    list.setArguments(args);
                    getSupportFragmentManager().beginTransaction().add(R.id.content_frame, list).commit();
                }else{
                    Bundle args = new Bundle();
                    args.putString("query",s);
                    list = new ArrayListFragment();
                    list.setArguments(args);
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, list).commit();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "searchView.isIconified" + searchView.isIconified());
        if(!searchView.isIconified()) {
            searchView.setIconified(true);
            searchView.setIconified(true);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
