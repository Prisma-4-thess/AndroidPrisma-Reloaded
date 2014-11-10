package gr.prisma.androidprisma.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import gr.prisma.androidprisma.MainActivity;
import gr.prisma.androidprisma.Utils.ServerUtils;

/**
 * Created by dimitris on 9/11/2014.
 */
public class ArrayListFragment extends ListFragment {
    private final static String Tag = "ArrayListFragment";
    private ArrayAdapter<String> decisions;
    private ServerUtils serverUtils;

    public void addToAdapterFragment(String ada){
        decisions.add(ada);
    }

    public void clearAdapterFragment(){
        decisions.clear();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        decisions = new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1);
        serverUtils = new ServerUtils(getActivity());
        serverUtils.setCurQuery(getArguments().getString("query"));
        Log.d(Tag,"onCreate");
    }

    public void readyToShow(){
        setListAdapter(decisions);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(Tag,"onActivityCreated");
        serverUtils.loadDecisions(this);
        //SET LIST ADAPTER
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(Tag,"onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
