package com.mattbozelka.com.mattbozelka.custom.adapters;

/*
*
* EventListAdapter
*
* Custom Array Adapter used to extend the android simple array adapter.
*
* It displays a list of events based on an event by event Location and Date. It also implements
* a "+" icon to insinuate that it is clickable to launch that specific event.
*
* Associated xml resource: event_item.xml
*
* Purely presentational.
*
* */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mattbozelka.cleanupstars.R;
import com.mattbozelka.model.Event;

import java.util.ArrayList;

    public class EventListAdapter extends ArrayAdapter<Event> {

    private final String LOG_TAG = LitterListAdapter.class.getSimpleName();
    private int layoutId;

    public EventListAdapter(Context context, int layoutId, ArrayList<Event> data) {
        super(context, 0, data);
        this.layoutId = layoutId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        EventItemHolder holder = null;
        Event event = getItem(position);

        if(convertView == null) {
            holder = new EventItemHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(layoutId, parent, false);

            holder.tvDate = (TextView) convertView.findViewById(R.id.event_date_tv);
            holder.tvLocation = (TextView) convertView.findViewById(R.id.event_Location_tv);
            convertView.setTag(holder);

        }else{
            holder = (EventItemHolder) convertView.getTag();
        }

        holder.tvDate.setText(event.getEventDate());
        holder.tvLocation.setText(event.getEventLocation());

        return convertView;
    }

    static class EventItemHolder
    {
        TextView tvDate;
        TextView tvLocation;
    }
}
