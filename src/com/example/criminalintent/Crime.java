package com.example.criminalintent;



import java.util.Date;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

import android.text.format.Time;







public class Crime {
	
	private static final String JSON_ID="id";
	private static final String JSON_TITLE="title";
	private static final String JSON_SOLVED="solved";
	private static final String JSON_DATE="date";
	
	private UUID mID;
	private String mTitle;
	private Date mDate=new Date();
	private Time mTime=new Time();
	private boolean mSolved;
	
	public Crime() {
		mID=UUID.randomUUID();
	}
	
	public String getTitle() {
		return mTitle;
	}
	public void setTitle(String title) {
		mTitle = title;
	}
	public UUID getID() {
		return mID;
	}

	public boolean isSolved() {
		return mSolved;
	}

	public void setSolved(boolean solved) {
		mSolved = solved;
	}
	
	public Date getDate() {
		return mDate;
	}

	public void setDate(Date date) {
		mDate = date;
	}
	
	public Crime(JSONObject json) throws JSONException{
		mID=UUID.fromString(json.getString(JSON_ID));
		if (json.has(JSON_TITLE)) {
			mTitle=json.getString(JSON_TITLE);
		}
		mSolved=json.getBoolean(JSON_SOLVED);
		mDate=new Date(json.getLong(JSON_DATE));
	}

	public JSONObject toJSON() throws JSONException{
		JSONObject json=new JSONObject();
		json.put(JSON_ID, mID.toString());
		json.put(JSON_TITLE,mTitle);
		json.put(JSON_SOLVED,mSolved);
		json.put(JSON_DATE,mDate.getTime());
		return json;
	}
	@Override
	public String toString() {
		return mTitle;
	}
}
