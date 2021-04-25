package com.example.intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView mImageView;
    Button mChooseBtn;

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TAMPILAN
        mImageView = findViewById(R.id.image_view);
        mChooseBtn = findViewById(R.id.choose_image_btn);

        //Untuk menangani tombol klik
        mChooseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //memeriksa izin waktu proses
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_DENIED) {
                        //izin tidak diberikan, minta
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        //tampilkan popup saat izin waktu proses
                        requestPermissions(permissions, PERMISSION_CODE);
                    }
                    else {
                        //izin sudah diberikan
                        pickImageFromGallery();
                    }
                }
                else {
                    //os sistem kurang dari marshmallow
                    pickImageFromGallery();
                }
            }
        });
    }


    private void pickImageFromGallery() {
        //Intent untuk memilih gambar
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    //menangani hasil izin waktu proses
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length >0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    //izin diberikan
                    pickImageFromGallery();
                }
                else {
                    //izin ditolak
                    Toast.makeText(this, "Izin Ditolak...!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    //menangani hasil gambar yang dipilih

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            //atur gambar ke tampilan gambar
            mImageView.setImageURI(data.getData());
        }
    }
}