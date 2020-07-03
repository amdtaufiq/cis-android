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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sibiko.api.ApiService;
import com.example.sibiko.api.UtilsApi;
import com.example.sibiko.data.CatatanPelanggaran;
import com.example.sibiko.data.Pelanggaran;
import com.example.sibiko.data.Siswa;
import com.example.sibiko.data.User;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitPelanggaranSiswaActivity extends AppCompatActivity {

    private TextView nis, nama, kelas, totalpoin, keterangan, poinpelanggaran, detail, tahap;
    private EditText info;
    private int user_id, siswa_id, pelanggaran_id;
    private ImageButton btnSubmitPelanggaran;
    private ImageButton btnBukti;
    private ImageView preview, back, historyPelanggaran;
    private ApiService apiService;
    private Bitmap bitmap;
    private String bukti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_pelanggaran_siswa);
        final String[] aksi = {"+"};

        nis = findViewById(R.id.nis);
        nama = findViewById(R.id.nama);
        kelas = findViewById(R.id.kelas);
        tahap =findViewById(R.id.textViewTahap);
        detail =findViewById(R.id.textViewKeterangan);
        keterangan = findViewById(R.id.pelanggaran);
        poinpelanggaran = findViewById(R.id.poinpelanggaran);
        info = findViewById(R.id.editTextInfo);
        btnSubmitPelanggaran = findViewById(R.id.btn_submit_pelanggaran);
        btnBukti = findViewById(R.id.btn_bukti);
        preview = findViewById(R.id.preview);
        back = findViewById(R.id.back);
        RadioGroup groupAksi = findViewById(R.id.group_aksi);
        final RadioButton plus = findViewById(R.id.rd_plus);
        final RadioButton min = findViewById(R.id.rd_min);

//        historyPelanggaran.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                bundle.putString("user_id", String.valueOf(user_id));
//                DialogPelanggaranFragment myDialogFragment = new DialogPelanggaranFragment();
//                myDialogFragment.setArguments(bundle);
//                myDialogFragment.show(getSupportFragmentManager(),"Pelanggaran");
//            }
//        });

        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SubmitPelanggaranSiswaActivity.this, "Tekan lama gambar untuk menghapus", Toast.LENGTH_SHORT).show();
            }
        });

        preview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                preview.setImageBitmap(null);
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

        btnBukti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage(SubmitPelanggaranSiswaActivity.this);
            }
        });

        groupAksi.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
               if (plus.isChecked()){
                   aksi[0] = "+";
               } else if (min.isChecked()){
                   aksi[0] = "-";
               }
            }
        });

        final User user = (User) getIntent().getSerializableExtra("user");
        Siswa siswa = (Siswa) getIntent().getSerializableExtra("siswa");
        Pelanggaran pelanggaran = (Pelanggaran) getIntent().getSerializableExtra("pelanggaran");

        nis.setText(siswa.getnis());
        nama.setText(siswa.getNama_siswa());
        kelas.setText(siswa.getTingkat_kelas()+" "+siswa.getKode_jurusan()+" "+siswa.getTipe_kelas());
        keterangan.setText(pelanggaran.getNama_pelanggaran());
        tahap.setText(siswa.getTahap());
        detail.setText(siswa.getKeterangan());
        poinpelanggaran.setText(String.valueOf(pelanggaran.getPoin_pelanggaran()));

        user.setUser_id(user.getUser_id());
        user.setNama_user(user.getNama_user());

        user_id = user.getUser_id();
        siswa_id = siswa.getSiswa_id();
        pelanggaran_id = pelanggaran.getPelanggaran_id();

        apiService = UtilsApi.getAPIService();


        btnSubmitPelanggaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bukti = null;
                if (bitmap == null) {
                    bukti = "";
                } else {
                    bukti = getStringImage(bitmap);
                }

                Call<CatatanPelanggaran> call = apiService.submitpelanggaran(siswa_id,pelanggaran_id,user_id, aksi[0],bukti, info.getText().toString());
                call.enqueue(new Callback<CatatanPelanggaran>() {
                    @Override
                    public void onResponse(Call<CatatanPelanggaran> call, Response<CatatanPelanggaran> response) {
                        String value = response.body().getValue();
                        String message = response.body().getMassage();
                        if (value.equals("1")){
                            Toast.makeText(SubmitPelanggaranSiswaActivity.this, message, Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(SubmitPelanggaranSiswaActivity.this, WelcomeActivity.class);
                            i.putExtra("user",user);
                            startActivity(i);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<CatatanPelanggaran> call, Throwable t) {
                        Toast.makeText(SubmitPelanggaranSiswaActivity.this, "Jaringan Error! "+t.toString(), Toast.LENGTH_SHORT).show();
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
                        preview.setImageBitmap(bitmap);
                    }

                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null && data.getData() != null) {
                        Uri filePath = data.getData();
                        try {

                            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                            preview.setImageBitmap(bitmap);

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

    public void RadioButtonClicked(View view) {

    }
}

