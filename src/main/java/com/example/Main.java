package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.model.TicketModel;
import com.example.model.Tickets;
import com.example.service.TicketService;

public class Main {
    final static String originTown = "VVO";
    final static String destinationTown = "TLV";

    public static void main(String[] args) {
        JsonSimpleParser parserTicket = new JsonSimpleParser();
        TicketModel ticketManager = parserTicket.parse();
        
        Map<String,Integer> greenwichMeanTime = new HashMap<String,Integer>();
        greenwichMeanTime.put("VVO", 10);
        greenwichMeanTime.put("TLV", 3); 
        greenwichMeanTime.put("UFA", 5); 
        greenwichMeanTime.put("LRN", 3);

        // создает лист со всеми авиаперевозчиками которые есть в файле json
        List<String> carriersList = new ArrayList<>();
        for (Tickets ticket : ticketManager.getTickets()) {
            if (!carriersList.contains(ticket.getCarrier())){
                carriersList.add(ticket.getCarrier());
            }
        }
        // вызов функции расчета минимального времени по массиву авиакомпаний
        for (String carriers : carriersList) {
            TicketService.minFlightTime(carriers, ticketManager, originTown, destinationTown, greenwichMeanTime.get(originTown), greenwichMeanTime.get(destinationTown));
        }
        // логика для расчета и вывод разницы между средней ценой и медианой
        TicketService.diffBetweenAvgAndMedian(ticketManager, originTown, destinationTown);
    }
}