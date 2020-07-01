package com.example.fingerprint;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static android.hardware.fingerprint.FingerprintManager.*;

public class MainActivity extends AppCompatActivity {
    private TextView text;
    private ImageView image;
    private Button b;
    private FingerprintManager fpm;
    private AuthenticationCallback ac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b = (Button) findViewById(R.id.button);
        text = (TextView) findViewById(R.id.text1);
        image = (ImageView) findViewById(R.id.imageView);
        fpm = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
        ac = new AuthenticationCallback() {
            @Override
            public void onAuthenticationSucceeded(AuthenticationResult result) {
                text.setText("WOW SUCCESS!!!");
                image.setImageResource(R.drawable.z);
                super.onAuthenticationSucceeded(result);
            }

            @Override
            public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                text.setText("HELP!");
                image.setImageResource(R.drawable.y);
                super.onAuthenticationHelp(helpCode, helpString);
            }

            @Override
            public void onAuthenticationFailed() {
                text.setText("FAILED!!!");
                super.onAuthenticationFailed();
            }

            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                text.setText("ERROR!!");
                super.onAuthenticationError(errorCode, errString);
            }
        };
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fpm.authenticate(null,null,0,ac,null);
            }
        });
    }

}
