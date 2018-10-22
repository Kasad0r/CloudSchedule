package org.ssunion.cloudschedule.parse;

import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.*;
import org.ssunion.cloudschedule.domain.Day;
import org.ssunion.cloudschedule.domain.Flasher;
import org.ssunion.cloudschedule.domain.Group;
import org.ssunion.cloudschedule.domain.Lesson;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Parse {

    public static final String GROUP_NAME_0 = "472";
    public static final String GROUP_NAME_1 = "471";
    public static final String GROUP_NAME_2 = "151";
    public static final String GROUP_NAME_3 = "345";
    public static final String GROUP_NAME_4 = "123";


    public static final String MONDAY_TIME_0 = "A3";
    public static final String MONDAY_TIME_1 = "A5";
    public static final String MONDAY_TIME_2 = "A7";
    public static final String MONDAY_TIME_3 = "A9";
    public static final String MONDAY_TIME_4 = "A11";

    public static final String TUESDAY_TIME_0 = "D3";
    public static final String TUESDAY_TIME_1 = "D5";
    public static final String TUESDAY_TIME_2 = "D7";
    public static final String TUESDAY_TIME_3 = "D9";
    public static final String TUESDAY_TIME_4 = "D11";

    public static final String WEDNESDAY_TIME_0 = "G3";
    public static final String WEDNESDAY_TIME_1 = "G5";
    public static final String WEDNESDAY_TIME_2 = "G7";
    public static final String WEDNESDAY_TIME_3 = "G9";
    public static final String WEDNESDAY_TIME_4 = "G11";

    public static final String THURSDAY_TIME_0 = "J3";
    public static final String THURSDAY_TIME_1 = "J5";
    public static final String THURSDAY_TIME_2 = "J7";
    public static final String THURSDAY_TIME_3 = "J9";
    public static final String THURSDAY_TIME_4 = "J11";

    public static final String FRIDAY_TIME_0 = "M3";
    public static final String FRIDAY_TIME_1 = "M5";
    public static final String FRIDAY_TIME_2 = "M7";
    public static final String FRIDAY_TIME_3 = "M9";
    public static final String FRIDAY_TIME_4 = "M11";

    public static final String XLS_PATH = "/home/kirill/Documents/CloudSchedule/src/main/resources/shedule.xls";
    public static Workbook wb;

    static {
        try {
            wb = new WorkbookFactory().create(new File(XLS_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String doParse(String cellName, String sheetName) throws IOException {
        List<String> mondatTime = new ArrayList<>();
        mondatTime.add(MONDAY_TIME_0);
        mondatTime.add(MONDAY_TIME_1);
        mondatTime.add(MONDAY_TIME_2);
        mondatTime.add(MONDAY_TIME_3);
        mondatTime.add(MONDAY_TIME_4);

        List<String> tuesdayTime = new ArrayList<>();
        tuesdayTime.add(TUESDAY_TIME_0);
        tuesdayTime.add(TUESDAY_TIME_1);
        tuesdayTime.add(TUESDAY_TIME_2);
        tuesdayTime.add(TUESDAY_TIME_3);
        tuesdayTime.add(TUESDAY_TIME_4);

        List<String> wednesdayTime = new ArrayList<>();
        wednesdayTime.add(WEDNESDAY_TIME_0);
        wednesdayTime.add(WEDNESDAY_TIME_1);
        wednesdayTime.add(WEDNESDAY_TIME_2);
        wednesdayTime.add(WEDNESDAY_TIME_3);
        wednesdayTime.add(WEDNESDAY_TIME_4);

        List<String> thursdayTime = new ArrayList<>();
        thursdayTime.add(THURSDAY_TIME_0);
        thursdayTime.add(THURSDAY_TIME_1);
        thursdayTime.add(THURSDAY_TIME_2);
        thursdayTime.add(THURSDAY_TIME_3);
        thursdayTime.add(THURSDAY_TIME_4);

        List<String> fridayTime = new ArrayList<>();
        fridayTime.add(FRIDAY_TIME_0);
        fridayTime.add(FRIDAY_TIME_1);
        fridayTime.add(FRIDAY_TIME_2);
        fridayTime.add(FRIDAY_TIME_3);
        fridayTime.add(FRIDAY_TIME_4);

        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            Sheet sheet = wb.getSheetAt(i);
            System.out.println("Sheet name:" + " " + sheet.getSheetName());

        }
//
//            CellReference ref = new CellReference(cellName);
//            Row row = sheet.getRow(ref.getRow());
//            Cell cell = row.getCell(ref.getCol());
//            String cellData = null;
//            if (DateUtil.isCellInternalDateFormatted(cell)) {
//
//                SimpleDateFormat sdf = new SimpleDateFormat("##:##");
//                cellData = sdf.format(cell.getDateCellValue());
//
//            } else if (cell.getStringCellValue().isEmpty() != true) {
//                cellData = cell.toString();
//
//            }
//
//            return cellData;
    }

    public static List<Group> parse() throws IOException {
        Group group = new Group();
        group.setGroupName("472");
        group.setLastUpdate(LocalDate.now().toString());

        Day day = new Day();
        day.setDayName(doParse("A1", "472"));
    }
//    public static void main(String[] args) throws IOException {
//
//
//        Group group = new Group();
//        group.setGroupName("472");
//        group.setLastUpdate(LocalDate.now().toString());
//
//        Day day = new Day();
//        day.setDayName(doParse("A1", "472"));
//
//        Lesson lesson0 = new Lesson();
//
//        Flasher upper0 = new Flasher();
//        upper0.setName(doParse("B3", "472"));
//        upper0.setTeacher(doParse("C3", "472"));
//        lesson0.setUpperWeek(upper0);
//        lesson0.setStartTime(doParse("A3", "472"));
//
//        Lesson lesson1 = new Lesson();
//
//        Flasher upper1 = new Flasher();
//        upper1.setTeacher(doParse("B5", "472"));
//        upper1.setName(doParse("C5", "472"));
//        lesson1.setUpperWeek(upper1);
//        lesson1.setStartTime(doParse("A5", "472").toString());
//
//        Lesson lesson2 = new Lesson();
//
//        Flasher upper2 = new Flasher();
//        upper2.setName(doParse("B7", "472"));
//        upper2.setTeacher(doParse("C7", "472"));
//        lesson2.setDownWeek(upper2);
//        lesson2.setStartTime(doParse("A7", "472"));
//
//
//        day.setLessons(Arrays.asList(lesson1, lesson2, lesson0));
//
//
//        Day day2 = new Day();
//        day2.setDayName(doParse("D1", "472"));
//
//
//        Lesson lesson2_0 = new Lesson();
//        Flasher upper2_0 = new Flasher();
//        upper2_0.setName(doParse("E3", "472"));
//        upper2_0.setTeacher(doParse("F3", "472"));
//        lesson2_0.setUpperWeek(upper2_0);
//        lesson2_0.setStartTime(doParse("D3", "472"));
//
//        Lesson lesson2_1 = new Lesson();
//        Flasher upper2_1 = new Flasher();
//        upper2_1.setName(doParse("E5", "472"));
//        upper2_1.setTeacher(doParse("F5", "472"));
//        lesson2_1.setUpperWeek(upper2_1);
//        lesson2_1.setStartTime(doParse("D5", "472"));
//
//        Lesson lesson2_2 = new Lesson();
//        Flasher upper2_2 = new Flasher();
//        upper2_2.setName(doParse("E7", "472"));
//        upper2_2.setTeacher(doParse("F7", "472"));
//        lesson2_2.setUpperWeek(upper2_2);
//        lesson2_2.setStartTime(doParse("D7", "472"));
//
//        Lesson lesson2_3 = new Lesson();
//        Flasher upper2_3 = new Flasher();
//        upper2_3.setName(doParse("E9", "472"));
//        upper2_3.setTeacher(doParse("F9", "472"));
//        lesson2_3.setUpperWeek(upper2_3);
//        lesson2_3.setStartTime(doParse("D9", "472"));
//
//        Lesson lesson2_4 = new Lesson();
//        Flasher down2_4 = new Flasher();
//        down2_4.setName(doParse("E12", "472"));
//        down2_4.setTeacher(doParse("F12", "472"));
//        lesson2_4.setDownWeek(down2_4);
//        lesson2_4.setStartTime(doParse("D12", "472"));
//
//
//        day2.setLessons(Arrays.asList(lesson2_0, lesson2_1, lesson2_2, lesson2_3, lesson2_4));
//
//
//        Day day3 = new Day();
//        day3.setDayName(doParse("G1", "472"));
//
//
//        Lesson lesson3_0 = new Lesson();
//        Flasher upper3_0 = new Flasher();
//        upper3_0.setName(doParse("H3", "472"));
//        upper3_0.setTeacher(doParse("I3", "472"));
//        lesson3_0.setUpperWeek(upper3_0);
//        lesson3_0.setStartTime(doParse("G3", "472"));
//
//        Lesson lesson3_1 = new Lesson();
//        Flasher upper3_1 = new Flasher();
//        upper3_1.setName(doParse("H5", "472"));
//        upper3_1.setTeacher(doParse("I5", "472"));
//        lesson3_1.setUpperWeek(upper2_2);
//        lesson3_1.setStartTime(doParse("G5", "472"));
//
//        Lesson lesson3_2 = new Lesson();
//        Flasher upper3_2 = new Flasher();
//        upper3_2.setName(doParse("H7", "472"));
//        upper3_2.setTeacher(doParse("I7", "472"));
//        lesson3_2.setUpperWeek(upper2_2);
//        lesson3_2.setStartTime(doParse("G7", "472"));
//
//        Lesson lesson3_3 = new Lesson();
//        Flasher down3_3 = new Flasher();
//        down3_3.setName(doParse("H10", "472"));
//        down3_3.setTeacher(doParse("I10", "472"));
//        lesson3_3.setDownWeek(down3_3);
//        lesson3_3.setStartTime(doParse("G10", "472"));
//
//
//        day3.setLessons(Arrays.asList(lesson3_0, lesson3_1, lesson3_2, lesson3_3));
//
//
//        Day day4 = new Day();
//        day4.setDayName(doParse("J1", "472"));
//
//
//        Lesson lesson4_3 = new Lesson();
//        Flasher upper4_3 = new Flasher();
//        upper4_3.setName(doParse("K7", "472"));
//        upper4_3.setTeacher(doParse("L7", "472"));
//        lesson4_3.setUpperWeek(upper4_3);
//        lesson4_3.setStartTime(doParse("J7", "472"));
//
//        Lesson lesson4_4 = new Lesson();
//        Flasher upper4_4 = new Flasher();
//        upper4_4.setName(doParse("K9", "472"));
//        upper4_4.setTeacher(doParse("L9", "472"));
//        lesson4_4.setUpperWeek(upper4_4);
//        lesson4_4.setStartTime(doParse("J9", "472"));
//
//
//        day4.setLessons(Arrays.asList(lesson4_3, lesson4_4));
//
//        Day day5 = new Day();
//        day5.setDayName(doParse("M1", "472"));
//
//
//        Lesson lesson5_0 = new Lesson();
//        Flasher upper5_0 = new Flasher();
//        upper5_0.setName(doParse("N3", "472"));
//        upper5_0.setTeacher(doParse("O3", "472"));
//        lesson5_0.setUpperWeek(upper5_0);
//        lesson5_0.setStartTime(doParse("M3", "472"));
//
//        Lesson lesson5_1 = new Lesson();
//        Flasher upper5_1 = new Flasher();
//        upper5_1.setName(doParse("N5", "472"));
//        upper5_1.setTeacher(doParse("O5", "472"));
//        lesson5_1.setUpperWeek(upper5_1);
//        lesson5_1.setStartTime(doParse("M5", "472"));
//
//        Lesson lesson5_2 = new Lesson();
//        Flasher upper5_2 = new Flasher();
//        Flasher down5_2 = new Flasher();
//        down5_2.setName(doParse("N8", "472"));
//        down5_2.setTeacher(doParse("O8", "472"));
//        upper5_2.setName(doParse("N7", "472"));
//        upper5_2.setTeacher(doParse("O7", "472"));
//        lesson5_2.setUpperWeek(upper5_2);
//        lesson5_2.setDownWeek(down5_2);
//        lesson5_2.setStartTime(doParse("M7", "472"));
//
//
//        day5.setLessons(Arrays.asList(lesson5_0, lesson5_1, lesson5_2));
//
//        group.setWeekSchedule(Arrays.asList(day, day2, day3, day4, day5));
//
//        System.out.println(group);
//
//        wb.close();
//
//        return group;
//    }
}
