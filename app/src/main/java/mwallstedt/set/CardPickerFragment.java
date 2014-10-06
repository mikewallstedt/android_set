package mwallstedt.set;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.View;

import java.util.List;
import java.util.Set;

public class CardPickerFragment extends DialogFragment {
	public static final String EXTRA_VIEW_ID = "com.example.setsolver.view_id";

    public static CardPickerFragment newInstance(int viewId) {
		Bundle args = new Bundle();
		args.putInt(EXTRA_VIEW_ID, viewId);

		CardPickerFragment fragment = new CardPickerFragment();
		fragment.setArguments(args);
		
		return fragment;
	}
	
	private void setOnClickCallback(View dialogView, int cardViewId, final Card card) {
		View cardView = dialogView.findViewById(cardViewId);
		cardView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
                ((MainActivity)getActivity()).onCardChosen(card);
				dismiss();
			}
		});
	}

    @NonNull
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
        int viewId = getArguments().getInt(EXTRA_VIEW_ID);
		View v = getActivity().getLayoutInflater()
				.inflate(viewId, null);
        SparseArray<Card> viewToCard = new SparseArray<Card>();
		switch (viewId) {
		case R.layout.dialog_d1:
			viewToCard.put(R.id.d1ge, new Card(Card.Shape.DIAMOND, Card.Count.ONE, Card.Color.GREEN, Card.Fill.EMPTY));
			viewToCard.put(R.id.d1gh, new Card(Card.Shape.DIAMOND, Card.Count.ONE, Card.Color.GREEN, Card.Fill.HALF));
			viewToCard.put(R.id.d1gf, new Card(Card.Shape.DIAMOND, Card.Count.ONE, Card.Color.GREEN, Card.Fill.FULL));
			viewToCard.put(R.id.d1re, new Card(Card.Shape.DIAMOND, Card.Count.ONE, Card.Color.RED, Card.Fill.EMPTY));
			viewToCard.put(R.id.d1rh, new Card(Card.Shape.DIAMOND, Card.Count.ONE, Card.Color.RED, Card.Fill.HALF));
			viewToCard.put(R.id.d1rf, new Card(Card.Shape.DIAMOND, Card.Count.ONE, Card.Color.RED, Card.Fill.FULL));
			viewToCard.put(R.id.d1pe, new Card(Card.Shape.DIAMOND, Card.Count.ONE, Card.Color.PURPLE, Card.Fill.EMPTY));
			viewToCard.put(R.id.d1ph, new Card(Card.Shape.DIAMOND, Card.Count.ONE, Card.Color.PURPLE, Card.Fill.HALF));
			viewToCard.put(R.id.d1pf, new Card(Card.Shape.DIAMOND, Card.Count.ONE, Card.Color.PURPLE, Card.Fill.FULL));
            break;
		case R.layout.dialog_d2:
			viewToCard.put(R.id.d2ge, new Card(Card.Shape.DIAMOND, Card.Count.TWO, Card.Color.GREEN, Card.Fill.EMPTY));
			viewToCard.put(R.id.d2gh, new Card(Card.Shape.DIAMOND, Card.Count.TWO, Card.Color.GREEN, Card.Fill.HALF));
			viewToCard.put(R.id.d2gf, new Card(Card.Shape.DIAMOND, Card.Count.TWO, Card.Color.GREEN, Card.Fill.FULL));
			viewToCard.put(R.id.d2re, new Card(Card.Shape.DIAMOND, Card.Count.TWO, Card.Color.RED, Card.Fill.EMPTY));
			viewToCard.put(R.id.d2rh, new Card(Card.Shape.DIAMOND, Card.Count.TWO, Card.Color.RED, Card.Fill.HALF));
			viewToCard.put(R.id.d2rf, new Card(Card.Shape.DIAMOND, Card.Count.TWO, Card.Color.RED, Card.Fill.FULL));
			viewToCard.put(R.id.d2pe, new Card(Card.Shape.DIAMOND, Card.Count.TWO, Card.Color.PURPLE, Card.Fill.EMPTY));
			viewToCard.put(R.id.d2ph, new Card(Card.Shape.DIAMOND, Card.Count.TWO, Card.Color.PURPLE, Card.Fill.HALF));
			viewToCard.put(R.id.d2pf, new Card(Card.Shape.DIAMOND, Card.Count.TWO, Card.Color.PURPLE, Card.Fill.FULL));
			break;
		case R.layout.dialog_d3:
			viewToCard.put(R.id.d3ge, new Card(Card.Shape.DIAMOND, Card.Count.THREE, Card.Color.GREEN, Card.Fill.EMPTY));
			viewToCard.put(R.id.d3gh, new Card(Card.Shape.DIAMOND, Card.Count.THREE, Card.Color.GREEN, Card.Fill.HALF));
			viewToCard.put(R.id.d3gf, new Card(Card.Shape.DIAMOND, Card.Count.THREE, Card.Color.GREEN, Card.Fill.FULL));
			viewToCard.put(R.id.d3re, new Card(Card.Shape.DIAMOND, Card.Count.THREE, Card.Color.RED, Card.Fill.EMPTY));
			viewToCard.put(R.id.d3rh, new Card(Card.Shape.DIAMOND, Card.Count.THREE, Card.Color.RED, Card.Fill.HALF));
			viewToCard.put(R.id.d3rf, new Card(Card.Shape.DIAMOND, Card.Count.THREE, Card.Color.RED, Card.Fill.FULL));
			viewToCard.put(R.id.d3pe, new Card(Card.Shape.DIAMOND, Card.Count.THREE, Card.Color.PURPLE, Card.Fill.EMPTY));
			viewToCard.put(R.id.d3ph, new Card(Card.Shape.DIAMOND, Card.Count.THREE, Card.Color.PURPLE, Card.Fill.HALF));
			viewToCard.put(R.id.d3pf, new Card(Card.Shape.DIAMOND, Card.Count.THREE, Card.Color.PURPLE, Card.Fill.FULL));
			break;
		case R.layout.dialog_o1:
			viewToCard.put(R.id.o1ge, new Card(Card.Shape.OVAL, Card.Count.ONE, Card.Color.GREEN, Card.Fill.EMPTY));
			viewToCard.put(R.id.o1gh, new Card(Card.Shape.OVAL, Card.Count.ONE, Card.Color.GREEN, Card.Fill.HALF));
			viewToCard.put(R.id.o1gf, new Card(Card.Shape.OVAL, Card.Count.ONE, Card.Color.GREEN, Card.Fill.FULL));
			viewToCard.put(R.id.o1re, new Card(Card.Shape.OVAL, Card.Count.ONE, Card.Color.RED, Card.Fill.EMPTY));
			viewToCard.put(R.id.o1rh, new Card(Card.Shape.OVAL, Card.Count.ONE, Card.Color.RED, Card.Fill.HALF));
			viewToCard.put(R.id.o1rf, new Card(Card.Shape.OVAL, Card.Count.ONE, Card.Color.RED, Card.Fill.FULL));
			viewToCard.put(R.id.o1pe, new Card(Card.Shape.OVAL, Card.Count.ONE, Card.Color.PURPLE, Card.Fill.EMPTY));
			viewToCard.put(R.id.o1ph, new Card(Card.Shape.OVAL, Card.Count.ONE, Card.Color.PURPLE, Card.Fill.HALF));
			viewToCard.put(R.id.o1pf, new Card(Card.Shape.OVAL, Card.Count.ONE, Card.Color.PURPLE, Card.Fill.FULL));
			break;
		case R.layout.dialog_o2:
			viewToCard.put(R.id.o2ge, new Card(Card.Shape.OVAL, Card.Count.TWO, Card.Color.GREEN, Card.Fill.EMPTY));
			viewToCard.put(R.id.o2gh, new Card(Card.Shape.OVAL, Card.Count.TWO, Card.Color.GREEN, Card.Fill.HALF));
			viewToCard.put(R.id.o2gf, new Card(Card.Shape.OVAL, Card.Count.TWO, Card.Color.GREEN, Card.Fill.FULL));
			viewToCard.put(R.id.o2re, new Card(Card.Shape.OVAL, Card.Count.TWO, Card.Color.RED, Card.Fill.EMPTY));
			viewToCard.put(R.id.o2rh, new Card(Card.Shape.OVAL, Card.Count.TWO, Card.Color.RED, Card.Fill.HALF));
			viewToCard.put(R.id.o2rf, new Card(Card.Shape.OVAL, Card.Count.TWO, Card.Color.RED, Card.Fill.FULL));
			viewToCard.put(R.id.o2pe, new Card(Card.Shape.OVAL, Card.Count.TWO, Card.Color.PURPLE, Card.Fill.EMPTY));
			viewToCard.put(R.id.o2ph, new Card(Card.Shape.OVAL, Card.Count.TWO, Card.Color.PURPLE, Card.Fill.HALF));
			viewToCard.put(R.id.o2pf, new Card(Card.Shape.OVAL, Card.Count.TWO, Card.Color.PURPLE, Card.Fill.FULL));
			break;
		case R.layout.dialog_o3:
			viewToCard.put(R.id.o3ge, new Card(Card.Shape.OVAL, Card.Count.THREE, Card.Color.GREEN, Card.Fill.EMPTY));
			viewToCard.put(R.id.o3gh, new Card(Card.Shape.OVAL, Card.Count.THREE, Card.Color.GREEN, Card.Fill.HALF));
			viewToCard.put(R.id.o3gf, new Card(Card.Shape.OVAL, Card.Count.THREE, Card.Color.GREEN, Card.Fill.FULL));
			viewToCard.put(R.id.o3re, new Card(Card.Shape.OVAL, Card.Count.THREE, Card.Color.RED, Card.Fill.EMPTY));
			viewToCard.put(R.id.o3rh, new Card(Card.Shape.OVAL, Card.Count.THREE, Card.Color.RED, Card.Fill.HALF));
			viewToCard.put(R.id.o3rf, new Card(Card.Shape.OVAL, Card.Count.THREE, Card.Color.RED, Card.Fill.FULL));
			viewToCard.put(R.id.o3pe, new Card(Card.Shape.OVAL, Card.Count.THREE, Card.Color.PURPLE, Card.Fill.EMPTY));
			viewToCard.put(R.id.o3ph, new Card(Card.Shape.OVAL, Card.Count.THREE, Card.Color.PURPLE, Card.Fill.HALF));
			viewToCard.put(R.id.o3pf, new Card(Card.Shape.OVAL, Card.Count.THREE, Card.Color.PURPLE, Card.Fill.FULL));
			break;
		case R.layout.dialog_s1:
			viewToCard.put(R.id.s1ge, new Card(Card.Shape.SQUIGGLE, Card.Count.ONE, Card.Color.GREEN, Card.Fill.EMPTY));
			viewToCard.put(R.id.s1gh, new Card(Card.Shape.SQUIGGLE, Card.Count.ONE, Card.Color.GREEN, Card.Fill.HALF));
			viewToCard.put(R.id.s1gf, new Card(Card.Shape.SQUIGGLE, Card.Count.ONE, Card.Color.GREEN, Card.Fill.FULL));
			viewToCard.put(R.id.s1re, new Card(Card.Shape.SQUIGGLE, Card.Count.ONE, Card.Color.RED, Card.Fill.EMPTY));
			viewToCard.put(R.id.s1rh, new Card(Card.Shape.SQUIGGLE, Card.Count.ONE, Card.Color.RED, Card.Fill.HALF));
			viewToCard.put(R.id.s1rf, new Card(Card.Shape.SQUIGGLE, Card.Count.ONE, Card.Color.RED, Card.Fill.FULL));
			viewToCard.put(R.id.s1pe, new Card(Card.Shape.SQUIGGLE, Card.Count.ONE, Card.Color.PURPLE, Card.Fill.EMPTY));
			viewToCard.put(R.id.s1ph, new Card(Card.Shape.SQUIGGLE, Card.Count.ONE, Card.Color.PURPLE, Card.Fill.HALF));
			viewToCard.put(R.id.s1pf, new Card(Card.Shape.SQUIGGLE, Card.Count.ONE, Card.Color.PURPLE, Card.Fill.FULL));
			break;
		case R.layout.dialog_s2:
			viewToCard.put(R.id.s2ge, new Card(Card.Shape.SQUIGGLE, Card.Count.TWO, Card.Color.GREEN, Card.Fill.EMPTY));
			viewToCard.put(R.id.s2gh, new Card(Card.Shape.SQUIGGLE, Card.Count.TWO, Card.Color.GREEN, Card.Fill.HALF));
			viewToCard.put(R.id.s2gf, new Card(Card.Shape.SQUIGGLE, Card.Count.TWO, Card.Color.GREEN, Card.Fill.FULL));
			viewToCard.put(R.id.s2re, new Card(Card.Shape.SQUIGGLE, Card.Count.TWO, Card.Color.RED, Card.Fill.EMPTY));
			viewToCard.put(R.id.s2rh, new Card(Card.Shape.SQUIGGLE, Card.Count.TWO, Card.Color.RED, Card.Fill.HALF));
			viewToCard.put(R.id.s2rf, new Card(Card.Shape.SQUIGGLE, Card.Count.TWO, Card.Color.RED, Card.Fill.FULL));
			viewToCard.put(R.id.s2pe, new Card(Card.Shape.SQUIGGLE, Card.Count.TWO, Card.Color.PURPLE, Card.Fill.EMPTY));
			viewToCard.put(R.id.s2ph, new Card(Card.Shape.SQUIGGLE, Card.Count.TWO, Card.Color.PURPLE, Card.Fill.HALF));
			viewToCard.put(R.id.s2pf, new Card(Card.Shape.SQUIGGLE, Card.Count.TWO, Card.Color.PURPLE, Card.Fill.FULL));
			break;
		case R.layout.dialog_s3:
			viewToCard.put(R.id.s3ge, new Card(Card.Shape.SQUIGGLE, Card.Count.THREE, Card.Color.GREEN, Card.Fill.EMPTY));
			viewToCard.put(R.id.s3gh, new Card(Card.Shape.SQUIGGLE, Card.Count.THREE, Card.Color.GREEN, Card.Fill.HALF));
			viewToCard.put(R.id.s3gf, new Card(Card.Shape.SQUIGGLE, Card.Count.THREE, Card.Color.GREEN, Card.Fill.FULL));
			viewToCard.put(R.id.s3re, new Card(Card.Shape.SQUIGGLE, Card.Count.THREE, Card.Color.RED, Card.Fill.EMPTY));
			viewToCard.put(R.id.s3rh, new Card(Card.Shape.SQUIGGLE, Card.Count.THREE, Card.Color.RED, Card.Fill.HALF));
			viewToCard.put(R.id.s3rf, new Card(Card.Shape.SQUIGGLE, Card.Count.THREE, Card.Color.RED, Card.Fill.FULL));
			viewToCard.put(R.id.s3pe, new Card(Card.Shape.SQUIGGLE, Card.Count.THREE, Card.Color.PURPLE, Card.Fill.EMPTY));
			viewToCard.put(R.id.s3ph, new Card(Card.Shape.SQUIGGLE, Card.Count.THREE, Card.Color.PURPLE, Card.Fill.HALF));
			viewToCard.put(R.id.s3pf, new Card(Card.Shape.SQUIGGLE, Card.Count.THREE, Card.Color.PURPLE, Card.Fill.FULL));
			break;
		}

        Set<Card> inPlay = ((MainActivity)getActivity()).getCardsInPlay();
        for (int i = 0; i < viewToCard.size(); i++) {
            int cardViewId = viewToCard.keyAt(i);
            Card card = viewToCard.get(cardViewId);
            if (inPlay.contains(card)) {
                v.findViewById(cardViewId).setAlpha(0);
            } else {
                v.findViewById(cardViewId).setAlpha(1);
                setOnClickCallback(v, cardViewId, card);
            }
        }

        return new AlertDialog.Builder(getActivity())
			.setView(v)
			//.setTitle(R.string.card_picker_title)
			//.setPositiveButton(android.R.string.ok, null)
			.create();
	}

}
