package com.nabilhachicha.kc.items.itemlist;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nabilhachicha.kc.R;
import com.nabilhachicha.kc.data.Database;
import com.nabilhachicha.kc.home.DataLoaderHelper;
import com.nabilhachicha.kc.io.KcObservables;
import com.nabilhachicha.kc.model.POI;
import com.nabilhachicha.kc.view.BaseFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import retrofit.RestAdapter;
import rx.Observable;


public class ItemsFragment extends BaseFragment implements DataLoaderHelper.ContentFlow<List<POI>> {

    private static final String CATEGORY_KEY = "category";

    @Inject
    RestAdapter mRestAdapter;

    @Inject
    Database mDatabase;

    @Inject
    Picasso mPicasso;

    private ItemsRecyclerAdapter mAdapter;

    /**
     * The recycle view
     */
    private RecyclerView mRecyclerView;
    private DataLoaderHelper mRxFlowHelper;
    private String mCategory;


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
        // Fetch remote data
        mRxFlowHelper = new DataLoaderHelper(this);
        mCategory = getArguments().getString(CATEGORY_KEY);
        mAdapter = new ItemsRecyclerAdapter(mPicasso);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdapter.SetOnItemClickListener(onItemClickListener);
    }

    ItemsRecyclerAdapter.OnItemClickListener onItemClickListener = new ItemsRecyclerAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(POI poi) {
            Toast.makeText(getActivity(), "Item selected: " + poi.getName(), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.item_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        return view;
    }

    @Override
    public void showError() {

    }

    @Override
    public void showContent(List<POI> data) {
        mAdapter.replace(mDatabase.getPois(mCategory, POI.Sort.BY_NAME));
    }

    @Override
    public void updateContent(List<POI> data) {
        showContent(data);
    }

    @Override
    public boolean isCacheAvailable() {
        return mDatabase.isEmpty();
    }

    @Override
    public List<POI> queryCache() {
        return mDatabase.getPois(mCategory);
    }

    @Override
    public Observable<List<POI>> queryBackend() {
        return KcObservables.getItemsByCategory(mDatabase, mCategory);
    }
}
