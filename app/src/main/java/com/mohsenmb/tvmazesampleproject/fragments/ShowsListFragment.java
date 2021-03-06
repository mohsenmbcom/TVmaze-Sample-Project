package com.mohsenmb.tvmazesampleproject.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mohsenmb.apimodule.di.module.ShowsListModule;
import com.mohsenmb.apimodule.presentation.ShowsListPresenter;
import com.mohsenmb.apimodule.service.model.Show;
import com.mohsenmb.apimodule.view.ShowsListView;
import com.mohsenmb.tvmazesampleproject.R;
import com.mohsenmb.tvmazesampleproject.TvMazeApplication;
import com.mohsenmb.tvmazesampleproject.adapter.ShowsListAdapter;
import com.mohsenmb.tvmazesampleproject.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ShowsListFragment extends Fragment implements ShowsListView, ShowsListAdapter.ShowItemClickListener {

    @Inject
    ShowsListPresenter presenter;

    private SwipeRefreshLayout srlShows;
    private ShowsListAdapter showsListAdapter;
    private int mPage;
    private List<Show> showsList;
    private boolean lastPage;
    private Snackbar mSnackbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        lastPage = false;
        mPage = 1;
    }

    private void injectDependencies() {
        TvMazeApplication.getComponent().plus(new ShowsListModule(this)).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shows_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        srlShows = view.findViewById(R.id.srlShows);
        srlShows.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                lastPage = false;
                mPage = 1;
                loadShows();
            }
        });
        RecyclerView rvShows = view.findViewById(R.id.rvShows);
        if (showsList == null) {
            showsList = new ArrayList<>();
        }
        showsListAdapter = new ShowsListAdapter(showsList);
        showsListAdapter.setShowItemClickListener(this);
        rvShows.setAdapter(showsListAdapter);
        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        rvShows.setLayoutManager(layoutManager);
        rvShows.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int count = layoutManager.getItemCount();
                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                // one row before the end of the grid
                if (!lastPage && !srlShows.isRefreshing() && lastVisibleItem >= count - 3) {
                    loadShows();
                }
            }
        });
    }

    private void loadShows() {
        if (mSnackbar != null && mSnackbar.isShown()) {
            mSnackbar.dismiss();
        }
        presenter.loadShowsList(mPage, AppUtils.isConnected(getContext()));
    }

    @Override
    public void onStart() {
        super.onStart();
        if (showsList == null || showsList.isEmpty()) {
            loadShows();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showOfflineMessage() {
        showMessage(getString(R.string.you_are_offline));
        showRetry();
    }

    @Override
    public void showConnectionError() {
        showMessage(getString(R.string.cant_connect_to_server));
        showRetry();
    }

    @Override
    public void toggleProgress(boolean show) {
        srlShows.setRefreshing(show);
    }

    @Override
    public void showRetry() {
        mSnackbar = Snackbar.make(getView(),
                R.string.problem_on_connecting_to_server, Snackbar.LENGTH_INDEFINITE);
        mSnackbar.setAction(R.string.retry, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadShows();
            }
        });
        mSnackbar.show();
    }

    @Override
    public void performShowsList(List<Show> showItems) {
        if (showItems != null && !showItems.isEmpty()) {
            if (mPage == 1) {
                showsList.clear();
                showsList.addAll(showItems);
                showsListAdapter.notifyDataSetChanged();
            } else {
                int oldSize = showsList.size();
                showsList.addAll(showItems);
                showsListAdapter.notifyItemRangeInserted(oldSize, showItems.size());
            }
            lastPage = false;
            mPage++;
        } else {
            lastPage = true;
        }
    }

    @Override
    public void onShowItemClicked(View view, Show show, int position) {
        ShowDetailsFragment showDetailsFragment = new ShowDetailsFragment();
        Bundle args = new Bundle();
        args.putInt(ShowDetailsFragment.ARG_SHOW_ID, show.getId());
        showDetailsFragment.setArguments(args);
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.container, showDetailsFragment)
                .addToBackStack(ShowDetailsFragment.class.getName())
                .commit();
    }
}
