package com.example.tp1;

import android.view.View;
import android.widget.Toast;

public class MyListener implements View.OnClickListener{
   MainActivity currentActivity ;
    public MyListener(MainActivity currentActivity)
    {
        this.currentActivity = currentActivity;
    }
   @Override
    public void onClick(View view) {
        // view est le coposant graphique qui a recu l'action
if(view.getId() == currentActivity.getConverter_bn().getId())
{  //on ecrit ici le code d'execution de la conversion:
    double resultat=0;
    double val =0;
    //les etapes:
    // 1. on recupere la valeur saisie:
    try {
        val = Double.parseDouble(currentActivity.getValue_et().getText().toString());
    } catch (NumberFormatException e) {
        Toast.makeText(currentActivity,
                "Please give one numeric value", Toast.LENGTH_LONG).show();
        return;
    }

    //2. savoir quel est le bouton radio qui a été selectionné,
    // si un bouton a été selectionné, on calculer le resulat,
    // sinon, on affiche un message d'erreur via une notification
    if(currentActivity.getEuro2dinar_rb().isChecked()==true)
    {
        resultat = val * 3.4;
    }
    else {
        if (currentActivity.getDinar2euro_rb().isChecked() == true) {
            resultat = val / 3.4;
        }
        else {
            Toast.makeText(currentActivity,
                    "Please select one choice", Toast.LENGTH_LONG).show();
            return;
        }

    }
//3. on affetce le resultat au la zone de type TextView : result_tv
    currentActivity.getResult_tv().setText(resultat+"");
}

if(view.getId() == currentActivity.getQuitter_bn().getId())
{
    //on ecrit le code relatif à la fermeture de l'application
   System.exit(0);
}
    }
}
