package com.knoldus.feedservice.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Date;

public final class TimeDifference {

    private static final Logger logger = LoggerFactory.getLogger(TimeDifference.class);

    public final static String getTimeDifference(Date currentDate, Date dbDate) {

        try {

            Duration between = Duration.between(dbDate.toInstant(), currentDate.toInstant());

            if (between.toDays() >= 1) {
                return (between.toDays() + " days ago");
            } else if (between.toHours() > 0 && between.toHours() <= 24) {
                return (between.toHours() + " hours ago");
            } else if (between.toMinutes() <= 60) {
                return (between.toMinutes() + " minutes ago");
            } else if (between.toSeconds() <= 60) {
                return (between.toSeconds() + " seconds ago");
            }else if (between.toMinutes() <= 60) {
                return (between.toMinutes() + " minutes ago");
            } else if (between.toHours() > 0 && between.toHours() <= 24) {
                return (between.toHours() + " hours ago");
            }

        } catch (Exception e) {
            logger.info("Exception is:::" + "e.printStackTrace()");
        }
        return "Time Not available";
    }
}
