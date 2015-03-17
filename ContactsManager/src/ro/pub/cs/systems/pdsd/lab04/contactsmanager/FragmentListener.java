package ro.pub.cs.systems.pdsd.lab04.contactsmanager;

import java.util.ArrayList;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class FragmentListener implements OnClickListener {

	public FragmentListener() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onClick(View v) {
		Activity a = (Activity) v.getContext();
		switch (v.getId()) {
			case R.id.buttonShow:
				FragmentManager fragmentManager = a.getFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
				AdditionalDetailsFragment additionalDetailsFragment = (AdditionalDetailsFragment)fragmentManager.findFragmentById(R.id.frame2);
				if (additionalDetailsFragment == null) {
				  fragmentTransaction.add(R.id.frame2, new AdditionalDetailsFragment());
				  ((Button)v).setText(a.getResources().getString(R.string.hide_additional_fields));
				  fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
				} else {
				  fragmentTransaction.remove(additionalDetailsFragment);
				  ((Button)v).setText(a.getResources().getString(R.string.show_additional_fields));
				  fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_EXIT_MASK);
				}
				fragmentTransaction.commit();
				break;
				
			case R.id.buttonSave:
				Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
				intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
				
				String name = ((TextView) a.findViewById(R.id.editTextName)).getText().toString();
				String phone = ((TextView) a.findViewById(R.id.editTextNo)).getText().toString();
				String email = ((TextView) a.findViewById(R.id.editTextEmail)).getText().toString();
				String address = ((TextView) a.findViewById(R.id.editTextAddr)).getText().toString();
				
				if (name != null) {
				  intent.putExtra(ContactsContract.Intents.Insert.NAME, name);
				}
				if (phone != null) {
				  intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone);
				}
				if (email != null) {
				  intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email);
				}
				if (address != null) {
				  intent.putExtra(ContactsContract.Intents.Insert.POSTAL, address);
				}
				if (jobTitle != null) {
				  intent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, jobTitle);
				}
				if (company != null) {
				  intent.putExtra(ContactsContract.Intents.Insert.COMPANY, company);
				}
				ArrayList<ContentValues> contactData = new ArrayList<ContentValues>();
				if (website != null) {
				  ContentValues websiteRow = new ContentValues();
				  websiteRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE);
				  websiteRow.put(ContactsContract.CommonDataKinds.Website.URL, website);
				  contactData.add(websiteRow);
				}
				if (im != null) {
				  ContentValues imRow = new ContentValues();
				  imRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE);
				  imRow.put(ContactsContract.CommonDataKinds.Im.DATA, im);
				  contactData.add(imRow);
				}
				intent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, contactData);
				getActivity().startActivity(intent);
		}
	}

}
