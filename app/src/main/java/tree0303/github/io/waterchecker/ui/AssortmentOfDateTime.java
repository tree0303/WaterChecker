package tree0303.github.io.waterchecker.ui;
import android.util.Log;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import tree0303.github.io.waterchecker.database.Plant;

public interface AssortmentOfDateTime {

    DateTimeFormatter form = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
    LocalDate nowDate = LocalDate.now();
    LocalTime startTime = LocalTime.of(00,00);
    LocalTime endTime = LocalTime.of(23,59);

    default Boolean isThisDay(String date){
        String startdatetime = LocalDateTime.of(nowDate,LocalTime.now()).format(form);
        String enddatetime = LocalDateTime.of(nowDate, endTime).format(form);
        boolean flag = (date.compareTo(startdatetime) >= 0) &&
                (date.compareTo(enddatetime) <= 0);
        return flag;

    }
    default Boolean isThisWeek(String date){
        String startdatetime = LocalDateTime.of(nowDate, startTime).plusDays(1).format(form);
        String enddatetime = LocalDateTime.of(nowDate, endTime).with(DayOfWeek.SATURDAY).format(form);
        boolean flag = (date.compareTo(startdatetime) >= 0) &&
                (date.compareTo(enddatetime) <= 0);
        return flag;
    }
    default Boolean isThisMonth(String date){
        String startdatetime = LocalDateTime.of(nowDate,startTime).with(DayOfWeek.SUNDAY).format(form);
        String enddatetime = LocalDateTime.of(nowDate, endTime).withDayOfMonth(1).plusMonths(1).minusDays(1).format(form);
        boolean flag = (date.compareTo(startdatetime) >= 0) &&
                (date.compareTo(enddatetime) <= 0);
        return flag;
    }

    default Boolean isAfterThisMonth(String date){
        String startdatetime = LocalDateTime.of(nowDate, startTime).withDayOfMonth(1).plusMonths(1).format(form);
        boolean flag = (date.compareTo(startdatetime) >= 0);
        return flag;
    }


    default List<Plant> getThisDayPlantList(List<Plant> plantlist){
        return plantlist.stream()
                .filter(plant -> isThisDay(plant.getDate()) == true)
                .collect(Collectors.toList());
    }
    default List<Plant> getThisWeekPlantList(List<Plant> plantlist){
        return plantlist.stream()
                .filter(plant ->  isThisWeek(plant.getDate()) == true)
                .collect(Collectors.toList());
    }
    default List<Plant> getThisMonthPlantList(List<Plant> plantlist){
        return plantlist.stream()
                .filter(plant ->  isThisMonth(plant.getDate()) == true)
                .collect(Collectors.toList());
    }
    default List<Plant> getAfterThisMonthPlantList(List<Plant> plantlist){
        return plantlist.stream()
                .filter(plant ->  isAfterThisMonth(plant.getDate()) == true)
                .collect(Collectors.toList());
    }
}
