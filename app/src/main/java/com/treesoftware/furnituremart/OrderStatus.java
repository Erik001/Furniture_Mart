package com.treesoftware.furnituremart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

        layout = (LinearLayout) findViewById(R.id.table_container);

        orderList = OrderDetail.orderList;

        TableLayout tl = new TableLayout(this);

        int colorCounter = 0;
        for (Order order : orderList) {
            TableRow tr = new TableRow(this);

            TextView orderId = new TextView(this);
            orderId.setText(order.getOrderId());
            orderId.setPadding(60, 60, 60, 60);
            tr.addView(orderId);

            TextView orderDate = new TextView(this);
            orderDate.setText(order.getOrderDate());
            orderDate.setPadding(60, 60, 60, 60);
            tr.addView(orderDate);

            if (colorCounter % 2 == 0)
                tr.setBackgroundColor(Color.GRAY);
            else
                tr.setBackgroundColor(Color.LTGRAY);

            tl.addView(tr);

            ++colorCounter;
        }

        layout.addView(tl);



    }

}
