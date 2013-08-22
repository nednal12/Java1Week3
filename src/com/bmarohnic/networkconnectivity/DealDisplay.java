package com.bmarohnic.networkconnectivity;

import android.content.Context;
import android.widget.GridLayout;
import android.widget.TextView;

public class DealDisplay extends GridLayout{
	
	TextView _name;
	TextView _dealSource;
	TextView _address;
	TextView _address2;
	TextView _state;
	TextView _city;
	TextView _ZIP;
	TextView _dealTitle;
	TextView _dealinfo;
	TextView _postDate;
	TextView _expirationDate;
	Context _context;
	
	public DealDisplay(Context context){
		super(context);
		
		_context = context;
		
		this.setColumnCount(2);
		
		TextView nameLabel = new TextView(_context);
		nameLabel.setText("Name: ");
		_name = new TextView(_context);
		
		TextView dealSourceLabel = new TextView(_context);
		dealSourceLabel.setText("Deal Source: ");
		_dealSource = new TextView(_context);
		
		TextView addressLabel = new TextView(_context);
		addressLabel.setText("Address: ");
		_address = new TextView(_context);
		
		TextView address2Label = new TextView(_context);
		address2Label.setText("Address 2: ");
		_address2 = new TextView(_context);
		
		TextView stateLabel = new TextView(_context);
		stateLabel.setText("State: ");
		_state = new TextView(_context);
		
		TextView cityLabel = new TextView(_context);
		cityLabel.setText("City: ");
		_city = new TextView(_context);
		
		TextView ZIPLabel = new TextView(_context);
		ZIPLabel.setText("ZIP: ");
		_ZIP = new TextView(_context);
		
		TextView dealTitleLabel = new TextView(_context);
		dealTitleLabel.setText("Deal Title: ");
		_dealTitle = new TextView(_context);
		
		TextView dealinfoLabel = new TextView(_context);
		dealinfoLabel.setText("Deal Info: ");
		_dealinfo = new TextView(_context);
		
		TextView postDateLabel = new TextView(_context);
		postDateLabel.setText("Post Date: ");
		_postDate = new TextView(_context);
		
		TextView expirationDateLabel = new TextView(_context);
		expirationDateLabel.setText("Expiration Date: ");
		_expirationDate = new TextView(_context);
		
		this.addView(nameLabel);
		this.addView(_name);
		
		this.addView(dealSourceLabel);
		this.addView(_dealSource);
		
		this.addView(addressLabel);
		this.addView(_address);
		
		this.addView(address2Label);
		this.addView(_address2);
		
		this.addView(cityLabel);
		this.addView(_city);
		
		this.addView(stateLabel);
		this.addView(_state);
		
		this.addView(ZIPLabel);
		this.addView(_ZIP);
		
		this.addView(dealTitleLabel);
		this.addView(_dealTitle);
		
		this.addView(dealinfoLabel);
		this.addView(_dealinfo);
		
		this.addView(postDateLabel);
		this.addView(_postDate);
		
		this.addView(expirationDateLabel);
		this.addView(_expirationDate);
		
	}
	
}
