package tabsviejoskomodines.tabsvk;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import link.fls.swipestacksample.R;


public class ListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] itemname;
    private final Integer[] imgid;

    public ListAdapter (Activity context,String[] itemname, Integer[] imgid){
        super(context, R.layout.fragment_display_lists,itemname);
        this.context=context;
        this.itemname=itemname;
        this.imgid=imgid;

    }
    public View getView (int position,View view,ViewGroup parent)
    {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.rowlist_layout,null,true);
       // TextView title = (TextView) rowView.findViewById(R.id.list_item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.listIcon);
        //TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);

        //title.setText(itemname[position]);
        imageView.setImageResource(imgid[position]);
        //extratxt.setText("Description "+itemname[position]);
        return rowView;

    };


}