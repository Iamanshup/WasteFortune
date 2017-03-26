package in.thegeekybaniya.wastefortune;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import in.thegeekybaniya.wastefortune.POJO.Users;

public class LoginActivity extends AppCompatActivity {


    public static final int RC_SIGN_IN= 0;

    private static final String TAG = "LoginActivity";
    FirebaseAuth auth= FirebaseAuth.getInstance();

    FirebaseDatabase mRoot=FirebaseDatabase.getInstance();

    DatabaseReference mUdata= mRoot.getReference().child("users");

    DatabaseReference MData;


    Button signOUt;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


       if (auth.getCurrentUser()!=null){
           Toast.makeText(this, "LOGGED IN ALREADY", Toast.LENGTH_SHORT).show();

       } else{
           startActivityForResult(AuthUI.getInstance()
                           .createSignInIntentBuilder()
                           .setProviders(
                                   AuthUI.GOOGLE_PROVIDER,
                                   AuthUI.FACEBOOK_PROVIDER,
                                   AuthUI.EMAIL_PROVIDER
                           ).build(),
                   RC_SIGN_IN
           );
       }


       signOUt = (Button) findViewById(R.id.btnOUT);

        signOUt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();

                AuthUI.getInstance()
                        .signOut(getParent())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Log.d(TAG, "onComplete: SIGNING OUT");


                                FirebaseAuth.getInstance().signOut();


                                finish();
                            }
                        });
            }
        });







    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode==RC_SIGN_IN){
            if(resultCode== RESULT_OK){
                Log.d(TAG, "onActivityResult: LOGGED IN"+ auth.getCurrentUser().getEmail());

                final FirebaseUser mAuth= auth.getCurrentUser();

                MData=mUdata.child(mAuth.getEmail().replace('.',','));

                mUdata.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (!snapshot.hasChild(mAuth.getEmail().replace('.',','))) {
                            Users user= new Users(mAuth.getDisplayName(), mAuth.getEmail(), mAuth.getPhotoUrl().toString());


//                            mUdata=mUdata.child(mAuth.getEmail().replace('.',','));
                            MData.setValue(user);

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });






            }else {
                Log.d(TAG, "onActivityResult: NOT LOGGED IN");
            }
        }
    }
}
