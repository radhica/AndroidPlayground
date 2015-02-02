package com.firoze.androidplayground.activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.firoze.androidplayground.R;
import com.firoze.baseClasses.BaseActivity;
import com.firoze.helpers.Helpers;

import java.util.ArrayList;
import java.util.Calendar;

public class PickersActivity extends BaseActivity {

    private TextView txtPickerOutput;

    @Override
    public String getTag() {
        return "PickersActivity";
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_pickers;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        txtPickerOutput = (TextView) findViewById(R.id.txtPickerOutput);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pickers, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showSimpleTimePicker(View v) {
        TimePickerDialog.OnTimeSetListener myTimePickerCallback = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                txtPickerOutput.setText(Helpers.getZeroPaddedNum(hourOfDay) + ":" + Helpers.getZeroPaddedNum(minute));
            }
        };

        new TimePickerDialog(this,
                TimePickerDialog.THEME_DEVICE_DEFAULT_DARK,
                myTimePickerCallback,
                Calendar.getInstance().get(Calendar.HOUR),
                Calendar.getInstance().get(Calendar.MINUTE),
                false).show();
    }

    public void showSimpleDatePicker(View v) {
        DatePickerDialog.OnDateSetListener myDatePickerCallback = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                txtPickerOutput.setText(
                        year + "/" + Helpers.getZeroPaddedNum(monthOfYear + 1) + "/" + Helpers.getZeroPaddedNum(dayOfMonth)
                );
            }
        };

        new DatePickerDialog(this,
                DatePickerDialog.THEME_DEVICE_DEFAULT_DARK,
                myDatePickerCallback,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)).show();
    }

    @SuppressWarnings("InflateParams")
    public void showCombinedDateTimePicker(View v) {
        View multiPickerLayout = LayoutInflater.from(this).inflate(R.layout.dialog_pickers, null);
        final DatePicker multiPickerDate = (DatePicker) multiPickerLayout.findViewById(R.id.multipicker_date);
        final TimePicker multiPickerTime = (TimePicker) multiPickerLayout.findViewById(R.id.multipicker_time);

        DialogInterface.OnClickListener dialogButtonListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch(which) {
                    case DialogInterface.BUTTON_NEGATIVE: {
                        dialog.dismiss();
                        break;
                    }
                    case DialogInterface.BUTTON_POSITIVE: {
                        txtPickerOutput.setText(
                                multiPickerDate.getYear() + "/" + Helpers.getZeroPaddedNum(multiPickerDate.getMonth() + 1) + "/" + Helpers.getZeroPaddedNum(multiPickerDate.getDayOfMonth())
                                + " - " + Helpers.getZeroPaddedNum(multiPickerTime.getCurrentHour()) + ":" + Helpers.getZeroPaddedNum(multiPickerTime.getCurrentMinute())
                        );
                        break;
                    }
                    default: {
                        dialog.dismiss();
                        break;
                    }
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
        builder.setView(multiPickerLayout);
        builder.setPositiveButton("Set", dialogButtonListener);
        builder.setNegativeButton("Cancel", dialogButtonListener);
        builder.show();
    }

    public void showCustomNumberPicker(View v) {
        NumberPicker myNumberPicker = new NumberPicker(this);
        myNumberPicker.setMaxValue(10);
        myNumberPicker.setMinValue(0);

        NumberPicker.OnValueChangeListener myValueChangedListener = new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                txtPickerOutput.setText("Value: " + newVal);
            }
        };

        myNumberPicker.setOnValueChangedListener(myValueChangedListener);

        new AlertDialog.Builder(this).setView(myNumberPicker).show();
    }

    public void showCustomStringPicker(View v) {
        final String genders[] = { "Male", "Female" };

        NumberPicker myNumberPicker = new NumberPicker(this);
        myNumberPicker.setMinValue(0);
        myNumberPicker.setMaxValue(genders.length - 1);
        myNumberPicker.setDisplayedValues(genders);

        NumberPicker.OnValueChangeListener myValueChangedListener = new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                txtPickerOutput.setText("Value: " + genders[newVal]);
            }
        };

        myNumberPicker.setOnValueChangedListener(myValueChangedListener);

        new AlertDialog.Builder(this).setView(myNumberPicker).show();
    }

    public void showCustomIntervalPicker(View v) {
        final ArrayList<String> numbersAsStrings = new ArrayList<>();

        for (int i = 0; i <= 10; i++) {
            numbersAsStrings.add(String.valueOf(i * 10));
        }

        NumberPicker myNumberPicker = new NumberPicker(this);
        myNumberPicker.setMinValue(0);
        myNumberPicker.setMaxValue(numbersAsStrings.size() - 1);
        myNumberPicker.setDisplayedValues(numbersAsStrings.toArray(new String[numbersAsStrings.size()]));

        NumberPicker.OnValueChangeListener myValueChangedListener = new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                txtPickerOutput.setText("Value: " + numbersAsStrings.get(newVal));
            }
        };

        myNumberPicker.setOnValueChangedListener(myValueChangedListener);

        new AlertDialog.Builder(this).setView(myNumberPicker).show();
    }
}
