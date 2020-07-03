package com.example.sibiko;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sibiko.api.ApiService;
import com.example.sibiko.api.UtilsApi;
import com.example.sibiko.data.CatatanPrestasi;
import com.example.sibiko.data.Siswa;
import com.example.sibiko.data.User;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitPrestasiSiswaActivity extends AppCompatActivity {

    private TextView nis, nama, kelas;
    private int user_id, siswa_id;
    private ImageButton btnSubmitPrestasi;
    private ImageButton btnBuktiPrestasi;
    private ApiService apiService;
    private EditText namaPrestasi;
    private ImageView previewPrestasi, back, historyPrestasi;
    private Bitmap bitmap;
    private String bukti;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_prestasi_siswa);

        nis = findViewById(R.id.nis);
        nama = findViewById(R.id.nama);
        kelas = findViewById(R.id.kelas);
        back = findViewById(R.id.back);
        namaPrestasi = findViewById(R.id.nama_prestasi);
        btnSubmitPrestasi = findViewById(R.id.btn_submit_prestasi);
        btnBuktiPrestasi = findViewById(R.id.btn_bukti_prestasi);
        previewPrestasi = findViewById(R.id.previewPrestasi);

        final User user = (User) getIntent().getSerializableExtra("user");
        Siswa siswa = (Siswa) getIntent().getSerializableExtra("siswa");

        nis.setText(siswa.getnis());
        nama.setText(siswa.getNama_siswa());
        kelas.setText(siswa.getTingkat_kelas()+" "+siswa.getKode_jurusan()+" "+siswa.getTipe_kelas());

        user.setUser_id(user.getUser_id());
        user.setNama_user(user.getNama_user());

        user_id = user.getUser_id();
        siswa_id = siswa.getSiswa_id();

        apiService = UtilsApi.getAPIService();

//        historyPrestasi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DialogPrestasiFragment myDialogFragment = new DialogPrestasiFragment();
//                Bundle bundle = new Bundle();
//                bundle.putInt("siswa_id", siswa_id);
//                myDialogFragment.setArguments(bundle);
//                myDialogFragment.show(getSupportFragmentManager(),"Prestasi");
//            }
//        });

        previewPrestasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SubmitPrestasiSiswaActivity.this, "Tekan lama gambar untuk menghapus", Toast.LENGTH_SHORT).show();
            }
        });

        previewPrestasi.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                previewPrestasi.setImageBitmap(null);
                bukti = null;
                bitmap = null;
                return true;
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnBuktiPrestasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage(SubmitPrestasiSiswaActivity.this);
            }
        });

        btnSubmitPrestasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(namaPrestasi.length()==0){
                    Toast.makeText(SubmitPrestasiSiswaActivity.this, "Catatan prestasi wajib diisi!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                String bukti = null;
                if (bitmap == null) {
                    bukti = "";
                } else {
                    bukti = getStringImage(bitmap);
                }

                Call<CatatanPrestasi> call = apiService.submitprestasi(namaPrestasi.getText().toString(),siswa_id,user_id, bukti);
                call.enqueue(new Callback<CatatanPrestasi>() {
                    @Override
                    public void onResponse(Call<CatatanPrestasi> call, Response<CatatanPrestasi> response) {
                        String value = response.body().getValue();
                        String message = response.body().getMassage();
                        if (value.equals("1")){
                            Toast.makeText(SubmitPrestasiSiswaActivity.this, message, Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(SubmitPrestasiSiswaActivity.this, WelcomeActivity.class);
                            i.putExtra("user",user);
                            startActivity(i);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<CatatanPrestasi> call, Throwable t) {
                        Toast.makeText(SubmitPrestasiSiswaActivity.this, "Jaringan Error! "+t.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    private void selectImage(Context context) {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Pilih metode");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, 1);//one can be replaced with any action code

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        bitmap = (Bitmap) data.getExtras().get("data");
                        previewPrestasi.setImageBitmap(bitmap);
                    }

                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null && data.getData() != null) {
                        Uri filePath = data.getData();
                        try {

                            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                            previewPrestasi.setImageBitmap(bitmap);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
        }
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
}
