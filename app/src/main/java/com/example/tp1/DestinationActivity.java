package com.example.tp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class DestinationActivity extends AppCompatActivity {
    private TextView resultat_id;
    private ImageButton return_bn;
    private ImageButton call_bn;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);
        resultat_id = findViewById(R.id.resultat_id);
        return_bn = findViewById(R.id.return_bn);
        call_bn = findViewById(R.id.call_bn);
        //On met a jour le contenu de la zone d'affichage du resultat
        //getIntent().getStringExtra("res");
        Intent i = getIntent();
        double res = i.getDoubleExtra("res", 0);
        resultat_id.setText(res + " ");
        // programmer l'action sur le bouton retour "call_bn";
        /*call_bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Intent.ACTION_DIAL);
                intent1.setData(Uri.parse("tel: 55555555"));
                startActivity(intent1);
            }
        });*/
        //Avec le ACTION_CALL POUR DIRECTEMENT appelé
        call_bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(DestinationActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE},MY_PERMISSIONS_REQUEST_CALL_PHONE);
                Intent intent1 = new Intent(Intent.ACTION_CALL);
                intent1.setData(Uri.parse("tel: 55555555"));
                DestinationActivity.this.startActivity(intent1);
            }
        });
// programmer l'action sur le bouton retour "return_bn";
    }

    public void retourner(View view) {
        Intent i = new Intent();
        i.putExtra("Name", "Berthonge");
        setResult(RESULT_OK, i);
        finish();
    }

    public void sendSms(View view) {
        try {
            // Methode 1 : via la ceation d'un intent :
           /* Intent sendSMSIntent= new Intent();
            sendSMSIntent.setAction(Intent.ACTION_SEND);
            sendSMSIntent.setType("text/plain");;
            sendSMSIntent.putExtra(Intent.EXTRA_TEXT,"Hello friend , where are you at?");
            startActivity(sendSMSIntent);*/

            // Methode 2 : via la recuperation d'un smsManager :
            ActivityCompat.requestPermissions(DestinationActivity.this, new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage("97097883", null,
                    "Hello Berthonge, where are you at ?",
                    null, null);

            Toast.makeText(this, "Message sent", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(DestinationActivity.this.getApplicationContext(),
                    "Error : Some fields are empty " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }
}