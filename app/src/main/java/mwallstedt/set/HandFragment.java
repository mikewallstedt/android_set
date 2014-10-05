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
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class HandFragment extends Fragment {
    public static final String EXTRA_CARD = "com.example.setsolver.EXTRA_CARD";

	private static final String TAG = HandFragment.class.getCanonicalName();
    private static final String SAVED_ROW_COUNT = "SAVED_ROW_COUNT";
    private static final int ROW_SIZE = 3;
    private static final int DEFAULT_NUMBER_OF_ROWS = 4;

    private List<Row> mSlots;
    private RowNum cursorRow = new RowNum(0);
    private ColNum cursorCol = new ColNum(0);
    private int numRows;

    private static class RowNum {
        int y;
        private RowNum(int y) {
            this.y = y;
        }
    }

    private static class ColNum {
        int x;
        private ColNum(int x) {
            this.x = x;
        }
    }

    private static class Row {
        List<Slot> slots = new ArrayList<Slot>();
    }

    private class Slot {
		private final ImageView mView;
		private Card mCard;

		private Slot(ImageView view, final RowNum r, final ColNum c) {
			mView = view;
			view.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
                    getSlot(cursorRow, cursorCol).unhighlight();
					highlight(getResources().getColor(R.color.cursor));
					cursorRow = r;
                    cursorCol = c;
                    Log.i("Set cursor: row ", + r.y + " col " + c.x);
				}
			});
			mCard = Card.BLANK_CARD;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        if (savedInstanceState != null) {
            numRows = savedInstanceState.getInt(SAVED_ROW_COUNT);
        } else {
            numRows = DEFAULT_NUMBER_OF_ROWS;
        }
        Log.i(TAG, "Num rows: " + numRows);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
    		Bundle savedInstanceState) {
    	super.onCreateView(inflater, parent, savedInstanceState);
    	View v = inflater.inflate(R.layout.fragment_hand, parent, false);
        final LinearLayout trioHolderView = (LinearLayout) v.findViewById(R.id.trio_holder);
        mSlots = new ArrayList<Row>();

        for (int ri = 0; ri < numRows; ri++) {
            Row row = new Row();
            LinearLayout rowView = new LinearLayout(getActivity());
            rowView.setOrientation(LinearLayout.HORIZONTAL);
            for (int ci = 0; ci < ROW_SIZE; ci++) {
                ImageView imageView = new ImageView(getActivity());
                Slot slot = new Slot(imageView, new RowNum(ri), new ColNum(ci));
                slot.setCard(getHand().get(getIndexInHand(new RowNum(ri), new ColNum(ci))));
                row.slots.add(slot);
                rowView.addView(imageView);
            }
            mSlots.add(row);
            trioHolderView.addView(rowView);
        }

        if (numRows > 0) {
            mSlots.get(0).slots.get(0).highlight(getResources().getColor(R.color.cursor));
        }
//		Button matchButton = (Button) v.findViewById(R.id.match_button);
//		matchButton.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				solve();
//			}
//		});
//        Button newTriadButton = (Button) v.findViewById(R.id.new_triad_button);
//        newTriadButton.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onAddRow(trioHolderView);
//            }
//        });
//        Button deleteTriadButton = (Button) v.findViewById(R.id.delete_triad_button);
//        deleteTriadButton.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onDeleteTriad(trioHolderView);
//            }
//        });
        return v;
    }

    private void onAddRow(LinearLayout trioHolderView) {
        LinearLayout trioView = new LinearLayout(getActivity());
        trioView.setOrientation(LinearLayout.HORIZONTAL);
        Row row = new Row();
        for (int i = 0; i < ROW_SIZE; i++) {
            ImageView imageView = new ImageView(getActivity());
            row.slots.add(new Slot(imageView, new RowNum(numRows), new ColNum(i)));
            trioView.addView(imageView);
        }
        trioHolderView.addView(trioView);
        mSlots.add(row);
        numRows++;
        resetCursor();
    }

    private void onDeleteTriad(LinearLayout trioHolderView) {
        getSlot(cursorRow, cursorCol).unhighlight();
        if (numRows <= 0) {
            return;
        }

        RowNum rowNum = new RowNum(numRows-1);
        for (int y = 0; y < ROW_SIZE; y++) {
            getHand().set(getIndexInHand(rowNum, new ColNum(y)), Card.BLANK_CARD);
        }

        trioHolderView.removeViewAt(numRows);
        if (numRows > 0) {
            resetCursor();
        }
        numRows--;
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
            if (numRows <= 0) {
                return;
            }

            getHand().set(getIndexInHand(cursorRow, cursorCol), card);
            getSlot(cursorRow, cursorCol).setCard(card);
            highlightNext();
    	} else {
    		Log.i(TAG, "PICK_CARD_CODE missing");
    	}
    }

    private void highlightNext() {
        getSlot(cursorRow, cursorCol).unhighlight();
        cursorCol.x++;
        if (cursorCol.x >= ROW_SIZE) {
            cursorCol.x = 0;
            cursorRow.y = (cursorRow.y + 1) % numRows;
        }
        getSlot(cursorRow, cursorCol).highlight(getResources().getColor(R.color.cursor));
    }

    private List<Card> getHand() {
        return ((MainActivity) getActivity()).mSelected;
    }

    private int getIndexInHand(RowNum r, ColNum c) {
        return r.y * ROW_SIZE + c.x;
    }

    private Slot getSlot(RowNum r, ColNum c) {
        return mSlots.get(r.y).slots.get(c.x);
    }

    private void resetCursor() {
        cursorCol = new ColNum(0);
        cursorRow = new RowNum(0);
        getSlot(new RowNum(0), new ColNum(0)).highlight(getResources().getColor(R.color.cursor));
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(SAVED_ROW_COUNT, numRows);
    }

    private void solve() {
//        List<Set<Card>> sets = findSets();
//        Set<Card> unified = new HashSet<Card>();
//        for (Set<Card> set: sets) {
//            unified.addAll(set);
//        }
//
//        int solutionColor = getResources().getColor(R.color.solution);
//        for (Set<Card> set : sets) {
//            for (int i = 0; i < (mXDim * mYDim); i++) {
//                int x = i % mXDim;
//                int y = i / mXDim;
//                Slot slot = mSlots.get(x)[y];
//                if (slot.getCard() == null || !unified.contains(slot.getCard())) {
//                    slot.unhighlight();
//                }
//                else if (set.contains(slot.getCard())) {
//                    slot.highlight(solutionColor);
//                }
//            }
//            solutionColor += 0x00222222;
//        }
//        Toast toast = Toast.makeText(getActivity(), "Num sets: " + sets.size(), Toast.LENGTH_SHORT);
//        toast.show();
    }

