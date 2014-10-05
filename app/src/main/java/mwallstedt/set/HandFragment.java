package mwallstedt.set;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HandFragment extends Fragment {
	private static final String TAG = HandFragment.class.getCanonicalName();

    private static final String SAVED_TRIAD_COUNT = "SAVED_TRIAD_COUNT";
    private static final int TRIAD_SIZE = 3;
    private static final int DEFAULT_NUMBER_OF_TRIADS = 4;

    private class Slot {
		private final ImageView mView;
		private Card mCard;

		private Slot(ImageView view, final int x, final int y) {
			mView = view;
			view.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
                    mSlots.get(mX)[mY].unhighlight();
					highlight(getResources().getColor(R.color.cursor));
					mX = x;
					mY = y;
				}
			});
			mCard = new Card(null,null,null,null);
		}

		private void setCard(Card card) {
			mCard = card;
			Resources resources = getActivity().getResources();
    		Drawable drawable = resources.getDrawable(card.getDrawableId());
    		mView.setImageDrawable(drawable);
		}

		private Card getCard() {
			return mCard;
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

	private List<Slot[]> mSlots;
	private int mX, mY;
	private int mXDim, mYDim;

	public static final String EXTRA_CARD = "com.example.setsolver.EXTRA_CARD";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mXDim = savedInstanceState.getInt(SAVED_TRIAD_COUNT);
        } else {
            mXDim = DEFAULT_NUMBER_OF_TRIADS;
        }
        Log.i(TAG, "Num triads: " + mXDim);
        mYDim = TRIAD_SIZE;
        Log.i(TAG, "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
    		Bundle savedInstanceState) {
    	super.onCreateView(inflater, parent, savedInstanceState);
    	View v = inflater.inflate(R.layout.fragment_hand, parent, false);
        final LinearLayout trioHolderView = (LinearLayout) v.findViewById(R.id.trio_holder);
    	mX = 0;
    	mY = 0;
        mSlots = new ArrayList<Slot[]>();
        for (int x = 0; x < mXDim; x++) {
            Slot[] slots = new Slot[TRIAD_SIZE];
            LinearLayout trioView = new LinearLayout(getActivity());
            trioView.setOrientation(LinearLayout.VERTICAL);
            for (int y = 0; y < mYDim; y++) {
                ImageView imageView = new ImageView(getActivity());
                slots[y] = new Slot(imageView, x, y);
                trioView.addView(imageView);
            }
            mSlots.add(slots);
            trioHolderView.addView(trioView);
        }

        for (int y = 0; y < mYDim; y++) {
            for (int x = 0; x < mXDim; x++) {
                mSlots.get(x)[y].setCard(getSelected().get(getIndexInSelected(x, y)));
            }
        }

        if (mXDim > 0) {
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

    private void onAddTriad(LinearLayout trioHolderView) {
        int x = mXDim;
        mXDim++;
        Slot[] slots = new Slot[TRIAD_SIZE];
        LinearLayout trioView = new LinearLayout(getActivity());
        trioView.setOrientation(LinearLayout.VERTICAL);
        for (int y = 0; y < mYDim; y++) {
            ImageView imageView = new ImageView(getActivity());
            slots[y] = new Slot(imageView, x, y);
            trioView.addView(imageView);
            slots[y].setCard(new Card(null,null,null,null));
        }
        mSlots.add(slots);
        trioHolderView.addView(trioView);
        mSlots.get(mX)[mY].unhighlight();
        mX = 0;
        mY = 0;
        mSlots.get(0)[0].highlight(getResources().getColor(R.color.cursor));
    }

    private void onDeleteTriad(LinearLayout trioHolderView) {
        mSlots.get(mX)[mY].unhighlight();
        int x = mSlots.size() - 1;
        if (x < 0) {
            return;
        }
        Slot[] removed = mSlots.remove(x);
        for (int y = 0; y < removed.length; y++) {
            getSelected().set(getIndexInSelected(mXDim - 1, y), Card.BLANK_CARD);
        }
        mXDim--;

        trioHolderView.removeViewAt(x + 1);
        if (x >= 1) {
            mX = 0;
            mY = 0;
            mSlots.get(0)[0].highlight(getResources().getColor(R.color.cursor));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (resultCode != Activity.RESULT_OK) {
    		Log.d(TAG, "resultCode not RESULT_OK");
    		return;
    	}
    	if (!data.hasExtra(EXTRA_CARD)) {
    		Log.d(TAG, "No extra drawable ID");
    		return;
    	}
    	if (requestCode == CardTemplatesFragment.PICK_CARD_CODE) {
    		Log.d(TAG, "PICK_CARD_CODE is present");
    		Card card = (Card) data.getSerializableExtra(EXTRA_CARD);
    		Log.i(TAG, "Card = " + card);
            if (mXDim < 1) {
                return;
            }

            getSelected().set(getIndexInSelected(mX, mY), card);
            mSlots.get(mX)[mY].setCard(card);
            highlightNext();
    	} else {
    		Log.i(TAG, "PICK_CARD_CODE missing");
    	}
    }

    private void highlightNext() {
        mSlots.get(mX)[mY].unhighlight();
        mX += 1;
        if (mX >= mXDim) {
            mX = 0;
            mY += 1;
        }
        if (mY >= mYDim) {
            mY = 0;
        }
        mSlots.get(mX)[mY].highlight(getResources().getColor(R.color.cursor));
    }

    private List<Card> getSelected() {
        return ((MainActivity) getActivity()).mSelected;
    }

    private int getIndexInSelected(int x, int y) {
        return y * mXDim + x;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(SAVED_TRIAD_COUNT, mXDim);
    }

    private void solve() {
        Set<Card> set = findSet();
        if (set.isEmpty()) {
            Toast toast = Toast.makeText(getActivity(), "No sets", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        for (Card card : set) {
            for (int i = 0; i < (mXDim * mYDim); i++) {
                int x = i % mXDim;
                int y = i / mXDim;
                Slot slot = mSlots.get(x)[y];
                if (card.equals(slot.getCard())) {
                    slot.highlight(getResources().getColor(R.color.solution));
                }
            }
        }
    }

    private Set<Card> findSet() {
        for (int first = 0; first < (mXDim * mYDim); first++) {
    		int firstX = first % mXDim;
    		int firstY = first / mXDim;
            Card firstCard = mSlots.get(firstX)[firstY].getCard();
    		if ((firstCard == null) || firstCard.isBlank()) {
    			continue;
    		}
    		for (int second = first + 1; second < (mXDim * mYDim); second++) {
    			int secondX = second % mXDim;
    			int secondY = second / mXDim;
                Card secondCard = mSlots.get(secondX)[secondY].getCard();
    			if ((secondCard == null) || secondCard.isBlank()) {
    				continue;
    			}
                Card thirdCard = firstCard.getCompleter(secondCard);
    			Log.i(TAG, "first=" + first + ",second=" + second + "," + thirdCard);
    			for (int third = second + 1; third < (mXDim * mYDim); third++) {
    				int thirdX = third % mXDim;
    				int thirdY = third / mXDim;
                    if (thirdCard.equals(mSlots.get(thirdX)[thirdY].getCard())) {
    					return new HashSet<Card>(Arrays.asList(firstCard, secondCard, thirdCard));
    				}
    			}
    		}
    	}
        return Collections.EMPTY_SET;
    }
}
