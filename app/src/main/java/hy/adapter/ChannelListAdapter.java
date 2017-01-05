package hy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import hy.channelselector.R;
import hy.entity.IdNameBean;
import hy.entity.RecyclerViewItemOnClickListener;

/**
 * Created by huangyue on 2016/6/1.
 */
public class ChannelListAdapter extends RecyclerView.Adapter {
    private RecyclerViewItemOnClickListener itemOnClickListener;
    private Context context;
    private ArrayList<IdNameBean> channelList;
    private int itemColor = 0xffffffff;
    private int itemTextColor = 0xff8f8f8f;
    //这个位置之前的都是默认的
    private int defaultChannelPosition = -1;
    private int itemBackgroundResource = -1;
    private int defaultItemBackgroundResource = -1;
    private RecyclerView recyclerView;
    public ChannelListAdapter(Context context, ArrayList<IdNameBean> channelList,RecyclerView recyclerView){
        this.context = context;
        this.channelList = channelList;
        this.recyclerView = recyclerView;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, context.getResources().getDimensionPixelSize(R.dimen.item_height));
        layoutParams.setMargins(9, 9, 9, 9);
        textView.setLayoutParams(layoutParams);
        textView.setTextSize(12);
        textView.setBackgroundColor(itemColor);
        textView.setTextColor(itemTextColor);
        if(itemBackgroundResource!=-1){
            textView.setBackgroundResource(itemBackgroundResource);
        }
        textView.setOnClickListener(clickListener);
        return new ChannelViewHolder(textView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
        TextView textView = channelViewHolder.getTextView();
        textView.setText(channelList.get(position).getName());
        textView.setTag(channelViewHolder.getLayoutPosition());
        if(defaultItemBackgroundResource!=-1 && position<=defaultChannelPosition){
            textView.setBackgroundResource(defaultItemBackgroundResource);
        }
    }

    @Override
    public int getItemCount() {
        return channelList.size();
    }

    private static class ChannelViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;
        public ChannelViewHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView;
        }
        public TextView getTextView(){
            return textView;
        }
    }

    public ArrayList<IdNameBean> getChannelList(){
        return this.channelList;
    }

    public void addChannel(IdNameBean channel){
        channelList.add(channelList.size(), channel);
        notifyDataSetChanged();
    }
    public void removeCahnnel(int position){
        /*删除数据源*/
        channelList.remove(position);
        /*显示删除动画*/
        notifyItemRemoved(position);
        /*对删除位置后面的数据进行重新绑定*/
        notifyItemRangeChanged(position,channelList.size()-(position));

    }
    public void setOnItemClickListener(RecyclerViewItemOnClickListener itemOnClickListener){
        this.itemOnClickListener = itemOnClickListener;
    }
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(itemOnClickListener!=null){
                int position = (int)v.getTag();
                itemOnClickListener.onItemClick(v,channelList.get(position),position);
            }
        }
    };

    public void setItemColor(int color){
        itemColor = color;
    }

    public void setItemTextColor(int color){
        itemTextColor = color;
    }

    public void setItemBackgroundResource(int id){
        itemBackgroundResource = id;
    }

    public void setDefaultItemBackgroundResource(int id ){
        defaultItemBackgroundResource = id;
    }

    public void setDefaultChannelPositionLimit(int position){
        defaultChannelPosition = position;
    }

    public void setItemDragEnable(){
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchCallBack);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
    private ItemTouchHelper.Callback  itemTouchCallBack = new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            return makeMovementFlags(dragFlags,0);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            int from = viewHolder.getAdapterPosition();
            int to  = target.getAdapterPosition();
            Collections.swap(channelList,from,to);
            ChannelListAdapter.this.notifyItemMoved(from,to);
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        }

        @Override
        public boolean isLongPressDragEnabled() {
            return true;
        }
    };


}
