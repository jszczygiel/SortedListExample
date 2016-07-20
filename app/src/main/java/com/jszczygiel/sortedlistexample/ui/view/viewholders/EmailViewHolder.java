package com.jszczygiel.sortedlistexample.ui.view.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.jszczygiel.sortedlistexample.R;
import com.jszczygiel.sortedlistexample.ui.view.viewmodels.EmailViewModel;

import butterknife.BindView;

public class EmailViewHolder extends BaseViewHolder<EmailViewModel> {

    @BindView(R.id.email)
    TextView email;

    public EmailViewHolder(View itemView, Context context) {
        super(itemView, context);
    }

    @Override
    public void onBind(EmailViewModel model, InteractionListener listener) {
        super.onBind(model,listener);

        email.setText(context.getString(R.string.format_email, model.getEmail()));
    }
}
