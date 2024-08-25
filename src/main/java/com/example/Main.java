package com.example;

import java.util.ArrayList;
import java.util.List;

import com.example.model.TicketModel;
import com.example.model.Tickets;
import com.example.servis.TicketServis;

public class Main {
    final static String Origin = "VVO";
    final static String Destination = "TLV";

    public static void main(String[] args) {
        JsonSimpleParser parser = new JsonSimpleParser();
        TicketModel tick = parser.parse();


        // создает лист со всеми авиаперевозчиками которые есть в файле json
        List<String> carriers = new ArrayList<>();
        for (Tickets ticket : tick.getTickets()) {
            if (!carriers.contains(ticket.getCarrier())){
                carriers.add(ticket.getCarrier());
            }
        }
        // вызов функции расчета минимального времени по массиву авиакомпаний
        for (String str : carriers) {
            TicketServis.Vtime(str, tick, Origin, Destination);
        }
        // логика для расчета и вывод разницы между средней ценой и медианой
        TicketServis.SrednayaMediana(tick, Origin, Destination);
    }
}