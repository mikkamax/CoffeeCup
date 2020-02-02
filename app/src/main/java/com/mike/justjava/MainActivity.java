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

    public void submitOrder(View view) {
        doDisplay();
    }

    public void doIncrement(View view) {
        quantity++;
        doDisplay();
    }

    public void doDecrement(View view) {
        quantity--;
        if (quantity < 0) {
            quantity = 0;
        }
        doDisplay();
    }

    public void doDisplay() {
        display(quantity);
        displayPrice(quantity * price);
    }

    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText(String.valueOf(number));
    }

    private void displayPrice(int price) {
        TextView priceTextView = findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance(Locale.US).format(price));
    }
}