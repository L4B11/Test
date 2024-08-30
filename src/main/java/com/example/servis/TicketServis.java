package com.example.servis;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.model.TicketModel;
import com.example.model.Tickets;

public class TicketServis {
    
    final static int greenwichMeanVladivostok = 10;
    final static int greenwichMeanTimeTelAviv = 3; 

    public static void minimumFlightTime(String str, TicketModel tick, String or, String des){
        Duration flightTime = null;
        Period flightDate = null;
        for (Tickets ticket : tick.getTickets()) {
            if (ticket.getCarrier().equals(str) && ticket.getOrigin().equals(or) && ticket.getDestination().equals(des)){
                LocalDateTime departureDateTime = LocalDateTime.parse(ticket.getDepartureDate() + " " + ticket.getDepartureTime(), DateTimeFormatter.ofPattern("d.M.y H:m")).plusYears(2000);
                LocalDateTime arrivalDateTime = LocalDateTime.parse(ticket.getArrivalDate() + " " + ticket.getArrivalTime(), DateTimeFormatter.ofPattern("d.M.y H:m")).plusYears(2000);
                if (flightTime == null){
                    flightTime = Duration.between(departureDateTime, arrivalDateTime);
                    flightDate = Period.between(departureDateTime.toLocalDate(), arrivalDateTime.toLocalDate());
                }else{
                    Duration deltaflightTime = Duration.between(departureDateTime, arrivalDateTime);
                    Period deltaflightDate = Period.between(departureDateTime.toLocalDate(), arrivalDateTime.toLocalDate());
                    if (deltaflightTime.compareTo(flightTime) < 0 && deltaflightDate.minus(flightDate).getYears() <= 0 && deltaflightDate.minus(flightDate).getMonths() <= 0 && deltaflightDate.minus(flightDate).getDays() <= 0) {
                        flightTime = deltaflightTime;
                        flightDate = deltaflightDate;
                    }
                }
            }
        }

        System.out.println(str + " Минимальная продолжительность: " + flightDate.getYears() + " лет " + flightDate.getMonths() + " месяцев " + flightDate.getDays() + " дней " + 
                        (flightTime.toHours() + greenwichMeanVladivostok-greenwichMeanTimeTelAviv) + " часов " + flightTime.toMinutesPart() + " минут " + flightTime.toSecondsPart() + " секунд");
    }

    public static void differenceBetweenAvgAndMedian(TicketModel ticketList, String originTown, String destinationTown){

        List <Integer> priceList = new ArrayList<>();

        float avgPrice;
        float medianPrice;

        for (Tickets ticket : ticketList.getTickets()){
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
