package com.example.rohitgiri.vibhassignment93;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

//Class declaration which is extending AppCompatActivity class and implementing onItemClickListener interface.
public class MainActivity extends AppCompatActivity implements View.OnCreateContextMenuListener {

    ListView listView;    //Declaring ListView object.

    //Creating Arrays of name and phone numbers.
    String[] personName;
    String[] phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);   //Setting initial view as activity_main.

        personName =getResources().getStringArray(R.array.array_Name);    //Creating Array from names and Phone num stored in strings.xml
        phoneNumber=getResources().getStringArray(R.array.array_Phone);

        listView=(ListView)findViewById(R.id.list);    //Setting listView with its id.

        CustomAdapter adapter=new CustomAdapter(this);   //Creating Adapter of CustomAdapter class.

        listView.setAdapter((ListAdapter)adapter);    //Setting adapter to the listView.

        registerForContextMenu(listView);
        // listView.setOnItemClickListener(this);      //Setting onClick event.
    }

    //Function to handle event when any item is clicked in the listView.
    //@Override
    // public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    //     Toast.makeText(getApplicationContext(),personName[position]+" is Clicked",Toast.LENGTH_LONG).show();
    //Showing Toast of clicked element.
    //  }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Select The Action");
        menu.add(0,101,1,"Call");
        menu.add(0,102,2,"Send SMS");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getItemId()==101 &&item.getGroupId()==0)   //matching item and group id
        {
            //Intent to make a call

            //starting activity
            startActivity( new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" +phoneNumber[4])));
        }

        if(item.getItemId()==102 && item.getGroupId()==0)
        {//Intent for  Sms
            Uri uri = Uri.parse("smsto:"+phoneNumber[4]);
            Intent it = new Intent(Intent.ACTION_SENDTO, uri);
            it.putExtra("HIII", "HELLO");                 //content of sms
            startActivity(it);                            //starting activity
        }
        return super.onContextItemSelected(item);
    }

    //Creating Nested class extending BaseAdapter class to modify the by default adapter properties.
    class CustomAdapter extends BaseAdapter
    {
        Context context;   //Creating instance of Context.

        public CustomAdapter(Context context){this.context=context;}   //Constructor used to initialize Context.

        @Override
        //method to count elements in the list.
        public int getCount() {
            return personName.length;
        }

        @Override
        //Method to get particular element in list by its position.
        public Object getItem(int position) {
            return personName[position];
        }

        @Override
        //Method to get position of certain element.
        public long getItemId(int position) {
            return position;
        }

        @Override
        //Whenever adapter is set of this class, this method will be automatically called.
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView= LayoutInflater.from(context).inflate(R.layout.custom_row,null);
            //Converting simple list view to modified xml file view.

            //Setting two TextViews one for name and another for phone number.
            TextView textView1=(TextView)convertView.findViewById(R.id.textView);
            TextView textView2=(TextView)convertView.findViewById(R.id.textView2);

            //Setting texts of TextViews as per the elements in the array.
            textView1.setText(personName[position]);
            textView2.setText(phoneNumber[position]);

            return convertView;    //returning View.
        }
    }   //End of CustomAdapter class.
}     //End of class.
