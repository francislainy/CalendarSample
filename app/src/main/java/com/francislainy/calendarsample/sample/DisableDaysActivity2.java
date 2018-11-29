package com.francislainy.calendarsample.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.francislainy.calendarsample.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import org.threeten.bp.LocalDate;
import org.threeten.bp.Month;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Show off setting min and max dates and disabling individual days
 */
public class DisableDaysActivity2 extends AppCompatActivity {

    @BindView(R.id.calendarView)
    MaterialCalendarView widget;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        ButterKnife.bind(this);

        // Add a decorator to disable prime numbered days
        // widget.addDecorator(new PrimeDayDisableDecorator());
        // // Add a second decorator that explicitly enables days <= 10. This will work because
        // // decorators are applied in order, and the system allows re-enabling
        // widget.addDecorator(new EnableOneToTenDecorator());

        widget.addDecorator(new MyDecorator());

        final LocalDate calendar = LocalDate.now();
        // widget.setSelectedDate(calendar);

        final LocalDate min = LocalDate.of(calendar.getYear(), Month.JANUARY, 1);
        final LocalDate max = LocalDate.of(calendar.getYear() + 1, Month.OCTOBER, 31);

        widget.state().edit()
                .setMinimumDate(min)
                .setMaximumDate(max)
                .commit();
    }

    private static class MyDecorator implements DayViewDecorator {

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return checkDateHasService(day.getDate());
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.setDaysDisabled(true);
        }
    }

    public static boolean checkDateHasService(LocalDate date) {

        // This could be coming from an api call (available date for month)
        ArrayList<LocalDate> datesWithAvailableServicesList = new ArrayList<>();

        datesWithAvailableServicesList.add(LocalDate.now());
        datesWithAvailableServicesList.add(LocalDate.now().plusDays(1));
        datesWithAvailableServicesList.add(LocalDate.now().plusDays(2));

        return !datesWithAvailableServicesList.contains(date);
    }

}