package fr.hcube_conseil.hcube_conseil.vue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import fr.hcube_conseil.hcube_conseil.R;
import fr.hcube_conseil.hcube_conseil.controleur.SessionManager;

public class EntrepriseActivity extends AppCompatActivity {

    private Button btn_deconnexion;
    private TextView tv_pseudo;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entreprise);

        btn_deconnexion = (Button) findViewById(R.id.btn_deconnexion);
        tv_pseudo = (TextView) findViewById(R.id.tv_pseudo);

        sessionManager = new SessionManager(this);
        if (sessionManager.isLogged()){
            String pseudo = sessionManager.getPseudo();
            String id = sessionManager.getId();
            tv_pseudo.setText(pseudo);
        }

        btn_deconnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
                Intent intent = new Intent (getApplicationContext(), TalentEntrepriseActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
