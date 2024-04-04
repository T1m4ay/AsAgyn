package com.example.asadmin.criteria;

import java.time.ZonedDateTime;

public class BetweenDatesCriteria {

    private ZonedDateTime startDate = ZonedDateTime.now();
    private ZonedDateTime endDate = ZonedDateTime.now().plusDays(10);

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }
}
