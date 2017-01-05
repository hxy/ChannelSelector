package hy.channelselector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import hy.adapter.ChannelListAdapter;
import hy.entity.IdNameBean;
import hy.entity.RecyclerViewItemOnClickListener;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mychannelsRV;
    private RecyclerView recommendchannelsRV;
    private ChannelListAdapter myAdapter;
    private ChannelListAdapter recommendAdapter;
    private ArrayList<IdNameBean> myDataList = new ArrayList<>();
    private ArrayList<IdNameBean> recommendDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mychannelsRV = (RecyclerView)findViewById(R.id.mychannels_list);
        recommendchannelsRV = (RecyclerView)findViewById(R.id.recommendchannels_list);

        recommendDataList.add(new IdNameBean("1","1"));
        recommendDataList.add(new IdNameBean("1","2"));
        recommendDataList.add(new IdNameBean("1","3"));
        recommendDataList.add(new IdNameBean("1","4"));
        recommendDataList.add(new IdNameBean("1","5"));
        recommendDataList.add(new IdNameBean("1","6"));
        recommendDataList.add(new IdNameBean("1","7"));
        recommendDataList.add(new IdNameBean("1","8"));
        recommendDataList.add(new IdNameBean("1","9"));
        recommendDataList.add(new IdNameBean("1","10"));

        myAdapter = new ChannelListAdapter(this,myDataList,mychannelsRV);
        myAdapter.setOnItemClickListener(myItemClickListener);
        myAdapter.setItemDragEnable();
        GridLayoutManager myGridLayoutManager = new GridLayoutManager(this,4);
        mychannelsRV.setLayoutManager(myGridLayoutManager);
        mychannelsRV.setAdapter(myAdapter);


        recommendAdapter = new ChannelListAdapter(this,recommendDataList,recommendchannelsRV);
        recommendAdapter.setOnItemClickListener(recommendItemClickListener);
        GridLayoutManager recommendGridLayoutManager = new GridLayoutManager(this,4);
        recommendchannelsRV.setLayoutManager(recommendGridLayoutManager);
        recommendchannelsRV.setAdapter(recommendAdapter);
    }


    private RecyclerViewItemOnClickListener myItemClickListener = new RecyclerViewItemOnClickListener() {
        @Override
        public void onItemClick(View v, Object item, int position) {
            myAdapter.removeCahnnel(position);
            recommendAdapter.addChannel((IdNameBean) item);
        }
    };

    private RecyclerViewItemOnClickListener recommendItemClickListener = new RecyclerViewItemOnClickListener() {
        @Override
        public void onItemClick(View v, Object item, int position) {
            recommendAdapter.removeCahnnel(position);
            myAdapter.addChannel((IdNameBean) item);
        }
    };
}
