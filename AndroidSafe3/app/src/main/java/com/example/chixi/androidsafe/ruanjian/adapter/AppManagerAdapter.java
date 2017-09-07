package com.example.chixi.androidsafe.ruanjian.adapter;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chixi.androidsafe.R;
import com.example.chixi.androidsafe.ruanjian.entity.AppInfo;
import com.example.chixi.androidsafe.ruanjian.utils.DensityUtil;
import com.example.chixi.androidsafe.ruanjian.utils.EngineUtils;

import java.util.List;

/**
 * Created by chixi on 2017/1/29.
 */

public class AppManagerAdapter extends BaseAdapter {
    private List<AppInfo>UserAppInfos;
    private List<AppInfo>SystemAppInfos;
    private Context context;
    public AppManagerAdapter(List<AppInfo>userAppInfos,List<AppInfo>systemAppInfos,Context context){
        super();
        UserAppInfos=userAppInfos;
        SystemAppInfos=systemAppInfos;
        this.context=context;
    }
    @Override
    public int getCount() {
        return UserAppInfos.size()+SystemAppInfos.size()+2;
    }

    @Override
    public Object getItem(int position) {
        if (position==0) {
            return null;
        }else if (position==(UserAppInfos.size()+1)){
            return null;
        }
        AppInfo appInfo;
        if (position<(UserAppInfos.size()+1)){
            appInfo=UserAppInfos.get(position-1);
        }else {
            int location=position-UserAppInfos.size()-2;
            appInfo=SystemAppInfos.get(location);
        }
        return appInfo;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (position==0){
            TextView tv=getTextView();
            tv.setText("用户程序："+UserAppInfos.size()+"个");
            return tv;
        }else if (position==(UserAppInfos.size()+1)){
            TextView tv=getTextView();
            tv.setText("系统程序："+SystemAppInfos.size()+"个");
            return tv;
        }
        AppInfo appInfo;
        if (position<(UserAppInfos.size()+1)){
            appInfo=UserAppInfos.get(position-1);
        }else{
            appInfo=SystemAppInfos.get(position-UserAppInfos.size()-2);
        }
        ViewHolder viewHolder=null;
        if (convertView!=null&convertView instanceof LinearLayout){
            viewHolder=(ViewHolder)convertView.getTag();
        }else {
            viewHolder=new ViewHolder();
            convertView=View.inflate(context,R.layout.item_appmanager_list,null);
            viewHolder.mAppIconImgv=(ImageView)convertView.findViewById(R.id.imgv_appicon);
            viewHolder.mAppLocationTV=(TextView)convertView.findViewById(R.id.tv_appisroom);
            viewHolder.mAppSizeTV=(TextView)convertView.findViewById(R.id.tv_appsize);
            viewHolder.mAppNameTV=(TextView)convertView.findViewById(R.id.tv_appname);
            viewHolder.mLuanchAppTV=(TextView)convertView.findViewById(R.id.tv_launch_app);
            viewHolder.msettingAppTV=(TextView)convertView.findViewById(R.id.tv_setting_app);
            viewHolder.mShareAppTV=(TextView)convertView.findViewById(R.id.tv_share_app);
            viewHolder.mUninstallTV=(TextView)convertView.findViewById(R.id.tv_uninstall_app);
            viewHolder.mAppOptionLL=(LinearLayout)convertView.findViewById(R.id.ll_option_app);
            convertView.setTag(viewHolder);
        }
        if (appInfo!=null){
            viewHolder.mAppLocationTV.setText(appInfo.getAppLocation(appInfo.isInRoom));
            viewHolder.mAppIconImgv.setImageDrawable(appInfo.icon);
            viewHolder.mAppSizeTV.setText(android.text.format.Formatter.formatFileSize(context,appInfo.appSize));
            viewHolder.mAppNameTV.setText(appInfo.appName);
            if (appInfo.isSelected){
                viewHolder.mAppOptionLL.setVisibility(View.VISIBLE);
            }else{
                viewHolder.mAppOptionLL.setVisibility(View.GONE);
            }
        }
        MyClickListener listener=new MyClickListener(appInfo);
        viewHolder.mLuanchAppTV.setOnClickListener(listener);
        viewHolder.msettingAppTV.setOnClickListener(listener);
        viewHolder.mShareAppTV.setOnClickListener(listener);
        viewHolder.mUninstallTV.setOnClickListener(listener);
        return convertView;
    }
    private TextView getTextView(){
        TextView tv=new TextView(context);
        tv.setBackgroundColor(context.getResources().getColor(R.color.graye5));
        tv.setPadding(DensityUtil.dip2px(context,5),DensityUtil.dip2px(context,5),
                DensityUtil.dip2px(context,5),DensityUtil.dip2px(context,5));
        tv.setTextColor(context.getResources().getColor(R.color.black));
        return tv;
    }
    static class ViewHolder{
        /**启动APP*/
        TextView mLuanchAppTV;
        /**卸载APP*/
        TextView mUninstallTV;
        /**分享APP*/
        TextView mShareAppTV;
        /**设置APP*/
        TextView msettingAppTV;
        /**app图标*/
        ImageView mAppIconImgv;
        /**app位置*/
        TextView mAppLocationTV;
        /**app大小*/
        TextView mAppSizeTV;
        /**APP名称*/
        TextView mAppNameTV;
        /**操作APP的线性布局*/
        LinearLayout mAppOptionLL;
    }
    class MyClickListener implements OnClickListener{
        private AppInfo appInfo;
        public MyClickListener(AppInfo appInfo){
            super();
            this.appInfo=appInfo;
        }
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tv_launch_app:
                    EngineUtils.startApplication(context,appInfo);
                    break;
                case R.id.tv_share_app:
                    EngineUtils.startApplication(context,appInfo);
                    break;
                case R.id.tv_setting_app:
                    EngineUtils.startApplication(context,appInfo);
                    break;
                case R.id.tv_uninstall_app:
                    if (appInfo.packageName.equals(context.getPackageName())){
                        Toast.makeText(context,"您没有权限卸载次应用！",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    EngineUtils.uninstallApplication(context,appInfo);
                    break;
            }
        }
    }
}
