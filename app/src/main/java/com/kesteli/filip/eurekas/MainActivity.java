package com.kesteli.filip.eurekas;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DatabaseReference databaseReference;
    private DatabaseReference childClanovi;
    private DatabaseReference childTimovi;
    SharedPreferences sharedpreferences;

    Toolbar toolbar;
    FloatingActionButton fab;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupFirebase();
        initViews();
        setupToolbar();
        setupNavigationDrawer();
        setupListeners();
        addToFirebase();
        firebaseQueryUpdateRoot();
    }

    private void setupFirebase() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    private void initViews() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
    }

    private void setupNavigationDrawer() {
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setupListeners() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToFirebase();
            }
        });
    }

    private void addToFirebase() {
        childClanovi = databaseReference.child("Clanovi");

        DatabaseReference childClan1 = childClanovi.child("Clan1");
        DatabaseReference childClan2 = childClanovi.child("Clan2");
        DatabaseReference childClan3 = childClanovi.child("Clan3");

        childClan1.child("ime").setValue("Pero");
        childClan2.child("ime").setValue("Ana");
        childClan3.child("ime").setValue("Iva");

        childClan1.child("prezime").setValue("Peric");
        childClan2.child("prezime").setValue("Anic");
        childClan3.child("prezime").setValue("Ivic");

        childClan1.child("godine").setValue(24);
        childClan2.child("godine").setValue(34);
        childClan3.child("godine").setValue(42);

        childClan1.child("tehnoIskustvo").setValue(24);
        childClan2.child("tehnoIskustvo").setValue(34);
        childClan3.child("tehnoIskustvo").setValue(42);

        childClan1.child("iskustvo").setValue(24);
        childClan2.child("iskustvo").setValue(34);
        childClan3.child("iskustvo").setValue(42);

        childClan1.child("obrazovanje").setValue(21);
        childClan2.child("obrazovanje").setValue(31);
        childClan3.child("obrazovanje").setValue(53);

        childClan1.child("znanje").setValue(32);
        childClan2.child("znanje").setValue(342);
        childClan3.child("znanje").setValue(321);

        childClan1.child("dani").setValue(24);
        childClan2.child("dani").setValue(34);
        childClan3.child("dani").setValue(42);

        childClan1.child("tehnoDani").setValue(24);
        childClan2.child("tehnoDani").setValue(34);
        childClan3.child("tehnoDani").setValue(42);

        childTimovi = databaseReference.child("Timovi");

        DatabaseReference childTim1 = childTimovi.child("Tim1");
        DatabaseReference childTim2 = childTimovi.child("Tim2");
        DatabaseReference childTim3 = childTimovi.child("Tim3");

        childTim1.child("imeTima").setValue("Pero");
        childTim1.child("korisnici").setValue("Peric");
        childTim1.child("net").setValue(232);
        childTim1.child("vjera").setValue(21);
        childTim1.child("ulog").setValue(32);
        childTim1.child("realno").setValue(312);
        childTim1.child("tkoUlog").setValue("Magdalena");
        childTim1.child("motivacija").setValue(43);
        childTim1.child("podrucja").setValue("fizika");
        childTim1.child("sansaUlog").setValue(14);
        childTim1.child("brojClanova").setValue(65);

//        firebaseQueryUpdateRoot();
    }

    private void firebaseQuery1() {
        Query queryRef1 = childClanovi.orderByChild("ime");
        queryRef1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Clan clan = dataSnapshot.getValue(Clan.class);
                Log.d("b1", dataSnapshot.getKey() + " " + clan.getIme() + " " + clan.getPrezime() + " " + clan.getGodine());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /**
     * Metoda kojom updateamo root, pa odmah i sve child rootove i krajnje value-e
     */
    private void firebaseQueryUpdateRoot() {
        childClanovi = databaseReference.child("Clanovi");
        Query queryRefUpdateRoot = childClanovi.orderByChild("prezime");

        queryRefUpdateRoot.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Clan clan = dataSnapshot.getValue(Clan.class);
                Log.d("d1", dataSnapshot.getKey() + " "
                        + clan.getIme() + " "
                        + clan.getPrezime() + " "
                        + clan.getGodine() + " "
                        + clan.getTehnoIskustvo() + " "
                        + clan.getIskustvo() + " "
                        + clan.getObrazovanje() + " "
                        + clan.getZnanje() + " "
                        + clan.getDani() + " "
                        + clan.getTehnoDani()
                );
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        childTimovi = databaseReference.child("Timovi");
        Query queryRefUpdateRootTimovi = childTimovi.orderByChild("imeTima");

        queryRefUpdateRootTimovi.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Tim tim = dataSnapshot.getValue(Tim.class);
                Log.d("d2", dataSnapshot.getKey() + " "
                        + tim.getImeTima() + " "
                        + tim.getKorisnici() + " "
                        + tim.getNet() + " "
                        + tim.getVjera() + " "
                        + tim.getUlog() + " "
                        + tim.getRealno() + " "
                        + tim.getTkoUlog() + " "
                        + tim.getMotivacija() + " "
                        + tim.getPodrucja() + " "
                        + tim.getSansaUlog() + " "
                        + tim.getBrojClanova()
                );
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
