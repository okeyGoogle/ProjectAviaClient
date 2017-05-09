package kz.projectaviaclient.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import kz.projectaviaclient.object.ExtCity;
import kz.projectaviaclient.ws.City;
import kz.projectaviaclient.ws.Flight;
import kz.projectaviaclient.ws.Passenger;
import kz.projectaviaclient.ws.Place;
import kz.projectaviaclient.ws.Reservation;
import kz.projectaviaclient.ws.SearchWS;
import kz.projectaviaclient.ws.SearchWS_Service;


//класс работающий с веб-сервисом
public class SearchClient {


    private static SearchClient searchClient;

    
    public static SearchClient getInstance() {
        if (searchClient == null) {
            searchClient = new SearchClient();
        }

        return searchClient;
    }
    private SearchWS_Service searchService;
    private SearchWS searchWS;

    //создаём клиента веб-сервиса
    private SearchClient() {
        searchService = new SearchWS_Service();
        searchWS = searchService.getSearchWSPort();//вызываем порт для работы с веб-сервисом
    }

    public ArrayList<ExtCity> getAllCities() {
        ArrayList<ExtCity> cityList = new ArrayList<>();
        
        for (City city : searchWS.getAllCities()) {
            ExtCity extCity = new ExtCity();
            extCity.setCode(city.getCode());
            extCity.setCountry(city.getCountry());
            extCity.setDesc(city.getDesc());
            extCity.setId(city.getId());
            extCity.setName(city.getName());
            cityList.add(extCity);
        }
        
        //сортировка названия городов по алфавиту
        Collections.sort(cityList, new Comparator<City>() {
            @Override
            public int compare(City t, City t1) {
                return t.getName().compareTo(t1.getName());
            }
        });
        return cityList;
    }

    //вызов методов у сервера
    public ArrayList<Flight> searchFlight(long date, City cityFrom, City cityTo) {
        ArrayList<Flight> flightList = new ArrayList<>();
        flightList.addAll(searchWS.searchFlight(date, cityFrom, cityTo));
        return flightList;
    }

    public boolean buyTicket(Flight flight, Place place, Passenger passenger, String addInfo) {
        return searchWS.buyTicket(flight, place, passenger, addInfo);
    }


    public Reservation checkReservationByCode(String code){
        return searchWS.checkReservationByCode(code);
    }

}
