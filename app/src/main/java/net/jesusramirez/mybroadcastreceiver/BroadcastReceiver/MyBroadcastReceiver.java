package net.jesusramirez.mybroadcastreceiver.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

//ESTA CLASE REPRESENTA EL BROADCAST RECEIVER ENCARGADO DE CUANDO ENTRA LA LLAMADA HACER LO QUE QUERAMOS
public class MyBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "MyBroadcastReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("BR","ENTRO AL BROADCAST");
        //OBTENER EL ESTADO SI ESTA SONANDO EL CELULAR
        String estado = intent.getExtras().getString(TelephonyManager.EXTRA_STATE);
        if(estado.equals(TelephonyManager.EXTRA_STATE_RINGING)){
            //OBTENER EL NUMERO DE QUIEN ESTA LLAMADO
            String numero = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
            if(numero!=null){
                //SE USARON LAS PREFERENCIAS PARA ALMACENAR LOS DATOS DEL DESTINATARIO Y EL MENSAJE QUE SE MANDARA
                SharedPreferences misPreferencias = PreferenceManager.getDefaultSharedPreferences(context);
                String numGuardado = misPreferencias.getString("NUM", "");
                if(numero.equals(numGuardado)){
                    String mensaje = misPreferencias.getString("SMS", "");
                    SmsManager smsManager = SmsManager.getDefault();
                    //SE MANDA EL MENSAJE
                    smsManager.sendTextMessage(numGuardado, null, mensaje, null, null);
                    Toast.makeText(context, "Mensaje Enviado", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
