package mwallstedt.set;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class HandFragment extends Fragment {
    private static final String SAVED_TRIAD_COUNT = "SAVED_TRIAD_COUNT";
    private static final String SAVED_CARDS_DISPLAYED = "SAVED_CARDS_DISPLAYED";

    private static final int TRIAD_SIZE = 3;
    private static final int INITIAL_NUMBER_OF_TRIADS = 4;

    private HandFragmentHostActivity mHandFragmentHostActivity;
    private SolutionFragmentHostActivity mSolutionFragmentHostActivity;
    private List<Slot[]> mSlots;
    private List<Card> mInitCards;
    private int mTriadIndex, mIndexInTriad;
    private int mNumTriads;

    private class Slot {
		private final ImageView mView;
        private Card mCard;
        private boolean mIsHighlighted;

		private Slot(final int x, final int y) {
            mIsHighlighted = false;
			mView = new ImageView(getActivity());
			mView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
                    clearAllHighlighting();
					highlight(getResources().getColor(R.color.cursor));
					mTriadIndex = x;
					mIndexInTriad = y;
                    mHandFragmentHostActivity.onExitSolutionDisplayMode();
				}
			});
		}

        private Card getCard() {
            return mCard;
        }

        private boolean isHighlighted() {
            return mIsHighlighted;
        }

		private void showCard(Card card) {
			mCard = card;
    		Drawable drawable = getActivity().getResources().getDrawable(card.getDrawableId());
    		mView.setImageDrawable(drawable);
            clearHighlighting();
		}

        private ImageView getView() {
            return mView;
        }

        private void highlight(int color) {
            mIsHighlighted = true;
            mView.setBackgroundColor(color);
            mView.invalidate();
        }

		private void clearHighlighting() {
            if (mIsHighlighted) {
                mIsHighlighted = false;
                mView.setBackgroundColor(Color.WHITE);
                mView.invalidate();
            }
		}
	}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandFragmentHostActivity = (HandFragmentHostActivity)getActivity();
        mSolutionFragmentHostActivity = (SolutionFragmentHostActivity)getActivity();
        mSlots = new ArrayList<Slot[]>();
        mInitCards = new ArrayList<Card>();
        if (savedInstanceState != null) {
            mNumTriads = savedInstanceState.getInt(SAVED_TRIAD_COUNT);
            @SuppressWarnings("unchecked")
            List<Card> cards =
                    (List<Card>)savedInstanceState.getSerializable(SAVED_CARDS_DISPLAYED);
            mInitCards = cards;
        } else {
            mNumTriads = INITIAL_NUMBER_OF_TRIADS;
            for (int i = 0; i < mNumTriads * TRIAD_SIZE; i++) {
                mInitCards.add(Card.BLANK_CARD);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
    		Bundle savedInstanceState) {
    	super.onCreateView(inflater, parent, savedInstanceState);
    	View v = inflater.inflate(R.layout.fragment_hand, parent, false);
        final LinearLayout triadHolderView = (LinearLayout) v.findViewById(R.id.triad_holder);
        mTriadIndex = 0;
        mIndexInTriad = 0;
        initializeSlots(triadHolderView);

        if (mNumTriads > 0) {
            mSlots.get(0)[0].highlight(getResources().getColor(R.color.cursor));
        }
		Button matchButton = (Button) v.findViewById(R.id.match_button);
		matchButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
                mSolutionFragmentHostActivity.showNextSolution();
			}
		});
        Button newTriadButton = (Button) v.findViewById(R.id.new_triad_button);
        newTriadButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddTriad(triadHolderView);
            }
        });
        Button deleteTriadButton = (Button) v.findViewById(R.id.delete_triad_button);
        deleteTriadButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onDeleteTriad(triadHolderView);
            }
        });
        Button removeButton = (Button) v.findViewById(R.id.remove_button);
        removeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Slot[] triad : mSlots) {
                    for (Slot slot : triad) {
                        if (slot.isHighlighted()) {
                            Card card = slot.getCard();
                            mHandFragmentHostActivity.removeFromHand(card);
                            slot.showCard(Card.BLANK_CARD);
                        }
                    }
                }
                mSlots.get(mTriadIndex)[mIndexInTriad].highlight(
                        getResources().getColor(R.color.cursor));
                mHandFragmentHostActivity.onExitSolutionDisplayMode();
            }
        });
        return v;
    }

    private void initializeSlots(LinearLayout triadHolderView) {
        for (int triadIndex = 0; triadIndex < mNumTriads; triadIndex++) {
            Slot[] slots = new Slot[TRIAD_SIZE];
            LinearLayout triadView = new LinearLayout(getActivity());
            LinearLayout.LayoutParams layoutParams =
                    new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.gravity = Gravity.CENTER;
            triadView.setLayoutParams(layoutParams);
            triadView.setOrientation(LinearLayout.HORIZONTAL);
            for (int indexInTriad = 0; indexInTriad < TRIAD_SIZE; indexInTriad++) {
                slots[indexInTriad] = new Slot(triadIndex, indexInTriad);
                triadView.addView(slots[indexInTriad].getView());
                slots[indexInTriad].showCard(mInitCards.get(indexInTriad + triadIndex * TRIAD_SIZE));
            }
            mSlots.add(slots);
            triadHolderView.addView(triadView);
        }
    }

    private void onAddTriad(LinearLayout triadHolderView) {
        clearAllHighlighting();
        mHandFragmentHostActivity.onExitSolutionDisplayMode();
        int triadIndex = mNumTriads;
        mNumTriads++;
        Slot[] slots = new Slot[TRIAD_SIZE];
        LinearLayout triadView = new LinearLayout(getActivity());
        triadView.setOrientation(LinearLayout.HORIZONTAL);
        for (int indexInTriad = 0; indexInTriad < TRIAD_SIZE; indexInTriad++) {
            slots[indexInTriad] = new Slot(triadIndex, indexInTriad);
            triadView.addView(slots[indexInTriad].getView());
            slots[indexInTriad].showCard(Card.BLANK_CARD);
        }
        mSlots.add(slots);
        triadHolderView.addView(triadView);
        mSlots.get(this.mTriadIndex)[mIndexInTriad].clearHighlighting();
        this.mTriadIndex = 0;
        mIndexInTriad = 0;
        mSlots.get(0)[0].highlight(getResources().getColor(R.color.cursor));
        mHandFragmentHostActivity.onExitSolutionDisplayMode();
    }

    private void onDeleteTriad(LinearLayout triadHolderView) {
        clearAllHighlighting();
        mHandFragmentHostActivity.onExitSolutionDisplayMode();
        int triadIndex = mSlots.size() - 1;
        if (triadIndex < 0) {
            return;
        }
        mSlots.get(mTriadIndex)[mIndexInTriad].clearHighlighting();
        Slot[] removed = mSlots.remove(triadIndex);
        for (Slot slot : removed) {
            mHandFragmentHostActivity.removeFromHand(slot.getCard());
        }
        mNumTriads--;

        triadHolderView.removeViewAt(triadIndex);
        if (triadIndex >= 1) {
            mTriadIndex = 0;
            mIndexInTriad = 0;
            mSlots.get(0)[0].highlight(getResources().getColor(R.color.cursor));
        }
        mHandFragmentHostActivity.onExitSolutionDisplayMode();
    }

    public Card swapCardAtCursor(Card card) {
        Slot slot = mSlots.get(mTriadIndex)[mIndexInTriad];
        Card oldCard = slot.getCard();
        slot.showCard(card);
        highlightNext();
        return oldCard;
    }

    private void highlightNext() {
        mSlots.get(mTriadIndex)[mIndexInTriad].clearHighlighting();
        mTriadIndex += 1;
        if (mTriadIndex >= mNumTriads) {
            mTriadIndex = 0;
            mIndexInTriad += 1;
        }
        if (mIndexInTriad >= TRIAD_SIZE) {
            mIndexInTriad = 0;
        }
        mSlots.get(mTriadIndex)[mIndexInTriad].highlight(getResources().getColor(R.color.cursor));
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(SAVED_TRIAD_COUNT, mNumTriads);
        List<Card> cardsDisplayed = new ArrayList<Card>();
        for (Slot[] slots : mSlots) {
            for (Slot slot : slots) {
                cardsDisplayed.add(slot.getCard());
            }
        }
        savedInstanceState.putSerializable(SAVED_CARDS_DISPLAYED, (Serializable) cardsDisplayed);
    }

    void showSolution(Set<Card> solution) {
        int solutionColor = getResources().getColor(R.color.solution);
        for (int i = 0; i < (mNumTriads * TRIAD_SIZE); i++) {
            int triadIndex = i % mNumTriads;
            int indexInTriad = i / mNumTriads;
            Slot slot = mSlots.get(triadIndex)[indexInTriad];
            Card card = slot.getCard();
            if (card == null || !solution.contains(card)) {
                slot.clearHighlighting();
            } else if (solution.contains(card)) {
                slot.highlight(solutionColor);
            }
        }
    }

    private void clearAllHighlighting() {
        for (int triadIndex = 0; triadIndex < mNumTriads; triadIndex++) {
            for (int cardIndex = 0; cardIndex < TRIAD_SIZE; cardIndex++) {
                mSlots.get(triadIndex)[cardIndex].clearHighlighting();
            }
        }
    }

}
