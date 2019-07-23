package com.example.tanush.registrationmodule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class WorkshopRegistrationActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout mDrawerLayout;
    Button ok;
    CheckBox c1,c2;

    boolean a = false, b = false, c = false, d = false, e = false;
    TextInputEditText par1, par2, vol, mail, contact, collegeName;
    int amount = 0;
    String am,am1;
    CheckBox fe, se, te, be;
    CheckBox bugOffI, bugOffT, justCodingI, justCodingT, reCodeItI, reCodeItT, codeBuddy, dataQuest, webAppDev, electroQuest, dextrous, photoShopRoyale, quiz2Bid, insight, cerebroI, cerebroT, GOTI, GOTT, friendsI, friendsT, HPI, HPT, marvelI, marvelT, DCI, DCT;
    //CheckBox codebuddy,dataquest,webappdev,electroquest,dextrous,photoshoproyale,quiz2bid,insight,got,harrypotter,friends,marveldc;
    Button submit;
    TextView fcost;
    ArrayList<String> arrayList=new ArrayList<>();
    TextInputLayout t1,t2,t3,t4,t5,t6;
    //private DocumentReference documentReference;
    //private CollectionReference WorkshopReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workshop_registration);

        //Set the toolbar as the app bar
        Toolbar toolbar =findViewById(R.id.toolbar) ;
        setSupportActionBar(toolbar);

        //Display the menu toggle icon on the app bar
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        }


        FirebaseApp.initializeApp(WorkshopRegistrationActivity.this);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        par1 = findViewById(R.id.participant1);
        par2 = findViewById(R.id.participant2);
        vol =  findViewById(R.id.volunteer);
        mail = findViewById(R.id.email);
        contact = findViewById(R.id.contact);
        collegeName = findViewById(R.id.college);

        par1.setEnabled(true);
        ok=findViewById(R.id.btn_Ok);
        mDrawerLayout =  findViewById(R.id.drawer_layout);
        NavigationView navigationView =findViewById(R.id.nav_view);


        t1=findViewById(R.id.text_input_volunteer);
        t2=findViewById(R.id.text_input_par1);
        t3=findViewById(R.id.text_input_par2);
        t4=findViewById(R.id.text_input_mail);
        t5=findViewById(R.id.text_input_contact);
        t6=findViewById(R.id.text_input_college);

        final CollectionReference WorkshopReference = db.collection("Workshop");

        fcost = findViewById(R.id.amount);
        fe = findViewById(R.id.fe);
        se = findViewById(R.id.se);
        te = findViewById(R.id.te);
        be = findViewById(R.id.be);
        //Registers the NavigationItemSelectedListener to the navigation view
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggler = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggler);
        toggler.syncState();
        int hack=0;
        int iot=0;
        c1=findViewById(R.id.hack);
        c2=findViewById(R.id.iot);
        if(c1.isChecked())
        {
            amount+=1500;
            hack=1;
            arrayList.add("Ethical_Hacking");
        }

        if(c2.isChecked())
        {
            amount+=1500;
            iot=1;
            arrayList.add("IOT");
        }


        final int finalHack = hack;
        final int finalIot = iot;
        ok.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {

                if (!funvol()) {
                    Toast.makeText(WorkshopRegistrationActivity.this, "Enter Required Fields", Toast.LENGTH_SHORT).show();
                    t1.setError("Mandatory");
                }
                if (!funpar1()) {
                    Toast.makeText(WorkshopRegistrationActivity.this, "Enter Required Fields", Toast.LENGTH_SHORT).show();
                    t2.setError("Mandatory");
                }
                if (!funmail()) {
                    Toast.makeText(WorkshopRegistrationActivity.this, "Enter Required Fields", Toast.LENGTH_SHORT).show();
                    t4.setError("Mandatory");
                }
                if (!funcon()) {
                    Toast.makeText(WorkshopRegistrationActivity.this, "Enter Required Fields", Toast.LENGTH_SHORT).show();
                    t5.setError("Mandatory");
                }
                if (!funcol()) {
                    Toast.makeText(WorkshopRegistrationActivity.this, "Enter Required Fields", Toast.LENGTH_SHORT).show();
                    t6.setError("Mandatory");
                }

                if(funcol()&&funcon()&&funpar1()&&funmail()&&funvol()) {
                    final AlertDialog.Builder mAlertDialog = new AlertDialog.Builder(WorkshopRegistrationActivity.this);
                    final View mView = getLayoutInflater().inflate(R.layout.activity_workshop_password, null);
                    mAlertDialog.setView(mView);
                    Button verify = mView.findViewById(R.id.verify);
                    final EditText password = mView.findViewById(R.id.etPassword);
                    final TextView textView = mView.findViewById(R.id.textView);
                    final EditText paying = mView.findViewById(R.id.etPaying);
                    String temp = String.valueOf(amount);
                    //textView.setText("To pay:- " + amount);
                    textView.setText("To Pay:- " + temp);
                    final AlertDialog dialog = mAlertDialog.create();
                    dialog.show();
                    verify.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (password.getText().toString().equals("PASC"))
                            {
                                final String participant1 = par1.getText().toString();
                                //final String participant2 = par2.getText().toString();
                                final String volunteer = vol.getText().toString();
                                final String email = mail.getText().toString();
                                final String contactno = contact.getText().toString();
                                final String college = collegeName.getText().toString();
                                if (finalHack == 1||finalIot == 1) {
                                    WorkshopUser workshopUser = new WorkshopUser(participant1,volunteer,email,contactno,college,"null",arrayList,Integer.parseInt(fcost.getText().toString()) );
                                    WorkshopReference.add(workshopUser).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            //Toast.makeText(EventRegistrationActivity.this, "", Toast.LENGTH_SHORT).show();
                                            Toast.makeText(WorkshopRegistrationActivity.this, "User Entered Into Database", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                }
                                    Resources res = getResources();
                                    String dontreply = String.format(res.getString(R.string.donotreply));
                                    String email1 = mail.getText().toString();
                                    String subject = "Pulzion'19 : E-Receipt generated for your registration";
                                    String message = "Dear " + par1.getText().toString() + "\n\nGreetings from PASC!! \n\nThank you for registering for Pulzion'19. " + "Your details have been recorded and corresponding payment received." + "\n\n You can download your receipt from the My Receipts " + "section of the Pulzion'19 app using your unique Registration ID." + "\n\nOur app can be downloaded from: --------" + "Please feel free to reach out to us in case of doubts or difficulty.\nTanush Maddiwar: 7015032436\nAnother Contact: 0000000000" + "\n\nAll the Best!!\nRegards,\nPICT ACM Student Chapter\n\n\n" + dontreply;

                                    //Creating SendMail object
                                    SendMail sm = new SendMail(WorkshopRegistrationActivity.this, email1, subject, message);

                                    //Executing sendmail to send email
                                    sm.execute();

                                    dialog.dismiss();
                                    //startActivity(new Intent(EventRegistrationActivity.this,EventRegistrationActivity.class));
//                                finish();
//                                startActivity(getIntent());
                            }

                            else
                                Toast.makeText(WorkshopRegistrationActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });


    }


    boolean funpar1(){
        if (par1.getText().toString().isEmpty()) {
            //par1.setError("Mandatory");
            return  false;
        } else
            return true;

    }
    boolean funvol(){
        if (vol.getText().toString().isEmpty()) {
            //vol.setError("Mandatory");
            return  false;
        } else
            return true;

    }
    boolean funmail(){
        String s = mail.getText().toString();
        int n = s.length();
        int counter = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '@') {
                counter = i;
                break;
            }
        }
        String s1 = "";
        for (int i = counter + 1; i < n; i++)
            s1 += s.charAt(i);

        if (s1.equals("gmail.com") || s1.equals("yahoo.com") || s1.equals("outlook.in") || s1.equals("rediffmail.com") || s1.equals("yahoo.in") || s1.equals("outlook.com") || s1.equals("hotmail.com"))
            return true;
        else {
            return false;

        }

    }
    boolean funcon(){
        if (contact.getText().toString().equals("") || contact.getText().toString().length() != 10) {
            //contact.setError("Mandatory");
            return false;
        } else
            return true;

    }
    boolean funcol(){
        if (collegeName.getText().toString().isEmpty()) {
            //collegeName.setError("Mandatory");
            return false;
        } else
            return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;

            case R.id.refresh:
                finish();
                startActivity(getIntent());
               return true;

            case R.id.logout:
                finish();
                Intent intent=new Intent(getBaseContext(),LoginActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        //super.onCreateOptionsMenu(menu)
        return true;
    }
    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press again to EXIT", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        item.setChecked(true);
        mDrawerLayout.closeDrawers();

        switch(item.getItemId()){
            case R.id.events:
                Intent intent=new Intent(getBaseContext(),EventRegistrationActivity.class);
                startActivity(intent);

                return true;
            case R.id.workshops:
                Intent intent1=new Intent(getBaseContext(),WorkshopRegistrationActivity.class);
                startActivity(intent1);        }
        return true;
    }
}
