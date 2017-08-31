package com.kesteli.filip.eurekas;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class ClanActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ClanAdapter adapter;
    private List<Clan> clanList;

    private FloatingActionButton fabLogika;

    public SharedPreferences sharedpreferences;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clan);

        setupFirebase();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_clan);
        setSupportActionBar(toolbar);

        initCollapsingToolbar();
        setupRecyclerView();

        initViews();
        setupListeners();
    }

    private void initViews() {
        fabLogika = (FloatingActionButton) findViewById(R.id.fabLogika);
    }

    private void setupListeners() {
        fabLogika.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logika();
            }
        });
    }

    private void logika() {
        sharedpreferences = getSharedPreferences(POJO.KEY_MOJ_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        String ime_tima = sharedpreferences.getString(POJO.KEY_IME_TIMA, "xxx");
        Toast.makeText(this, ime_tima, Toast.LENGTH_SHORT).show();
        //TODO: Sloziti sharedPreferences
        String ime = sharedpreferences.getString(POJO.KEY_IME_CLANA, "xxx");
        String prezime = sharedpreferences.getString(POJO.KEY_PREZIME_CLANA, "xxx");
        DatabaseReference clanovi = databaseReference.child("Timovi").child(ime_tima).child("Clanovi");
        final List<String> clanList = new ArrayList<>();

        //TODO: Gettati podatke s firebasea
        Query queryRef = clanovi.orderByChild("ime");
        queryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Clan clan = dataSnapshot.getValue(Clan.class);
                Log.d("a3", dataSnapshot.getKey() + clan.getIme() + " " + clan.getGodine());
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

//                Log.d("a3", databaseReference.child("Timovi").child(ime_tima).child("Clanovi").orderByChild("Clan" + ime + prezime));

        Log.d("a1", ime);
        Log.d("a2", prezime);

/*
        childNewTeam.child("Ime").setValue(etIme.getText().toString());
        childNewTeam.child("Prezime").setValue(prezime);
        childNewTeam.child("Godine").setValue(godine);
        childNewTeam.child("Iskustvo u danoj tehnologiji").setValue(tehnoIskustvo);
        childNewTeam.child("Iskustvo opcenito").setValue(iskustvo);
        childNewTeam.child("Stupanj obrazovanja").setValue(obrazovanje);
        childNewTeam.child("Znanje u danoj tehnologiji").setValue(znanje);
        childNewTeam.child("Koliko dana covjek nije bio na poslu opcenito").setValue(dani);
        childNewTeam.child("Koliko dana covjek nije radio na danoj tehnologiji").setValue(tehnoDani);
*/
    }

    private void setupFirebase() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    private void setupRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        clanList = new ArrayList<>();
        adapter = new ClanAdapter(this, clanList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        try {
            Glide.with(this).load(R.drawable.bill_gates).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


    public class ClanAdapter extends RecyclerView.Adapter<ClanAdapter.MyViewHolder> {

        private Context mContext;
        private List<Clan> clanList;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public EditText etIme, etPrezime, etGodine, etTehnoIskustvo, etIskustvo, etObrazovanje, etZnanje, etDani, etTehnoDani;
            public Button btnPotvrdi;

            public MyViewHolder(View view) {
                super(view);
                etIme = (EditText) view.findViewById(R.id.etIme);
                etPrezime = (EditText) view.findViewById(R.id.etPrezime);
                etGodine = (EditText) view.findViewById(R.id.etGodine);
                etTehnoIskustvo = (EditText) view.findViewById(R.id.etTehnoIskustvo);
                etIskustvo = (EditText) view.findViewById(R.id.etIskustvo);
                etObrazovanje = (EditText) view.findViewById(R.id.etObrazovanje);
                etZnanje = (EditText) view.findViewById(R.id.etZnanje);
                etDani = (EditText) view.findViewById(R.id.etDani);
                etTehnoDani = (EditText) view.findViewById(R.id.etTehnoDani);
                btnPotvrdi = (Button) view.findViewById(R.id.btnPotvrdi);
            }
        }

        //Konstruktor kojim punimo listu
        public ClanAdapter(Context mContext, List<Clan> clanList) {
            this.mContext = mContext;
            this.clanList = clanList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.card_clan, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
            //TODO Staviti sve podatke u firebase
//            Clan clan = clanList.get(position);

            Log.d("kakaka", sharedpreferences.getString(POJO.KEY_IME_TIMA, "poc"));
//            Log.d("kakaka", sharedpreferences.getString(prezime, "poc"));

            holder.btnPotvrdi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        String ime, prezime, godine, tehnoIskustvo, iskustvo, obrazovanje, znanje, dani, tehnoDani;

                        ime = holder.etIme.getText().toString();
                        prezime = holder.etPrezime.getText().toString();
                        godine = holder.etGodine.getText().toString();
                        tehnoIskustvo = holder.etTehnoIskustvo.getText().toString();
                        iskustvo = holder.etIskustvo.getText().toString();
                        obrazovanje = holder.etObrazovanje.getText().toString();
                        znanje = holder.etZnanje.getText().toString();
                        dani = holder.etZnanje.getText().toString();
                        tehnoDani = holder.etTehnoDani.getText().toString();

                        sharedpreferences = getSharedPreferences(POJO.KEY_MOJ_SHARED_PREFERENCES, Context.MODE_PRIVATE);
                        final String ime_tima = sharedpreferences.getString(POJO.KEY_IME_TIMA, "poc");
                        DatabaseReference childNewTeam = databaseReference.child("Timovi").child(ime_tima).child("Clanovi").child("Clan" + ime + prezime);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString(POJO.KEY_IME_CLANA, ime);
                        editor.putString(POJO.KEY_PREZIME_CLANA, prezime);
                        editor.commit();// commit is important here.

                        Log.d("probica", sharedpreferences.getString(POJO.KEY_IME_CLANA, "poc"));

                        childNewTeam.child("Ime").setValue(holder.etIme.getText().toString());
                        childNewTeam.child("Prezime").setValue(prezime);
                        childNewTeam.child("Godine").setValue(godine);
                        childNewTeam.child("Iskustvo u danoj tehnologiji").setValue(tehnoIskustvo);
                        childNewTeam.child("Iskustvo opcenito").setValue(iskustvo);
                        childNewTeam.child("Stupanj obrazovanja").setValue(obrazovanje);
                        childNewTeam.child("Znanje u danoj tehnologiji").setValue(znanje);
                        childNewTeam.child("Koliko dana covjek nije bio na poslu opcenito").setValue(dani);
                        childNewTeam.child("Koliko dana covjek nije radio na danoj tehnologiji").setValue(tehnoDani);

                        /*//Set the values
                        Set<String> set = new HashSet<String>();

                        set.addAll(listOfExistingScores);
                        editor.putStringSet();
                        scoreEditor.putStringSet("key", set);
                        scoreEditor.commit();*/

                        DatabaseReference clanovi = databaseReference.child("Timovi").child(ime_tima).child("Clanovi");

                        //TODO: Gettati podatke s firebasea
                        Query queryRef = clanovi;
                        queryRef.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                Clan clan = dataSnapshot.getValue(Clan.class);
                                Log.d("a3", dataSnapshot.getKey() + " " + clan.getIme() + " " + clan.getGodine());
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



                    } catch (DatabaseException e) {
                        Log.d("baza clanova", "nije uspjelo");
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            sharedpreferences = getSharedPreferences(POJO.KEY_MOJ_SHARED_PREFERENCES, Context.MODE_PRIVATE);
            return sharedpreferences.getInt(POJO.KEY_BROJ_CLANOVA, 2);
        }
    }
}
