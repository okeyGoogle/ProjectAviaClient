package kz.projectaviaclient.object;

import kz.projectaviaclient.ws.City;


//наследник класса City реализует метод "toString()" для отображения названия городов
public class ExtCity extends City{

    @Override
    public String toString() {
        return super.name;
    }

    
    
}
