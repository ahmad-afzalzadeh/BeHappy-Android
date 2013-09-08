package com.ehappy.behappy;

import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * Created by ahmad on 9/8/13.
 */
public class MyArrayAdapter<T> extends ArrayAdapter
{

    public MyArrayAdapter(Context context, int resource, Object[] objects)
    {
        super(context, resource, objects);
    }

    public boolean areAllItemsEnabled() {
        return false;
    }

    public boolean isEnabled(int position) {
        return false;
    }
}
