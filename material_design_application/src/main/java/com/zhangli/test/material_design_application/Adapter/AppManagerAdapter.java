package com.zhangli.test.material_design_application.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhangli.test.material_design_application.Bean.AppModel;
import com.zhangli.test.material_design_application.R;

import java.util.List;


/**
 * Created by LX5 on 2019/4/28.
 */

public class AppManagerAdapter extends RecyclerView.Adapter<AppManagerAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    private List<AppModel> mList;

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public AppManagerAdapter(Context mContext, List<AppModel> mList) {
        this.mContext = mContext;
        this.mList = mList;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_app_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        AppModel model = mList.get(position);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            holder.iv_app_icon.setBackground(model.getIcon());
        } else {
            BitmapDrawable drawable = (BitmapDrawable) model.getIcon();
            Bitmap bitmap = drawable.getBitmap();
            holder.iv_app_icon.setImageBitmap(bitmap);
        }
        holder.tv_app_name.setText(model.getAppName());
        holder.tv_app_packagename.setText(model.getPackageName());
        holder.tv_app_size.setText(model.getAppSize());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_app_icon;
        private TextView tv_app_name;
        private TextView tv_app_packagename;
        private TextView tv_app_size;

        public ViewHolder(View itemView) {
            super(itemView);

            iv_app_icon = (ImageView) itemView.findViewById(R.id.iv_app_icon);
            tv_app_name = (TextView) itemView.findViewById(R.id.tv_app_name);
            tv_app_packagename = (TextView) itemView.findViewById(R.id.tv_app_packagename);
            tv_app_size = (TextView) itemView.findViewById(R.id.tv_app_size);
        }
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }
}
