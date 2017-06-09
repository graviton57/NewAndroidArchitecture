package com.havrylyuk.newandroidarchitecture.ui.main;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.havrylyuk.newandroidarchitecture.BuildConfig;
import com.havrylyuk.newandroidarchitecture.R;
import com.havrylyuk.newandroidarchitecture.data.entiry.Country;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igor Havrylyuk on 08.06.2017.
 */

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.ItemHolder> {

    public interface ItemClickListener {
        void onItemClick(Country country);
    }

    private ItemClickListener listener;
    private List<Country> list;


    public CountriesAdapter(ItemClickListener listener ) {
        this.listener = listener;
        this.list = new ArrayList<>();
    }

    public void setData(List<Country> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemHolder holder, int position) {
        holder.view.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(list.get(position));
            }
        });
        String country = list.get(position).getCountryName();
        holder.country.setText(country);
        String capital = list.get(position).getCapital();
        holder.capital.setText(capital);
        String flagUrl = BuildConfig.GEONAME_ICON_URL +
                list.get(position).getCountryCode().toLowerCase() + ".png";
        holder.flag.setImageURI(Uri.parse(flagUrl));
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        private  View view;
        private  SimpleDraweeView flag;
        private  TextView country;
        private  TextView capital;

        public ItemHolder(View view) {
            super(view);
            this.view = view;
            this.flag = (SimpleDraweeView) view.findViewById(R.id.list_item_icon);
            this.country = (TextView) view.findViewById(R.id.list_item_name);
            this.capital = (TextView) view.findViewById(R.id.list_item_sub_name);
        }
    }
}
