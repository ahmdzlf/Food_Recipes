package com.azarcorp.food_recipes;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class DetailResepActivity extends AppCompatActivity {

    private TextView tvJudul, tvPorsi, tvDurasi, tvBahan, tvLangkah;
    private ImageView imgResep;
    private Button btnSimpan;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.isi_resep);

        // Bind views
        tvJudul = findViewById(R.id.tvJudulResep);
        tvPorsi = findViewById(R.id.tvPorsi);
        tvDurasi = findViewById(R.id.tvDurasi);
        tvBahan = findViewById(R.id.tvBahan);
        tvLangkah = findViewById(R.id.tvLangkah);
        imgResep = findViewById(R.id.imgResep);
        btnSimpan = findViewById(R.id.btnSimpan);

        // Init Firestore
        db = FirebaseFirestore.getInstance();

        // Ambil idResep dari Intent
        String idResep = getIntent().getStringExtra("idResep");

        if (idResep != null && !idResep.isEmpty()) {
            ambilDataResep(idResep);
        } else {
            Toast.makeText(this, "ID Resep tidak ditemukan", Toast.LENGTH_SHORT).show();
            finish();
        }

        btnSimpan.setOnClickListener(v -> {
            Toast.makeText(this, "Resep disimpan ke favorit", Toast.LENGTH_SHORT).show();
            // TODO: Tambahkan logika simpan resep ke database lokal atau Firebase jika diperlukan
        });
    }

    private void ambilDataResep(String idResep) {
        db.collection("resep").document(idResep)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        // Ambil dan tampilkan data dari Firestore
                        String nama = documentSnapshot.getString("nama");
                        String porsi = documentSnapshot.getString("porsi");
                        String durasi = documentSnapshot.getString("durasi");
                        String bahan = documentSnapshot.getString("bahan");
                        String langkah = documentSnapshot.getString("langkah");
                        String imageUrl = documentSnapshot.getString("imageUrl");

                        tvJudul.setText(nama != null ? nama : "Resep Tidak Dikenal");
                        tvPorsi.setText(porsi != null ? porsi : "-");
                        tvDurasi.setText(durasi != null ? durasi : "-");
                        tvBahan.setText(bahan != null ? bahan : "-");
                        tvLangkah.setText(langkah != null ? langkah : "-");

                        if (imageUrl != null && !imageUrl.isEmpty()) {
                            Picasso.get()
                                    .load(imageUrl)
                                    .placeholder(R.drawable.placeholders)
                                    .error(R.drawable.placeholders)
                                    .into(imgResep);
                        } else {
                            imgResep.setImageResource(R.drawable.placeholders);
                        }

                    } else {
                        Toast.makeText(this, "Data resep tidak ditemukan", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Gagal mengambil data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}
