package me.everything.providers.sample;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import me.everything.providers.sample.fragments.CalendarsFragment;
import me.everything.providers.sample.fragments.CallsFragment;
import me.everything.providers.sample.fragments.ContactsFragment;
import me.everything.providers.sample.fragments.ConversationsFragment;
import me.everything.providers.sample.fragments.DictionaryFragment;
import me.everything.providers.sample.fragments.HomeFragment;
import me.everything.providers.sample.fragments.SearchFragment;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // root
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        // drawer
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                Snackbar.make(mDrawerLayout, menuItem.getTitle() + " pressed", Snackbar.LENGTH_LONG).show();
                menuItem.setChecked(true);

                try {
                    int itemId = menuItem.getItemId();
                    switch (itemId) {
                        case R.id.drawer_home:
                            setFragment(HomeFragment.class);
                            break;
                        case R.id.drawer_contacts:
                            setFragment(ContactsFragment.class);
                            break;
                        case R.id.drawer_calendar:
                            setFragment(CalendarsFragment.class);
                            break;
                        case R.id.drawer_calllogs:
                            setFragment(CallsFragment.class);
                            break;
                        case R.id.drawer_telephony:
                            setFragment(ConversationsFragment.class);
                            break;
                        case R.id.drawer_browser:
                            setFragment(SearchFragment.class);
                            break;
                        case R.id.drawer_dictionary:
                            setFragment(DictionaryFragment.class);
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        setFragment(HomeFragment.class);
    }

    private void setFragment(Class<? extends Fragment> fragment) {
        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_fragment_container, fragment.newInstance());
            fragmentTransaction.commit();
        } catch (Exception e) {
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
