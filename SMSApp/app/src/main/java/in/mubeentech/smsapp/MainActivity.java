package in.mubeentech.smsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText1,editText2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //CODE START
        editText1=findViewById(R.id.mobile);
        editText2=findViewById(R.id.sms);

    }

    public void send(View view) {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        if(permissionCheck== PackageManager.PERMISSION_GRANTED){
            MyMessage();
        }

       else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},0);
        }
    }

    private void MyMessage() {
        String phoneNumber = editText1.getText().toString().trim();
        String message=editText2.getText().toString().trim();

        if(!editText1.getText().toString().equals("")&&!editText2.getText().toString().equals("")){
        SmsManager smsManager=SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber,null,message,null,null);
        Toast.makeText(this, "Message Send", Toast.LENGTH_SHORT).show();
    }

    else{
            Toast.makeText(this, "Please Enter Mobile No or Message", Toast.LENGTH_SHORT).show();
        }
}


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case 0:
                if(grantResults.length >=0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    MyMessage();
                }
                else {
                    Toast.makeText(this, "You Don't Have Required Permission", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}
