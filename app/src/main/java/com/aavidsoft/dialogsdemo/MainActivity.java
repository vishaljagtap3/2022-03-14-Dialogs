package com.aavidsoft.dialogsdemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView txtData;
    private Button btnAlertDialog, btnDatePickerDialog, btnTimePickerDialog, btnLoginDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        btnAlertDialog.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                        builder.setTitle("BitCode");
                        builder.setMessage("Do you like BitCode");
                        builder.setIcon(R.mipmap.ic_launcher);

                        //builder.setCancelable(false);

                        builder.setPositiveButton("Yes", new YesListener());
                        builder.setNegativeButton("No", new NoListener());
                        builder.setNeutralButton("Don't Know", new NeutralListener());

                        builder.setOnCancelListener(
                                new DialogInterface.OnCancelListener() {
                                    @Override
                                    public void onCancel(DialogInterface dialog) {
                                        mt("Dialog cancelled");
                                    }
                                }
                        );

                        builder.setOnDismissListener(
                                new DialogInterface.OnDismissListener() {
                                    @Override
                                    public void onDismiss(DialogInterface dialog) {
                                        mt("Dialog dismissed");
                                    }
                                }
                        );
                        /*DialogInterface.OnClickListener listener =
                                new AlertDialogListener();

                        builder.setPositiveButton("Yes", listener);
                        builder.setNegativeButton("No", listener);
                        builder.setNeutralButton("Don't Know", listener);*/

                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                }
        );

        btnDatePickerDialog.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog datePickerDialog =
                                new DatePickerDialog(
                                        MainActivity.this,
                                        new MyOnDateSetListener(),
                                        2022,
                                        2,
                                        24
                                );
                        datePickerDialog.show();
                    }
                }
        );

        btnTimePickerDialog.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TimePickerDialog  timePickerDialog =
                                new TimePickerDialog(
                                        MainActivity.this,
                                        new MyOnTimeSetListener(),
                                        19,
                                        55,
                                        true
                                );
                        timePickerDialog.show();
                    }
                }
        );

        btnLoginDialog.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog loginDialog = new Dialog(MainActivity.this);
                        loginDialog.setContentView(R.layout.login_dialog);

                        Button btnLogin = loginDialog.findViewById(R.id.btnLogin);
                        EditText edtUsername = loginDialog.findViewById(R.id.edtUsername);
                        EditText edtPassword = loginDialog.findViewById(R.id.edtPassword);

                        btnLogin.setOnClickListener(
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if(edtUsername.getText().toString().equals("bitcode")) {
                                            mt("Success");
                                            loginDialog.dismiss();
                                        }
                                        else {
                                            mt("Login failed...");
                                        }
                                    }
                                }
                        );

                        loginDialog.show();
                    }
                }
        );
    }

    private class MyOnTimeSetListener implements TimePickerDialog.OnTimeSetListener {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            txtData.setText(hourOfDay + " : " + minute);
        }
    }

    private class MyOnDateSetListener implements DatePickerDialog.OnDateSetListener{
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            txtData.setText(year + " " + (month+1) + " " + dayOfMonth);
        }
    }

    private class YesListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            mt("Yes");
        }
    }

    private class NoListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            mt("No");
        }
    }

    private class NeutralListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            mt("Don't know");
        }
    }

    private class AlertDialogListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    mt("You said yes");
                    yesFunction();
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    mt("You said no");
                    break;
                case DialogInterface.BUTTON_NEUTRAL:
                    mt("You said nothing");
                    break;
            }

        }
    }

    private void yesFunction() {

    }

    private void mt(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    private void init() {
        txtData = findViewById(R.id.txtData);
        btnLoginDialog = findViewById(R.id.btnLoginDialog);
        btnAlertDialog = findViewById(R.id.btnAlertDialog);
        btnDatePickerDialog = findViewById(R.id.btnDatePickerDialog);
        btnTimePickerDialog = findViewById(R.id.btnTimePickerDialog);
    }
}