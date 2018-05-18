package fr.hcube_conseil.hcube_conseil.vue;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;

import fr.hcube_conseil.hcube_conseil.R;
import fr.hcube_conseil.hcube_conseil.controleur.SessionManager;
import fr.hcube_conseil.hcube_conseil.modele.VolleySingleton;
import fr.hcube_conseil.hcube_conseil.outils.MyRequest;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout til_pseudo, til_password;
    private RequestQueue queue;
    private MyRequest request;
    private Button btn_send, btn_send_to_register;
    private ProgressBar pb_loader;
    private Handler handler;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //recuperation du message de l'inscription
        Intent intent = getIntent();
        if (intent.hasExtra("REGISTER")){
            Toast.makeText(this, intent.getStringExtra("REGISTER"), Toast.LENGTH_LONG).show();
        }

        til_pseudo = (TextInputLayout) findViewById(R.id.til_pseudo_log);
        til_password = (TextInputLayout) findViewById(R.id.til_password_log);
        btn_send = (Button) findViewById(R.id.btn_send);
        btn_send_to_register = (Button) findViewById(R.id.btn_send_to_register);
        pb_loader = (ProgressBar) findViewById(R.id.pb_loader);

        queue = VolleySingleton.getInstance(this).getRequestQueue();
        request = new MyRequest(this, queue);
        handler = new Handler();
        sessionManager = new SessionManager(this);

        btn_send_to_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String pseudo = til_pseudo.getEditText().getText().toString().trim();
                final String password = til_password.getEditText().getText().toString().trim();
                pb_loader.setVisibility(View.VISIBLE);
                if (pseudo.length() > 0 && password.length() > 0){
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            request.connection(pseudo, password, new MyRequest.loginCallBack() {
                                @Override
                                public void onSuccess(String id, String pseudo) {
                                    pb_loader.setVisibility(View.GONE);
                                    sessionManager.insertUser(id,pseudo);
                                    Intent intent = new Intent (getApplicationContext(),TalentEntrepriseActivity.class);
                                    startActivity(intent);
                                    finish();
                                }

                                @Override
                                public void onError(String message) {
                                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

                                }
                            });
                        }
                    },1000);
                }else{
                    Toast.makeText(getApplicationContext(), "Veuillez remplir tous les champs", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
