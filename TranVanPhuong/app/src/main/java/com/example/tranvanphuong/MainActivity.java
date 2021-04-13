package com.example.tranvanphuong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edName,edQuantity;
    private Button btADD,btView;

    ProductDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = ProductDatabase.getProductDatabase(this);
        initView();



    }
    private void initView(){
        edName = (EditText) findViewById(R.id.edName);
        edQuantity = (EditText) findViewById(R.id.edQuantity);
        btADD = (Button) findViewById(R.id.btADD);
        btView = (Button) findViewById(R.id.btView);
        btADD.setOnClickListener(this);


    }
    private void onADD(){
        if(edName.getText().toString().isEmpty()){
            Toast.makeText(this,"Please enter",Toast.LENGTH_LONG).show();
            return;
        }
        if(edQuantity.getText().toString().isEmpty()){
            Toast.makeText(this,"Please enter",Toast.LENGTH_LONG).show();
            return;
        }
        ProductEntity product = new ProductEntity();
        product.Name = edName.getText().toString();
        Integer qty = Integer.valueOf(edQuantity.getText().toString());
        product.Quantity = qty;
        db.productDao().insertProduct(product);
        Toast.makeText(getApplicationContext(),"Data Save",Toast.LENGTH_LONG).show();
        edName.setText("");
        edQuantity.setText("");
    }
    private void onView(){
        Intent intent = new Intent(MainActivity.this,DetailProduct.class);
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        if(v == btADD){
            onADD();
        }
        if(v == btView){
            onView();
        }
    }
}