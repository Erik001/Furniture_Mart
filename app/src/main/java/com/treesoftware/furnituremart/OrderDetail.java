package com.treesoftware.furnituremart;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.treesoftware.furnituremart.data.Order;
import com.treesoftware.furnituremart.utils.CurrencyTextWatcher;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class OrderDetail extends AppCompatActivity implements View.OnClickListener {

    public static final String ORDER_STATUS_LIST = "orderStatusList";
    public static final String ORDERS_BUNDLE = "ordersBundle";
    private EditText mOrderId;
    private EditText mOrderDate;
    private AutoCompleteTextView mOrderStatus;
    private EditText mOrderCustomerDetails;
    private EditText mOrderTotalAmount;
    private SimpleDateFormat dateFormatter;
    private DatePickerDialog orderDatePickerDialog;
    private TextView storeIdView;
    private String storeId;
    private String[] status;

    public static ArrayList<Order> orderList = new ArrayList<Order>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        storeId = getIntent().getStringExtra(LoginActivity.STORE_ID);
        storeIdView = (TextView) findViewById(R.id.detail_view_storeId);
        storeIdView.setText(storeId);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        mOrderId = (EditText) findViewById(R.id.order_Id);
        mOrderDate = (EditText) findViewById(R.id.order_date);
        mOrderDate.setInputType(InputType.TYPE_NULL);
        setDateTimeField();
        mOrderStatus = (AutoCompleteTextView) findViewById(R.id.order_status);

        status = getResources().getStringArray(R.array.status_array);
        addStatusToAutoComplete(status);

        mOrderCustomerDetails = (EditText) findViewById(R.id.order_customer_details);
        mOrderTotalAmount = (EditText) findViewById(R.id.order_total_amount);

        mOrderTotalAmount.addTextChangedListener(new CurrencyTextWatcher(mOrderTotalAmount));


        Button mDisplayButton = (Button) findViewById(R.id.display_button);
        mDisplayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayOrder();
            }
        });

    }

    private void setDateTimeField() {
        mOrderDate.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        orderDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                mOrderDate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }

    @Override
    public void onClick(View view) {
        if(view == mOrderDate) {
            orderDatePickerDialog.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.action_clear:
                clearFields();
                break;

            case android.R.id.home:
                finish();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void clearFields() {
        mOrderId.setText("");
        mOrderStatus.setText("");
        mOrderDate.setText("");
        mOrderCustomerDetails.setText("");
        mOrderTotalAmount.setText("$0.00");
    }

    /**
     * Attempts to sign in.
     * If there are form errors (invalid userId, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void displayOrder() {

        // Reset errors.
        mOrderId.setError(null);
        mOrderDate.setError(null);
        mOrderStatus.setError(null);
        mOrderCustomerDetails.setError(null);
        mOrderTotalAmount.setError(null);

        // Store values at the time of the go to display screen attempt.
        String orderId = mOrderId.getText().toString();
        String orderDate = mOrderDate.getText().toString();
        String orderStatus = mOrderStatus.getText().toString();
        String orderCustomerDetails = mOrderCustomerDetails.getText().toString();
        String orderTotalAmount = mOrderTotalAmount.getText().toString();


        boolean cancel = false;
        View focusView = null;

        // Check for an existing orderId
        if (TextUtils.isEmpty(orderId)) {
            mOrderId.setError(getString(R.string.error_field_required));
            focusView = mOrderId;
            cancel = true;
        }

        // Check for an existing order date
        if (TextUtils.isEmpty(orderDate)) {
            mOrderDate.setError(getString(R.string.error_field_required));
            focusView = mOrderDate;
            cancel = true;
        }

        // Check for an existing order status
        if (TextUtils.isEmpty(orderStatus)) {
            mOrderStatus.setError(getString(R.string.error_field_required));
            focusView = mOrderStatus;
            cancel = true;
        }

        // Check for an existing order customer details
        if (TextUtils.isEmpty(orderCustomerDetails)) {
            mOrderCustomerDetails.setError(getString(R.string.error_field_required));
            focusView = mOrderCustomerDetails;
            cancel = true;
        }

        // Check for an existing order total amount
        if (TextUtils.isEmpty(orderTotalAmount)) {
            mOrderTotalAmount.setError(getString(R.string.error_field_required));
            focusView = mOrderTotalAmount;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {

            orderList.add(new Order(orderId, orderDate, orderStatus, orderCustomerDetails, orderTotalAmount, storeId));
            Intent orderStatusIntent = new Intent(this, OrderStatus.class);

            startActivity(orderStatusIntent);
        }
    }

    private void addStatusToAutoComplete(String[] stores) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter adapter =
                new ArrayAdapter(OrderDetail.this,
                        android.R.layout.simple_dropdown_item_1line, stores);

        mOrderStatus.setAdapter(adapter);
    }
}
