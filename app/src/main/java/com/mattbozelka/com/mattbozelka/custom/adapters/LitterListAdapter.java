package com.mattbozelka.com.mattbozelka.custom.adapters;

/*
*
* LitterListAdapter
*
* Custom ArrayAdapter used to display a LitterPiece item that can be collected. Based off of a
* Litter object.
*
* Displays to the screen the name of the LitterPiece, an icon for the LitterPiece, as well
* as the current count of a LitterPiece that a volunteer has collected for the piece.
*
* Associated XML: litter_item.xml
*
* purely presentational
*
* */

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mattbozelka.cleanupstars.R;
import com.mattbozelka.model.LitterPiece;

import java.util.ArrayList;

public class LitterListAdapter extends ArrayAdapter<LitterPiece> {

    private final String LOG_TAG = LitterListAdapter.class.getSimpleName();
    private Context context;
    private int layoutId;

    public LitterListAdapter(Context context, int layoutId, ArrayList<LitterPiece> data) {
        super(context, 0, data);
        this.context = context;
        this.layoutId = layoutId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LitterItemHolder holder = null;
        LitterPiece litterPiece = getItem(position);

        if(convertView == null) {
            holder = new LitterItemHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(layoutId, parent, false);

            holder.imIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
            holder.tvCount = (TextView) convertView.findViewById(R.id.tv_count);
            holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(holder);

        }else{
            holder = (LitterItemHolder) convertView.getTag();
        }

        int imageResource = context.getResources().getIdentifier(litterPiece.getIconName(),
                "drawable", context.getPackageName());
        Drawable icon = context.getResources().getDrawable(imageResource);
        holder.imIcon.setImageDrawable(icon);
        holder.tvCount.setText(Long.toString(litterPiece.getCount()));
        holder.tvName.setText(litterPiece.getName());

        return convertView;
    }

    static class LitterItemHolder
    {
        ImageView imIcon;
        TextView tvCount;
        TextView tvName;
    }
}
