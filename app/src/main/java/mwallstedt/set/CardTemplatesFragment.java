package mwallstedt.set;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;
import java.util.Set;

public class CardTemplatesFragment extends Fragment {
	private static final String DIALOG_O1 = "o1";
	private static final String DIALOG_O2 = "o2";
	private static final String DIALOG_O3 = "o3";
	private static final String DIALOG_D1 = "d1";
	private static final String DIALOG_D2 = "d2";
	private static final String DIALOG_D3 = "d3";
	private static final String DIALOG_S1 = "s1";
	private static final String DIALOG_S2 = "s2";
	private static final String DIALOG_S3 = "s3";
	
	public static final int PICK_CARD_CODE = 0;
	
	ImageView mO1, mO2, mO3;
	ImageView mD1, mD2, mD3;
	ImageView mS1, mS2, mS3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
    		Bundle savedInstanceState) {
    	View v = inflater.inflate(R.layout.fragment_card_templates, parent, false);
        final List<Card> selected = ((MainActivity)getActivity()).mSelected;

        mO1 = (ImageView)v.findViewById(R.id.o1);
        mO1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentManager fm = getActivity().getSupportFragmentManager();
				CardPickerFragment dialog = CardPickerFragment.newInstance(R.layout.dialog_o1);
				Fragment handFragment = fm.findFragmentById(R.id.handFragmentContainer);
				if (handFragment != null) {
					dialog.setTargetFragment(handFragment, PICK_CARD_CODE);
				}
				dialog.show(fm, DIALOG_O1);
			}
		});
        
        mO2 = (ImageView)v.findViewById(R.id.o2);
        mO2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentManager fm = getActivity().getSupportFragmentManager();
				CardPickerFragment dialog = CardPickerFragment.newInstance(R.layout.dialog_o2);
				Fragment handFragment = fm.findFragmentById(R.id.handFragmentContainer);
				if (handFragment != null) {
					dialog.setTargetFragment(handFragment, PICK_CARD_CODE);
				}
				dialog.show(fm, DIALOG_O2);
			}
		});
        
        mO3 = (ImageView)v.findViewById(R.id.o3);
        mO3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentManager fm = getActivity().getSupportFragmentManager();
				CardPickerFragment dialog = CardPickerFragment.newInstance(R.layout.dialog_o3);
				Fragment handFragment = fm.findFragmentById(R.id.handFragmentContainer);
				if (handFragment != null) {
					dialog.setTargetFragment(handFragment, PICK_CARD_CODE);
				}
				dialog.show(fm, DIALOG_O3);
			}
		});
        
        mD1 = (ImageView)v.findViewById(R.id.d1);
        mD1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentManager fm = getActivity().getSupportFragmentManager();
				CardPickerFragment dialog = CardPickerFragment.newInstance(R.layout.dialog_d1);
				Fragment handFragment = fm.findFragmentById(R.id.handFragmentContainer);
				if (handFragment != null) {
					dialog.setTargetFragment(handFragment, PICK_CARD_CODE);
				}
				dialog.show(fm, DIALOG_D1);
			}
		});
        
        mD2 = (ImageView)v.findViewById(R.id.d2);
        mD2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentManager fm = getActivity().getSupportFragmentManager();
				CardPickerFragment dialog = CardPickerFragment.newInstance(R.layout.dialog_d2);
				Fragment handFragment = fm.findFragmentById(R.id.handFragmentContainer);
				if (handFragment != null) {
					dialog.setTargetFragment(handFragment, PICK_CARD_CODE);
				}
				dialog.show(fm, DIALOG_D2);
			}
		});
        
        mD3 = (ImageView)v.findViewById(R.id.d3);
        mD3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentManager fm = getActivity().getSupportFragmentManager();
				CardPickerFragment dialog = CardPickerFragment.newInstance(R.layout.dialog_d3);
				Fragment handFragment = fm.findFragmentById(R.id.handFragmentContainer);
				if (handFragment != null) {
					dialog.setTargetFragment(handFragment, PICK_CARD_CODE);
				}
				dialog.show(fm, DIALOG_D3);
			}
		});
        
        mS1 = (ImageView)v.findViewById(R.id.s1);
        mS1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentManager fm = getActivity().getSupportFragmentManager();
				CardPickerFragment dialog = CardPickerFragment.newInstance(R.layout.dialog_s1);
				Fragment handFragment = fm.findFragmentById(R.id.handFragmentContainer);
				if (handFragment != null) {
					dialog.setTargetFragment(handFragment, PICK_CARD_CODE);
				}
				dialog.show(fm, DIALOG_S1);
			}
		});
        
        mS2 = (ImageView)v.findViewById(R.id.s2);
        mS2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentManager fm = getActivity().getSupportFragmentManager();
				CardPickerFragment dialog = CardPickerFragment.newInstance(R.layout.dialog_s2);
				Fragment handFragment = fm.findFragmentById(R.id.handFragmentContainer);
				if (handFragment != null) {
					dialog.setTargetFragment(handFragment, PICK_CARD_CODE);
				}
				dialog.show(fm, DIALOG_S2);
			}
		});
        
        mS3 = (ImageView)v.findViewById(R.id.s3);
        mS3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentManager fm = getActivity().getSupportFragmentManager();
				CardPickerFragment dialog = CardPickerFragment.newInstance(R.layout.dialog_s3);
				Fragment handFragment = fm.findFragmentById(R.id.handFragmentContainer);
				if (handFragment != null) {
					dialog.setTargetFragment(handFragment, PICK_CARD_CODE);
				}
				dialog.show(fm, DIALOG_S3);
			}
		});
        
        return v;
    }
}
