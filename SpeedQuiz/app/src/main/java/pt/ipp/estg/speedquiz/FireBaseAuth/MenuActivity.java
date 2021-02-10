package pt.ipp.estg.speedquiz.FireBaseAuth;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

import java.util.ArrayList;
import java.util.List;

import pt.ipp.estg.speedquiz.HomeFragment;
import pt.ipp.estg.speedquiz.PerfilFragment;
import pt.ipp.estg.speedquiz.QuizFragment;
import pt.ipp.estg.speedquiz.R;
import pt.ipp.estg.speedquiz.TakeFragment;

public class MenuActivity extends AppCompatActivity implements TakeFragment.OnFragmentInteractionListener{
private SpaceNavigationView navigationView;
private Fragment fragment;
private TextView toolbarText;

public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;


private List<Utilizador> utilList;
private UtilizadorViewModel utilizadorViewModel;

        SharedPreferences mUser;
        SharedPreferences.Editor mEditor;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        utilizadorViewModel= ViewModelProviders.of(this).get(UtilizadorViewModel.class);

        //ActivityCompat.requestPermissions(this , new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_FINE_LOCATION);
        checkAndRequestPermissions();
   /*     if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        0);


                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }*/

        PowerManager power=(PowerManager) this.getSystemService(this.POWER_SERVICE);
        PowerManager.WakeLock wakeLock=power.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "myapp:mywakelock");
        wakeLock.acquire();
        mUser = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        mEditor = mUser.edit();

        utilizadorViewModel.getUtilizadorList().observe(this, new Observer<List<Utilizador>>() {
@Override
public void onChanged(List<Utilizador> eventList) {
        utilList = eventList;
        if (utilList.isEmpty()){
        ContactAddDialog addDialog = new ContactAddDialog();
        addDialog.show(getSupportFragmentManager(), "add cont dialog");
        }else{
        mEditor.putString("name",utilList.get(0).getNome());
        mEditor.putString("cont", String.valueOf(utilList.get(0).getContacto()));
        mEditor.commit();
        }
        }
        });





        Toolbar myToolbar= (Toolbar)findViewById(R.id.toolbarMenu);
        //setSupportActionBar(myToolbar);



        fragment=new HomeFragment();

        getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.fragment_container, fragment)
        .commit();

        navigationView=(SpaceNavigationView) findViewById(R.id.space);

        navigationView.initWithSaveInstanceState(savedInstanceState);
        navigationView.addSpaceItem(new SpaceItem("Quiz", R.drawable.ic_videogame_asset_white_24dp));
        navigationView.addSpaceItem(new SpaceItem("Perfil", R.drawable.ic_person_black_24dp));


        navigationView.showIconOnly();
        navigationView.setCentreButtonSelectable(true);

        navigationView.setCentreButtonSelected();


        navigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
@Override
public void onCentreButtonClick() {

        toolbarText.setText("Home");
        fragment=new HomeFragment();
        if (fragment!=null){
        getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.fragment_container, fragment)
        .commit();
        }
        }

@Override
public void onItemClick(int itemIndex, String itemName) {
        toolbarText.setText(itemName);

        switch (itemIndex){
        case 0:
        fragment=new QuizFragment();
        break;
        case 1:
        fragment=new PerfilFragment();
        break;
        }
        setFragment();
        }

@Override
public void onItemReselected(int itemIndex, String itemName) {
        switch (itemIndex){
        case 0:
        fragment=new QuizFragment();
        break;
        case 1:
        fragment=new PerfilFragment();
        break;
        }
        setFragment();
        }
        });





        }

private  boolean checkAndRequestPermissions() {
        int permissionSendMessage = ContextCompat.checkSelfPermission(this,
        Manifest.permission.SEND_SMS);
        int locationPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (locationPermission != PackageManager.PERMISSION_GRANTED) {
        listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (permissionSendMessage != PackageManager.PERMISSION_GRANTED) {
        listPermissionsNeeded.add(Manifest.permission.SEND_SMS);
        }
        if (!listPermissionsNeeded.isEmpty()) {
        ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),REQUEST_ID_MULTIPLE_PERMISSIONS);
        return false;
        }
        return true;
        }

public void setFragment(){
        if (fragment!=null){
        getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.fragment_container, fragment)
        .commit();
        }
        }

@Override
public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.top_app_bar, menu);
        return true;
        }

@Override
public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
        case R.id.action_logout:
        mEditor.clear();
        mEditor.commit();
        Intent intent = new Intent(getApplicationContext(), FireBaseAuth.class);
        startActivity(intent);
        return true;
default:
        return super.onOptionsItemSelected(item);
        }

        }
@Override
public void onBackPressed() {

        }
}
