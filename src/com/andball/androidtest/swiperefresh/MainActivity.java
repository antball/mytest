package com.andball.androidtest.swiperefresh;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.andball.androidtest.R;

public class MainActivity extends ActionBarActivity implements OnRefreshListener
{

    public static final String[] TITLES =
    { "Henry IV (1)", "Henry V", "Henry VIII", "Richard II", "Richard III", "Merchant of Venice", "Othello", "King Lear", "Henry IV (1)", "Henry V",
            "Henry VIII", "Richard II", "Richard III", "Merchant of Venice", "Othello", "King Lear", "Henry IV (1)", "Henry V", "Henry VIII", "Richard II",
            "Richard III", "Merchant of Venice", "Othello", "King Lear", "Henry IV (1)", "Henry V", "Henry VIII", "Richard II", "Richard III",
            "Merchant of Venice", "Othello", "King Lear" };
    // Try a SUPER quick refresh to make sure we don't get extra refreshes
    // while the user's finger is still down.
    private static final boolean SUPER_QUICK_REFRESH = false;
    private View mContent;
    private SwipeRefreshLayout mSwipeRefreshWidget;
    private ListView mList;
    private Handler mHandler = new Handler();
    private final Runnable mRefreshDone = new Runnable()
    {

        @Override
        public void run()
        {
            mSwipeRefreshWidget.setRefreshing(false);
        }

    };

    @Override
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.swiperefresh);
        mSwipeRefreshWidget = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_widget);
        mSwipeRefreshWidget.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mList = (ListView) findViewById(R.id.content);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, TITLES);
        mList.setAdapter(arrayAdapter);
        mSwipeRefreshWidget.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh()
    {
        refresh();
    }

    private void refresh()
    {
        mHandler.removeCallbacks(mRefreshDone);
        mHandler.postDelayed(mRefreshDone, 1000);
    }
}
