package extrace.ui.misc;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.view.View;
import android.widget.SearchView;
import android.widget.SearchView.OnCloseListener;
import android.widget.SearchView.OnQueryTextListener;
/**
 * Implementation of SearchView compatibility that can call Honeycomb APIs.
 */
class SearchViewCompatHoneycomb {
    public static void checkIfLegalArg(View searchView) {
        if (searchView == null) {
            throw new IllegalArgumentException("searchView must be non-null");
        }
        if (!(searchView instanceof SearchView)) {
            throw new IllegalArgumentException("searchView must be an instance of" +
                    "android.widget.SearchView");
        }
    }
    interface OnQueryTextListenerCompatBridge {
        boolean onQueryTextSubmit(String query);
        boolean onQueryTextChange(String newText);
    }
    interface OnCloseListenerCompatBridge {
        boolean onClose();
    }
    public static View newSearchView(Context context) {
        return new SearchView(context);
    }
    public static void setSearchableInfo(View searchView, ComponentName searchableComponent) {
        SearchView sv = ((SearchView) searchView);
        SearchManager searchManager = (SearchManager)
                sv.getContext().getSystemService(Context.SEARCH_SERVICE);
        sv.setSearchableInfo(searchManager.getSearchableInfo(searchableComponent));
    }
    public static Object newOnQueryTextListener(final OnQueryTextListenerCompatBridge listener) {
        return new OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return listener.onQueryTextSubmit(query);
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return listener.onQueryTextChange(newText);
            }
        };
    }
    public static void setOnQueryTextListener(View searchView, Object listener) {
        ((SearchView) searchView).setOnQueryTextListener((OnQueryTextListener) listener);
    }
    public static Object newOnCloseListener(final OnCloseListenerCompatBridge listener) {
        return new OnCloseListener() {
            @Override
            public boolean onClose() {
                return listener.onClose();
            }
        };
    }
    public static void setOnCloseListener(View searchView, Object listener) {
        ((SearchView) searchView).setOnCloseListener((OnCloseListener) listener);
    }
    public static CharSequence getQuery(View searchView) {
        return ((SearchView) searchView).getQuery();
    }
    public static void setQuery(View searchView, CharSequence query, boolean submit) {
        ((SearchView) searchView).setQuery(query, submit);
    }
    public static void setQueryHint(View searchView, CharSequence hint) {
        ((SearchView) searchView).setQueryHint(hint);
    }
    public static void setIconified(View searchView, boolean iconify) {
        ((SearchView) searchView).setIconified(iconify);
    }
    public static boolean isIconified(View searchView) {
        return ((SearchView) searchView).isIconified();
    }
    public static void setSubmitButtonEnabled(View searchView, boolean enabled) {
        ((SearchView) searchView).setSubmitButtonEnabled(enabled);
    }
    public static boolean isSubmitButtonEnabled(View searchView) {
        return ((SearchView) searchView).isSubmitButtonEnabled();
    }
    public static void setQueryRefinementEnabled(View searchView, boolean enable) {
        ((SearchView) searchView).setQueryRefinementEnabled(enable);
    }
    public static boolean isQueryRefinementEnabled(View searchView) {
        return ((SearchView) searchView).isQueryRefinementEnabled();
    }
    public static void setMaxWidth(View searchView, int maxpixels) {
        ((SearchView) searchView).setMaxWidth(maxpixels);
    }
}
