package net.jesusramirez.mybroadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText tel;
    EditText sms;
    Button btnAcept;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tel=findViewById(R.id.txtNumero);
        sms=findViewById(R.id.txtTexto);
        btnAcept=findViewById(R.id.btnAceptar);
        //SE USAN LAS PREFERENCIAS PARA ALMACENAR LOS DATOS INGRESADOS POR EL USUARIO
        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(MainActivity.this);;
        //EL EDITOR ES QUIEN MODIFICA EL ARCHIVO DE PREFERENCIAS
        SharedPreferences.Editor editor=sharedPreferences.edit();
        btnAcept.setOnClickListener(view -> {
            editor.remove("NUMERO");
            editor.remove("MENSAJE");
            editor.putString("NUM", tel.getText().toString());
            editor.putString("SMS", sms.getText().toString());
            editor.commit();
            Toast.makeText(this, "Datos Guardados", Toast.LENGTH_SHORT).show();
        });
        tel.setText(sharedPreferences.getString("NUM", ""));
        sms.setText(sharedPreferences.getString("SMS", ""));
    }
}