package com.jszczygiel.sortedlistexample.ui.view.viewholders;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jszczygiel.sortedlistexample.ui.view.viewmodels.BaseViewModel;

import java.util.List;

import butterknife.ButterKnife;

public class BaseViewHolder<T extends BaseViewModel> extends RecyclerView.ViewHolder {
    protected final Context context;

    public BaseViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;
        ButterKnife.bind(this, itemView);
    }

    public void onBind(T model, InteractionListener listener) {
    }

    @CallSuper
    public void onBind(T model, InteractionListener listener, List<Object> payloads) {
        if (payloads == null || payloads.size() == 0) {
            this.onBind(model, listener);
        }

    }

    protected boolean isViewAvailable() {
        return this.itemView != null;
    }

    public static class InteractionListener {
        public void onDelete(BaseViewModel model) {
        }
    }

}
