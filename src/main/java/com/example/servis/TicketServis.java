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
    
    final static int GMT_VVO = 10;
    final static int GMT_TLV = 3;

    public static void Vtime(String str, TicketModel tick, String or, String des){
        Duration time = null;
        Period date = null;
        for (Tickets ticket : tick.getTickets()) {
            if (ticket.getCarrier().equals(str) && ticket.getOrigin().equals(or) && ticket.getDestination().equals(des)){
                LocalDateTime departureDateTime = LocalDateTime.parse(ticket.getDeparture_date() + " " + ticket.getDeparture_time(), DateTimeFormatter.ofPattern("d.M.y H:m")).plusYears(2000);
                LocalDateTime arrivalDateTime = LocalDateTime.parse(ticket.getArrival_date() + " " + ticket.getArrival_time(), DateTimeFormatter.ofPattern("d.M.y H:m")).plusYears(2000);
                if (time == null){
                    time = Duration.between(departureDateTime, arrivalDateTime);
                    date = Period.between(departureDateTime.toLocalDate(), arrivalDateTime.toLocalDate());
                }else{
                    Duration VTime = Duration.between(departureDateTime, arrivalDateTime);
                    Period VDate = Period.between(departureDateTime.toLocalDate(), arrivalDateTime.toLocalDate());
                    if (VTime.compareTo(time) < 0 && VDate.minus(date).getYears() <= 0 && VDate.minus(date).getMonths() <= 0 && VDate.minus(date).getDays() <= 0) {
                        time = VTime;
                        date = VDate;
                    }
                }
            }
        }

        System.out.println(str + " Минимальная продолжительность: " + date.getYears() + " лет " + date.getMonths() + " мес " + date.getDays() + " дн " + (time.toHours() + GMT_VVO-GMT_TLV) + " час " + time.toMinutesPart() + " мин " + time.toSecondsPart() + " сек");
    }

    public static void SrednayaMediana(TicketModel tick, String or, String des){

        List <Integer> price = new ArrayList<>();

        float Sred = 0;
        float Mediana;

        for (Tickets ticket : tick.getTickets()){
            if (ticket.getOrigin().equals(or) && ticket.getDestination().equals(des)) {
                price.add(ticket.getPrice());
            }
        }
        Collections.sort(price);
        if (price.size()%2 == 1){
            Mediana = price.get(price.size()/2);
        }else{
            Mediana = (price.get(price.size()/2-1) + price.get(price.size()/2))/2;
        }
        for (Integer pr : price){ Sred += pr;}
        Sred = Sred/price.size();
        
        System.out.println("Разницa между средней ценой и медианой для\r\n" + //
                        "полета между городами  Владивосток и Тель-Авив составляет: " + Math.abs(Sred - Mediana));
    }


}
