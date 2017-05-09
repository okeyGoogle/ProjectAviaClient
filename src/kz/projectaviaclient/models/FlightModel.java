package kz.projectaviaclient.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import kz.projectaviaclient.ws.Flight;
import kz.projectaviaclient.ws.Place;

public class FlightModel extends AbstractTableModel {

    public static final int COLUMN_COUNT = 6;
    private ArrayList<Flight> list;
    private SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");

    
    //передаём коллекцию в конструктор для извлечения нужных данных из этой коллекции
    public FlightModel(ArrayList<Flight> list) {
        this.list = list;
    }

    //колличество записей в таблице берётся из коллекции
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_COUNT;
    }

    //метод показывающий количество свободных мест
    private int getFreeCount(Flight flight) {
        int count = 0;
        for (Place place : flight.getAircraft().getPlaceList()) {
            if (!place.isBusy()) {
                count++;
            }
        }

        return count;
    }

    
    //отображение данных в таблице
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Flight flight = list.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return flight.getCode();
            case 1:
                //при генерации метод "getDateDepart" возвращает XMLGregorianCalendar далее метод "toGregorianCalendar()" преобразует в обычный GregorianCalendar
                return formatDate.format(flight.getDateDepart().toGregorianCalendar().getTime());
            case 2:
                //при генерации метод "getDateDepart" возвращает XMLGregorianCalendar далее метод "toGregorianCalendar()" преобразует в обычный GregorianCalendar
                return formatDate.format(flight.getDateCome().toGregorianCalendar().getTime());
            case 3:
                return flight.getAircraft().getName();
            case 4:
                return flight.getDuration();
            case 5:
                return getFreeCount(flight);
        }
        return "";
    }

    @Override
    public String getColumnName(int i) {
        switch (i) {
            case 0:
                return "Код";
            case 1:
                return "Дата вылета";
            case 2:
                return "Дата прилета";
            case 3:
                return "Самолет";
            case 4:
                return "Длительность";
            case 5:
                return "Количество мест";

        }

        return "";
    }
}
