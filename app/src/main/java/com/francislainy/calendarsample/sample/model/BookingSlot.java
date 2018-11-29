package com.francislainy.calendarsample.sample.model;

import org.threeten.bp.LocalDate;

/**
 * Created by Francislainy on 29/11/2018.
 */

public class BookingSlot {

    private LocalDate localDate;
    private boolean isAvailable;

    public BookingSlot(LocalDate localDate, boolean isAvailable) {
        this.localDate = localDate;
        this.isAvailable = isAvailable;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
