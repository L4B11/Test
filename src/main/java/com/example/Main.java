package com.example;

import java.util.ArrayList;
import java.util.List;

import com.example.model.TicketModel;
import com.example.model.Tickets;
import com.example.servis.TicketServis;

public class Main {
    final static String originTown = "VVO";
    final static String destinationTown = "TLV";

    public static void main(String[] args) {
        JsonSimpleParser parser = new JsonSimpleParser();
        TicketModel ticketList = parser.parse();


        // создает лист со всеми авиаперевозчиками которые есть в файле json
        List<String> carriersList = new ArrayList<>();
        for (Tickets ticket : ticketList.getTickets()) {
            if (!carriersList.contains(ticket.getCarrier())){
                carriersList.add(ticket.getCarrier());
            }
        }
        // вызов функции расчета минимального времени по массиву авиакомпаний
        for (String carriers : carriersList) {
            TicketServis.minimumFlightTime(carriers, ticketList, originTown, destinationTown);
        }
        // логика для расчета и вывод разницы между средней ценой и медианой
        TicketServis.differenceBetweenAvgAndMedian(ticketList, originTown, destinationTown);
    }
}