package mwallstedt.set;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class CardTemplatesFragment extends Fragment {
    private static final class Resources {
        int mTemplateViewId;
        int mDialogLayoutId;
        String mDialogName;

        Resources(int templateViewId, int dialogLayoutId, String dialogName) {
            mTemplateViewId = templateViewId;
            mDialogLayoutId = dialogLayoutId;
            mDialogName = dialogName;
        }
    }

    private final class TemplateOnClickListener implements View.OnClickListener {
        private int mLayoutId;
        private String mDialogName;

        TemplateOnClickListener(int layoutId, String dialogName) {
            mLayoutId = layoutId;
            mDialogName = dialogName;
        }

        @Override
        public void onClick(View view) {
            FragmentManager fm = getActivity().getSupportFragmentManager();
            CardPickerFragment dialog = CardPickerFragment.newInstance(mLayoutId);
            Fragment handFragment = fm.findFragmentById(R.id.handFragmentContainer);
            if (handFragment != null) {
                dialog.setTargetFragment(handFragment, PICK_CARD_CODE);
            }
            dialog.show(fm, mDialogName);
        }
    }

    private static final Resources[] sResources = new Resources[] {
            new Resources(R.id.d1, R.layout.dialog_d1, "d1"),
            new Resources(R.id.d2, R.layout.dialog_d2, "d2"),
            new Resources(R.id.d3, R.layout.dialog_d3, "d3"),
            new Resources(R.id.o1, R.layout.dialog_o1, "o1"),
            new Resources(R.id.o2, R.layout.dialog_o2, "o2"),
            new Resources(R.id.o3, R.layout.dialog_o3, "o3"),
            new Resources(R.id.s1, R.layout.dialog_s1, "s1"),
            new Resources(R.id.s2, R.layout.dialog_s2, "s2"),
            new Resources(R.id.s3, R.layout.dialog_s3, "s3")
    };

	public static final int PICK_CARD_CODE = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
    	View v = inflater.inflate(R.layout.fragment_card_templates, parent, false);

        for (Resources resources : sResources) {
            ImageView templateView = (ImageView)v.findViewById(resources.mTemplateViewId);
            templateView.setOnClickListener(
                    new TemplateOnClickListener(resources.mDialogLayoutId, resources.mDialogName));
        }

        return v;
    }
}
