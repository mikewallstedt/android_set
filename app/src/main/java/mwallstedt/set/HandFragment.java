package mwallstedt.set;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HandFragment extends Fragment {
	private static final String TAG = HandFragment.class.getCanonicalName();

    private static final String SAVED_TRIAD_COUNT = "SAVED_TRIAD_COUNT";
    private static final String SAVED_CARDS_DISPLAYED = "SAVED_CARDS_DISPLAYED";
    public static final String EXTRA_CARD = "com.example.setsolver.EXTRA_CARD";

    private static final int TRIAD_SIZE = 3;
    private static final int DEFAULT_NUMBER_OF_TRIADS = 4;

    private List<Slot[]> mSlots = new ArrayList<Slot[]>();
    private List<Card> mInitCards = new ArrayList<Card>();
    private int mTrioIndex, mIndexInTrio;
    private int numTrios, trioSize;

    private class Slot {
		private final ImageView mView;
        private Card mCard;

		private Slot(final int x, final int y) {
			mView = new ImageView(getActivity());
			mView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
                    mSlots.get(mTrioIndex)[mIndexInTrio].unhighlight();
					highlight(getResources().getColor(R.color.cursor));
					mTrioIndex = x;
					mIndexInTrio = y;
				}
			});
		}

        private Card getCard() {
            return mCard;
        }

		private void showCard(Card card) {
			mCard = card;
    		Drawable drawable = getActivity().getResources().getDrawable(card.getDrawableId());
    		mView.setImageDrawable(drawable);
		}

        private ImageView getView() {
            return mView;
        }

        private void highlight(int color) {
            mView.setBackgroundColor(color);
            mView.invalidate();
        }

		private void unhighlight() {
            mView.setBackgroundColor(Color.WHITE);
            mView.invalidate();
		}
	}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        trioSize = TRIAD_SIZE;
        if (savedInstanceState != null) {
            numTrios = savedInstanceState.getInt(SAVED_TRIAD_COUNT);
            mInitCards = (List<Card>) savedInstanceState.getSerializable(SAVED_CARDS_DISPLAYED);
        } else {
            numTrios = DEFAULT_NUMBER_OF_TRIADS;
            for (int i = 0; i < numTrios * trioSize; i++) {
                mInitCards.add(Card.BLANK_CARD);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
    		Bundle savedInstanceState) {
    	super.onCreateView(inflater, parent, savedInstanceState);
    	View v = inflater.inflate(R.layout.fragment_hand, parent, false);
        final LinearLayout trioHolderView = (LinearLayout) v.findViewById(R.id.trio_holder);
        mTrioIndex = 0;
        mIndexInTrio = 0;
        initializeSlots(trioHolderView);

        if (numTrios > 0) {
            mSlots.get(0)[0].highlight(getResources().getColor(R.color.cursor));
        }
		Button matchButton = (Button) v.findViewById(R.id.match_button);
		matchButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				solve();
			}
		});
        Button newTriadButton = (Button) v.findViewById(R.id.new_triad_button);
        newTriadButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddTriad(trioHolderView);
            }
        });
        Button deleteTriadButton = (Button) v.findViewById(R.id.delete_triad_button);
        deleteTriadButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onDeleteTriad(trioHolderView);
            }
        });
        return v;
    }

    private void initializeSlots(LinearLayout trioHolderView) {
        for (int trioIndex = 0; trioIndex < numTrios; trioIndex++) {
            Slot[] slots = new Slot[TRIAD_SIZE];
            LinearLayout trioView = new LinearLayout(getActivity());
            trioView.setOrientation(LinearLayout.VERTICAL);
            for (int indexInTrio = 0; indexInTrio < trioSize; indexInTrio++) {
                slots[indexInTrio] = new Slot(trioIndex, indexInTrio);
                trioView.addView(slots[indexInTrio].getView());
                slots[indexInTrio].showCard(mInitCards.get(indexInTrio + trioIndex * trioSize));
            }
            mSlots.add(slots);
            trioHolderView.addView(trioView);
        }
    }

    private void onAddTriad(LinearLayout trioHolderView) {
        int trioIndex = numTrios;
        numTrios++;
        Slot[] slots = new Slot[TRIAD_SIZE];
        LinearLayout trioView = new LinearLayout(getActivity());
        trioView.setOrientation(LinearLayout.VERTICAL);
        for (int indexInTrio = 0; indexInTrio < trioSize; indexInTrio++) {
            slots[indexInTrio] = new Slot(trioIndex, indexInTrio);
            trioView.addView(slots[indexInTrio].getView());
            slots[indexInTrio].showCard(Card.BLANK_CARD);
        }
        mSlots.add(slots);
        trioHolderView.addView(trioView);
        mSlots.get(this.mTrioIndex)[mIndexInTrio].unhighlight();
        this.mTrioIndex = 0;
        mIndexInTrio = 0;
        mSlots.get(0)[0].highlight(getResources().getColor(R.color.cursor));
    }

    private void onDeleteTriad(LinearLayout trioHolderView) {
        mSlots.get(mTrioIndex)[mIndexInTrio].unhighlight();
        int trioIndex = mSlots.size() - 1;
        if (trioIndex < 0) {
            return;
        }
        Slot[] removed = mSlots.remove(trioIndex);
        for (Slot slot : removed) {
            getMainActivity().removeCardInPlay(slot.getCard());
        }
        numTrios--;

        trioHolderView.removeViewAt(trioIndex + 1);
        if (trioIndex >= 1) {
            mTrioIndex = 0;
            mIndexInTrio = 0;
            mSlots.get(0)[0].highlight(getResources().getColor(R.color.cursor));
        }
    }

    public void setCardAtCursor(Card card) {
        Slot slot = mSlots.get(mTrioIndex)[mIndexInTrio];
        slot.showCard(card);
        getMainActivity().removeCardInPlay(slot.getCard());
        highlightNext();        
    }

    private void highlightNext() {
        mSlots.get(mTrioIndex)[mIndexInTrio].unhighlight();
        mTrioIndex += 1;
        if (mTrioIndex >= numTrios) {
            mTrioIndex = 0;
            mIndexInTrio += 1;
        }
        if (mIndexInTrio >= trioSize) {
            mIndexInTrio = 0;
        }
        mSlots.get(mTrioIndex)[mIndexInTrio].highlight(getResources().getColor(R.color.cursor));
    }

    private MainActivity getMainActivity() {
        return (MainActivity) getActivity();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(SAVED_TRIAD_COUNT, numTrios);
        List<Card> cardsDisplayed = new ArrayList<Card>();
        for (Slot[] slots : mSlots) {
            for (Slot slot : slots) {
                cardsDisplayed.add(slot.getCard());
            }
        }
        savedInstanceState.putSerializable(SAVED_CARDS_DISPLAYED, (Serializable) cardsDisplayed);
    }

    private void solve() {
        List<Set<Card>> sets = findSets();
        Set<Card> unified = new HashSet<Card>();
        for (Set<Card> set: sets) {
            unified.addAll(set);
        }

        int solutionColor = getResources().getColor(R.color.solution);
        for (Set<Card> set : sets) {
            for (int i = 0; i < (numTrios * trioSize); i++) {
                int trioIndex = i % numTrios;
                int indexInTrio = i / numTrios;
                Slot slot = mSlots.get(trioIndex)[indexInTrio];
                if (slot.getCard() == null || !unified.contains(slot.getCard())) {
                    slot.unhighlight();
                }
                else if (set.contains(slot.getCard())) {
                    slot.highlight(solutionColor);
                }
            }
            solutionColor += 0x00222222;
        }
        Toast toast = Toast.makeText(getActivity(), "Num sets: " + sets.size(), Toast.LENGTH_SHORT);
        toast.show();
    }

    private List<Set<Card>> findSets() {
        List<Set<Card>> result = new ArrayList<Set<Card>>();
        for (int first = 0; first < (numTrios * trioSize); first++) {
    		int firstTrioIndex = first % numTrios;
    		int firstIndexInTrio = first / numTrios;
            Card firstCard = mSlots.get(firstTrioIndex)[firstIndexInTrio].getCard();
    		if ((firstCard == null) || firstCard.isBlank()) {
    			continue;
    		}
    		for (int second = first + 1; second < (numTrios * trioSize); second++) {
    			int secondTrioIndex = second % numTrios;
    			int secondIndexInTrio = second / numTrios;
                Card secondCard = mSlots.get(secondTrioIndex)[secondIndexInTrio].getCard();
    			if ((secondCard == null) || secondCard.isBlank()) {
    				continue;
    			}
                Card thirdCard = firstCard.getCompleter(secondCard);
    			for (int third = second + 1; third < (numTrios * trioSize); third++) {
    				int thirdTrioIndex = third % numTrios;
    				int thirdIndexInTrio = third / numTrios;
                    if (thirdCard.equals(mSlots.get(thirdTrioIndex)[thirdIndexInTrio].getCard())) {
    					result.add(new HashSet<Card>(Arrays.asList(firstCard, secondCard, thirdCard)));
    				}
    			}
    		}
    	}
        return  result;
    }
}
