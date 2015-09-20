package com.example.criminalintent;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

public class TimePickerFragment extends DialogFragment {

	private Date mDate;
	public static final String EXTRA_TIME="com.example.criminalintent.time";
	public static TimePickerFragment newInstance(Date date)  {
		Bundle args=new Bundle();
		args.putSerializable(EXTRA_TIME,date);
		TimePickerFragment fragment=new TimePickerFragment();
		fragment.setArguments(args);
		return fragment;
	}
	private void sendResult(int resultCode){
		if (getTargetFragment()==null) {
			return;
		}
		Intent i = new Intent();
		i.putExtra(EXTRA_TIME,mDate);
		getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		mDate=(Date) getArguments().getSerializable(EXTRA_TIME);
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(mDate);
		int hour=calendar.get(Calendar.HOUR);
		int second=calendar.get(Calendar.SECOND);
		
		
		
		View v=getActivity().getLayoutInflater().inflate(R.layout.dialog_time, null);
		
		TimePicker timePicker=(TimePicker) v.findViewById(R.id.dialog_time_datePicker);
		timePicker.setIs24HourView(true);
		timePicker.setOnTimeChangedListener(new OnTimeChangedListener() {
			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				mDate.setHours(hourOfDay);
				mDate.setMinutes(minute);
				getArguments().putSerializable(EXTRA_TIME,mDate);
			
			
			}
		});
		
		
		
		return new AlertDialog.Builder(getActivity())
				.setView(v)
				.setTitle(R.string.time_picker_title)
				.setPositiveButton(android.R.string.ok,new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						sendResult(Activity.RESULT_OK);
					}
				})
				.create();
	}
}
