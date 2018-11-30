package com.francislainy.calendarsample.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.francislainy.calendarsample.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import org.threeten.bp.DateTimeUtils;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.Month;
import org.threeten.bp.ZoneId;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Date;

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
        String[] datesFromAPI = new String[]{"2018-11-30", "2018-11-29", "2018-11-27", "2018-10-25"};
        ArrayList<LocalDate> datesWithAvailableServicesList = new ArrayList<>();

        for (String s: datesFromAPI) {
            datesWithAvailableServicesList.add(stringToLocalDate(s));
        }

        // The decoration fades out the days so we don't want it applied to available dates
        return !datesWithAvailableServicesList.contains(date);
    }


    private static LocalDate stringToLocalDate(String stringDate) {

        Date date = getDateOfStringDateYearMonthDate(stringDate);

        return dateToLocalDate(date);
    }



    public static Date getDateOfStringDateYearMonthDate(String dateString) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);

        Instant i = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();

        return DateTimeUtils.toDate(i);
    }

    public static LocalDate dateToLocalDate(Date date) {

        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

}