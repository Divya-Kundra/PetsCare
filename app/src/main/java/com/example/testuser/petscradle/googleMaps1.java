package com.example.testuser.petscradle;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class googleMaps1 extends AppCompatActivity {

    private static final String TAG="googleMaps1";

    private static final int ERROR_DIALOG_REQUEST=9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps1);

        if(correctVersion()){
            addMap();
        }
    }

    private void addMap(){
        Button btnMap=(Button) findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(googleMaps1.this,MapActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean correctVersion(){

        int available= GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(googleMaps1.this);

        if(available== ConnectionResult.SUCCESS){
            //version of google play is good

            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //version not good but can be fixed

            Dialog dialog=GoogleApiAvailability.getInstance().getErrorDialog(googleMaps1.this,available,ERROR_DIALOG_REQUEST);
            dialog.show();
        }
        else{
            //can't fix
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
