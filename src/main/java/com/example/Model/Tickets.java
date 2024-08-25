package com.example.model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Getter;

public class Tickets {
    @Getter
    private String origin;
    @Getter
    private String origin_name;
    @Getter
    private String destination;
    @Getter
    private String destination_name;
    @Getter
    private String departure_date;
    @Getter
    private String departure_time;
    @Getter
    private String arrival_date;
    @Getter
    private String arrival_time;
    @Getter
    private String carrier;
    @Getter
    private int stops;
    @Getter
    private int price;

    public Tickets(String origin, String origin_name, String destination, String destination_name, String departure_date, String departure_time, String arrival_date, String arrival_time, String carrier, int stops, int price){
        this.origin = origin;
        this.origin_name = origin_name;
        this.destination = destination;
        this.destination_name = destination_name;
        this.departure_date = departure_date;
        this.departure_time = departure_time;
        this.arrival_date = arrival_date;
        this.arrival_time = arrival_time;
        this.carrier = carrier;
        this.stops = stops;
        this.price = price;
    }

    public LocalDateTime getDepartureDateTime(){
        LocalDateTime departureDateTime = LocalDateTime.parse(this.departure_date + " " + this.departure_time, DateTimeFormatter.ofPattern("d.M.y H:m")).plusYears(2000);
        return departureDateTime;
    }

    public LocalDateTime getArrivalDateTime(){
        LocalDateTime arrivalDateTime = LocalDateTime.parse(this.arrival_date + " " + this.arrival_time, DateTimeFormatter.ofPattern("d.M.y H:m")).plusYears(2000);
        return arrivalDateTime;
    }

    public String toString(){
        return "Tickets{ " + "origin = '" + origin + "' origin_name = " + origin_name + " destination = " + destination + " destination_name = " + destination_name + " departure_date = " + departure_date + " departure_time = " + departure_time + " arrival_date = " + arrival_date + " arrival_time = " + arrival_time + " carrier = " + carrier + " stops = " + stops + " price = " + price + '}';
    }
}
