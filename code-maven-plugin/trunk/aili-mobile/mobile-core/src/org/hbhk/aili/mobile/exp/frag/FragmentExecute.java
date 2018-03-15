package org.hbhk.aili.mobile.exp.frag;


import org.hbhk.aili.mobile.R;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentExecute extends Fragment {

	public FragmentExecute() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (container == null) {
			// Currently in a layout without a container, so no
			// reason to create our view.
			return null;
		}
		LayoutInflater myInflater = (LayoutInflater) getActivity()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = myInflater.inflate(R.layout.frag_execute, container,
				false);

		return layout;
	}
}
