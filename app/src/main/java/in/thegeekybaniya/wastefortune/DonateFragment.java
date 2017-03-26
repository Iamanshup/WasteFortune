package in.thegeekybaniya.wastefortune;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import in.thegeekybaniya.wastefortune.Forms.GENERIC2;
import in.thegeekybaniya.wastefortune.Forms.GENERIC3;

/**
 * Created by Kabir on 21/03/2017.
 */

public class DonateFragment extends Fragment {

    LinearLayout ngo1,ngo2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.donate_fragment, container, false);


        ngo1= (LinearLayout) v.findViewById(R.id.ngo1);

        ngo2= (LinearLayout) v.findViewById(R.id.ngo2);

        ngo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(FirebaseAuth.getInstance().getCurrentUser()==null){
                    Toast.makeText(getContext(), "Sign in First", Toast.LENGTH_SHORT).show();

                }else{


                    Intent i = new Intent(getActivity(), GENERIC2.class);
                    startActivity(i);

                }

            }
        });

        ngo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FirebaseAuth.getInstance().getCurrentUser()==null){
                    Toast.makeText(getContext(), "Sign in First", Toast.LENGTH_SHORT).show();

                }else{
                    Intent i = new Intent(getActivity(), GENERIC3.class);
                    startActivity(i);

                }



            }
        });


        // Inflate the layout for this fragment
        return v;
    }



}





