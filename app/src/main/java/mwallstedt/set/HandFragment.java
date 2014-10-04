package mwallstedt.set;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
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
import android.widget.Toast;

import java.util.List;

public class HandFragment extends Fragment {
	private static final String TAG = HandFragment.class.getCanonicalName();

	private class Slot {
		private final ImageView mView;
		private Card mCard;
		
		private Slot(ImageView view, final int x, final int y) {
			mView = view;
			view.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					mSlots[mY][mX].unhighlight();
					highlight();
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
		
		private void highlight() {
			Resources resources = getActivity().getResources();
    		Drawable drawable = resources.getDrawable(mCard.getHighlightedDrawableId());
    		mView.setImageDrawable(drawable);
		}
		
		private void unhighlight() {
			Resources resources = getActivity().getResources();
    		Drawable drawable = resources.getDrawable(mCard.getDrawableId());
    		mView.setImageDrawable(drawable);
		}
	}
	
	private Slot mSlots[][];
	private int mX, mY;
	private int mXDim, mYDim;
	
	public static final String EXTRA_CARD = "com.example.setsolver.EXTRA_CARD";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mXDim = 4;
        mYDim = 3;
        Log.i(TAG, "onCreate");
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
    		Bundle savedInstanceState) {
    	super.onCreateView(inflater, parent, savedInstanceState);
    	View v = inflater.inflate(R.layout.fragment_hand, parent, false);
    	mX = 0;
    	mY = 0;
    	mSlots = new Slot[mYDim][mXDim];
    	mSlots[0][0] = new Slot((ImageView) v.findViewById(R.id.h00), 0, 0);
    	mSlots[0][1] = new Slot((ImageView) v.findViewById(R.id.h01), 1, 0);
    	mSlots[0][2] = new Slot((ImageView) v.findViewById(R.id.h02), 2, 0);
    	mSlots[0][3] = new Slot((ImageView) v.findViewById(R.id.h03), 3, 0);
    	mSlots[1][0] = new Slot((ImageView) v.findViewById(R.id.h10), 0, 1);
    	mSlots[1][1] = new Slot((ImageView) v.findViewById(R.id.h11), 1, 1);
    	mSlots[1][2] = new Slot((ImageView) v.findViewById(R.id.h12), 2, 1);
    	mSlots[1][3] = new Slot((ImageView) v.findViewById(R.id.h13), 3, 1);
    	mSlots[2][0] = new Slot((ImageView) v.findViewById(R.id.h20), 0, 2);
    	mSlots[2][1] = new Slot((ImageView) v.findViewById(R.id.h21), 1, 2);
    	mSlots[2][2] = new Slot((ImageView) v.findViewById(R.id.h22), 2, 2);
    	mSlots[2][3] = new Slot((ImageView) v.findViewById(R.id.h23), 3, 2);
    	
    	if (savedInstanceState != null) {
            List<Card> cards = ((MainActivity)getActivity()).mSelected;
            for (int y = 0; y < mYDim; y++) {
        		for (int x = 0; x < mXDim; x++) {
        			 mSlots[y][x].setCard(cards.get((y*mXDim) + x));
        		}
        	}
        }
    	
		mSlots[0][0].highlight();
		Button matchButton = (Button) v.findViewById(R.id.match_button);
		matchButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				findSet();
			}
		});
        return v;
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (resultCode != Activity.RESULT_OK) {
    		Log.i(TAG, "resultCode not RESULT_OK");
    		return;
    	}
    	if (!data.hasExtra(EXTRA_CARD)) {
    		Log.i(TAG, "No extra drawable ID");
    		return;
    	}
    	if (requestCode == CardTemplatesFragment.PICK_CARD_CODE) {
    		Log.i(TAG, "PICK_CARD_CODE is present");
    		Card card = (Card) data.getSerializableExtra(EXTRA_CARD);
            ((MainActivity)getActivity()).mSelected.add(card);
            Log.i(TAG, "Card = " + card);
    		mSlots[mY][mX].setCard(card);
            ((MainActivity)getActivity()).mSelected.add(card);
    		mX += 1;
    		if (mX >= mXDim) {
    			mX = 0;
    			mY += 1;
    		}
    		if (mY >= mYDim) {
    			mY = 0;
    		}
    		mSlots[mY][mX].highlight();
    	} else {
    		Log.i(TAG, "PICK_CARD_CODE missing");
    	}
    }
    
    private void findSet() {
    	for (int first = 0; first < (mXDim * mYDim); first++) {
    		int firstX = first % mXDim;
    		int firstY = first / mXDim;
    		Card firstCard = mSlots[firstY][firstX].getCard();
    		if ((firstCard == null) || firstCard.isBlank()) {
    			continue;
    		}
    		for (int second = first + 1; second < (mXDim * mYDim); second++) {
    			int secondX = second % mXDim;
    			int secondY = second / mXDim;
    			Card secondCard = mSlots[secondY][secondX].getCard();
    			if ((secondCard == null) || secondCard.isBlank()) {
    				continue;
    			}
    			Card thirdCard = firstCard.getCompleter(secondCard);
    			Log.i(TAG, "first=" + first + ",second=" + second + "," + thirdCard);
    			for (int third = second + 1; third < (mXDim * mYDim); third++) {
    				int thirdX = third % mXDim;
    				int thirdY = third / mXDim;
    				if (thirdCard.equals(mSlots[thirdY][thirdX].getCard())) {
    					String message = first + "," + second + "," + third;
    					Toast toast = Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT);
    					toast.show();
    					return;
    				}
    			}
    		}
    	}
    	Toast toast = Toast.makeText(getActivity(), "No sets", Toast.LENGTH_SHORT);
    	toast.show();
    }

}
