package in.thegeekybaniya.wastefortune.Forms;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import in.thegeekybaniya.wastefortune.POJO.Product;
import in.thegeekybaniya.wastefortune.R;

public class GENERIC extends AppCompatActivity {
    FirebaseDatabase mRoot= FirebaseDatabase.getInstance();


    DatabaseReference mProd;

    EditText etSeller, etProd, etDesc,etPrice, etType;

    String key;

    Button btnPush;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    ImageButton img;

    private ProgressDialog progressDialog;
    private StorageReference storage;

    String mCurrentPhotoPath;
    Uri photoURI;

    StorageReference filepath;

    Spinner spinnerType;






    @Override
    protected void onCreate(Bundle savedInstanceState) {










        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic);





        spinnerType= (Spinner) findViewById(R.id.spinnerType);

        List<String> typeList= new ArrayList<>();
        typeList.add("laptop");
        typeList.add("other");
        typeList.add("mobile");
        typeList.add("tv");

        final ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, typeList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(spinnerAdapter);










        storage = FirebaseStorage.getInstance().getReference();

        mProd= mRoot.getReference();

        mProd= mProd.child(
                "products"
        );

        etSeller= (EditText) findViewById(R.id.etSeller);
        etProd= (EditText) findViewById(R.id.etProd);
        etDesc= (EditText) findViewById(R.id.etDesc);
        etPrice= (EditText) findViewById(R.id.etPrice);
        img= (ImageButton) findViewById(R.id.imageView6);


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });


        btnPush= (Button) findViewById(R.id.btnPush);

        btnPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product prod= new Product(etProd.getText().toString(),etDesc.getText().toString(),Float.parseFloat(etPrice.getText().toString()),etSeller.getText().toString(),spinnerType.getSelectedItem().toString());

                mProd.child(spinnerType.getSelectedItem().toString()).push().setValue(prod);
            }
        });


    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePictureIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY,1);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        OutputStream os = null;
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            progressDialog=new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();



            File file = new File(mCurrentPhotoPath);


            Uri uri = Uri.fromFile(file);
            filepath = storage.child("Photos").child(uri.getLastPathSegment());


            filepath.putFile(getImageUri(getApplicationContext(), BitmapFactory.decodeFile(file.getAbsolutePath()))).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getApplicationContext(), "Upload Successful!", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), "Upload Failed!", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 50, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }


}
