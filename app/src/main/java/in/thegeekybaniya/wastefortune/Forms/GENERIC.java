package in.thegeekybaniya.wastefortune.Forms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import in.thegeekybaniya.wastefortune.POJO.Product;
import in.thegeekybaniya.wastefortune.R;

public class GENERIC extends AppCompatActivity {
    FirebaseDatabase mRoot= FirebaseDatabase.getInstance();

    DatabaseReference mProd;

    EditText etSeller, etProd, etDesc,etPrice, etType;

    String key;

    Button btnPush;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic);

        mProd= mRoot.getReference();

        mProd= mProd.child(
                "products"
        );

        etSeller= (EditText) findViewById(R.id.etSeller);
        etProd= (EditText) findViewById(R.id.etProd);
        etDesc= (EditText) findViewById(R.id.etDesc);
        etPrice= (EditText) findViewById(R.id.etPrice);
        etType= (EditText) findViewById(R.id.etType);

        btnPush= (Button) findViewById(R.id.btnPush);

        btnPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product prod= new Product(etProd.getText().toString(),etDesc.getText().toString(),Float.parseFloat(etPrice.getText().toString()),etSeller.getText().toString(),etType.getText().toString().toLowerCase());

                mProd.child(etType.getText().toString().toLowerCase()).push().setValue(prod);
            }
        });


    }
}
