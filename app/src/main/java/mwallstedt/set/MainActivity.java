package mwallstedt.set;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class MainActivity extends FragmentActivity
        implements CardPickerHostActivity, HandFragmentHostActivity, SolutionFragmentHostActivity {

    private final String SAVED_HAND = "SAVED_HAND";

    private final String SAVED_SOLUTION_DISPLAY_MODE = "SAVED_SOLUTION_DISPLAY_MODE";

    private final String SAVED_SOLUTION_INDEX = "SAVED_SOLUTION_INDEX";

    private final String SAVED_SOLUTIONS = "SAVED_SOLUTIONS";

    private Hand mHand;

    private HandFragment mHandFragment;

    private boolean mSolutionDisplayMode;

    private int mSolutionIndex;

    private List<Set<Card>> mSolutions;

    @Override
    public boolean handContains(Card card) {
        return mHand.contains(card);
    }

    @Override
    public void onCardChosen(Card card) {
        Card oldCard = mHandFragment.swapCardAtCursor(card);
        mHand.remove(oldCard);
        mHand.add(card);
    }

    @Override
    public void onExitSolutionDisplayMode() {
        mSolutionDisplayMode = false;
    }

    @Override
    public void removeFromHand(Card card) {
        mHand.remove(card);
    }

    @Override
    public void showNextSolution() {
        if (!mSolutionDisplayMode) {
            mSolutions = mHand.findSets();
            mSolutionIndex = 0;
            mSolutionDisplayMode = true;
        }
        String toastMessage = "No solutions";
        if (!mSolutions.isEmpty()) {
            toastMessage = "Showing solution " + (mSolutionIndex + 1) + " of " + mSolutions.size();
        }
        Toast toast = Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT);
        toast.show();
        if (mSolutions.isEmpty()) {
            mSolutionDisplayMode = false;
            return;
        }
        mHandFragment.showSolution(mSolutions.get(mSolutionIndex));
        mSolutionIndex++;
        mSolutionIndex %= mSolutions.size();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        if (savedInstanceState != null) {
            @SuppressWarnings("unchecked")
            Hand hand = (Hand)savedInstanceState.getSerializable(SAVED_HAND);
            mHand = hand;
            mSolutionDisplayMode = savedInstanceState.getBoolean(SAVED_SOLUTION_DISPLAY_MODE);
            mSolutionIndex = savedInstanceState.getInt(SAVED_SOLUTION_INDEX);
            @SuppressWarnings("unchecked")
            List<Set<Card>> solutions =
                    (List<Set<Card>>)savedInstanceState.getSerializable(SAVED_SOLUTIONS);
            mSolutions = solutions;
        } else {
            mHand = new Hand();
            mSolutionDisplayMode = false;
        }

        FragmentManager fm = getSupportFragmentManager();
        
        Fragment cardTemplatesFragment = fm.findFragmentById(R.id.cardTemplatesFragmentContainer);
        if (cardTemplatesFragment == null) {
        	cardTemplatesFragment = new CardTemplatesFragment();
        	fm.beginTransaction()
        		.add(R.id.cardTemplatesFragmentContainer, cardTemplatesFragment)
        		.commit();
        }

        mHandFragment = (HandFragment) fm.findFragmentById(R.id.handFragmentContainer);
        if (mHandFragment == null) {
            mHandFragment = new HandFragment();
        	fm.beginTransaction()
        		.add(R.id.handFragmentContainer, mHandFragment)
        		.commit();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_HAND, mHand);
        outState.putBoolean(SAVED_SOLUTION_DISPLAY_MODE, mSolutionDisplayMode);
        outState.putInt(SAVED_SOLUTION_INDEX, mSolutionIndex);
        outState.putSerializable(SAVED_SOLUTIONS, (Serializable)mSolutions);
    }
}
