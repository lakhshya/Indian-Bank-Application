package com.example.indianbankapp.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;


import com.example.indianbankapp.app.content.AppContent;

/**
 * A fragment representing a single Option detail screen.
 * This fragment is either contained in a {@link OptionListActivity}
 * in two-pane mode (on tablets) or a {@link OptionDetailActivity}
 * on handsets.
 */
public class OptionDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The content title this fragment is presenting.
     */
    private AppContent.AppContentItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public OptionDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the content title specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load title from a title provider.
            mItem = AppContent.PRODUCT_MAP.get(getArguments().getInt(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_option_detail, container, false);

        // Show the content title as text in a TextView.
        if (mItem != null) {
            WebView webView = (WebView) rootView.findViewById(R.id.option_detail);
            webView.getSettings().setDisplayZoomControls(false);
//            webView.getSettings().setLoadWithOverviewMode(true);
            webView.getSettings().setUseWideViewPort(true);
            webView.getSettings().setBuiltInZoomControls(true);
//            webView.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
            webView.loadUrl(((AppContent.AppContentProduct)mItem).url);
        }

        return rootView;
    }
}
