package com.andball.androidtest.drawerlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andball.androidtest.R;

public class SecondFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
    ViewGroup container, @Nullable
    Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.second, null);
    }
}
