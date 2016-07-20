package com.jszczygiel.sortedlistexample.ui.view.adapters;

import android.content.Context;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jszczygiel.sortedlistexample.R;
import com.jszczygiel.sortedlistexample.ui.view.viewholders.BaseViewHolder;
import com.jszczygiel.sortedlistexample.ui.view.viewholders.EmailViewHolder;
import com.jszczygiel.sortedlistexample.ui.view.viewholders.PersonViewHolder;
import com.jszczygiel.sortedlistexample.ui.view.viewholders.PhoneViewHolder;
import com.jszczygiel.sortedlistexample.ui.view.viewmodels.BaseViewModel;
import com.jszczygiel.sortedlistexample.ui.view.viewmodels.EmailViewModel;
import com.jszczygiel.sortedlistexample.ui.view.viewmodels.PersonViewModel;
import com.jszczygiel.sortedlistexample.ui.view.viewmodels.PhoneViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private final LayoutInflater inflater;
    private final Context context;
    private final SortedList<BaseViewModel> sortedList;
    private final BaseViewHolder.InteractionListener listener;

    public MainAdapter(Context context, BaseViewHolder.InteractionListener listener) {
        this.context = context;
        this.listener = listener;
        this.inflater = LayoutInflater.from(context);
        this.sortedList = new SortedList<>(BaseViewModel.class, new SortedList.Callback<BaseViewModel>() {
            @Override
            public int compare(BaseViewModel o1, BaseViewModel o2) {
                return MainAdapter.this.compare(o1, o2);
            }

            @Override
            public void onInserted(int position, int count) {
                notifyItemRangeInserted(position, count);
            }

            @Override
            public void onRemoved(int position, int count) {
                notifyItemRangeRemoved(position, count);

            }

            @Override
            public void onMoved(int fromPosition, int toPosition) {
                notifyItemMoved(fromPosition, toPosition);
            }

            @Override
            public void onChanged(int position, int count) {
                notifyItemRangeChanged(position, count);

            }

            @Override
            public boolean areContentsTheSame(BaseViewModel oldItem, BaseViewModel newItem) {
                return MainAdapter.this.areContentsTheSame(oldItem, newItem);
            }

            @Override
            public boolean areItemsTheSame(BaseViewModel item1, BaseViewModel item2) {
                return MainAdapter.this.areItemsTheSame(item1, item2);
            }
        });

    }

    private boolean areItemsTheSame(BaseViewModel item1, BaseViewModel item2) {
        return item1.hashCode() == item2.hashCode();
    }

    private boolean areContentsTheSame(BaseViewModel oldItem, BaseViewModel newItem) {
        return oldItem.equals(newItem);
    }

    private int compare(BaseViewModel o1, BaseViewModel o2) {
        int result = o1.getId().compareTo(o2.getId());
        if (result == 0) {

            if (o1 instanceof PersonViewModel && (o2 instanceof EmailViewModel || o2 instanceof PhoneViewModel)) {
                return -1;
            } else if ((o1 instanceof EmailViewModel || o1 instanceof PhoneViewModel) && o2 instanceof PersonViewModel) {
                return 1;
            } else if (o1 instanceof EmailViewModel && o2 instanceof PhoneViewModel) {
                return -1;
            } else if (o1 instanceof PhoneViewModel && o2 instanceof EmailViewModel) {
                return 1;
            } else if (o1.getClass().equals(o2.getClass())) {
                if (o1 instanceof PhoneViewModel) {
                    return ((PhoneViewModel) o1).getPhoneNumber().compareTo(((PhoneViewModel) o2).getPhoneNumber());
                }
                if (o1 instanceof EmailViewModel) {
                    return ((EmailViewModel) o1).getEmail().compareTo(((EmailViewModel) o2).getEmail());
                }
                if (o1 instanceof PersonViewModel) {
                    return result;
                }
            }
        } else {
            return result;
        }

        throw new IllegalStateException("should not reach");
    }

    @Override
    public int getItemViewType(int position) {
        return sortedList.get(position).getModelType();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case BaseViewModel.Types.EMAIL:
                return new EmailViewHolder(inflater.inflate(R.layout.viewholder_email, parent, false), context);
            case BaseViewModel.Types.PHONE:
                return new PhoneViewHolder(inflater.inflate(R.layout.viewholder_phone, parent, false), context);
            case BaseViewModel.Types.PERSON:
                return new PersonViewHolder(inflater.inflate(R.layout.viewholder_person, parent, false), context);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(sortedList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return sortedList.size();
    }

    public void addOrUpdate(BaseViewModel baseViewModel) {
        sortedList.add(baseViewModel);
    }

    public void remove(BaseViewModel baseViewModel) {
        sortedList.remove(baseViewModel);
    }

    public List<BaseViewModel> findById(String id) {
        List<BaseViewModel> toReturn = new ArrayList<>();
        boolean foundFirst = false;
        for (int i = 0; i < sortedList.size(); i++) {
            BaseViewModel item = sortedList.get(i);
            if (item.getId().equals(id)) {
                foundFirst = true;
                toReturn.add(item);
            } else if (foundFirst) {
                break;
            }
        }
        return toReturn;
    }
}
