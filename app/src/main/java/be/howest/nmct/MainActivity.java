package be.howest.nmct;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
import android.widget.TextView;

import java.io.Console;


public class MainActivity extends Activity {
    public final static String EXTRA_BIRTHYEAR = "be.howest.nmct.horoscoop.BIRTHYEAR";
    public final static String EXTRA_HOROSCOOP = "be.howest.nmct.horoscoop.HOROSCOOP";
    public static final int REQUEST_BIRTHYEAR = 1;
    public static final int REQUEST_HOROSCOOP = 2;
    private EditText txtNaam;
    private EditText txtVoorNaam;
    private TextView lblGeboortejaar;
    private Button btnGeboortejaar;
    private Button btnHoroscoop;
    private ImageView imgHoroscoopMain;


    public static String year;
    public static int nummerHoroscoop=-1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.txtNaam = (EditText) findViewById(R.id.txtNaam);
        this.txtVoorNaam = (EditText) findViewById(R.id.txtVoorNaam);
        this.lblGeboortejaar = (TextView) findViewById(R.id.lblGeboortejaar);
        this.btnGeboortejaar = (Button) findViewById(R.id.btnGeboortejaar);
        this.btnHoroscoop = (Button) findViewById(R.id.btnHoroscoop);
        this.imgHoroscoopMain = (ImageView) findViewById(R.id.imgHoroscoopMain);

        if(year!=null){
       lblGeboortejaar.setText("Geboortejaar: "+year);
       }

       if(nummerHoroscoop!=-1){
           imgHoroscoopMain.setImageResource(nummerHoroscoop);
       }

        this.btnGeboortejaar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                selecteerGeboortejaar(v);
            }
        });

        this.btnHoroscoop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                selecteerHoroscoop(v);
            }
        });
    }

    public void selecteerGeboortejaar(View v)
    {
        Intent intent = new Intent(MainActivity.this, SelectGeboortejaarActivity.class);
        startActivityForResult(	intent, REQUEST_BIRTHYEAR);

    }

    public void selecteerHoroscoop(View v)
    {
        Intent intent = new Intent(MainActivity.this, HoroscoopActivity.class);
        startActivityForResult(intent, REQUEST_HOROSCOOP);
    }


    @Override
    public void onStop() {
        super.onStop();
        Log.d(getClass().getSimpleName(), "onStopFrag");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void  onActivityResult (int requestCode, int resultCode, Intent data){

        switch (requestCode){

            case REQUEST_BIRTHYEAR:
                switch (resultCode){
                    case RESULT_OK:
                        year = data.getStringExtra(EXTRA_BIRTHYEAR);
                        lblGeboortejaar.setText("Geboortejaar:" + data.getStringExtra(EXTRA_BIRTHYEAR));
                        break;
                }

                break;
            case REQUEST_HOROSCOOP:
                switch(resultCode){
                    case RESULT_OK:
                        nummerHoroscoop = getResource(Data.Horoscoop.values()[Integer.parseInt(data.getStringExtra(EXTRA_HOROSCOOP))]);
                        imgHoroscoopMain.setImageResource(getResource(Data.Horoscoop.values()[Integer.parseInt(data.getStringExtra(EXTRA_HOROSCOOP))]));
                        imgHoroscoopMain.setTag(getResource(Data.Horoscoop.values()[Integer.parseInt(data.getStringExtra(EXTRA_HOROSCOOP))]));
                        break;
                }
                break;
            default:
                super.onActivityResult(requestCode,resultCode,data);
        }


    }




    public int getResource(Data.Horoscoop horoscoop) {
        switch (horoscoop) {
            case WATERMAN:
                return R.drawable.waterman;
            case VISSEN:
                return R.drawable.vissen;
            case RAM:
                return R.drawable.ram;
            case STIER:
                return R.drawable.stier;
            case TWEELING:
                return R.drawable.tweeling;
            case KREEFT:
                return R.drawable.kreeft;
            case LEEUW:
                return R.drawable.leeuw;
            case MAAGD:
                return R.drawable.maagd;
            case WEEGSCHAAL:
                return R.drawable.weegschaal;
            case SCHORPIOEN:
                return R.drawable.schorpioen;
            case BOOGSCHUTTER:
                return R.drawable.boogschutter;
            case STEENBOK:
                return R.drawable.steenbok;
            default:
                return 0;
        }
    }

}
