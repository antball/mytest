package com.andball.androidtest.slidingpanelayout;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.andball.androidtest.R;
import com.andball.androidtest.drawerlayout.FirstFragment;
import com.andball.androidtest.drawerlayout.SecondFragment;
import com.andball.androidtest.slidingpanelayout.actionbartoggle.ActionBarToggle;

public class MainActivity extends FragmentActivity
{

    public static final String[] TITLES =
    { "First", "Second" };
    private SlidingPaneLayout mSlidingPaneLayout;// DrawerLayout容器
    private RelativeLayout mMenu_layout_left;// 左边抽屉

    private ActionBarToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slidingpanel);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment fragment = null;
        fragment = new FirstFragment();

        ft.replace(R.id.fragment_layout, fragment);

        ft.commit();

        mSlidingPaneLayout = (SlidingPaneLayout) findViewById(R.id.sliding_layout);
        // mSlidingPaneLayout.setShadowResource(R.drawable.drawer_shadow);
        mSlidingPaneLayout.setShadowResourceLeft(R.drawable.drawer_shadow);

        mMenu_layout_left = (RelativeLayout) findViewById(R.id.menu_layout_left);
        ListView menu_listview_l = (ListView) mMenu_layout_left.findViewById(R.id.menu_listView_l);

        menu_listview_l.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, TITLES));

        // 监听菜单
        menu_listview_l.setOnItemClickListener(new DrawerItemClickListenerLeft());

        mDrawerToggle = new ActionBarToggle(this, mSlidingPaneLayout, R.drawable.ic_drawer, R.string.hello_world, R.string.app_name)
        {
            @Override
            public void onDrawerOpened(View drawerView)
            {

            }

            public void onDrawerClosed(View drawerView)
            {

            };
        };

        mSlidingPaneLayout.setPanelSlideListener(mDrawerToggle);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        // getActionBar().setHomeButtonEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event

        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }

        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);

        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * 左侧列表点击事件
     * 
     * @author busy_boy
     * 
     */
    public class DrawerItemClickListenerLeft implements OnItemClickListener
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            Fragment fragment = null;

            // 根据item点击行号判断启用哪个Fragment
            switch (position)
            {
            case 0:
                fragment = new FirstFragment();
                break;
            case 1:
                fragment = new SecondFragment();
                break;
            default:
                break;
            }
            ft.replace(R.id.fragment_layout, fragment);
            ft.commit();
            mSlidingPaneLayout.closePane();// 关闭mMenu_layout
        }

    }

}
