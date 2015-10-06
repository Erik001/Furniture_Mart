package com.treesoftware.furnituremart.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by eriktorres on 10/6/15.
 */
public class Order implements Serializable {

    private String orderId;

    private String orderDate;

    private String orderStatus;

    private String orderCustomerDetails;

    private String orderTotalAmount;

    private String storeId;

    public Order(String orderId, String orderDate, String orderStatus, String orderCustomerDetails, String orderTotalAmount, String storeId) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.orderCustomerDetails = orderCustomerDetails;
        this.orderTotalAmount = orderTotalAmount;
        this.storeId = storeId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public String getOrderCustomerDetails() {
        return orderCustomerDetails;
    }

    public String getOrderTotalAmount() {
        return orderTotalAmount;
    }

    public String getStoreId() {
        return storeId;
    }

}
