package com.kalyandechiraju.goweather.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.kalyandechiraju.goweather.R;
import com.kalyandechiraju.goweather.databinding.ForecastListItemBinding;
import com.kalyandechiraju.goweather.model.Forecastday;
import com.kalyandechiraju.goweather.util.DateUtil;

import java.text.ParseException;
import java.util.List;
import java.util.Locale;

/**
 * Created by kalyandechiraju on 08/10/17.
 */

public class ForecastAdapter extends BaseAdapter {
    private static final String TAG = ForecastAdapter.class.getName();

    private List<Forecastday> mData;
    //private Context mContext;
    private LayoutInflater mInflater;

    public ForecastAdapter(List<Forecastday> data) {
        mData = data;
        //mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mData != null ? mData.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (mInflater == null) {
            mInflater = (LayoutInflater) parent.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (mData != null) {
            Forecastday forecastday = mData.get(position);

            ForecastListItemBinding binding;
            if (convertView == null) {
                binding = DataBindingUtil.inflate(mInflater, R.layout.forecast_list_item, parent, false);
            } else {
                binding = DataBindingUtil.getBinding(convertView);
            }

            try {
                binding.setDay(DateFormat.format("EEEE", DateUtil.parseDate(forecastday.getDate())).toString());
            } catch (ParseException e) {
                Log.e(TAG, "Cannot parse date", e);
            }
            binding.setTemperature(String.format(Locale.ENGLISH, "%.0f C", forecastday.getDay().getAvgtempC()));
            //binding.executePendingBindings();
            return binding.getRoot();
        } else {
            return null;
        }
    }

}
