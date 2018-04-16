package com.simcoder.uber;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.ArrayList;

public class DriverCalendarActivity extends AppCompatActivity {

    private Button mBack;
    private CalendarView simpleCalendarView;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_calendar);

        mBack = (Button) findViewById(R.id.back);
        simpleCalendarView = (CalendarView) findViewById(R.id.simpleCalendarView);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DriverCalendarActivity.this, DriverMapActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        simpleCalendarView.setFocusedMonthDateColor(Color.RED);
        simpleCalendarView.setUnfocusedMonthDateColor(Color.BLUE);
        simpleCalendarView.setSelectedWeekBackgroundColor(Color.RED);
        simpleCalendarView.setWeekSeparatorLineColor(Color.GREEN);
        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                String[] items = {"Rajesh", "Mahesh", "Vijayakumar"};
                showDialog();
//                Toast.makeText(getApplicationContext(), dayOfMonth + "/" + month + "/" + year, Toast.LENGTH_LONG).show();
            }
        });
    }


//    public void showDialog() {
//        dialog = new Dialog(DriverCalendarActivity.this);
//        dialog.setTitle("Thangcode.com");
//        dialog.setContentView(R.layout.dialog);
//        dialog.show();
//    }

    public void showDialog() {
        String[] datas = {"Watering", "Reporting", "Returning Tools"};
        ArrayList al = new ArrayList();
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("What task have you done today?");
//        b.setMessage("What you have done today");
        b.setMultiChoiceItems(datas, null,new DialogInterface.OnMultiChoiceClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
            }
        });
        b.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
//                finish();
                dialog.dismiss();
                Toast.makeText(DriverCalendarActivity.this, "Sent Successfully", Toast.LENGTH_SHORT).show();
            }
        });
        b.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        b.show();
//        AlertDialog.Builder b = new AlertDialog.Builder(this);
//        b.setMultiChoiceItems(datas, null,new DialogInterface.OnMultiChoiceClickListener(){
//            @Override
//            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//                //Nếu người dùng chọn
//                if (isChecked) {
//                    //Thêm người dùng người dùng chọn vào ArrayList
////                    al.add(datas[which]);
//                }
//            }
//        });
//        b.setTitle("Xác nhận");
//        b.setMessage("Bạn có đồng ý thoát chương trình không?");
//        b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
////                finish();
//                dialog.dismiss();
//            }
//        });
//        b.setNegativeButton("Không đồng ý", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                dialog.cancel();
//            }
//        });
//        AlertDialog al = b.create();
//        al.show();
    }
}
