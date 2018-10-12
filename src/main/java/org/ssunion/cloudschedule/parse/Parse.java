package org.ssunion.cloudschedule.parse;

import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.*;
import org.ssunion.cloudschedule.domain.Day;
import org.ssunion.cloudschedule.domain.Flasher;
import org.ssunion.cloudschedule.domain.Group;
import org.ssunion.cloudschedule.domain.Lesson;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parse {

    public static final String XLS_PATH = "/home/kirill/Documents/CloudSchedule/src/main/resources/shedule.xls";


    public static String doParse(String cellName) throws IOException {

        Workbook wb = new WorkbookFactory().create(new File(XLS_PATH));
        Sheet sheet = wb.getSheet("Шаблон");

        CellReference ref = new CellReference(cellName);
        Row row = sheet.getRow(ref.getRow());
        Cell cell = row.getCell(ref.getCol());
        String cellData = null;
        if (cell.getStringCellValue().isEmpty() != true) {

            cellData = cell.toString();
        }


        return cellData;
    }



    public static void main(String[] args) throws IOException {
        List<String> timeList = new ArrayList<>();
        timeList.add("13:30");
        timeList.add("14:30");
        timeList.add("15:30");
        timeList.add("16:30");
        timeList.add("17:30");
        timeList.add("18:30");

        Group group = new Group();
        group.setGroupName("152");
        group.setLastUpdate(LocalDate.now().toString());

        Day day = new Day();
        day.setDayName(doParse("A1"));

        Lesson lesson = new Lesson();

        Flasher down = new Flasher();
        down.setName(doParse("B4"));
        down.setTeacher(doParse("C4"));
        Flasher upper = new Flasher();
        upper.setTeacher(doParse("B3"));
        upper.setName(doParse("C3"));
        lesson.setDownWeek(down);
        lesson.setUpperWeek(upper);
        lesson.setStartTime(timeList.get(1));

        Lesson lesson2 = new Lesson();

        Flasher down2 = new Flasher();
        down2.setName(doParse("B6"));
        down2.setTeacher(doParse("C6"));
        lesson2.setDownWeek(down2);
        lesson2.setStartTime(timeList.get(2));

        Lesson lesson3 = new Lesson();

        Flasher down3 = new Flasher();
        down3.setName(doParse("B7"));
        down3.setTeacher(doParse("C7"));
        Flasher upper3 = new Flasher();
        upper3.setTeacher(doParse("B8"));
        upper3.setName(doParse("C8"));
        lesson3.setDownWeek(down3);
        lesson3.setUpperWeek(upper3);
        lesson3.setStartTime(timeList.get(3));

        Lesson lesson4 = new Lesson();

        Flasher upper4 = new Flasher();
        upper4.setName(doParse("B9"));
        upper4.setTeacher(doParse("C9"));
        lesson4.setUpperWeek(upper4);
        lesson4.setStartTime(timeList.get(4));

        Lesson lesson5 = new Lesson();

        Flasher upper5 = new Flasher();
        upper5.setName(doParse("B11"));
        upper5.setTeacher(doParse("C11"));
        lesson5.setUpperWeek(upper5);
        lesson5.setStartTime(timeList.get(5));

        day.setLessons(Arrays.asList(lesson, lesson2, lesson3, lesson4, lesson5));

        Day day2 = new Day();
        day2.setDayName(doParse("D1"));


        Lesson lesson2_1 = new Lesson();

        Flasher upper2_1 = new Flasher();
        Flasher down2_1 = new Flasher();
        upper2_1.setName(doParse("E3"));
        upper2_1.setTeacher(doParse("F3"));
        down2_1.setName(doParse("E4"));
        down2_1.setTeacher(doParse("F4"));
        lesson2_1.setUpperWeek(upper2_1);
        lesson2_1.setDownWeek(down2_1);
        lesson2_1.setStartTime(timeList.get(1));

        Lesson lesson2_2 = new Lesson();
        Flasher upper2_2 = new Flasher();
        upper2_2.setName(doParse("E5"));
        upper2_2.setTeacher(doParse("F5"));
        lesson2_2.setUpperWeek(upper2_2);
        lesson2_2.setStartTime(timeList.get(2));

        Lesson lesson2_3 = new Lesson();
        Flasher upper2_3 = new Flasher();
        upper2_3.setName(doParse("E7"));
        upper2_3.setTeacher(doParse("F7"));
        lesson2_3.setUpperWeek(upper2_3);
        lesson2_3.setStartTime(timeList.get(3));

        Lesson lesson2_4 = new Lesson();
        Flasher down2_4 = new Flasher();
        down2_4.setName(doParse("E10"));
        down2_4.setTeacher(doParse("F10"));
        lesson2_4.setDownWeek(down2_4);
        lesson2_4.setStartTime(timeList.get(4));


        day2.setLessons(Arrays.asList(lesson2_1, lesson2_2, lesson2_3, lesson2_4));

        group.setWeekSchedule(Arrays.asList(day, day2));
        System.out.println(group);

    }
}
