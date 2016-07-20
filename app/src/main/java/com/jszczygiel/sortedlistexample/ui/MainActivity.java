package com.jszczygiel.sortedlistexample.ui;

import com.jszczygiel.foundation.views.SimpleFragmentActivityImpl;
import com.jszczygiel.sortedlistexample.ui.view.MainFragmentImpl;

public class MainActivity extends SimpleFragmentActivityImpl<MainFragmentImpl> {
    @Override
    public MainFragmentImpl newFragmentInstance() {
        return new MainFragmentImpl();
    }
}
