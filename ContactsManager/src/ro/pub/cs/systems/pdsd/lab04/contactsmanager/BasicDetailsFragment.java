package ro.pub.cs.systems.pdsd.lab04.contactsmanager;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class BasicDetailsFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle state) {
		return layoutInflater.inflate(R.layout.fragment_basic_details, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		if (savedInstanceState != null) {
			Boolean isShownExtra = savedInstanceState.getBoolean("extra");
			Button buttonShow = (Button) getActivity().findViewById(R.id.buttonShow);
			if (isShownExtra) {
				buttonShow.setText(R.string.hide_additional_fields);
			} else {
				buttonShow.setText(R.string.show_additional_fields);
			}
		}
	}

}
