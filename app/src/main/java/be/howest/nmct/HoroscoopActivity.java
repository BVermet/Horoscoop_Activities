package be.howest.nmct;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class HoroscoopActivity extends ListActivity {
    private HoroscoopAdapter myHoroscoopAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_horoscoop);
        myHoroscoopAdapter = new HoroscoopAdapter();
        setListAdapter(myHoroscoopAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_horoscoop, menu);
        return true;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent();
        intent.putExtra("requestCode", MainActivity.REQUEST_HOROSCOOP);
        intent.putExtra(MainActivity.EXTRA_HOROSCOOP, "" + position);
        setResult(RESULT_OK, intent);
        finish();
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

    class HoroscoopAdapter extends ArrayAdapter<Data.Horoscoop> {
        public HoroscoopAdapter() {
            super(HoroscoopActivity.this, R.layout.row_horoscoop,
                    R.id.textViewNaamHoroscoop, Data.Horoscoop.values());
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            /*
            View row = super.getView(position, convertView, parent);
            Data.Horoscoop horoscoop = Data.Horoscoop.values()[position];

            TextView textViewNaamHoroscoop = (TextView) row.findViewById(R.id.textViewNaamHoroscoop);
            textViewNaamHoroscoop.setText(horoscoop.getNaamHoroscoop());
            ImageView imgHoroscoop = (ImageView) row.findViewById(R.id.imgHoroscoop);
            imgHoroscoop.setImageResource(getResource(horoscoop));
            */
            View row = super.getView(position, convertView, parent);
            final Data.Horoscoop horoscoop = Data.Horoscoop.values()[position];
            ImageView imgHoroscoop = (ImageView) row.findViewById(R.id.imgHoroscoop);
            imgHoroscoop.setImageResource(getResource(horoscoop));
            ViewHolder holder = (ViewHolder) row.getTag();
            if (holder == null){
                holder = new ViewHolder(row);
                row.setTag(holder);
            }
            TextView textViewNaamHoroscoop = holder.textviewNaamHoroscoop;
            textViewNaamHoroscoop.setText(horoscoop.getNaamHoroscoop());
            Button btnToonInfo = (Button) row.findViewById(R.id.btnToonInfo);
            btnToonInfo.setContentDescription("" + position);
            btnToonInfo.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), horoscoop.values()[Integer.parseInt((String) v.getContentDescription())].getBeginDatum() + " - " + horoscoop.values()[Integer.parseInt((String) v.getContentDescription())].getEindDatum(), Toast.LENGTH_SHORT).show();

                }
            });
            return row;

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
    class ViewHolder{
        public ImageView imageviewHoroscoop =null;
        public TextView textviewNaamHoroscoop = null;
        public Button btnToonInfo = null;
        public ViewHolder(View row)
        {
            this.imageviewHoroscoop = (ImageView) row.findViewById(R.id.imgHoroscoop);
            this.textviewNaamHoroscoop = (TextView)row.findViewById(R.id.textViewNaamHoroscoop);
            this.btnToonInfo = (Button) row.findViewById(R.id.btnToonInfo);
        }
    }

}


