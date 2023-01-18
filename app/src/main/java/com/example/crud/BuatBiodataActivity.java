package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.android.material.textfield.TextInputEditText;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class BuatBiodataActivity extends AppCompatActivity {

    protected Cursor cursor;

    DataHelper dbHelper;
    Button ton1, ton2;
    ImageView imageQr;
    TextInputEditText text1, text2, text3, text4, text5;
    MultiFormatWriter multi = new MultiFormatWriter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_biodata);

        dbHelper = new DataHelper(this);
        text1 = (TextInputEditText) findViewById(R.id.editText1);
        text2 = (TextInputEditText) findViewById(R.id.editText2);
        text3 = (TextInputEditText) findViewById(R.id.editText3);
        text4 = (TextInputEditText) findViewById(R.id.editText4);
        text5 = (TextInputEditText) findViewById(R.id.editText5);
        ton1 = findViewById(R.id.button1);
        imageQr = findViewById(R.id.image_qr);

        ton2 = (Button) findViewById(R.id.button2);

        ton1.setOnClickListener((arg0)->{
            try {
                String code = text1.getText().toString();
                BitMatrix bitMatrix = multi.encode(code, BarcodeFormat.QR_CODE, 300, 300);

                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into biodata(no, nama, tgl, jk, alamat,image)values ('"+
                        text1.getText().toString() + "','"+
                        text2.getText().toString() + "','"+
                        text3.getText().toString() + "','"+
                        text4.getText().toString() + "','"+
                        text5.getText().toString() + "','"+
                        imageQr.getImageMatrix().toShortString() + "')");

            } catch (WriterException e){
                Toast.makeText(getApplicationContext(),"Berhasil", Toast.LENGTH_SHORT).show();
            }


            MainActivity.ma.RefreshList();
            finish();
        });


        ton2.setOnClickListener((arg0)->{
            finish();
        });
    }
}