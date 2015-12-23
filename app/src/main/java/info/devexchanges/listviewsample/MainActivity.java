package info.devexchanges.listviewsample;

import android.app.Activity;
import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView selection;
    private ListView listView;
    private static final String[] items = {"lorem", "ipsum", "dolor", "sit",
            "amet", "consectetuer", "adipiscing", "elit", "morbi", "vel",
            "ligula", "vitae", "arcu", "aliquet", "mollis", "etiam", "vel",
            "erat", "placerat", "ante", "porttitor", "sodales", "pellentesque",
            "augue", "purus"};

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);
        selection = (TextView) findViewById(R.id.selection);
        listView = (ListView) findViewById(R.id.list);

        //set on click listener for each List Item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                selection.setText(items[position]);
            }
        });

        //set listview adapter
        ArrayAdapter<String> adapter = new IconicAdapter();
        listView.setAdapter(adapter);
    }

    private class IconicAdapter extends ArrayAdapter<String> {

        public IconicAdapter() {
            super(MainActivity.this, R.layout.row, items);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            LayoutInflater inflater = (LayoutInflater) getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            // If holder not exist then locate all view from UI file.
            if (convertView == null) {
                // inflate UI from XML file
                convertView = inflater.inflate(R.layout.row, parent, false);
                // get all UI view
                holder = new ViewHolder(convertView);
                // set tag for holder
                convertView.setTag(holder);
            } else {
                // if holder created, get tag from view
                holder = (ViewHolder) convertView.getTag();
            }

            if (items[position].length() > 4) {
                holder.icon.setImageResource(R.mipmap.ic_delete);
            } else {
                holder.icon.setImageResource(R.mipmap.ic_check);
            }
            holder.text.setText(items[position]);

            return convertView;
        }
    }

    private class ViewHolder {

        private ImageView icon;
        private TextView text;

        public ViewHolder(View v) {
            icon = (ImageView)v.findViewById(R.id.icon);
            text = (TextView)v.findViewById(R.id.label);
        }
    }
}
