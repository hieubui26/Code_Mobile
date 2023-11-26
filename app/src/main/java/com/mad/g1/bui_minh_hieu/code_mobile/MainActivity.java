package com.mad.g1.bui_minh_hieu.code_mobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mad.g1.bui_minh_hieu.code_mobile.Adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private BottomNavigationView bottomNavigation;
    private FloatingActionButton btnFloating;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initView() {
        viewPager = findViewById(R.id.viewPager);
        bottomNavigation = findViewById(R.id.bottomNavigation);
        btnFloating = findViewById(R.id.btnFloating);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);
    }

    private void initListener() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                    default:
                        bottomNavigation.getMenu().findItem(R.id.menuList).setChecked(true);
                        break;
                    case 1:
                        bottomNavigation.getMenu().findItem(R.id.menuInfo).setChecked(true);
                        break;
                    case 2:
                        bottomNavigation.getMenu().findItem(R.id.menuSearch).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menuList) {
                    viewPager.setCurrentItem(0);
                } else if (item.getItemId() == R.id.menuInfo) {
                    viewPager.setCurrentItem(1);
                } else if (item.getItemId() == R.id.menuSearch) {
                    viewPager.setCurrentItem(2);
                } else {
                    viewPager.setCurrentItem(0);  // Giá trị mặc định nếu không có trường hợp nào khớp
                }
                return true;
            }
        });

        btnFloating.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddActivity.class);
            startActivity(intent);
        });
    }
}