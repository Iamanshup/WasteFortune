package in.thegeekybaniya.wastefortune;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import in.thegeekybaniya.wastefortune.Forms.NewProduct;
import in.thegeekybaniya.wastefortune.Forms.OrderView;

/**
 * Created by Kabir on 21/03/2017.
 */

public class AccountFragment extends Fragment {
    LinearLayout opAdd, opOrders;

    RelativeLayout accOp;

    TextView tvname, tvdetail;


    FirebaseAuth auth= FirebaseAuth.getInstance();








    @Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {

    View v =inflater.inflate(R.layout.account_fragment, container, false);

        tvname= (TextView) v.findViewById(R.id.acName);

        tvdetail= (TextView) v.findViewById(R.id.acDetail);

        if (auth.getCurrentUser()==null){
            tvname.setText("Guest");
            tvdetail.setText("Tap to Login");

        }else{
            tvname.setText(auth.getCurrentUser().getDisplayName());
            tvdetail.setText("Tap to LogOut");
        }


        accOp= (RelativeLayout) v.findViewById(R.id.relativeLayout);

                accOp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent i = new Intent(getActivity(), LoginActivity.class);
                        startActivity(i);

                    }
                });

        opAdd= (LinearLayout) v.findViewById(R.id.opAdd);

        opAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(FirebaseAuth.getInstance().getCurrentUser()==null){
                    Toast.makeText(getContext(), "Sign in First", Toast.LENGTH_SHORT).show();

                }else{
                    Intent i = new Intent(getActivity(), NewProduct.class );
                    startActivity(i);

                }


            }
        });

        opOrders= (LinearLayout) v.findViewById(R.id.opOrders);

        opOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FirebaseAuth.getInstance().getCurrentUser()==null){
                    Toast.makeText(getContext(), "Sign in First", Toast.LENGTH_SHORT).show();

                }else{
                    Intent i = new Intent(getActivity(), OrderView.class);
                    startActivity(i);

                }



            }
        });





        // Inflate the layout for this fragment
    return v;
}
}
