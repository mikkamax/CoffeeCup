package com.mike.justjava;



import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    private int quantity, price = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void doIncrement(View view) {
        quantity++;
        displayQuantity();
    }

    public void doDecrement(View view) {
        quantity--;
        if (quantity < 0) {
            quantity = 0;
        }
        displayQuantity();
    }

    private void displayQuantity() {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText(String.valueOf(quantity));
    }

    public void displayOrderSummary(View view) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(createOrderSummary());
    }

    private int calculateOrder() {
        return quantity * price;
    }

    private String createOrderSummary() {
        return "Name: Mike Pashkov\n" +
                "Quantity: " + quantity + "\n" +
                "Total: " + NumberFormat.getCurrencyInstance(Locale.US).format(calculateOrder()) + "\n" +
                "Thanks!";
    }
}