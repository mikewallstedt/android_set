package mwallstedt.set;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class MainActivity extends FragmentActivity {

    private final String SELECETD_CARDS = "SELECTED_CARDS";

    protected List<Card> mSelected = new ArrayList<Card>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        if (savedInstanceState != null) {
            mSelected = (List<Card>)savedInstanceState.getSerializable(SELECETD_CARDS);
        }

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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SELECETD_CARDS, (Serializable) mSelected);
    }
}
