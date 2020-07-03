package com.example.sibiko;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sibiko.data.User;

public class WelcomeActivity extends AppCompatActivity {

    private TextView mUser;
    private Button btnLogout;
    String level;

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        final User user = (User) getIntent().getSerializableExtra("user");
        user.setUser_id(user.getUser_id());
        user.setNama_user(user.getNama_user());
        user.setLevel(user.getLevel());

        mUser = (TextView) findViewById(R.id.tv_welcome);
        mUser.setText(user.getNama_user());

        level = user.getLevel().toString();

        btnLogout = findViewById(R.id.button_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(WelcomeActivity.this);
                builder.setCancelable(false);
                builder.setMessage("Anda yakin keluar?");
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                        finish();
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });

        Button mBtnPelanggaran = findViewById(R.id.btn_pelanggaran);
        Button mBtnPrestasi = findViewById(R.id.btn_prestasi);

        // TODO: 26/12/2019 intent pelanggaran
        mBtnPelanggaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, SearchPelanggaranActivity.class);
                user.setUser_id(user.getUser_id());
                user.setNama_user(user.getNama_user());
                user.setLevel(user.getLevel());
                intent.putExtra("user",user);
                user.setLevel(user.getLevel());

                startActivity(intent);
            }
        });

        // TODO: 26/12/2019 intent prestasi
        mBtnPrestasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, SearchPrestasiActivity.class);
                user.setUser_id(user.getUser_id());
                user.setNama_user(user.getNama_user());
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });
    }
}
