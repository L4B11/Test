package com.example.model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Getter;

public class Tickets {
    @Getter
    private String origin;
    @Getter
    private String originName;
    @Getter
    private String destination;
    @Getter
    private String destinationName;
    @Getter
    private String departureDate;
    @Getter
    private String departureTime;
    @Getter
    private String arrivalDate;
    @Getter
    private String arrivalTime;
    @Getter
    private String carrier;
    @Getter
    private int stops;
    @Getter
    private int price;

    public Tickets(String origin, String originName, String destination, String destinationName, String departureDate, String departureTime, String arrivalDate, String arrivalTime, String carrier, int stops, int price){
        this.origin = origin;
        this.originName = originName;
        this.destination = destination;
        this.destinationName = destinationName;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.carrier = carrier;
        this.stops = stops;
        this.price = price;
    }

    public LocalDateTime getDepartureDateTime(){
        LocalDateTime departureDateTime = LocalDateTime.parse(this.departureDate + " " + this.departureTime, DateTimeFormatter.ofPattern("d.M.y H:m")).plusYears(2000);
        return departureDateTime;
    }

    public LocalDateTime getArrivalDateTime(){
        LocalDateTime arrivalDateTime = LocalDateTime.parse(this.arrivalDate + " " + this.arrivalTime, DateTimeFormatter.ofPattern("d.M.y H:m")).plusYears(2000);
        return arrivalDateTime;
    }

    public String toString(){
        return "Tickets{ " + "origin = '" + origin + "' originName = " + originName + " destination = " + destination + " destinationName = " + destinationName + " departureDate = " + departureDate + " departureTime = " + departureTime + " arrivalDate = " + arrivalDate + " arrivalTime = " + arrivalTime + " carrier = " + carrier + " stops = " + stops + " price = " + price + '}';
    }
}
