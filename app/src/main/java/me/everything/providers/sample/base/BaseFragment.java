package me.everything.providers.sample.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import me.everything.providers.sample.R;

public class BaseFragment extends Fragment {

    protected void setToolbarTitle(String title) {
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    protected void setFragment(Fragment fragment) {
        try {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_fragment_container, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } catch (Exception e) {
        }
    }

    protected void setFragment(Class<? extends Fragment> fragmentCls) {
        try {
            Fragment fragment = fragmentCls.newInstance();
            setFragment(fragment);
        } catch (Exception e) {
        }
    }

    public Context getApplicationContext() {
        return getActivity().getApplicationContext();
    }

}
