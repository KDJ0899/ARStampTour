package com.example.arstest;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kj.lee on 2017. 9. 26..
 */

public class ListAdapter extends BaseAdapter
{
    LayoutInflater inflater = null;
    private ArrayList<ItemData> m_oData = null;
    private int nListCnt = 0;
    Context context;

    public ListAdapter(ArrayList<ItemData> _oData,Context context)
    {
        m_oData = _oData;
        this.context = context;
        nListCnt = m_oData.size();
    }

    @Override
    public int getCount()
    {
        Log.i("TAG", "getCount");
        return nListCnt;
    }

    @Override
    public Object getItem(int position)
    {
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            final Context context = parent.getContext();
            if (inflater == null)
            {
                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }
            convertView = inflater.inflate(R.layout.activity_card_item, parent, false);
        }

        TextView rewardTitle = (TextView) convertView.findViewById(R.id.rewardTitle);
        TextView stampAmt = (TextView) convertView.findViewById(R.id.stampAmt);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.rewardImage);
        Button oBtn = (Button) convertView.findViewById(R.id.btnSelector);

        rewardTitle.setText(m_oData.get(position).strTitle);
        stampAmt.setText(m_oData.get(position).strData);
        imageView.setBackgroundResource(context.getResources().getIdentifier(m_oData.get(position).image, "drawable", context.getPackageName()));
        oBtn.setOnClickListener(m_oData.get(position).onClickListener);

        convertView.setTag(""+position);
        return convertView;
    }
}
