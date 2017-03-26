package in.thegeekybaniya.wastefortune.Forms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import in.thegeekybaniya.wastefortune.R;

public class NewProduct extends AppCompatActivity {

    Button btnLap, btnTv, btnMob;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);

        btnLap= (Button) findViewById(R.id.button5);

        btnTv= (Button) findViewById(R.id.button6);

        btnMob= (Button) findViewById(R.id.button7);


        btnLap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NewProduct.this,GENERIC.class);
                startActivity(i);
            }
        });

        btnTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NewProduct.this,GENERIC.class);
                startActivity(i);
            }
        });

        btnMob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NewProduct.this,GENERIC.class);
                startActivity(i);
            }
        });


    }
}
