package mwallstedt.set;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;


public class MainActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        FragmentManager fm = getSupportFragmentManager();
        
        Fragment cardTemplatesFragment = fm.findFragmentById(R.id.cardTemplatesFragmentContainer);
        if (cardTemplatesFragment == null) {
        	cardTemplatesFragment = new CardTemplatesFragment();
        	fm.beginTransaction()
        		.add(R.id.cardTemplatesFragmentContainer, cardTemplatesFragment)
        		.commit();
        }

        Fragment handFragment = fm.findFragmentById(R.id.handFragmentContainer);
        if (handFragment == null) {
        	handFragment = new HandFragment();
        	fm.beginTransaction()
        		.add(R.id.handFragmentContainer, handFragment)
        		.commit();
        }
    }
}
