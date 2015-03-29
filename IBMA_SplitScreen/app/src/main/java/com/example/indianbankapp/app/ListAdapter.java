package com.example.indianbankapp.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.indianbankapp.app.content.AppContent.*;

import java.util.ArrayList;

/**
 * Created by lakhshya on 07/07/14.
 */
public class ListAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList items;
    private LayoutInflater vi;

    public ListAdapter(Context context, ArrayList items) {
        super(context,0, items);
        this.context = context;
        this.items = items;
        vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        final AppContentItem i = (AppContentItem)items.get(position);
        if (i != null) {
            if(i instanceof AppContentSection){
                AppContentSection si = (AppContentSection)i;
                v = vi.inflate(R.layout.list_item_section, null);
                v.setOnClickListener(null);
                v.setOnLongClickListener(null);
                v.setLongClickable(false);
                final TextView sectionView =
                        (TextView) v.findViewById(R.id.list_item_section_text);
                sectionView.setText(si.title);
            } else if (i instanceof AppContentProduct) {
                AppContentProduct ei = (AppContentProduct)i;
                v = vi.inflate(R.layout.list_item_entry, null);
                final TextView title =
                        (TextView)v.findViewById(R.id.list_item_entry_title);
                if (title != null) title.setText(ei.title);
            } else if (i instanceof AppContentCalculator){
                AppContentCalculator ei = (AppContentCalculator) i;
                v = vi.inflate(R.layout.list_item_entry, null);
                final TextView title =
                        (TextView) v.findViewById(R.id.list_item_entry_title);
                if (title != null) title.setText(ei.title);
            }
        }
        return v;
    }
}
