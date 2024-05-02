package com.example.asadmin.criteria;

import java.time.ZonedDateTime;

public class BetweenDatesCriteria {

    private String startDate = String.valueOf(ZonedDateTime.now());
    private String endDate = String.valueOf(ZonedDateTime.now().plusDays(10));

    public ZonedDateTime getStartDate() {
        return ZonedDateTime.parse(startDate);
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getEndDate() {
        return ZonedDateTime.parse(endDate);
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
