package com.example.yashoza.billreminder_devit;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddBillActivity extends AppCompatActivity implements View.OnClickListener{

    EditText name, amount, date, desc;
    Spinner type;
    String dueDate;
    String flag;

    private TextView monthlyText;
    private TextView biMonthlyText;
    private TextView quarterlyText;
    private TextView halfYearlyText;
    private TextView yearlyText;

    private int mYear, mMonth, mDay;

    private FirebaseAuth mAuth=FirebaseAuth.getInstance();;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference myRef = database.getReference("bills");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bill);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeAsUpIndicator(R.d);

        type=findViewById(R.id.add_type);
        date=findViewById(R.id.add_date);
        name=findViewById(R.id.add_name);
        amount=findViewById(R.id.add_amount);
        desc=findViewById(R.id.add_desc);

        monthlyText = (TextView) findViewById(R.id.bill_reminder_monthly);
        halfYearlyText = (TextView) findViewById(R.id.bill_reminder_half_yearly);
        biMonthlyText = (TextView) findViewById(R.id.bill_reminder_bi_monthly);
        yearlyText = (TextView) findViewById(R.id.bill_reminder_yearly);
        quarterlyText = (TextView) findViewById(R.id.bill_reminder_quarterly);

        monthlyText.setOnClickListener(this);
        halfYearlyText.setOnClickListener(this);
        biMonthlyText.setOnClickListener(this);
        yearlyText.setOnClickListener(this);
        quarterlyText.setOnClickListener(this);

        List<String> spinnerArray = new ArrayList<>();
        spinnerArray.add("Food");
        spinnerArray.add("Electricity");
        spinnerArray.add("Transport");
        spinnerArray.add("Water");
        spinnerArray.add("Gas");
        spinnerArray.add("Insurance");
        spinnerArray.add("Internet/Phone");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(adapter);
    }


    private void saveData(Bill bill){
        String userId=mAuth.getCurrentUser().getUid();
        String timeStamp= String.valueOf(Calendar.getInstance().getTimeInMillis());
        myRef.child(userId).child(timeStamp).setValue(bill);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.add_bill){
            //Save button
            saveData(new Bill(name.getText().toString().trim(),
                    date.getText().toString().trim(),
                    amount.getText().toString().trim(),
                    flag,
                    desc.getText().toString().trim(),
                    type.getSelectedItem().toString().trim()));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void selectDueDate(View view) {
            date.setFocusableInTouchMode(true);
            date.setFocusable(true);

            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
            dueDate = date.getText().toString();
        }

    @Override
    public void onClick(View view) {
        if (view == monthlyText ) {
            flag = "Monthly";

            monthlyText.setBackgroundColor(Color.BLUE);
            biMonthlyText.setBackgroundColor(Color.WHITE);
            halfYearlyText.setBackgroundColor(Color.WHITE);
            yearlyText.setBackgroundColor(Color.WHITE);
            quarterlyText.setBackgroundColor(Color.WHITE);

            monthlyText.setTextColor(getResources().getColor(R.color.bill_reminder_detail_activity_textView_color));
            biMonthlyText.setTextColor(getResources().getColor(R.color.bill_reminder_textView_textColor));
            halfYearlyText.setTextColor(getResources().getColor(R.color.bill_reminder_textView_textColor));
            yearlyText.setTextColor(getResources().getColor(R.color.bill_reminder_textView_textColor));
            quarterlyText.setTextColor(getResources().getColor(R.color.bill_reminder_textView_textColor));
        } else if (view == biMonthlyText) {
            flag = "Bi Monthly";

            biMonthlyText.setBackgroundColor(Color.BLUE);
            monthlyText.setBackgroundColor(Color.WHITE);
            halfYearlyText.setBackgroundColor(Color.WHITE);
            yearlyText.setBackgroundColor(Color.WHITE);
            quarterlyText.setBackgroundColor(Color.WHITE);

            biMonthlyText.setTextColor(getResources().getColor(R.color.bill_reminder_detail_activity_textView_color));
            monthlyText.setTextColor(getResources().getColor(R.color.bill_reminder_textView_textColor));
            halfYearlyText.setTextColor(getResources().getColor(R.color.bill_reminder_textView_textColor));
            yearlyText.setTextColor(getResources().getColor(R.color.bill_reminder_textView_textColor));
            quarterlyText.setTextColor(getResources().getColor(R.color.bill_reminder_textView_textColor));
        } else if (view == quarterlyText ) {
            flag = "Quarterly";

            quarterlyText.setBackgroundColor(Color.BLUE);
            monthlyText.setBackgroundColor(Color.WHITE);
            halfYearlyText.setBackgroundColor(Color.WHITE);
            yearlyText.setBackgroundColor(Color.WHITE);
            biMonthlyText.setBackgroundColor(Color.WHITE);

            quarterlyText.setTextColor(getResources().getColor(R.color.bill_reminder_detail_activity_textView_color));
            monthlyText.setTextColor(getResources().getColor(R.color.bill_reminder_textView_textColor));
            halfYearlyText.setTextColor(getResources().getColor(R.color.bill_reminder_textView_textColor));
            yearlyText.setTextColor(getResources().getColor(R.color.bill_reminder_textView_textColor));
            biMonthlyText.setTextColor(getResources().getColor(R.color.bill_reminder_textView_textColor));
        } else if (view == halfYearlyText ) {
            flag = "Half Yearly";

            halfYearlyText.setBackgroundColor(Color.BLUE);
            monthlyText.setBackgroundColor(Color.WHITE);
            biMonthlyText.setBackgroundColor(Color.WHITE);
            yearlyText.setBackgroundColor(Color.WHITE);
            quarterlyText.setBackgroundColor(Color.WHITE);

            halfYearlyText.setTextColor(getResources().getColor(R.color.bill_reminder_detail_activity_textView_color));
            monthlyText.setTextColor(getResources().getColor(R.color.bill_reminder_textView_textColor));
            quarterlyText.setTextColor(getResources().getColor(R.color.bill_reminder_textView_textColor));
            yearlyText.setTextColor(getResources().getColor(R.color.bill_reminder_textView_textColor));
            biMonthlyText.setTextColor(getResources().getColor(R.color.bill_reminder_textView_textColor));
        } else if (view == yearlyText ) {
            flag = "Yearly";

            yearlyText.setBackgroundColor(Color.BLUE);
            monthlyText.setBackgroundColor(Color.WHITE);
            halfYearlyText.setBackgroundColor(Color.WHITE);
            biMonthlyText.setBackgroundColor(Color.WHITE);
            quarterlyText.setBackgroundColor(Color.WHITE);

            yearlyText.setTextColor(getResources().getColor(R.color.bill_reminder_detail_activity_textView_color));
            monthlyText.setTextColor(getResources().getColor(R.color.bill_reminder_textView_textColor));
            quarterlyText.setTextColor(getResources().getColor(R.color.bill_reminder_textView_textColor));
            halfYearlyText.setTextColor(getResources().getColor(R.color.bill_reminder_textView_textColor));
            biMonthlyText.setTextColor(getResources().getColor(R.color.bill_reminder_textView_textColor));
        }
    }
}
