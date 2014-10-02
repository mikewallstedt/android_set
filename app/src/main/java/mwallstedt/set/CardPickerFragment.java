package mwallstedt.set;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.View;

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
				Fragment targetFragment = getTargetFragment();
				if (targetFragment != null) {
					Intent i = new Intent();
					i.putExtra(HandFragment.EXTRA_CARD, card);
					targetFragment.onActivityResult(
							getTargetRequestCode(), Activity.RESULT_OK, i);
				}
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
		switch (viewId) {
		case R.layout.dialog_d1:
			setOnClickCallback(v, R.id.d1ge, new Card(Card.Shape.DIAMOND, Card.Count.ONE, Card.Color.GREEN, Card.Fill.EMPTY));
			setOnClickCallback(v, R.id.d1gh, new Card(Card.Shape.DIAMOND, Card.Count.ONE, Card.Color.GREEN, Card.Fill.HALF));
			setOnClickCallback(v, R.id.d1gf, new Card(Card.Shape.DIAMOND, Card.Count.ONE, Card.Color.GREEN, Card.Fill.FULL));
			setOnClickCallback(v, R.id.d1re, new Card(Card.Shape.DIAMOND, Card.Count.ONE, Card.Color.RED, Card.Fill.EMPTY));
			setOnClickCallback(v, R.id.d1rh, new Card(Card.Shape.DIAMOND, Card.Count.ONE, Card.Color.RED, Card.Fill.HALF));
			setOnClickCallback(v, R.id.d1rf, new Card(Card.Shape.DIAMOND, Card.Count.ONE, Card.Color.RED, Card.Fill.FULL));
			setOnClickCallback(v, R.id.d1pe, new Card(Card.Shape.DIAMOND, Card.Count.ONE, Card.Color.PURPLE, Card.Fill.EMPTY));
			setOnClickCallback(v, R.id.d1ph, new Card(Card.Shape.DIAMOND, Card.Count.ONE, Card.Color.PURPLE, Card.Fill.HALF));
			setOnClickCallback(v, R.id.d1pf, new Card(Card.Shape.DIAMOND, Card.Count.ONE, Card.Color.PURPLE, Card.Fill.FULL));
			break;
		case R.layout.dialog_d2:
			setOnClickCallback(v, R.id.d2ge, new Card(Card.Shape.DIAMOND, Card.Count.TWO, Card.Color.GREEN, Card.Fill.EMPTY));
			setOnClickCallback(v, R.id.d2gh, new Card(Card.Shape.DIAMOND, Card.Count.TWO, Card.Color.GREEN, Card.Fill.HALF));
			setOnClickCallback(v, R.id.d2gf, new Card(Card.Shape.DIAMOND, Card.Count.TWO, Card.Color.GREEN, Card.Fill.FULL));
			setOnClickCallback(v, R.id.d2re, new Card(Card.Shape.DIAMOND, Card.Count.TWO, Card.Color.RED, Card.Fill.EMPTY));
			setOnClickCallback(v, R.id.d2rh, new Card(Card.Shape.DIAMOND, Card.Count.TWO, Card.Color.RED, Card.Fill.HALF));
			setOnClickCallback(v, R.id.d2rf, new Card(Card.Shape.DIAMOND, Card.Count.TWO, Card.Color.RED, Card.Fill.FULL));
			setOnClickCallback(v, R.id.d2pe, new Card(Card.Shape.DIAMOND, Card.Count.TWO, Card.Color.PURPLE, Card.Fill.EMPTY));
			setOnClickCallback(v, R.id.d2ph, new Card(Card.Shape.DIAMOND, Card.Count.TWO, Card.Color.PURPLE, Card.Fill.HALF));
			setOnClickCallback(v, R.id.d2pf, new Card(Card.Shape.DIAMOND, Card.Count.TWO, Card.Color.PURPLE, Card.Fill.FULL));
			break;
		case R.layout.dialog_d3:
			setOnClickCallback(v, R.id.d3ge, new Card(Card.Shape.DIAMOND, Card.Count.THREE, Card.Color.GREEN, Card.Fill.EMPTY));
			setOnClickCallback(v, R.id.d3gh, new Card(Card.Shape.DIAMOND, Card.Count.THREE, Card.Color.GREEN, Card.Fill.HALF));
			setOnClickCallback(v, R.id.d3gf, new Card(Card.Shape.DIAMOND, Card.Count.THREE, Card.Color.GREEN, Card.Fill.FULL));
			setOnClickCallback(v, R.id.d3re, new Card(Card.Shape.DIAMOND, Card.Count.THREE, Card.Color.RED, Card.Fill.EMPTY));
			setOnClickCallback(v, R.id.d3rh, new Card(Card.Shape.DIAMOND, Card.Count.THREE, Card.Color.RED, Card.Fill.HALF));
			setOnClickCallback(v, R.id.d3rf, new Card(Card.Shape.DIAMOND, Card.Count.THREE, Card.Color.RED, Card.Fill.FULL));
			setOnClickCallback(v, R.id.d3pe, new Card(Card.Shape.DIAMOND, Card.Count.THREE, Card.Color.PURPLE, Card.Fill.EMPTY));
			setOnClickCallback(v, R.id.d3ph, new Card(Card.Shape.DIAMOND, Card.Count.THREE, Card.Color.PURPLE, Card.Fill.HALF));
			setOnClickCallback(v, R.id.d3pf, new Card(Card.Shape.DIAMOND, Card.Count.THREE, Card.Color.PURPLE, Card.Fill.FULL));
			break;
		case R.layout.dialog_o1:
			setOnClickCallback(v, R.id.o1ge, new Card(Card.Shape.OVAL, Card.Count.ONE, Card.Color.GREEN, Card.Fill.EMPTY));
			setOnClickCallback(v, R.id.o1gh, new Card(Card.Shape.OVAL, Card.Count.ONE, Card.Color.GREEN, Card.Fill.HALF));
			setOnClickCallback(v, R.id.o1gf, new Card(Card.Shape.OVAL, Card.Count.ONE, Card.Color.GREEN, Card.Fill.FULL));
			setOnClickCallback(v, R.id.o1re, new Card(Card.Shape.OVAL, Card.Count.ONE, Card.Color.RED, Card.Fill.EMPTY));
			setOnClickCallback(v, R.id.o1rh, new Card(Card.Shape.OVAL, Card.Count.ONE, Card.Color.RED, Card.Fill.HALF));
			setOnClickCallback(v, R.id.o1rf, new Card(Card.Shape.OVAL, Card.Count.ONE, Card.Color.RED, Card.Fill.FULL));
			setOnClickCallback(v, R.id.o1pe, new Card(Card.Shape.OVAL, Card.Count.ONE, Card.Color.PURPLE, Card.Fill.EMPTY));
			setOnClickCallback(v, R.id.o1ph, new Card(Card.Shape.OVAL, Card.Count.ONE, Card.Color.PURPLE, Card.Fill.HALF));
			setOnClickCallback(v, R.id.o1pf, new Card(Card.Shape.OVAL, Card.Count.ONE, Card.Color.PURPLE, Card.Fill.FULL));
			break;
		case R.layout.dialog_o2:
			setOnClickCallback(v, R.id.o2ge, new Card(Card.Shape.OVAL, Card.Count.TWO, Card.Color.GREEN, Card.Fill.EMPTY));
			setOnClickCallback(v, R.id.o2gh, new Card(Card.Shape.OVAL, Card.Count.TWO, Card.Color.GREEN, Card.Fill.HALF));
			setOnClickCallback(v, R.id.o2gf, new Card(Card.Shape.OVAL, Card.Count.TWO, Card.Color.GREEN, Card.Fill.FULL));
			setOnClickCallback(v, R.id.o2re, new Card(Card.Shape.OVAL, Card.Count.TWO, Card.Color.RED, Card.Fill.EMPTY));
			setOnClickCallback(v, R.id.o2rh, new Card(Card.Shape.OVAL, Card.Count.TWO, Card.Color.RED, Card.Fill.HALF));
			setOnClickCallback(v, R.id.o2rf, new Card(Card.Shape.OVAL, Card.Count.TWO, Card.Color.RED, Card.Fill.FULL));
			setOnClickCallback(v, R.id.o2pe, new Card(Card.Shape.OVAL, Card.Count.TWO, Card.Color.PURPLE, Card.Fill.EMPTY));
			setOnClickCallback(v, R.id.o2ph, new Card(Card.Shape.OVAL, Card.Count.TWO, Card.Color.PURPLE, Card.Fill.HALF));
			setOnClickCallback(v, R.id.o2pf, new Card(Card.Shape.OVAL, Card.Count.TWO, Card.Color.PURPLE, Card.Fill.FULL));
			break;
		case R.layout.dialog_o3:
			setOnClickCallback(v, R.id.o3ge, new Card(Card.Shape.OVAL, Card.Count.THREE, Card.Color.GREEN, Card.Fill.EMPTY));
			setOnClickCallback(v, R.id.o3gh, new Card(Card.Shape.OVAL, Card.Count.THREE, Card.Color.GREEN, Card.Fill.HALF));
			setOnClickCallback(v, R.id.o3gf, new Card(Card.Shape.OVAL, Card.Count.THREE, Card.Color.GREEN, Card.Fill.FULL));
			setOnClickCallback(v, R.id.o3re, new Card(Card.Shape.OVAL, Card.Count.THREE, Card.Color.RED, Card.Fill.EMPTY));
			setOnClickCallback(v, R.id.o3rh, new Card(Card.Shape.OVAL, Card.Count.THREE, Card.Color.RED, Card.Fill.HALF));
			setOnClickCallback(v, R.id.o3rf, new Card(Card.Shape.OVAL, Card.Count.THREE, Card.Color.RED, Card.Fill.FULL));
			setOnClickCallback(v, R.id.o3pe, new Card(Card.Shape.OVAL, Card.Count.THREE, Card.Color.PURPLE, Card.Fill.EMPTY));
			setOnClickCallback(v, R.id.o3ph, new Card(Card.Shape.OVAL, Card.Count.THREE, Card.Color.PURPLE, Card.Fill.HALF));
			setOnClickCallback(v, R.id.o3pf, new Card(Card.Shape.OVAL, Card.Count.THREE, Card.Color.PURPLE, Card.Fill.FULL));
			break;
		case R.layout.dialog_s1:
			setOnClickCallback(v, R.id.s1ge, new Card(Card.Shape.SQUIGGLE, Card.Count.ONE, Card.Color.GREEN, Card.Fill.EMPTY));
			setOnClickCallback(v, R.id.s1gh, new Card(Card.Shape.SQUIGGLE, Card.Count.ONE, Card.Color.GREEN, Card.Fill.HALF));
			setOnClickCallback(v, R.id.s1gf, new Card(Card.Shape.SQUIGGLE, Card.Count.ONE, Card.Color.GREEN, Card.Fill.FULL));
			setOnClickCallback(v, R.id.s1re, new Card(Card.Shape.SQUIGGLE, Card.Count.ONE, Card.Color.RED, Card.Fill.EMPTY));
			setOnClickCallback(v, R.id.s1rh, new Card(Card.Shape.SQUIGGLE, Card.Count.ONE, Card.Color.RED, Card.Fill.HALF));
			setOnClickCallback(v, R.id.s1rf, new Card(Card.Shape.SQUIGGLE, Card.Count.ONE, Card.Color.RED, Card.Fill.FULL));
			setOnClickCallback(v, R.id.s1pe, new Card(Card.Shape.SQUIGGLE, Card.Count.ONE, Card.Color.PURPLE, Card.Fill.EMPTY));
			setOnClickCallback(v, R.id.s1ph, new Card(Card.Shape.SQUIGGLE, Card.Count.ONE, Card.Color.PURPLE, Card.Fill.HALF));
			setOnClickCallback(v, R.id.s1pf, new Card(Card.Shape.SQUIGGLE, Card.Count.ONE, Card.Color.PURPLE, Card.Fill.FULL));
			break;
		case R.layout.dialog_s2:
			setOnClickCallback(v, R.id.s2ge, new Card(Card.Shape.SQUIGGLE, Card.Count.TWO, Card.Color.GREEN, Card.Fill.EMPTY));
			setOnClickCallback(v, R.id.s2gh, new Card(Card.Shape.SQUIGGLE, Card.Count.TWO, Card.Color.GREEN, Card.Fill.HALF));
			setOnClickCallback(v, R.id.s2gf, new Card(Card.Shape.SQUIGGLE, Card.Count.TWO, Card.Color.GREEN, Card.Fill.FULL));
			setOnClickCallback(v, R.id.s2re, new Card(Card.Shape.SQUIGGLE, Card.Count.TWO, Card.Color.RED, Card.Fill.EMPTY));
			setOnClickCallback(v, R.id.s2rh, new Card(Card.Shape.SQUIGGLE, Card.Count.TWO, Card.Color.RED, Card.Fill.HALF));
			setOnClickCallback(v, R.id.s2rf, new Card(Card.Shape.SQUIGGLE, Card.Count.TWO, Card.Color.RED, Card.Fill.FULL));
			setOnClickCallback(v, R.id.s2pe, new Card(Card.Shape.SQUIGGLE, Card.Count.TWO, Card.Color.PURPLE, Card.Fill.EMPTY));
			setOnClickCallback(v, R.id.s2ph, new Card(Card.Shape.SQUIGGLE, Card.Count.TWO, Card.Color.PURPLE, Card.Fill.HALF));
			setOnClickCallback(v, R.id.s2pf, new Card(Card.Shape.SQUIGGLE, Card.Count.TWO, Card.Color.PURPLE, Card.Fill.FULL));
			break;
		case R.layout.dialog_s3:
			setOnClickCallback(v, R.id.s3ge, new Card(Card.Shape.SQUIGGLE, Card.Count.THREE, Card.Color.GREEN, Card.Fill.EMPTY));
			setOnClickCallback(v, R.id.s3gh, new Card(Card.Shape.SQUIGGLE, Card.Count.THREE, Card.Color.GREEN, Card.Fill.HALF));
			setOnClickCallback(v, R.id.s3gf, new Card(Card.Shape.SQUIGGLE, Card.Count.THREE, Card.Color.GREEN, Card.Fill.FULL));
			setOnClickCallback(v, R.id.s3re, new Card(Card.Shape.SQUIGGLE, Card.Count.THREE, Card.Color.RED, Card.Fill.EMPTY));
			setOnClickCallback(v, R.id.s3rh, new Card(Card.Shape.SQUIGGLE, Card.Count.THREE, Card.Color.RED, Card.Fill.HALF));
			setOnClickCallback(v, R.id.s3rf, new Card(Card.Shape.SQUIGGLE, Card.Count.THREE, Card.Color.RED, Card.Fill.FULL));
			setOnClickCallback(v, R.id.s3pe, new Card(Card.Shape.SQUIGGLE, Card.Count.THREE, Card.Color.PURPLE, Card.Fill.EMPTY));
			setOnClickCallback(v, R.id.s3ph, new Card(Card.Shape.SQUIGGLE, Card.Count.THREE, Card.Color.PURPLE, Card.Fill.HALF));
			setOnClickCallback(v, R.id.s3pf, new Card(Card.Shape.SQUIGGLE, Card.Count.THREE, Card.Color.PURPLE, Card.Fill.FULL));
			break;
		}
		return new AlertDialog.Builder(getActivity())
			.setView(v)
			//.setTitle(R.string.card_picker_title)
			//.setPositiveButton(android.R.string.ok, null)
			.create();
	}

}
