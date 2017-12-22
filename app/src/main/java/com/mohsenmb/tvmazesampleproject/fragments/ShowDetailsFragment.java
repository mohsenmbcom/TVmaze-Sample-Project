package com.mohsenmb.tvmazesampleproject.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mohsenmb.apimodule.di.module.ShowDetailsModule;
import com.mohsenmb.apimodule.presentation.ShowDetailsPresenter;
import com.mohsenmb.apimodule.service.model.Show;
import com.mohsenmb.apimodule.view.ShowDetailsView;
import com.mohsenmb.tvmazesampleproject.R;
import com.mohsenmb.tvmazesampleproject.TvMazeApplication;
import com.mohsenmb.tvmazesampleproject.utils.AppUtils;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

public class ShowDetailsFragment extends Fragment implements ShowDetailsView {

    public static final java.lang.String ARG_SHOW_ID = "show-id";

    @Inject
    ShowDetailsPresenter presenter;

    private int showId;
    private Show show;

    private ProgressBar progressBar;
    private ImageView imgShowCover;
    private TextView tvShowInfo;
    private TextView tvShowDescription;
    private Snackbar mSnackbar;
    private Toolbar toolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            showId = getArguments().getInt(ARG_SHOW_ID, 0);
        }

        if (showId == 0) {
            getActivity().onBackPressed();
            showMessage(getString(R.string.error_loading_show_details));
            return;
        }

        injectDependencies();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_show_details, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toolbar = view.findViewById(R.id.toolbar);

        progressBar = view.findViewById(R.id.progress);
        imgShowCover = view.findViewById(R.id.imgShowCover);
        tvShowInfo = view.findViewById(R.id.tvShowInfo);
        tvShowDescription = view.findViewById(R.id.tvShowDescription);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (show == null) {
            if (mSnackbar != null && mSnackbar.isShown()) {
                mSnackbar.dismiss();
            }
            presenter.loadShowDetails(showId, AppUtils.isConnected(getContext()));
        }
    }

    private void injectDependencies() {
        TvMazeApplication.getComponent().plus(new ShowDetailsModule(this)).inject(this);
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
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showRetry() {
        mSnackbar = Snackbar.make(getView(),
                R.string.problem_on_connecting_to_server, Snackbar.LENGTH_INDEFINITE);
        mSnackbar.setAction(R.string.retry, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.loadShowDetails(showId, AppUtils.isConnected(getContext()));
            }
        });
        mSnackbar.show();
    }

    @Override
    public void performShowDetails(Show show) {
        if (show != null) {
            toolbar.setTitle(show.getName());
            tvShowInfo.setText(String.format("%s * %s * %s", show.getPremiered(), show.getNetwork().getName(),
                    show.getNetwork().getCountry().getName()));
            tvShowDescription.setText(Html.fromHtml(show.getSummary()));
            if (show.getImage() != null) {
                Picasso
                        .with(getContext())
                        .load(show.getImage().getOriginal())
                        .fit()
                        .placeholder(R.drawable.img_loading_placeholder)
                        .error(R.drawable.img_loading_placeholder)
                        .into(imgShowCover);
            }
        } else {
            getActivity().onBackPressed();
            showMessage(getString(R.string.error_loading_show_details));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter != null) {
            presenter.destroy();
        }
    }
}
