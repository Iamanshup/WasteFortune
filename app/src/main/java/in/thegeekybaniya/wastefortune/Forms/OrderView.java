package in.thegeekybaniya.wastefortune.Forms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.ui.database.ChangeEventListener;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import in.thegeekybaniya.wastefortune.POJO.Order;
import in.thegeekybaniya.wastefortune.R;

public class OrderView extends AppCompatActivity {


    FirebaseDatabase mRoot= FirebaseDatabase.getInstance();

   DatabaseReference mOrderList= mRoot.getReference(), mOrders;
    FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();

    ListView lv;

    Order model;

    private static final String TAG = "OrderView";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_view);


        lv = (ListView) findViewById(R.id.lvOrder);

        mOrders=mRoot.getReference().child("orders");

//        mOrderList = mOrderList.child("users").child(user.getEmail().replace('.', ',')).child("orders");



        final FirebaseListAdapter<Order> orderAdapter = new FirebaseListAdapter<Order>(
                this,
                Order.class,
                R.layout.order_view,
                mOrders
        ) {
            @Override
            protected void onChildChanged(ChangeEventListener.EventType type, int index, int oldIndex) {
                super.onChildChanged(type, index, oldIndex);
            }

            @Override
            protected void onDataChanged() {
                super.onDataChanged();
            }

            @Override
            protected void populateView(View v, Order model, int position) {
                TextView from, to, produ, date;

                ProgressBar progressBar;

                from = (TextView) v.findViewById(R.id.from);
                to = (TextView) v.findViewById(R.id.to);
                produ = (TextView) v.findViewById(R.id.produ);
                date = (TextView) v.findViewById(R.id.date);
                progressBar = (ProgressBar) v.findViewById(R.id.progressBar2);

                from.setText("From:" + model.getFrom());

                to.setText("To: " + model.getTo());

                produ.setText("Product: " + model.getProducts());

                if (System.currentTimeMillis() < model.getDeliveryDate()) {
                    int i = (int) (((model.getDeliveryDate() - System.currentTimeMillis()) / 90000000)+1);

                    progressBar.setProgress(25 * (4-i));

                    date.setText("Days Left: " + Integer.toString(i));

                } else {
                    progressBar.setProgress(100);
                    date.setText("Delivered");
                }
            }
        };
//
//            @Override
//            protected void populateView(View v, Order model, int position) {
//
//
//
//
//
//
//
//                TextView from, to, produ, date;
//
//                ProgressBar progressBar;
//
//                from= (TextView) v.findViewById(R.id.from);
//                to= (TextView) v.findViewById(R.id.to);
//                produ= (TextView) v.findViewById(R.id.produ);
//                date= (TextView) v.findViewById(R.id.date);
//                progressBar= (ProgressBar) v.findViewById(R.id.progressBar2);
//
//                from.setText("From:"+model.getFrom());
//
//                to.setText("To: "+model.getTo());
//
//                produ.setText("Product: "+ OrderView.this.model.getProducts());
//
//                if (System.currentTimeMillis()< OrderView.this.model.getDeliveryDate()){
//                    int i = (int)((OrderView.this.model.getDeliveryDate()-System.currentTimeMillis())/90000000);
//
//                    progressBar.setProgress(25*i);
//
//                    date.setText("Days Left: "+ Integer.toString(i) );
//
//                }else {
//                    progressBar.setProgress(100);
//                    date.setText("Delivered");
//                }
//
//
//
//            }
//        };

        lv.setAdapter(orderAdapter);






    }
}
