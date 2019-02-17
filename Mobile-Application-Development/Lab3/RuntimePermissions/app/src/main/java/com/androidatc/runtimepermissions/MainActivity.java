package com.androidatc.runtimepermissions;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    //Permission code that will be checked in method onRequestPermissionsResult
    private int RUNTIME_PERMISSION_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing button
        final Button btn_request_permissions = (Button) findViewById(R.id.btn_request_permissions);

        //adding click listener
        btn_request_permissions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //first check if the app already has permission

                if (isCameraAccessAllowed()) {
                    //if yes, show Toast
                    Toast.makeText(v.getContext(), "You already have the permission to access Camera",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                //if not, ask for permission
                requestCameraPermission();
            }
        });
    }

    //method to check permission status
    private boolean isCameraAccessAllowed() {
        //permission status
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);

        //if permission is granted
        if (result == PackageManager.PERMISSION_GRANTED)
            return true;
        //if permission is not granted
        return false;
    }

    //requesting permission
    private void requestCameraPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA)) {
            //if user has denied permission previously, your code will come to this block
            //you can explain here
            showAlertDialog();
        }

        //then, ask for permission
        ActivityCompat.requestPermissions(this, new String[] {
                Manifest.permission.CAMERA }, RUNTIME_PERMISSION_CODE);
    }

    //method to get result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //check request code
        if (requestCode == RUNTIME_PERMISSION_CODE) {
            //if granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //display toast
                Toast.makeText(this,"Permission granted, now you can use the camera", Toast.LENGTH_LONG).show();
            } else {
                //display another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied permission", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void showAlertDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        //set title
        alertDialogBuilder.setTitle("Runtime Permissions");

        //set dialog message
        alertDialogBuilder.setMessage("This is tutorial for Runtime Permission. This needs permission of accessing you device Camera."
        + "Please grant the permission") .setCancelable(false)
        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

        //create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        //show it
        alertDialog.show();

    }
}