//    private List<Set<Card>> findSets() {
//        List<Set<Card>> result = new ArrayList<Set<Card>>();
//        for (int first = 0; first < (mXDim * mYDim); first++) {
//    		int firstX = first % mXDim;
//    		int firstY = first / mXDim;
//            Card firstCard = mSlots.get(firstX)[firstY].getCard();
//    		if ((firstCard == null) || firstCard.isBlank()) {
//    			continue;
//    		}
//    		for (int second = first + 1; second < (mXDim * mYDim); second++) {
//    			int secondX = second % mXDim;
//    			int secondY = second / mXDim;
//                Card secondCard = mSlots.get(secondX)[secondY].getCard();
//    			if ((secondCard == null) || secondCard.isBlank()) {
//    				continue;
//    			}
//                Card thirdCard = firstCard.getCompleter(secondCard);
//    			Log.i(TAG, "first=" + first + ",second=" + second + "," + thirdCard);
//    			for (int third = second + 1; third < (mXDim * mYDim); third++) {
//    				int thirdX = third % mXDim;
//    				int thirdY = third / mXDim;
//                    if (thirdCard.equals(mSlots.get(thirdX)[thirdY].getCard())) {
//    					result.add(new HashSet<Card>(Arrays.asList(firstCard, secondCard, thirdCard)));
//    				}
//    			}
//    		}
//    	}
//        return  result;
//    }
}
