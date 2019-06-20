package com.zhangli.test.material_design_application.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhangli.test.material_design_application.Bean.GridViewBean;
import com.zhangli.test.material_design_application.R;

import java.util.List;

public class GridViewAdapter extends BaseAdapter {

    private Context context;
    private List<GridViewBean> list;

    public GridViewAdapter(Context context, List<GridViewBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item, null, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        try {
            /**
             * =========从list中取出数据并设置在控件中
             */

            viewHolder.gvItemImg.setImageResource(list.get(position).getImage());
            viewHolder.gvItemTv.setText(list.get(position).getName());

        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return convertView;
    }


    @Override
    public int getCount() {
        return list==null? 0:list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    static class ViewHolder {
        private ImageView gvItemImg;
        private TextView gvItemTv;

        ViewHolder(View view) {
            gvItemImg = view.findViewById(R.id.imageview);
            gvItemTv = view.findViewById(R.id.textview);
        }
    }
}
