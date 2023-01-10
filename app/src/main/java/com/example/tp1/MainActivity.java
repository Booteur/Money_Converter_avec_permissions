package com.example.tp1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //declaration des objets de type View qui seront utilisé pour réferencier
    // les objets View de layout XML:
    private TextView title_tv;
    private EditText value_et;
    private RadioButton euro2dinar_rb, dinar2euro_rb;
    private Button converter_bn, quitter_bn;
    private TextView result_tv;
    private final int REQUESTCODE=1;

    public TextView getTitle_tv() {
        return title_tv;
    }

    public EditText getValue_et() {
        return value_et;
    }

    public RadioButton getEuro2dinar_rb() {
        return euro2dinar_rb;
    }

    public RadioButton getDinar2euro_rb() {
        return dinar2euro_rb;
    }

    public Button getConverter_bn() {
        return converter_bn;
    }

    public Button getQuitter_bn() {
        return quitter_bn;
    }

    public TextView getResult_tv() {
        return result_tv;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // referencier les objets de la vue crée par le layout XML:
        title_tv = findViewById(R.id.title_tv);
        value_et =  findViewById(R.id.value_et); //la zone de texte de saisie du montant à convertir
         euro2dinar_rb = findViewById(R.id.euro2dinar_rb); //bouton radio : euro-->dinar
         dinar2euro_rb = findViewById(R.id.dinar2euro_rb); //bouton radio : dinar -->euro
        converter_bn = findViewById(R.id.convert_bn); //le bouton converter
        result_tv = (TextView) findViewById(R.id.result_tv); //la zone de texte où on affichera le resultat de la conversion.
        quitter_bn = findViewById(R.id.quitter_bn);


        // on programme l'action Click qui peut être exercer sur le bouton "converter":
        // dans ce cas, on a utilisé une definition d'une classe soit nommé
        // // ou bien anonyme
        // qui implements l'interface View.OnClickListener : MyListener
        converter_bn.setOnClickListener(this);
        quitter_bn.setOnClickListener(new MyListener(this));

    }

    @Override
    public void onClick(View view) {
        // view est le coposant graphique qui a recu l'action
        if(view.getId() == converter_bn.getId())
        {  //on ecrit ici le code d'execution de la conversion:
            double resultat=0;
            double val =0;
            //les etapes:
            // 1. on recupere la valeur saisie:
            try {
                val = Double.parseDouble(value_et.getText().toString());
            } catch (NumberFormatException e) {
                Toast.makeText(MainActivity.this,
                        "Please give one numeric value", Toast.LENGTH_LONG).show();
                return;
            }

            //2. savoir quel est le bouton radio qui a été selectionné,
            // si un bouton a été selectionné, on calculer le resulat,
            // sinon, on affiche un message d'erreur via une notification
            if(euro2dinar_rb.isChecked()==true)
            {
                resultat = val * 3.4;
            }
            else {
                if (dinar2euro_rb.isChecked() == true) {
                    resultat = val / 3.4;
                }
                else {
                    Toast.makeText(MainActivity.this,
                            "Please select one choice", Toast.LENGTH_LONG).show();
                    return;
                }

            }
//3. on affecte le resultat dans la zone de type TextView : result_tv
           // result_tv.setText(resultat+"");

            //4- Envoyer le resultat vers une fenetre une destination
            // on utilise un intent explicite avec reponse
            Intent i = new Intent(MainActivity.this,DestinationActivity.class);
            i.putExtra("res",resultat);
            startActivityForResult(i,REQUESTCODE);

        }

        if(view.getId() == quitter_bn.getId())
        {
            //on ecrit le code relatif à la fermeture de l'application
            System.exit(0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUESTCODE){
            if(resultCode == RESULT_OK){
            // on recupere la variable data au lieu de l'intent
                String name = data.getStringExtra("Name");
                Toast.makeText(this, " your name is" +" "+ name, Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "nothing to display", Toast.LENGTH_SHORT).show();
            }
        }
    }
}