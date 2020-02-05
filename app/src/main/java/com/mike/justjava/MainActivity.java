package com.mike.justjava;



import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    private int quantity;

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
        EditText nameEditText = findViewById(R.id.name_edit_text);
        CheckBox toppingWhippedCream = findViewById(R.id.toppings_cream);
        CheckBox toppingChocolate = findViewById(R.id.toppings_chocolate);

        String customerName = nameEditText.getText().toString().trim();
        boolean hasWhippedCream = toppingWhippedCream.isChecked();
        boolean hasChocolate = toppingChocolate.isChecked();
        int total = calculateOrder(hasWhippedCream, hasChocolate);

        String orderSummary = createOrderSummary(customerName, hasWhippedCream, hasChocolate, total);
        orderSummaryTextView.setText(orderSummary);
    }

    private int calculateOrder(boolean hasWhippedCream, boolean hasChocolate) {
        int basePrice = 5;
        int price = basePrice + (hasWhippedCream ? 1 : 0) + (hasChocolate ? 2 : 0);
        return quantity * price;
    }

    private String createOrderSummary(String name, boolean hasWhippedCream, boolean hasChocolate, int total) {
        return "Name: " + name + "\n" +
                "Add whipped cream: " + hasWhippedCream + "\n" +
                "Add chocolate: " + hasChocolate + "\n" +
                "Quantity: " + quantity + "\n" +
                "Total: " + NumberFormat.getCurrencyInstance(Locale.US).format(total) + "\n" +
                "Thanks!";
    }
}