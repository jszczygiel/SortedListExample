package com.jszczygiel.sortedlistexample.ui.view.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.jszczygiel.sortedlistexample.R;
import com.jszczygiel.sortedlistexample.ui.view.viewmodels.PhoneViewModel;

import butterknife.BindView;

public class PhoneViewHolder extends BaseViewHolder<PhoneViewModel> {
    @BindView(R.id.phone)
    TextView phone;
    public PhoneViewHolder(View itemView, Context context) {
        super(itemView, context);
    }

    @Override
    public void onBind(PhoneViewModel model, InteractionListener listener) {
        super.onBind(model,listener);

        phone.setText(context.getString(R.string.format_phone, model.getPhoneNumber()));

    }
}
