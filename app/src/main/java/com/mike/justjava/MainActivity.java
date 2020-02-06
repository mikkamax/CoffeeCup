package com.mike.justjava;



import android.content.Intent;
import android.net.Uri;
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

    public void submitOrder(View view) {
        EditText nameEditText = findViewById(R.id.name_edit_text);
        CheckBox toppingWhippedCream = findViewById(R.id.toppings_cream);
        CheckBox toppingChocolate = findViewById(R.id.toppings_chocolate);

        String customerName = nameEditText.getText().toString().trim();
        boolean hasWhippedCream = toppingWhippedCream.isChecked();
        boolean hasChocolate = toppingChocolate.isChecked();
        int total = calculateOrder(hasWhippedCream, hasChocolate);

        String orderSummary = createOrderSummary(customerName, hasWhippedCream, hasChocolate, total);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto: " + getResources().getString(R.string.orderEmail)));
        intent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.orderSubject));
        intent.putExtra(Intent.EXTRA_TEXT, orderSummary);


        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private int calculateOrder(boolean hasWhippedCream, boolean hasChocolate) {
        int price = basePrice + (hasWhippedCream ? 1 : 0) + (hasChocolate ? 2 : 0);
        return quantity * price;
    }

    private String createOrderSummary(String name, boolean hasWhippedCream, boolean hasChocolate, int total) {
        return  getString(R.string.nameForTheOrder, name) + "\n" +
                getString(R.string.add_cream) + ": " + (hasWhippedCream ? getString(R.string.yes) : getString(R.string.no)) + "\n" +
                getString(R.string.add_choco) + ": " + (hasChocolate  ? getString(R.string.yes) : getString(R.string.no)) + "\n" +
                getString(R.string.quantity) + ": " + quantity + "\n" +
                getString(R.string.total) + ": " + NumberFormat.getCurrencyInstance(Locale.US).format(total) + "\n" +
                getString(R.string.thanks);
    }
}