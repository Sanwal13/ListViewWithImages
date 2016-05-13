package blackriders.listviewwithimages;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Sanwal Singh on 13/5/16.
 */
public class EventAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private List<Events> eventsList;

    public EventAdapter(Context context, List<Events> eventsList) {
        this.mContext = context;
        this.eventsList = eventsList;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return eventsList.size();
    }

    @Override
    public Object getItem(int location) {
        return eventsList.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.custom_list, parent,
                    false);
            viewHolder = new ViewHolder();

            viewHolder.img_product = (ImageView) convertView.findViewById(R.id.img_event);
            viewHolder.product_name = (TextView) convertView.findViewById(R.id.txt_event);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String imageUrl = eventsList.get(position).getLogo().toString();
        Picasso.with(mContext).load(imageUrl).placeholder(R.drawable.ic_launcher)
                .error(R.drawable.ic_launcher).fit().centerInside()
                .into(viewHolder.img_product);

        viewHolder.product_name.setText(eventsList.get(position).getName());

        return convertView;
    }

    class ViewHolder {
        ImageView img_product;
        TextView product_name;
    }


}
