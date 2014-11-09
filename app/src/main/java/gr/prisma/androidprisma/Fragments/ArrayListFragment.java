package gr.prisma.androidprisma.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dimitris on 9/11/2014.
 */
public class ArrayListFragment extends ListFragment {
    private final static String Tag = "ArrayListFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(Tag,"onCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(Tag,"onActivityCreated");
        //SET LIST ADAPTER
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(Tag,"onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
