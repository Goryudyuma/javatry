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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of Number with color-box. <br>
 * Show answer by log() for question of javadoc.
 * @author jflute
 * @author your_name_here
 */
public class Step13NumberTest extends PlainTestCase {

    // ===================================================================================
    //                                                                               Basic
    //                                                                               =====
    /**
     * How many integer-type values in color-boxes are between 0 and 54? <br>
     * (カラーボックの中に入っているInteger型で、0から54までの値は何個ある？)
     */
    public void test_countZeroToFiftyFour_IntegerOnly() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        long ans = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(space -> space.getContent())
                .filter(one -> one instanceof Integer)
                .map(one -> (Integer) (one))
                .filter(one -> 0 <= one && one <= 54)
                .count();
        log(ans);
    }

    /**
     * How many number values in color-boxes are between 0 and 54? <br>
     * (カラーボックの中に入っている数値で、0から54までの値は何個ある？)
     */
    public void test_countZeroToFiftyFour_Number() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        long ans = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(space -> space.getContent())
                .filter(one -> one instanceof Number)
                .map(one -> ((Number) (one)).doubleValue())
                .filter(one -> 0 <= one && one <= 54)
                .count();
        log(ans);
    }

    /**
     * What color name is used by color-box that has integer-type content and the biggest width in them? <br>
     * (カラーボックスの中で、Integer型の Content を持っていてBoxSizeの幅が一番大きいカラーボックスの色は？)
     */
    public void test_findColorBigWidthHasInteger() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String ans = colorBoxList.stream()
                .filter(colorBox -> colorBox.getSpaceList().stream().anyMatch(space -> space.getContent() instanceof Integer))
                .reduce((a, b) -> {
                    return a.getSize().getWidth() < b.getSize().getWidth() ? b : a;
                })
                .get()
                .getColor()
                .getColorName();
        log(ans);

    }

    /**
     * What is total of BigDecimal values in List in color-boxes? <br>
     * (カラーボックスの中に入ってる List の中の BigDecimal を全て足し合わせると？)
     */
    public void test_sumBigDecimalInList() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(space -> space.getContent())
                .filter(one -> one instanceof List)
                .map(one -> (List) one)
                .filter(one -> one.size() != 0 && one.get(0) instanceof BigDecimal)
                .map(one -> one.stream().filter(a -> a instanceof BigDecimal).reduce((a, b) -> ((BigDecimal) a).add((BigDecimal) b)))
                .collect(Collectors.toList())
                .forEach(one -> log(one.get()));
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * What key is related to value that is max number in Map that has only number in color-boxes? <br>
     * (カラーボックスに入ってる、valueが数値のみの Map の中で一番大きいvalueのkeyは？)
     */
    public void test_findMaxMapNumberValue() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(space -> space.getContent())
                .filter(one -> one instanceof Map)
                .map(one -> ((Map) one))
                .filter(one -> one.values().stream().allMatch(n -> n instanceof Number))
                .map(one -> ((Map.Entry) Arrays.stream(((Map) one).entrySet().toArray())
                        .reduce((a, b) -> (Integer) ((Map.Entry) a).getValue() > (Integer) ((Map.Entry) b).getValue() ? a : b)
                        .get()).getKey())
                .forEach(one -> log(one));
    }

    /**
     * What is total of number or number-character values in Map in purple color-box? <br> 
     * (purpleのカラーボックスに入ってる Map の中のvalueの数値・数字の合計は？)
     */
    public void test_sumMapNumberValue() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        Integer sum = (Integer) colorBoxList.stream()
                .filter(colorBox -> colorBox.getColor().getColorName().equals("purple"))
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .filter(one -> one.getContent() instanceof Map)
                .map(one -> ((Map) one.getContent()).values())
                .filter(one -> !one.isEmpty())
                .flatMap(one -> one.stream())
                .filter(one -> one instanceof Integer)
                .reduce(0, (a, b) -> ((Integer) a) + ((Integer) b));
        log(sum);
    }
}
