package fr.hcube_conseil.hcube_conseil.vue;

import android.annotation.TargetApi;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import fr.hcube_conseil.hcube_conseil.R;
import fr.hcube_conseil.hcube_conseil.controleur.SessionManager;
import fr.hcube_conseil.hcube_conseil.fragments.APropos;
import fr.hcube_conseil.hcube_conseil.fragments.Client;
import fr.hcube_conseil.hcube_conseil.fragments.Contact;
import fr.hcube_conseil.hcube_conseil.fragments.Equipe;

public class TalentEntrepriseActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private NavigationView mNavigationView;

    private Button btn_inscrivez_vous;
    private Button btn_connexion;

    private SessionManager sessionManager;

    private TextView tv_header_bm_pseudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talent_entreprise);

        sessionManager = new SessionManager(this);
        if (sessionManager.isLogged()){
            Intent intent = new Intent(this, EntrepriseActivity.class);
            startActivity(intent);
            finish();
        }

        tv_header_bm_pseudo = (TextView) findViewById(R.id.tv_header_bm_pseudo);

        btn_inscrivez_vous = (Button) findViewById(R.id.btn_inscrez_vous);
        btn_connexion = (Button) findViewById(R.id.btn_connexion);

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupDrawerContent(mNavigationView);

        btn_connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_inscrivez_vous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_login:
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                return true;
        }

        if (mActionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void selectItemDrawer(MenuItem menuItem){
        Fragment mFragment = null;
        Class fragmentClass;
        switch (menuItem.getItemId()){
            case R.id.aPropos:
                fragmentClass = APropos.class;
                break;
            case R.id.clients:
                fragmentClass = Client.class;
                break;
            case R.id.equipe:
                fragmentClass = Equipe.class;
                break;
            case R.id.contact:
                fragmentClass = Contact.class;
                break;
            default:fragmentClass = APropos.class;
        }
        try{
            mFragment = (Fragment) fragmentClass.newInstance();
        }catch(Exception e){
            e.printStackTrace();
        }

        FragmentManager mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction().replace(R.id.flContent,mFragment).commit();
        menuItem.setCheckable(true);
        setTitle(menuItem.getTitle());
        mDrawerLayout.closeDrawers();
    }

    private void setupDrawerContent (NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectItemDrawer(item);
                return true;
            }
        });
    }
}
