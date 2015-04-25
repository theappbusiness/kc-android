package com.nabilhachicha.kc.items.itemlist;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nabilhachicha.kc.KcApp;
import com.nabilhachicha.kc.R;
import com.nabilhachicha.kc.data.Database;
import com.nabilhachicha.kc.model.POI;
import com.nabilhachicha.kc.view.BaseFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;


public class ItemsFragment extends BaseFragment {

    private static final String CATEGORY_KEY = "category";

    @Inject
    Database mDatabase;

    @Inject
    Picasso mPicasso;

    private RecyclerView.Adapter mAdapter;

    /**
     * The recycle view
     */
    private RecyclerView mRecyclerView;


    public ItemsFragment() {
    }

    public static ItemsFragment newInstance(String category) {
        ItemsFragment fragment = new ItemsFragment();
        Bundle args = new Bundle();
        args.putString(CATEGORY_KEY, category);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.item_list);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        // Init the adapter
        initAdapter();

        // Set the adapter
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    private void initAdapter() {
        List<POI> pois = mDatabase.getPois(getArguments().getString(CATEGORY_KEY), POI.Sort.BY_NAME);
        POI poi = new POI();
        poi.setName("test");
        poi.setDescription("test2");
        poi.setImgUrl("https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcSS6zCdbKLAl2cjUxMTGFnDPFSZKT6K0xAiudBLKTyyzdCOnXHSc_pHicFJIA");
        pois.add(poi);
        mAdapter = new ItemsRecyclerAdapter(pois, mPicasso);
    }

}
