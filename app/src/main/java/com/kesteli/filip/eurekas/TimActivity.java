package com.kesteli.filip.eurekas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TimActivity extends AppCompatActivity {

    private EditText etImeTima;
    private EditText etKorisnici;
    private EditText etNet;
    private EditText etVjera;
    private EditText etUlog;
    private EditText etRealno;
    private EditText etTkoUlog;
    private EditText etMotivacija;
    private EditText etPodrucja;
    private EditText etSansaUlog;
    private EditText etBrojClanova;
    private Button btnMembers;

    //Firebase setup
    private DatabaseReference databaseReference;
    private DatabaseReference childTimovi;
    private DatabaseReference childMojTim;
    SharedPreferences sharedpreferences;

    private String imeTima = "", korisnici = "", tkoUlog = "", podrucja = "";
    private int net, vjera = 0, ulog = 0, realno = 0, motivacija = 0, sansaUlog = 0, brojClanova = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim);

        setupFirebase();
        initViews();
        setupListeners();
    }

    private void setupFirebase() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    private void initViews() {
        etImeTima = (EditText) findViewById(R.id.etImeTima);
        etKorisnici = (EditText) findViewById(R.id.etKorisnici);
        etNet = (EditText) findViewById(R.id.etNet);
        etVjera = (EditText) findViewById(R.id.etVjera);
        etUlog = (EditText) findViewById(R.id.etUlog);
        etRealno = (EditText) findViewById(R.id.etRealno);
        etTkoUlog = (EditText) findViewById(R.id.etTkoUlog);
        etMotivacija = (EditText) findViewById(R.id.etMotivacija);
        etPodrucja = (EditText) findViewById(R.id.etPodrucja);
        etSansaUlog = (EditText) findViewById(R.id.etSansaUlog);
        etBrojClanova = (EditText) findViewById(R.id.etBrojClanova);
        btnMembers = (Button) findViewById(R.id.btnMembers);
    }

    private void setupListeners() {
        btnMembers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToFirebase();
                startIntent();
            }
        });
    }

    private void addToFirebase() {
        imeTima = etImeTima.getText().toString();
        korisnici = etKorisnici.getText().toString();
        net = Integer.parseInt(etNet.getText().toString());
        vjera = Integer.parseInt(etVjera.getText().toString());
        ulog = Integer.parseInt(etUlog.getText().toString());
        realno = Integer.parseInt(etRealno.getText().toString());
        tkoUlog = etTkoUlog.getText().toString();
        motivacija = Integer.parseInt(etMotivacija.getText().toString());
        podrucja = etPodrucja.getText().toString();
        sansaUlog = Integer.parseInt(etSansaUlog.getText().toString());
        brojClanova = Integer.parseInt(etBrojClanova.getText().toString());

        /*if ("".equals(net)) {
            net = 0;
        } else {
            net = Integer.parseInt(etNet.getText().toString());
        }*/

        sharedpreferences = getSharedPreferences(POJO.KEY_MOJ_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        String ime_tima_sifra = (imeTima + (int) (Math.round(Math.random() * 100.0) / 100.0 * 100));
        editor.putString(POJO.KEY_IME_TIMA, ime_tima_sifra);
        editor.commit();// commit is important here.

        try {
            editor.putInt(POJO.KEY_BROJ_CLANOVA, brojClanova);
            editor.commit();// commit is important here.
            Log.d("broj_shared", "" + sharedpreferences.getInt(POJO.KEY_BROJ_CLANOVA, 0));
        } catch (NumberFormatException nfe) {
            Log.d("broj_clanova", etBrojClanova.getText().toString());
        }

        try {
            childTimovi = databaseReference.child("Timovi");

            childMojTim = childTimovi.child(ime_tima_sifra);

            childMojTim.child("Broj korisnika koje ce zahvatiti projekt").setValue(korisnici);
            childMojTim.child("Koliko takvih projekata postoji na netu").setValue(net);
            childMojTim.child("Koliko vi vjerujete da ce projekt uspjeti").setValue(vjera);
            childMojTim.child("Koliko cete uloziti u projekt").setValue(ulog);
            childMojTim.child("Koliko je realno da ce projekt uspjeti").setValue(realno);
            childMojTim.child("Tko ce uloziti u projekt").setValue(tkoUlog);
            childMojTim.child("Koja je motivacija za projekt").setValue(motivacija);
            childMojTim.child("Checkirajte koja sva podrucja pokriva projekt").setValue(podrucja);
            childMojTim.child("Kolika je sansa da ce netko uloziti u vas projekt").setValue(sansaUlog);
            childMojTim.child("Koliko je clanova u vasem timu").setValue(brojClanova);
        } catch (DatabaseException e) {
            Log.d("Ime tima", ime_tima_sifra);
        }

    }

    private void startIntent() {
        Intent intent = new Intent(TimActivity.this, ClanActivity.class);
        startActivity(intent);
    }
}






