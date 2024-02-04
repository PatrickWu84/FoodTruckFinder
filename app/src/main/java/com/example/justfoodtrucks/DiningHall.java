package com.example.justfoodtrucks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DiningHall {
    private String name;
    private String address;
    private List<DiningDay> days;
    private String image;
    private int id;

    public DiningHall(String name, String address, List<DiningDay> days, String image, int id) {
        this.name = name;
        this.address = address;
        this.days = days;
        this.image = image;
        this.id = id;
    }
    public DiningDay getDiningDay() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (DiningDay day : days) {
            LocalDate dayDate = LocalDate.parse(day.getDate(), formatter);
            if (dayDate.equals(currentDate)) {
                return day;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<DiningDay> getDays() {
        return days;
    }

    public void setDays(List<DiningDay> days) {
        this.days = days;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
// one day of a specific dining hall
class DiningDay {
    private String date;
    private String status; // Assuming 'open' or 'closed'
    private List<DayPart> dayparts;

    public DiningDay(String date, String status, List<DayPart> dayparts) {
        this.date = date;
        this.status = status;
        this.dayparts = dayparts;
    }

    public String getHours() {
        StringBuilder hours = new StringBuilder();
        DateTimeFormatter inputFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("h:mm a");

        for (DayPart daypart : dayparts) {
            LocalTime start = LocalTime.parse(daypart.getStarttime().substring(11), inputFormatter);
            LocalTime end = LocalTime.parse(daypart.getEndtime().substring(11), inputFormatter);
            String range = start.format(outputFormatter) + " - " + end.format(outputFormatter);
            hours.append(range).append(" | ");
        }
        if (hours.length() > 1) {
            hours.deleteCharAt(hours.length() - 1);
            hours.deleteCharAt(hours.length() - 1);
        } else {
            return "CLOSED TODAY";
        }

        return hours.toString();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        if (status.equals("")) {
            status = "closed";
        }
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DayPart> getDayparts() {
        return dayparts;
    }

    public void setDayparts(List<DayPart> dayparts) {
        this.dayparts = dayparts;
    }
}

// part of a certain day of a specific dining hall
class DayPart {
    private String starttime;
    private String endtime;
    private String message;
    private String label;

    public DayPart(String starttime, String endtime, String message, String label) {
        this.starttime = starttime;
        this.endtime = endtime;
        this.message = message;
        this.label = label;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}

