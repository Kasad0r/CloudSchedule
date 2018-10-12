package org.ssunion.cloudschedule.parse;

import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.*;
import org.ssunion.cloudschedule.domain.Day;
import org.ssunion.cloudschedule.domain.Flasher;
import org.ssunion.cloudschedule.domain.Group;
import org.ssunion.cloudschedule.domain.Lesson;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parse {

    public static final String XLS_PATH = "/home/kirill/Documents/CloudSchedule/src/main/resources/shedule.xls";


    public static String doParse(String cellName, String sheetName) throws IOException {

        Workbook wb = new WorkbookFactory().create(new File(XLS_PATH));
        Sheet sheet = wb.getSheet(sheetName);

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
        timeList.add("12:00");
        timeList.add("13:30");
        timeList.add("14:30");
        timeList.add("15:30");
        timeList.add("16:30");
        timeList.add("17:30");
        timeList.add("18:30");

        Group group = new Group();
        group.setGroupName("472");
        group.setLastUpdate(LocalDate.now().toString());

        Day day = new Day();
        day.setDayName(doParse("A1", "472"));

        Lesson lesson0 = new Lesson();

        Flasher upper0 = new Flasher();
        upper0.setName(doParse("B7", "472"));
        upper0.setTeacher(doParse("C7", "472"));
        lesson0.setUpperWeek(upper0);
        lesson0.setStartTime(timeList.get(3));

        Lesson lesson1 = new Lesson();

        Flasher upper1 = new Flasher();
        upper1.setTeacher(doParse("B3", "472"));
        upper1.setName(doParse("C3", "472"));
        lesson1.setUpperWeek(upper1);
        lesson1.setStartTime(timeList.get(1));

        Lesson lesson2 = new Lesson();

        Flasher upper2 = new Flasher();
        upper2.setName(doParse("B5", "472"));
        upper2.setTeacher(doParse("C5", "472"));
        lesson2.setDownWeek(upper2);
        lesson2.setStartTime(timeList.get(2));


        day.setLessons(Arrays.asList(lesson1, lesson2, lesson0));


        Day day2 = new Day();
        day2.setDayName(doParse("D1", "472"));


        Lesson lesson2_0 = new Lesson();
        Flasher upper2_0 = new Flasher();
        upper2_0.setName(doParse("E11", "472"));
        upper2_0.setTeacher(doParse("F11", "472"));
        lesson2_0.setUpperWeek(upper2_0);
        lesson2_0.setStartTime(timeList.get(1));

        Lesson lesson2_1 = new Lesson();
        Flasher upper2_1 = new Flasher();
        upper2_1.setName(doParse("E3", "472"));
        upper2_1.setTeacher(doParse("F3", "472"));
        lesson2_1.setUpperWeek(upper2_1);
        lesson2_1.setStartTime(timeList.get(1));

        Lesson lesson2_2 = new Lesson();
        Flasher upper2_2 = new Flasher();
        upper2_2.setName(doParse("E5", "472"));
        upper2_2.setTeacher(doParse("F5", "472"));
        lesson2_2.setUpperWeek(upper2_2);
        lesson2_2.setStartTime(timeList.get(2));

        Lesson lesson2_3 = new Lesson();
        Flasher upper2_3 = new Flasher();
        upper2_3.setName(doParse("E7", "472"));
        upper2_3.setTeacher(doParse("F7", "472"));
        lesson2_3.setUpperWeek(upper2_3);
        lesson2_3.setStartTime(timeList.get(3));

        Lesson lesson2_4 = new Lesson();
        Flasher down2_4 = new Flasher();
        down2_4.setName(doParse("E10", "472"));
        down2_4.setTeacher(doParse("F10", "472"));
        lesson2_4.setDownWeek(down2_4);
        lesson2_4.setStartTime(timeList.get(4));


        day2.setLessons(Arrays.asList(lesson2_0, lesson2_1, lesson2_2, lesson2_3, lesson2_4));


        Day day3 = new Day();
        day3.setDayName(doParse("G1", "472"));


        Lesson lesson3_0 = new Lesson();
        Flasher upper3_0 = new Flasher();
        upper3_0.setName(doParse("H9", "472"));
        upper3_0.setTeacher(doParse("I9", "472"));
        lesson3_0.setUpperWeek(upper3_0);
        lesson3_0.setStartTime(timeList.get(1));

        Lesson lesson3_1 = new Lesson();
        Flasher upper3_1 = new Flasher();
        upper3_1.setName(doParse("H3", "472"));
        upper3_1.setTeacher(doParse("I3", "472"));
        lesson3_1.setUpperWeek(upper2_2);
        lesson3_1.setStartTime(timeList.get(2));

        Lesson lesson3_2 = new Lesson();
        Flasher upper3_2 = new Flasher();
        upper3_2.setName(doParse("H5", "472"));
        upper3_2.setTeacher(doParse("I5", "472"));
        lesson3_2.setUpperWeek(upper2_2);
        lesson3_2.setStartTime(timeList.get(2));

        Lesson lesson3_3 = new Lesson();
        Flasher down3_3 = new Flasher();
        down3_3.setName(doParse("H8", "472"));
        down3_3.setTeacher(doParse("I8", "472"));
        lesson3_3.setDownWeek(down3_3);
        lesson3_3.setStartTime(timeList.get(2));


        day3.setLessons(Arrays.asList(lesson3_0, lesson3_1, lesson3_2, lesson3_3));


        Day day4 = new Day();
        day4.setDayName(doParse("J1", "472"));


        Lesson lesson4_3 = new Lesson();
        Flasher upper4_3 = new Flasher();
        upper4_3.setName(doParse("K7", "472"));
        upper4_3.setTeacher(doParse("L7", "472"));
        lesson4_3.setUpperWeek(upper4_3);
        lesson4_3.setStartTime(timeList.get(3));

        Lesson lesson4_4 = new Lesson();
        Flasher upper4_4 = new Flasher();
        upper4_4.setName(doParse("K9", "472"));
        upper4_4.setTeacher(doParse("L9", "472"));
        lesson4_4.setUpperWeek(upper4_4);
        lesson4_4.setStartTime(timeList.get(4));


        day4.setLessons(Arrays.asList(lesson4_3, lesson4_4));


        Day day5 = new Day();
        day5.setDayName(doParse("M1", "472"));


        Lesson lesson5_0 = new Lesson();
        Flasher upper5_0 = new Flasher();
        upper5_0.setName(doParse("N11", "472"));
        upper5_0.setTeacher(doParse("O11", "472"));
        lesson5_0.setUpperWeek(upper5_0);
        lesson5_0.setStartTime(timeList.get(0));

        Lesson lesson5_1 = new Lesson();
        Flasher upper5_1 = new Flasher();
        upper5_1.setName(doParse("N3", "472"));
        upper5_1.setTeacher(doParse("O3", "472"));
        lesson5_1.setUpperWeek(upper5_1);
        lesson5_1.setStartTime(timeList.get(1));

        Lesson lesson5_2 = new Lesson();
        Flasher upper5_2 = new Flasher();
        Flasher down5_2 = new Flasher();
        down5_2.setName(doParse("N6", "472"));
        down5_2.setTeacher(doParse("O6", "472"));
        upper5_2.setName(doParse("N5", "472"));
        upper5_2.setTeacher(doParse("O5", "472"));
        lesson5_2.setUpperWeek(upper5_2);
        lesson5_2.setDownWeek(down5_2);
        lesson5_2.setStartTime(timeList.get(2));


        day5.setLessons(Arrays.asList(lesson5_0, lesson5_1, lesson5_2));


        group.setWeekSchedule(Arrays.asList(day, day2, day3, day4, day5));
        System.out.println(group);

    }
}
