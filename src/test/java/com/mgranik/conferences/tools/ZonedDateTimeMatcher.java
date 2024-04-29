package com.mgranik.conferences.tools;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ZonedDateTimeMatcher extends TypeSafeMatcher<String> {
    private final ZonedDateTime expectedDateTime;
    private final DateTimeFormatter formatter;

    public ZonedDateTimeMatcher(ZonedDateTime expectedDateTime) {
        this.expectedDateTime = expectedDateTime;
        this.formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
    }

    @Override
    protected boolean matchesSafely(String actualDateTimeString) {
        ZonedDateTime actualDateTime = ZonedDateTime.parse(actualDateTimeString, formatter);
        return actualDateTime.isEqual(expectedDateTime);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a string representing ").appendValue(expectedDateTime);
    }

    public static ZonedDateTimeMatcher matchesZonedDateTime(ZonedDateTime expectedDateTime) {
        return new ZonedDateTimeMatcher(expectedDateTime);
    }
}