package com.ehappy.behappy;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ehappy.behappy.R;

public class FriendsActivity extends Activity {

    ListView listView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_friends);
        listView1 = (ListView) findViewById(R.id.listView1);
        String[] items = { "Milk", "Butter", "Yogurt", "Toothpaste", "Ice Cream" };
        MyArrayAdapter<String> adapter = new MyArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items);

        listView1.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.friends, menu);
        return true;
    }
    
}
