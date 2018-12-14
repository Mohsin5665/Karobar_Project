package com.vcodewise.karobarlist.activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vcodewise.karobarlist.R;
import com.vcodewise.karobarlist.adapters.Categories_Adapter;
import com.vcodewise.karobarlist.fragment.ColoredFragment;
import com.vcodewise.karobarlist.fragment.SearchFragment;
import com.vcodewise.karobarlist.models.Categories_list;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    int container;
    private FragmentTransaction mFragmentTransaction;
    private FragmentManager mFragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = R.id.container;

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(container, new SearchFragment());
        mFragmentTransaction.commit();


        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigationView);
        removeShiftMode(bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.action_item1);
        bottomNavigationView.performClick();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id =item.getItemId();
                Fragment fragment = new Fragment();
                switch (id){
                    case R.id.action_item1:
                        fragment = SearchFragment.newInstance("","");
                        break;
                    case R.id.action_item2:
                        fragment = ColoredFragment.newInstance(R.color.colorAccent, "Activity");
                        break;
                    case R.id.action_item3:
                        fragment = ColoredFragment.newInstance(R.color.colorPrimaryDark, "Play");
                        break;
                    case R.id.action_item4:
                        fragment = ColoredFragment.newInstance(R.color.colorAccent, "Activity4");
                        break;
                    case R.id.action_item5:
                        fragment = ColoredFragment.newInstance(R.color.colorPrimary, "Activity5");
                        break;
                    default:
                        fragment = ColoredFragment.newInstance(Color.MAGENTA, "Default");
                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(container, fragment).commit();
                return true;
            }
        });


    }


    @SuppressLint("RestrictedApi")
    private  void removeShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("ERROR NO SUCH FIELD", "Unable to get shift mode field");
        } catch (IllegalAccessException e) {
            Log.e("ERROR ILLEGAL ALG", "Unable to change value of shift mode");
        }
    }




}

