package ru.bkmz;

import java.util.ArrayList;

public class logic {

    static String slovo = "a,b,c";
    static int sizeSlovo = 20;
    static int sizeTable = 5;

    static ArrayList<String> rootList = new ArrayList<>(), list = new ArrayList<>();
    static int root = 0, current = 0, end = 0;

    public static void main(String[] args) {
        for (String s :
                slovo.split(",")) {
            rootList.add(s);
        }
        genList(sizeSlovo);
        for (String arg :
                list) {
            System.out.println(arg);
        }
        stranTable(sizeTable);
    }

    static void genList(int sizeMax) {
        if (list.size() == 0) {
            for (String arg :
                    rootList) {
                list.add(arg);
            }
            genList(sizeMax);
        }
        while (end < sizeMax - 1) {
            int size = list.size();
            for (int i = root; i < size; i++) {
                for (String arg :
                        rootList) {
                    list.add(list.get(i) + arg);

                }
            }
            System.out.println(end + "/" + sizeMax);
            root = size;
            end++;

        }

    }

    static void stranTable(int size) {
        int max = size;
        String s = "";
        for (int i = 1; i <= max; i++) {
            s += "\t" + i + "\t";
        }
        System.out.println(s);
        s = s.replace("\t", "____");
        for (int i = 0; i < s.length(); i++) {
            System.out.print("_");
        }
        System.out.println();
        for (int y = 1; y <= max; y++) {
            System.out.print("" + y + "|\t");
            for (int x = 1; x <= max; x++) {
                System.out.print(formulaZ(y, x) + "\t\t");
            }
            System.out.println();
        }


    }

    static int formulaZ(int x, int y) {
        return ((x + y - 1) * (x + y - 2) / 2 + y);
    }
}
