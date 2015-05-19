package com.test.tudou.library.MonthSwitchPager.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.test.tudou.library.MonthSwitchPager.view.MonthView;
import com.test.tudou.library.model.CalendarDay;
import com.test.tudou.library.util.DayUtils;
import java.util.ArrayList;

/**
 * Created by tudou on 15-4-30.
 */
public class MonthViewAdapter extends RecyclerView.Adapter<MonthViewAdapter.MonthViewHolder> {

  private Context mContext;
  private CalendarDay mStartDay;
  private CalendarDay mEndDay;
  private int mSelectPosition;
  private ArrayList<CalendarDay> mAbleCalendayDays;

  public MonthViewAdapter(Context context) {
    mContext = context;
    mAbleCalendayDays = new ArrayList<>();
  }

  public void setData(CalendarDay startDay, CalendarDay endDay, ArrayList<CalendarDay> calendarDayArrayList) {
    mStartDay = startDay;
    mEndDay = endDay;
    if (calendarDayArrayList != null) {
      mAbleCalendayDays.clear();
      mAbleCalendayDays.addAll(calendarDayArrayList);
    }
    notifyDataSetChanged();
  }

  @Override
  public int getItemCount() {
    if (mStartDay == null || mEndDay == null) {
      return 0;
    }
    int monthCount = DayUtils.calculateMonthCount(mStartDay, mEndDay);
    return monthCount;
  }



  @Override
  public MonthViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
    MonthView monthView = new MonthView(mContext);
    int width = mContext.getResources().getDisplayMetrics().widthPixels;
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,
        ViewGroup.LayoutParams.MATCH_PARENT);
    monthView.setLayoutParams(params);
    MonthViewHolder viewHolder = new MonthViewHolder(monthView, mStartDay, mAbleCalendayDays);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(final MonthViewHolder viewHolder, final int position) {
    viewHolder.bind(position, mSelectPosition);
  }

  public static class MonthViewHolder extends RecyclerView.ViewHolder {

    MonthView monthView;

    public MonthViewHolder(View view,CalendarDay startDay, ArrayList<CalendarDay> mAbleCalendayDays) {
      super(view);
      monthView = (MonthView) view;
      monthView.setFirstDay(startDay);
    }

    public void bind(int position, int selectPostion) {
      monthView.setMonthPosition(position);
    }

  }
}