package rtphan.github.ideahacks2019;

import android.app.Fragment;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StatusFragment extends android.support.v4.app.Fragment {
    private RecyclerView mRoomView;
    private ProgressBar mPBar;
    private FirebaseDatabase mDatabase;
    private TextView listen_tv;
    private DatabaseReference listen;
    //private DatabaseReference restroomsRef;
    //private ArrayList<Restroom> RestroomList;
    //private NearbyAdapter mAdaptor;
    //private FavFragment favFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_status, container, false);
        listen = mDatabase.getReference("listen");
        listen_tv = (TextView) getView().findViewById(R.id.listen);
        mDatabase.add {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mDatabase = FirebaseDatabase.getInstance();
                TextView txt_listen = findViewById(R.id.listen);
                Integer value = dataSnapshot.getValue(Integer.class);
                if (value == 1);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("LogFragment", "loadLog:onCancelled", databaseError.toException());
            }
        };
        /* RestroomList = new ArrayList<>(); //make a new array list
        restroomsRef = mDatabase.getReference("restrooms"); //grab all the restrooms
        RestroomList.clear(); //clear array list

        //initialize all member view variables
        initViews(view);

        //initialize pull to refresh (low priority)
        initLoad();*/
        return view;
    }

   /* public ArrayList<Restroom> getFavRoomList(){
        return mAdaptor.getFavRoomList();
    }

    public void setFaveRoomList(ArrayList<Restroom> newList){
        mAdaptor.refreshDataSource(newList);
    }

    private void initViews(View view) {
        mPBar = view.findViewById(R.id.pb_pBar);
        mRoomView = view.findViewById(R.id.rv_room_list);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        mRoomView.setLayoutManager(llm);

        //TODO: write the load routine to pull data from firebase (pass the resulting arraylist to adapter)
        mPBar.setVisibility(View.VISIBLE); //show loading circle


        NearbyAdapter adapter = new NearbyAdapter(RestroomList, favFragment);
        mAdaptor = adapter;
        mRoomView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        mPBar.setVisibility(View.INVISIBLE);
    }

    public void passParam(FirebaseDatabase mDatabase, FavFragment favFragment) {
        this.mDatabase = mDatabase;
        this.favFragment = favFragment;
    }

    @Override
    public void onResume() { //run all the time in background
        super.onResume();

        restroomsRef.orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                RestroomList.clear(); //clear ALL old onees
                for (DataSnapshot data: dataSnapshot.getChildren()) {
                    Restroom room = data.getValue(Restroom.class);
                    RestroomList.add(room); //load in all restrooms refreshed
                }
                mAdaptor.notifyDataSetChanged(); //update screen
                mAdaptor.favFragment.refresh();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void initLoad(){
        //to be implemented later
        return;
    }*/
}
