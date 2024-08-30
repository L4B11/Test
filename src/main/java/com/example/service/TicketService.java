package com.example.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.example.model.TicketModel;
import com.example.model.Tickets;

public class TicketService {
    
     	
    HashMap<String, String> ictionary = new HashMap<>();
    
    
    //final static int greenwichMeanTimeVladivostok = 10;
    //final static int greenwichMeanTimeTelAviv = 3; 

    public static void minFlightTime(String carriers, TicketModel ticketManager, String originTown, String destinationTown, Integer greenwichMeanTimeOrigin, Integer greenwichMeanTimeDestination){
        Duration flightTime = null;
        Period flightDate = null;
        for (Tickets ticket : ticketManager.getTickets()) {
            if (ticket.getCarrier().equals(carriers) && ticket.getOrigin().equals(originTown) && ticket.getDestination().equals(destinationTown)){
                LocalDateTime departureDateTime = LocalDateTime.parse(ticket.getDepartureDate() + " " + ticket.getDepartureTime(), DateTimeFormatter.ofPattern("d.M.y H:m")).plusYears(2000);
                LocalDateTime arrivalDateTime = LocalDateTime.parse(ticket.getArrivalDate() + " " + ticket.getArrivalTime(), DateTimeFormatter.ofPattern("d.M.y H:m")).plusYears(2000);
                if (flightTime == null){
                    flightTime = Duration.between(departureDateTime, arrivalDateTime);
                    flightDate = Period.between(departureDateTime.toLocalDate(), arrivalDateTime.toLocalDate());
                }else{
                    Duration deltaFlightTime = Duration.between(departureDateTime, arrivalDateTime);
                    Period deltaFlightDate = Period.between(departureDateTime.toLocalDate(), arrivalDateTime.toLocalDate());
                    if (deltaFlightTime.compareTo(flightTime) < 0 && deltaFlightDate.minus(flightDate).getYears() <= 0 && deltaFlightDate.minus(flightDate).getMonths() <= 0 && deltaFlightDate.minus(flightDate).getDays() <= 0) {
                        flightTime = deltaFlightTime;
                        flightDate = deltaFlightDate;
                    }
                }
            }
        }

        System.out.println(carriers + " Минимальная продолжительность: " + flightDate.getYears() + " лет " + flightDate.getMonths() + " месяцев " + flightDate.getDays() + " дней " + 
                        (flightTime.toHours() + greenwichMeanTimeOrigin-greenwichMeanTimeDestination) + " часов " + flightTime.toMinutesPart() + " минут " + flightTime.toSecondsPart() + " секунд");
    }

    public static void diffBetweenAvgAndMedian(TicketModel ticketManager, String originTown, String destinationTown){

        List <Integer> priceList = new ArrayList<>();

        float avgPrice;
        float medianPrice;

        for (Tickets ticket : ticketManager.getTickets()){
            if (ticket.getOrigin().equals(originTown) && ticket.getDestination().equals(destinationTown)) {
                priceList.add(ticket.getPrice());
            }
        }
        Collections.sort(priceList);
        // Расчет медианы в зависимости от четности 
        if (priceList.size()%2 == 1){
            medianPrice = priceList.get(priceList.size()/2);
        }else{
            medianPrice = (priceList.get(priceList.size()/2-1) + priceList.get(priceList.size()/2))/2;
        }
        avgPrice = priceList.stream().mapToInt(Integer::intValue).sum()/priceList.size();
        
        System.out.println("Разницa между средней ценой и медианой для\r\n" + 
                        "полета между городами  Владивосток и Тель-Авив составляет: " + Math.abs(avgPrice - medianPrice));
    }


}
