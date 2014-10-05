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

    private final String SELECTED_CARDS = "SELECTED_CARDS";

    private Set<Card> mCardsInPlay = new HashSet<Card>();

    private boolean mIsActiveCursor;

    protected List<Card> mSelected = new ArrayList<Card>();

    public Set<Card> getCardsInPlay() {
        return mCardsInPlay;
    }

    public void removeCardInPlay(Card card) {
        mCardsInPlay.remove(card);
    }

    public void addCardInPlay(Card card) {
        mCardsInPlay.add(card);
    }

    public boolean isActiveCursor() {
        return mIsActiveCursor;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        if (savedInstanceState != null) {
            @SuppressWarnings("unchecked")
            List<Card> cardList = (List<Card>)savedInstanceState.getSerializable(SELECTED_CARDS);
            mSelected = cardList;
        } else {
            for (int i = 0; i < 81; i++) {
                mSelected.add(Card.BLANK_CARD);
            }
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
        outState.putSerializable(SELECTED_CARDS, (Serializable) mSelected);
    }
}
