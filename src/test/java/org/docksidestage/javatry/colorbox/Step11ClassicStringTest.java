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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.color.BoxColor;
import org.docksidestage.bizfw.colorbox.space.BoxSpace;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of String with color-box, not using Stream API. <br>
 * Show answer by log() for question of javadoc. <br>
 * <pre>
 * addition:
 * o e.g. "string in color-boxes" means String-type content in space of color-box
 * o don't fix the YourPrivateRoom class and color-box classes
 * </pre>
 * @author jflute
 * @author your_name_here
 */
public class Step11ClassicStringTest extends PlainTestCase {

    // ===================================================================================
    //                                                                            length()
    //                                                                            ========
    /**
     * How many lengths does color name of first color-boxes have? <br>
     * (最初のカラーボックスの色の名前の文字数は？)
     */
    public void test_length_basic() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        if (!colorBoxList.isEmpty()) {
            ColorBox colorBox = colorBoxList.get(0);
            BoxColor boxColor = colorBox.getColor();
            String colorName = boxColor.getColorName();
            int answer = colorName.length();
            log(answer + " (" + colorName + ")"); // also show name for visual check
        } else {
            log("*not found");
        }
    }

    /**
     * Which string has max length in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長い文字列は？)
     */
    public void test_length_findMax() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int ans = -1;
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : spaceList) {
                Object content = boxSpace.getContent();
                if (content instanceof String) {
                    int length = ((String) content).length();
                    ans = Math.max(length, ans);
                }
            }
        }
        log(ans);
    }

    /**
     * How many characters are difference between max and min length of string in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長いものと短いものの差は何文字？)
     */
    public void test_length_findMaxMinDiff() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int maxValue = Integer.MIN_VALUE, minValue = Integer.MAX_VALUE;
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : spaceList) {
                Object content = boxSpace.getContent();
                if (content instanceof String) {
                    int length = ((String) content).length();
                    maxValue = Math.max(length, maxValue);
                    minValue = Math.min(length, minValue);
                }
            }
        }
        log(maxValue - minValue);
    }

    /**
     * Which value (toString() if non-string) has second-max legnth in color-boxes? (without sort)<br>
     * (カラーボックスに入ってる値 (文字列以外はtoString()) の中で、二番目に長い文字列は？ (ソートなしで))
     */
    public void test_length_findSecondMax() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int ans1 = -1, ans2 = -1;
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : spaceList) {
                Object content = boxSpace.getContent();
                if (content != null) {
                    int length = (content.toString()).length();
                    if (ans1 < length) {
                        int tmp = length;
                        length = ans1;
                        ans1 = tmp;
                    }
                    if (ans2 < length) {
                        int tmp = length;
                        length = ans2;
                        ans2 = tmp;
                    }
                }
            }
        }
        log(ans2);
    }

    /**
     * How many total lengths of strings in color-boxes? <br>
     * (カラーボックスに入ってる文字列の長さの合計は？)
     */
    public void test_length_calculateLengthSum() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int sum = 0;
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : spaceList) {
                Object content = boxSpace.getContent();
                if (content instanceof String) {
                    int length = ((String) content).length();
                    sum += length;
                }
            }
        }
        log(sum);
    }

    /**
     * Which color name has max length in color-boxes? <br>
     * (カラーボックスの中で、色の名前が一番長いものは？)
     */
    public void test_length_findMaxColorSize() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int max = -1;
        String name = "";
        for (ColorBox colorBox : colorBoxList) {
            String colorName = colorBox.getColor().getColorName();
            int length = colorName.length();
            if (max < length) {
                name = colorName;
                max = length;
            }
        }
        log(max, name);
    }

    // ===================================================================================
    //                                                            startsWith(), endsWith()
    //                                                            ========================
    /**
     * What is color in the color-box that has string starting with "Water"? <br>
     * ("Water" で始まる文字列をしまっているカラーボックスの色は？)
     */
    public void test_startsWith_findFirstWord() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String ans = "";
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : spaceList) {
                Object content = boxSpace.getContent();
                if (content instanceof String) {
                    String s = content.toString();
                    if (s.startsWith("Water")) {
                        ans = colorBox.getColor().toString();
                    }
                }
            }
        }
        log(ans);
    }

    /**
     * What is color in the color-box that has string ending with "front"? <br>
     * ("front" で終わる文字列をしまっているカラーボックスの色は？)
     */
    public void test_endsWith_findLastWord() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String ans = "";
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : spaceList) {
                Object content = boxSpace.getContent();
                if (content instanceof String) {
                    String s = content.toString();
                    if (s.endsWith("front")) {
                        ans = colorBox.getColor().toString();
                    }
                }
            }
        }
        log(ans);
    }

    // ===================================================================================
    //                                                            indexOf(), lastIndexOf()
    //                                                            ========================
    /**
     * What number character is starting with "front" of string ending with "front" in color-boxes? <br>
     * (あなたのカラーボックスに入ってる "front" で終わる文字列で、"front" は何文字目から始まる？)
     */
    public void test_indexOf_findIndex() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int ans = -1;
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : spaceList) {
                Object content = boxSpace.getContent();
                if (content instanceof String) {
                    String s = content.toString();
                    if (s.endsWith("front")) {
                        ans = s.indexOf("front");
                    }
                }
            }
        }
        log(ans);
    }

    /**
     * What number character is starting with the late "ど" of string containing plural "ど"s in color-boxes? (e.g. "どんどん" => 3) <br>
     * (あなたのカラーボックスに入ってる「ど」を二つ以上含む文字列で、最後の「ど」は何文字目から始まる？ (e.g. "どんどん" => 3))
     */
    public void test_lastIndexOf_findIndex() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int ans = -1;
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : spaceList) {
                Object content = boxSpace.getContent();
                if (content instanceof String) {
                    String s = content.toString();
                    int count = 0;
                    char[] ss = s.toCharArray();
                    for (int i = 0; i < ss.length; i++) {
                        if (ss[i] == 'ど') {
                            count++;
                            if (count >= 2) {
                                ans = i;
                            }
                        }
                    }
                }
            }
        }
        log(ans);
    }

    // ===================================================================================
    //                                                                         substring()
    //                                                                         ===========
    /**
     * What character is first of string ending with "front" in color-boxes? <br>
     * (カラーボックスに入ってる "front" で終わる文字列の最初の一文字は？)
     */
    public void test_substring_findFirstChar() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        char ans = ' ';
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : spaceList) {
                Object content = boxSpace.getContent();
                if (content instanceof String) {
                    String s = content.toString();
                    if (s.endsWith("front")) {
                        ans = s.toCharArray()[0];
                    }
                }
            }
        }
        log(ans);
    }

    /**
     * What character is last of string starting with "Water" in color-boxes? <br>
     * (カラーボックスに入ってる "Water" で始まる文字列の最後の一文字は？)
     */
    public void test_substring_findLastChar() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        char ans = ' ';
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : spaceList) {
                Object content = boxSpace.getContent();
                if (content instanceof String) {
                    String s = content.toString();
                    if (s.startsWith("Water")) {
                        ans = s.toCharArray()[s.toCharArray().length - 1];
                    }
                }
            }
        }
        log(ans);
    }

    // ===================================================================================
    //                                                                           replace()
    //                                                                           =========
    /**
     * How many characters does string that contains "o" in color-boxes and removing "o" have? <br>
     * (カラーボックスに入ってる "o" (おー) を含んだ文字列から "o" を全て除去したら何文字？)
     */
    public void test_replace_remove_o() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int ans = -1;
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : spaceList) {
                Object content = boxSpace.getContent();
                if (content instanceof String) {
                    String s = content.toString();
                    if (s.contains("o")) {
                        int count = 0;
                        for (char c : s.toCharArray()) {
                            if (c == 'o') {
                                count++;
                            }
                        }
                        ans = count;
                    }
                }
            }
        }
        log(ans);
    }

    /**
     * What string is path string of java.io.File in color-boxes, which is replaced with "/" to Windows file separator? <br>
     * カラーボックスに入ってる java.io.File のパス文字列のファイルセパレーターの "/" を、Windowsのファイルセパレーターに置き換えた文字列は？
     */
    public void test_replace_fileseparator() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String ans = "";
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : spaceList) {
                Object content = boxSpace.getContent();
                if (content instanceof java.io.File) {
                    String s = content.toString();
                    ans = s.replace("/", "\\");
                }
            }
        }
        log(ans);
    }

    // ===================================================================================
    //                                                                    Welcome to Devil
    //                                                                    ================
    /**
     * What is total length of text of DevilBox class in color-boxes? <br>
     * (カラーボックスの中に入っているDevilBoxクラスのtextの長さの合計は？)
     */
    public void test_welcomeToDevil() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int ans = 0;
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : spaceList) {
                Object content = boxSpace.getContent();
                if (content instanceof YourPrivateRoom.DevilBox) {
                    ((YourPrivateRoom.DevilBox) content).wakeUp();
                    ((YourPrivateRoom.DevilBox) content).allowMe();
                    ((YourPrivateRoom.DevilBox) content).open();
                    try {
                        int length = ((YourPrivateRoom.DevilBox) content).getText().length();
                        ans += length;
                    } catch (Exception ignored) {
                    }
                }
            }
        }
        log(ans);
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * What string is converted to style "map:{ key = value ; key = value ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = value ; ... }" という形式で表示すると？)
     */
    public void test_showMap_flat() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : spaceList) {
                Object content = boxSpace.getContent();
                if (content instanceof java.util.Map) {
                    log("map:" + content.toString().replace(",", ";"));
                }
            }
        }
    }

    /**
     * What string is converted to style "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" という形式で表示すると？)
     */
    public void test_showMap_nested() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : spaceList) {
                Object content = boxSpace.getContent();
                if (content instanceof java.util.Map) {
                    log("map:" + content.toString().replace(",", ";").replace("={", "=map:{ ").replace("=", " = "));
                }
            }
        }
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * What string of toString() is converted from text of SecretBox class in upper space on the "white" color-box to java.util.Map? <br>
     * (whiteのカラーボックスのupperスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
     */
    public void test_parseMap_flat() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String ans = "";
        for (ColorBox colorBox : colorBoxList) {
            if (colorBox.getColor().toString().equals("{white}")) {
                List<BoxSpace> spaceList = colorBox.getSpaceList();
                BoxSpace boxSpace = spaceList.get(0);
                YourPrivateRoom.SecretBox content = (YourPrivateRoom.SecretBox) boxSpace.getContent();
                ans = content.getText();
            }
        }
        log(ans);
        log(stringToMap(ans).toString());
    }

    private Map<String, Object> stringToMap(String S) {
        Map<String, Object> ret = new HashMap<>();

        String sRaw = S.substring(5, S.length() - 1).trim();
        int beginPoint = 0;

        int level = 0;
        for (int i = 0; i < sRaw.length(); i++) {
            char c = sRaw.charAt(i);
            if (c == '{') {
                level++;
            } else if (c == '}') {
                level--;
            } else if (c == ';' && level == 0) {
                String ss = sRaw.substring(beginPoint, i).trim();
                String key = ss.substring(0, ss.indexOf("=") - 1).trim();
                String value = ss.substring(ss.indexOf("=") + 1).trim();

                if (value.startsWith("map:")) {
                    ret.put(key, stringToMap(value));
                } else {
                    ret.put(key, value);
                }
                beginPoint = i + 1;
            }
        }
        String ss = sRaw.substring(beginPoint).trim();
        String key = ss.substring(0, ss.indexOf("=") - 1).trim();
        String value = ss.substring(ss.indexOf("=") + 1).trim();

        if (value.startsWith("map:")) {
            ret.put(key, stringToMap(value));
        } else {
            ret.put(key, value);
        }
        return ret;
    }

    /**
     * What string of toString() is converted from text of SecretBox class in both middle and lower spaces on the "white" color-box to java.util.Map? <br>
     * (whiteのカラーボックスのmiddleおよびlowerスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
     */
    public void test_parseMap_nested() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String ans1 = "", ans2 = "";
        for (ColorBox colorBox : colorBoxList) {
            if (colorBox.getColor().toString().equals("{white}")) {
                List<BoxSpace> spaceList = colorBox.getSpaceList();
                {
                    BoxSpace boxSpace = spaceList.get(1);
                    YourPrivateRoom.SecretBox content = (YourPrivateRoom.SecretBox) boxSpace.getContent();
                    ans1 = content.getText();
                }
                {
                    BoxSpace boxSpace = spaceList.get(2);
                    YourPrivateRoom.SecretBox content = (YourPrivateRoom.SecretBox) boxSpace.getContent();
                    ans2 = content.getText();
                }
            }
        }
        log(ans1);
        log(stringToMap(ans1).toString());
        log(ans2);
        log(stringToMap(ans2).toString());
    }
}
