package kz.projectaviaclient.object;

import kz.projectaviaclient.ws.Place;


//наследник класса Place реализует метод "toString()" для отображения названия мест самолётов
public class ExtPlace extends Place{

    @Override
    public String toString() {
        return seatLetter+String.valueOf(seatNumber); //буква ряда + номер места
    }

    
    
}
