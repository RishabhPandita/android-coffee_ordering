package com.example.rishabh.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        int quantity = Integer.parseInt(quantityTextView.getText().toString());

        CheckBox isWcTrue = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        Boolean isWhippedCream = isWcTrue.isChecked();

        CheckBox isChocTrue = (CheckBox) findViewById(R.id.choclate_checkbox);
        Boolean isChocateTrue = isChocTrue.isChecked();

        EditText name_edit = (EditText) findViewById(R.id.name_edit_text);
        String name = name_edit.getText().toString();
        display(quantity,isWhippedCream,isChocateTrue,name);


    }

    private void composeEmail(String[] addresses, String subject, String body) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_TEXT,body);

        intent.putExtra(Intent.EXTRA_EMAIL,addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT,subject);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }


    public void addOrder(View view) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        int quantity = Integer.parseInt(quantityTextView.getText().toString());
        quantity = displayQuantity(quantity + 1);
        displayQuantity(quantity);
    }


    public void subtractOrder(View view) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        int quantity = Integer.parseInt(quantityTextView.getText().toString());
        if (quantity > 0) {
            quantity = displayQuantity(quantity - 1);

        } else
            quantity = displayQuantity(0);
    }

    private void display(int quant,boolean isWC,boolean isChoc, String name) {

        int total= 0;
        int eachPrice=5;
        int whippedCream=isWC==true?2:0;
        int choc = isChoc==true?3:0;

        total = quant * (eachPrice+whippedCream+choc);

        String whipCreamText = isWC==true?getString(R.string.yes):getString(R.string.no);
        String chocText = isChoc==true?getString(R.string.yes):getString(R.string.no);

        String body = getString(R.string.ordersummary)+"\n\n" + getString(R.string.order_summary_name,name)+"\n"+getString(R.string.quantity)+ ": "+ quant +"\n"+getString(R.string.addWhipCream)+  " : "+whipCreamText+"\n"+getString(R.string.addChoclate)+" : "+chocText+"\n"+getString(R.string.total_price) +" : $"+ total;
        String addresses[]={"rishabh.pandita@gmail.com"};
        String subject = getString(R.string.subject_text);
        composeEmail(addresses,subject,body);

    }

    private int displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
        return number;
    }
}
