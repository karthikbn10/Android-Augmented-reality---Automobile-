package com.qualcomm.QCARSamples.ImageTargets;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ContactListAdapter extends BaseAdapter {
	
	Context con;
	static ArrayList<ContactDealer> cl = new ArrayList<ContactDealer>();
	

	public ContactListAdapter(Context con) {
		super();
		this.con = con;
		this.cl = populateDealerList();
	}

	

	public int getCount() {
		// TODO Auto-generated method stub
		return cl.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return cl.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return  position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ContactDealer temp = cl.get(position);
		View tempView = convertView;
		ContactHolder ch;
		if(tempView == null){
			LayoutInflater inflater = ((Activity)con).getLayoutInflater();
			ch = new ContactHolder();
			tempView = inflater.inflate(R.layout.contact_list_item, parent, false);
			ch.tv = (TextView) tempView.findViewById(R.id.tvContactListItem);
			tempView.setTag(ch);
		}else {
			ch = (ContactHolder) tempView.getTag();
		}
		ch.tv.setText(temp.getName());
		return tempView;
	}
	static class ContactHolder{
		TextView tv;
	}
	private ArrayList<ContactDealer> populateDealerList() {
		// TODO Auto-generated method stub
		ArrayList<ContactDealer> cl = new ArrayList<ContactDealer>();
		ContactDealer cd = new ContactDealer("Bimal Auto Agency","9900245982l" );
		cl.add(cd);
		cd = new ContactDealer("Garuda Auto craft P Ltd.", "08022976165l");
		cl.add(cd);
		cd = new ContactDealer("Kalyani Motors Pvt Ltd..", "08042439999");
		cl.add(cd);
		cd = new ContactDealer("LEO MOTORS PVT. LTD.", "08033400999l");
		cl.add(cd);
		cd = new ContactDealer("Mandovi Motors Pvt Ltd", "08041253434");
		cl.add(cd);
		return cl;
	}

}
