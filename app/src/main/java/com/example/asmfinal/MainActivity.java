package com.example.asmfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.asmfinal.Fragment.Fragment_chi;
import com.example.asmfinal.Fragment.Fragment_thongke;
import com.example.asmfinal.Fragment.Fragment_thu;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerlayout;
   Toolbar toolbar;
    NavigationView navigation;
    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerlayout=findViewById(R.id.drawerlayout);
        toolbar=findViewById(R.id.toolbar);
        navigation=findViewById(R.id.navigation);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle = setupdrawertoogle();
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();
        drawerlayout.addDrawerListener(drawerToggle);


        if(savedInstanceState == null){
            navigation.setCheckedItem(R.id.khoanthu);
            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new Fragment_thu()).commit();
        }
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                Fragment fragment = null;
//                Class fragmentclass = null;
                switch (item.getItemId()){
                    case R.id.khoanthu:
//                        fragmentclass = Fragment_thu.class;
                        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new Fragment_thu()).commit();
                        break;
                    case R.id.khoanchi:
//                        fragmentclass = Fragment_chi.class;
                        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new Fragment_chi()).commit();
                        break;
                    case R.id.thongke:
//                        fragmentclass = Fragment_thongke.class;
                        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new Fragment_thongke()).commit();
                        break;
                    case R.id.about:
                        Toast.makeText(MainActivity.this, "About", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.exit:
                        Intent startMain = new Intent(Intent.ACTION_MAIN);
                        startMain.addCategory(Intent.CATEGORY_HOME);
                        startActivity(startMain);
                        finish();
                        break;
                    default:
                        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new Fragment_thu()).commit();
//                        fragmentclass = Fragment_thu.class;
                }
//                try {
//                    fragment = (Fragment) fragmentclass.newInstance();
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//
//                FragmentManager fragmentManager= getSupportFragmentManager();
//                fragmentManager.beginTransaction().replace(R.id.framelayout,fragment).commit();
                item.setCheckable(true);
                setTitle(item.getTitle());
                drawerlayout.closeDrawers();
                return true;
            }
        });
    }
    private ActionBarDrawerToggle setupdrawertoogle(){
        return new ActionBarDrawerToggle(MainActivity.this,drawerlayout,toolbar,R.string.Open,R.string.Close);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
