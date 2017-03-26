package in.thegeekybaniya.wastefortune.Forms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import in.thegeekybaniya.wastefortune.POJO.Order;
import in.thegeekybaniya.wastefortune.POJO.Users;
import in.thegeekybaniya.wastefortune.R;

public class ProductView extends AppCompatActivity {

    TextView name, desc, price;

    Button addC;
    Intent i;

    FirebaseUser mUser= FirebaseAuth.getInstance().getCurrentUser();

    DatabaseReference mData= FirebaseDatabase.getInstance().getReference().child("users").child(mUser.getEmail().replace('.',','));

    DatabaseReference mOrder= FirebaseDatabase.getInstance().getReference().child("orders");

    Users u;

    private static final String TAG = "ProductView";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);



        mData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                u= dataSnapshot.getValue(Users.class);




            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        name= (TextView) findViewById(R.id.pvName);

        desc= (TextView) findViewById(R.id.pvDesc);

        price= (TextView) findViewById(R.id.pvPrice);

        addC= (Button) findViewById(R.id.pvCART);

        i = getIntent();


        i.getExtras();

        name.setText(i.getStringExtra("name"));
        desc.setText(i.getStringExtra("desc"));

        price.setText("Rs."+i.getStringExtra("price"));



        addC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "onClick: "+u.toString());

            u.AddCart(i.getStringExtra("key"));

                mData.setValue(u);

                createOrder(i.getStringExtra("seller"), mUser.getDisplayName(),i.getStringExtra("name") );


                Toast.makeText(ProductView.this, "ORDER COMPLETED SUCCESSFULLY", Toast.LENGTH_SHORT).show();








            }
        });


    }

    void createOrder(String from, String to, String prod){

        Order o = new Order(from, to, prod);

        String s = mOrder.push().getKey();

        u.AddOrder(s);

        mOrder.child(s).setValue(o);

        mData.setValue(u);







    }
}
