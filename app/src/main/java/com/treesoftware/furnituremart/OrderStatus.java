package com.treesoftware.furnituremart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.treesoftware.furnituremart.data.Order;

import java.util.ArrayList;

public class OrderStatus extends AppCompatActivity {

    LinearLayout layout;
    private ArrayList<Order> orderList = new ArrayList<Order>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        layout = (LinearLayout) findViewById(R.id.table_container);

        orderList = OrderDetail.orderList;

        TableLayout tl = new TableLayout(this);

        TableRow tr = new TableRow(this);

        TextView orderIdHead = new TextView(this);
        orderIdHead.setText(R.string.prompt_order_id);
        orderIdHead.setPadding(60, 60, 60, 60);
        tr.addView(orderIdHead);

        TextView orderDateHead = new TextView(this);
        orderDateHead.setText(R.string.prompt_order_date);
        orderDateHead.setPadding(60, 60, 60, 60);
        tr.addView(orderDateHead);


        TextView orderStatusHead = new TextView(this);
        orderStatusHead.setText(R.string.prompt_order_status);
        orderStatusHead.setPadding(60, 60, 60, 60);
        tr.addView(orderStatusHead);

        TextView orderCustomerDetailsHead = new TextView(this);
        orderCustomerDetailsHead.setText(R.string.prompt_customer_details);
        orderCustomerDetailsHead.setPadding(60, 60, 60, 60);
        tr.addView(orderCustomerDetailsHead);


        TextView orderTotalAmountHead = new TextView(this);
        orderTotalAmountHead.setText(R.string.prompt_total_amount);
        orderTotalAmountHead.setPadding(60, 60, 60, 60);
        tr.addView(orderTotalAmountHead);

        tr.setBackgroundColor(Color.GRAY);

        tl.addView(tr);

        int colorCounter = 0;
        for (Order order : orderList) {
            tr = new TableRow(this);

            TextView orderId = new TextView(this);
            orderId.setText(order.getOrderId());
            orderId.setPadding(60, 60, 60, 60);
            tr.addView(orderId);

            TextView orderDate = new TextView(this);
            orderDate.setText(order.getOrderDate());
            orderDate.setPadding(60, 60, 60, 60);
            tr.addView(orderDate);


            TextView orderStatus = new TextView(this);
            orderStatus.setText(order.getOrderStatus());
            orderStatus.setPadding(60, 60, 60, 60);
            tr.addView(orderStatus);

            TextView orderCustomerDetails = new TextView(this);
            orderCustomerDetails.setText(order.getOrderCustomerDetails());
            orderCustomerDetails.setPadding(60, 60, 60, 60);
            tr.addView(orderCustomerDetails);


            TextView orderTotalAmount = new TextView(this);
            orderTotalAmount.setText(order.getOrderTotalAmount());
            orderTotalAmount.setPadding(60, 60, 60, 60);
            tr.addView(orderTotalAmount);

            if (colorCounter % 2 == 0)
                tr.setBackgroundColor(Color.WHITE);
            else
                tr.setBackgroundColor(Color.LTGRAY);

            tl.addView(tr);

            ++colorCounter;
        }

        layout.addView(tl);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
