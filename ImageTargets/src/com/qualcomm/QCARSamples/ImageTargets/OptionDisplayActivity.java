package com.qualcomm.QCARSamples.ImageTargets;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class OptionDisplayActivity extends Activity implements OnClickListener {
	Intent prevIntent;
	public static final byte SWIFT = 1;
	public static final byte ALTO = 2;
	public static final byte WAGONR = 3;
	public static final byte RITZ = 4;

	public static final String SELECTED_CAR = "selectedcar";
	byte option= -1;
	Button btnSpecs, btnCompare, btnShowRoom;
	ImageButton btnPlay;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.options_menu);
		prevIntent = getIntent();
		option = prevIntent.getByteExtra(SELECTED_CAR, (byte) -1);
		btnCompare = (Button) findViewById(R.id.btnCompare);
		btnPlay = (ImageButton) findViewById(R.id.imgBtnPlay);
		btnShowRoom = (Button) findViewById(R.id.btnShowRoom);
		btnSpecs = (Button) findViewById(R.id.btnSpec);
		btnCompare.setOnClickListener(this);
		btnPlay.setOnClickListener(this);
		btnShowRoom.setOnClickListener(this);
		btnSpecs.setOnClickListener(this);



	}
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.btnCompare:
			setCompareOptions();
			break;
		case R.id.btnShowRoom:
			setShowRoomOptions();
			break;
		case R.id.btnSpec:
			setSpecificationsOptions();
			break;
		case R.id.imgBtnPlay:
			setVideoOptions();
			break;

		default:
			break;
		}

	}
	void setVideoOptions(){
		switch (option) {
		case SWIFT:
			Intent intent1 = new Intent(this,VideoPlaySwift.class);
			startActivity(intent1);
			break;
		case ALTO:
			Intent intent2 = new Intent(this,VideoPlayAlto.class);
			startActivity(intent2);
			break;
		case WAGONR:
			Intent intent3 = new Intent(this,VideoPlayWagnor.class);
			startActivity(intent3);
			break;
		case RITZ:
			Intent intent4 = new Intent(this,VideoPlayRitz.class);
			startActivity(intent4);
			break;
		default:
			break;
		}	
	}
	void setCompareOptions(){
		final Dialog d = new Dialog(this);
		d.setContentView(R.layout.compare_layout);
		Button btnDismiss = (Button) d.findViewById(R.id.btnDialogDismiss);
		ImageView compareImage = (ImageView) d.findViewById(R.id.imageView1);
		btnDismiss.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				d.dismiss();
			}
		});
		switch (option) {
		case SWIFT:
			compareImage.setImageResource(R.drawable.swift);
			break;
		case ALTO:
			compareImage.setImageResource(R.drawable.alto_k10);
			break;
		case WAGONR:
			compareImage.setImageResource(R.drawable.wagonr);
			break;
		case RITZ:
			compareImage.setImageResource(R.drawable.ritz);
			break;
		default:
			break;
		}
		d.show();

	}
	void setSpecificationsOptions(){
		final Dialog d = new Dialog(this);
		d.setContentView(R.layout.compare_layout);
		Button btnDismiss = (Button) d.findViewById(R.id.btnDialogDismiss);
		ImageView compareImage = (ImageView) d.findViewById(R.id.imageView1);
		btnDismiss.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				d.dismiss();
			}
		});
		switch (option) {
		case SWIFT:
			compareImage.setImageResource(R.drawable.swift_specifications);
			break;
		case ALTO:
			compareImage.setImageResource(R.drawable.alto_k10spec01);
			break;
		case WAGONR:
			compareImage.setImageResource(R.drawable.wagonr_spec01);
			break;
		case RITZ:
			compareImage.setImageResource(R.drawable.ritz_spec01);
			break;
		default:
			break;
		}
		d.show();

	}
	void setShowRoomOptions(){
		final Dialog d = new Dialog(this);
		d.setContentView(R.layout.showroom_details);
		TextView tvDetails = (TextView) d.findViewById(R.id.tvShowroomDetails);
		ListView lvContactList = (ListView) d.findViewById(R.id.lvContactList);
		ContactListAdapter cla = new ContactListAdapter(this);
		lvContactList.setAdapter(cla);
		final ArrayList<ContactDealer>  cl = ContactListAdapter.cl;
		
		lvContactList.setOnItemClickListener(new OnItemClickListener() {
			
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Intent.ACTION_CALL);
				i.setData(Uri.parse("tel:" + cl.get(position).contactNo));
				startActivity(i);
			}
		});
		tvDetails.setText(readTxt());
		d.show();

	}
	private String readTxt(){

		AssetManager am = getAssets();
		InputStream is = null;

		try {
			is= am.open("showroom.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(is);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		int i;
		try {
			i = is.read();
			while (i != -1)
			{
				byteArrayOutputStream.write(i);
				i = is.read();
			}
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return byteArrayOutputStream.toString();
	}



}
