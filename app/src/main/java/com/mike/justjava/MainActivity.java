package com.mike.justjava;



import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    private int quantity;
    private int minOrder;
    private int maxOrder;
    private int basePrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quantity = getResources().getInteger(R.integer.baseAmountOfCups);
        minOrder = getResources().getInteger(R.integer.minimumAmountOfCups);
        maxOrder = getResources().getInteger(R.integer.maximumAmountOfCups);
        basePrice = getResources().getInteger(R.integer.basePrice);
    }

    public void doIncrement(View view) {
        if (++quantity > maxOrder) {
            quantity = maxOrder;
            Toast.makeText(this, getResources().getString(R.string.orderMaximum, maxOrder), Toast.LENGTH_SHORT).show();
        }
        displayQuantity();
    }

    public void doDecrement(View view) {
        if (--quantity < minOrder) {
            quantity = minOrder;
            Toast.makeText(this, getResources().getString(R.string.orderMinimum, minOrder), Toast.LENGTH_SHORT).show();
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