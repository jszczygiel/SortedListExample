package com.jszczygiel.sortedlistexample.ui.view.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jszczygiel.compkit.images.ImageBuilder;
import com.jszczygiel.sortedlistexample.R;
import com.jszczygiel.sortedlistexample.ui.view.viewmodels.PersonViewModel;

import butterknife.BindView;

public class PersonViewHolder extends BaseViewHolder<PersonViewModel> {
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.displayName)
    TextView displayName;
    @BindView(R.id.delete)
    View delete;

    public PersonViewHolder(View itemView, Context context) {
        super(itemView, context);
    }

    @Override
    public void onBind(final PersonViewModel model, final InteractionListener listener) {
        super.onBind(model, listener);

        displayName.setText(context.getString(R.string.format_name, model.getDisplayName()));

        ImageBuilder.with(context).into(image).load(model.getImage()).build();
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDelete(model);
            }
        });
    }
}
