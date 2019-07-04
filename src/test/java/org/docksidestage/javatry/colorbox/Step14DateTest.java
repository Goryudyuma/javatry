/*
 * Copyright 2019-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.javatry.colorbox;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of Date with color-box. <br>
 * Show answer by log() for question of javadoc.
 * @author jflute
 * @author your_name_here
 */
public class Step14DateTest extends PlainTestCase {

    // ===================================================================================
    //                                                                               Basic
    //                                                                               =====
    /**
     * What string is date in color-boxes formatted as slash-separated (e.g. 2019/04/24)? <br>
     * (カラーボックスに入っている日付をスラッシュ区切り (e.g. 2019/04/24) のフォーマットしたら？)
     */
    public void test_formatDate() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(one -> one.getContent())
                .filter(one -> one != null)
                .filter(one -> one instanceof LocalDate)
                .map(one -> (LocalDate) one)
                .map(one -> one.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")))
                .forEach(one -> System.out.println(one));
    }

    /**
     * What string of toString() is converted to LocalDate from slash-separated date string (e.g. 2019/04/24) in Set in yellow color-box? <br>
     * (yellowのカラーボックスに入っているSetの中のスラッシュ区切り (e.g. 2019/04/24) の日付文字列をLocalDateに変換してtoString()したら？)
     */
    public void test_parseDate() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");

        colorBoxList.stream()
                .filter(colorBox -> colorBox.getColor().getColorName().equals("yellow"))
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(one -> one.getContent())
                .filter(one -> one instanceof Set)
                .map(one -> (Set) one)
                .flatMap(one -> one.stream())
                .filter(one -> one instanceof String)
                .map(one -> one.toString())
                .map(one -> {
                    try {
                        return simpleDateFormat.parse(one.toString()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    } catch (ParseException e) {
                        return "";
                    }
                })
                .filter(one -> !one.equals(""))
                .forEach(one -> System.out.println(one.toString()));
    }

    /**
     * What is total of month numbers of date in color-boxes? <br>
     * (カラーボックスに入っている日付の月を全て足したら？)
     */
    public void test_sumMonth() {
    }

    /**
     * What day of week is second-found date in color-boxes added to three days? <br>
     * (カラーボックスに入っている二番目に見つかる日付に3日進めると何曜日？)
     */
    public void test_plusDays_weekOfDay() {
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * How many days (number of day) are between two dates in yellow color-boxes?   <br>
     * (yellowのカラーボックスに入っている二つの日付は何日離れている？)
     */
    public void test_diffDay() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        List<LocalDate> ans = colorBoxList.stream()
                .filter(colorBox -> colorBox.getColor().getColorName().equals("yellow"))
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(one -> one.getContent())
                .filter(one -> one instanceof LocalDate || one instanceof LocalDateTime)
                .map(one -> {
                    if (one instanceof LocalDate) {
                        return ((LocalDate) one).atTime(0, 0, 0);
                    } else {
                        return (LocalDateTime) one;
                    }
                })
                .map(one -> one.toLocalDate())
                .collect(Collectors.toList());

        System.out.println(ChronoUnit.DAYS.between(ans.get(0), ans.get(1)));
    }

    /**
     * What date is LocalDate in yellow color-box
     * that is month-added with LocalDateTime's seconds in the same color-box,
     * and is day-added with Long value in red color-box,
     * and is day-added with the first decimal place of BigDecimal that has three (3) as integer in List in color-boxes? <br>
     * (yellowのカラーボックスに入っているLocalDateに、同じカラーボックスに入っているLocalDateTimeの秒数を月数として足して、
     * redのカラーボックスに入っているLong型を日数として足して、カラーボックスに入っているリストの中のBigDecimalの整数値が3の小数点第一位の数を日数として引いた日付は？)
     */
    public void test_birthdate() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        colorBoxList.stream()
                .filter(colorBox -> colorBox.getColor().getColorName().equals("yellow"))
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(one -> one.getContent())
                .filter(one -> one instanceof LocalDate)
                .map(one -> ((LocalDate) one).plusMonths(colorBoxList.stream()
                        .filter(colorBox -> colorBox.getColor().getColorName().equals("yellow"))
                        .flatMap(colorBox -> colorBox.getSpaceList().stream())
                        .map(two -> two.getContent())
                        .filter(two -> two instanceof LocalDateTime)
                        .map(two -> ((LocalDateTime) two).getSecond())
                        .map(two -> two.longValue())
                        .collect(Collectors.toList())
                        .get(0)))
                .map(one -> ((LocalDate) one).plusDays(colorBoxList.stream()
                        .filter(colorBox -> colorBox.getColor().getColorName().equals("red"))
                        .flatMap(colorBox -> colorBox.getSpaceList().stream())
                        .map(two -> two.getContent())
                        .filter(two -> two instanceof Long)
                        .map(two -> (Long) two)
                        .collect(Collectors.toList())
                        .get(0)))
                .map(one -> (one.minusDays((long) ((List) colorBoxList.stream()
                        .filter(colorBox -> colorBox.getColor().getColorName().equals("yellow"))
                        .flatMap(colorBox -> colorBox.getSpaceList().stream())
                        .map(two -> two.getContent())
                        .filter(two -> two instanceof List)
                        .map(two -> (List) two)
                        .flatMap(two -> two.stream())
                        .filter(two -> two instanceof BigDecimal)
                        .map(two -> ((BigDecimal) two).doubleValue())
                        .filter(two -> ((Double) two).longValue() % 10 == 3)
                        .map(two -> ((long) (((double) two) * 10)) % 10)
                        .collect(Collectors.toList())).get(0))))
                .forEach(one -> System.out.println(one));

    }

    /**
     * What second is LocalTime in color-boxes? <br>
     * (カラーボックスに入っているLocalTimeの秒は？)
     */
    public void test_beReader() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(one -> one.getContent())
                .filter(one -> one != null)
                .filter(one -> one instanceof LocalDateTime)
                .map(one -> ((LocalDateTime) one).getSecond())
                .forEach(one -> System.out.println(one));
    }
}
