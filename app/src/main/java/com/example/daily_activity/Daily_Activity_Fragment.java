package com.example.daily_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class Daily_Activity_Fragment extends Fragment {
    private RecyclerView dailyactivityrecyclerview;
    private dailyactivityadapter UAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_dailyactivity_list,container,false);
        dailyactivityrecyclerview=(RecyclerView) view.findViewById(R.id.layout_dailyactivity_fragment);
        dailyactivityrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }
    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }
    @Override
    public void  onCreateOptionsMenu(Menu homemenu, MenuInflater inflater){
        super.onCreateOptionsMenu(homemenu,inflater);
        inflater.inflate(R.menu.fragment_dailyactvity_list,homemenu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem items){
        switch(items.getItemId()){
            case R.id.search_plate:
            Daily_Activity new1=new Daily_Activity();
            Routines.get(getActivity()).adddailyactivity(new1);
            Intent intent = dailyactivity_activity_pager.newIntent(getActivity(),new1.getID());
            startActivity(intent);
            return true;
            case R.id.display_help:

                Intent helpIntent = Activity_GetHelp.newIntent(getActivity());
                startActivity(helpIntent);
            return true;
            default:
                return super.onOptionsItemSelected(items);

        }
    }

    private void updateUI(){
        Routines routine=Routines.get(getActivity());
        List<Daily_Activity>Dailyactivities=routine.getdailylist();
        if(UAdapter==null) {
            UAdapter = new dailyactivityadapter(Dailyactivities);
            dailyactivityrecyclerview.setAdapter(UAdapter);
        }else{
            UAdapter.setDAILYACTIVITY(Dailyactivities);
            UAdapter.notifyDataSetChanged();
        }

    }
    private class dailyactivityholder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Daily_Activity UDaily_Activity;
        private TextView Utitleview;
        private TextView Uplaceview;
        private TextView Udateview;
        public dailyactivityholder(LayoutInflater inflater,ViewGroup parent) {
            super(inflater.inflate(R.layout.dailyactivity_list_item, parent, false));
            itemView.setOnClickListener(this);
            Utitleview = (TextView) itemView.findViewById(R.id.text1);
            Uplaceview = (TextView) itemView.findViewById(R.id.text7);
            Udateview = (TextView) itemView.findViewById(R.id.text12);
        }
        @Override
        public void onClick(View view){
           Intent intent = dailyactivity_activity_pager.newIntent(getActivity(),UDaily_Activity.getID());
            startActivity(intent);
        }

            public void bind(Daily_Activity new1){
                UDaily_Activity=new1;
                Utitleview.setText(UDaily_Activity.getUtitle());
                Udateview.setText(UDaily_Activity.getUdate().toString());
                Uplaceview.setText(UDaily_Activity.getUplace());
            }

        }



    private class dailyactivityadapter extends RecyclerView.Adapter<dailyactivityholder>{
        private List<Daily_Activity>UDaily_Activitys;
        public dailyactivityadapter(List<Daily_Activity>Dailyactivities){
            UDaily_Activitys=Dailyactivities;
        }


        @Override
        public dailyactivityholder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater=LayoutInflater.from(getActivity());
            return new dailyactivityholder(layoutInflater,parent);

        }


        @Override
        public void onBindViewHolder(dailyactivityholder holder, int position) {
            Daily_Activity new1=UDaily_Activitys.get(position);
            holder.bind(new1);




        }

        @Override
        public int getItemCount() {
            return UDaily_Activitys.size();
        }
        public void setDAILYACTIVITY(List<Daily_Activity> Daily_activities) {
            UDaily_Activitys = Daily_activities;
        }
    }
}
