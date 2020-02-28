package ru.bkmz.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class controllerMain {
    public TextField txt;
    public TextField size;
    public TextArea out;
    public TextField size2;
    public TextArea out2;


    static ArrayList<String> rootList = new ArrayList<>(), list = new ArrayList<>();
    static int root = 0, current = 0, end = 0;

    public void initialize() {

    }

    public void start(ActionEvent actionEvent) {
        new Thread(new Runnable() {
            @Override public void run() {
                root = 0;
                current = 0;
                end = 0;
                rootList = new ArrayList<>();
                list = new ArrayList<>();
                for (String s :
                        txt.getText().split(",")) {
                    rootList.add(s);
                }
                genList(Integer.parseInt(size.getText()));
                StringBuilder s = new StringBuilder();
                for (String arg :
                        list) {
                    s.append(arg).append("\n");
                }
                Platform.runLater(new Runnable() {

                    @Override public void run() {
                        out.setText(s.toString());
                    }
                });

            }
        }).start();
    }


    public void start2(ActionEvent actionEvent) {
        new Thread(new Runnable() {
            @Override public void run() {
                root = 0;
                current = 0;
                end = 0;
                rootList = new ArrayList<>();
                list = new ArrayList<>();

                out2.setText(stranTable(Integer.parseInt(size2.getText())));
            }
        }).start();

    }

    void genList(int sizeMax) {
        if (list.size() == 0) {
            for (String arg :
                    rootList) {
                list.add(arg);
            }
            genList(sizeMax);
        }
        while (end < sizeMax - 1) {
            Platform.runLater(new Runnable() {
                @Override public void run() {
                    out.setText(end + "/" + sizeMax);
                }
            });
            int size = list.size();
            for (int i = root; i < size; i++) {
                for (String arg :
                        rootList) {
                    list.add(list.get(i) + arg);


                }
            }

            root = size;
            end++;

        }

    }

    String stranTable(int size) {
        StringBuffer stringBuffer = new StringBuffer();
        int max = size;
        String s = "";
        for (int i = 1; i <= max; i++) {
            s += "\t" + i + "\t";
        }
        stringBuffer.append(s).append("\n");
        s = s.replace("\t", "____");
        stringBuffer.append("\t");
        for (int i = 0; i < s.length(); i++) {
            stringBuffer.append("_");
        }
        stringBuffer.append("\n");
        for (int y = 1; y <= max; y++) {
            stringBuffer.append("" + y + "|\t");
            for (int x = 1; x <= max; x++) {
                stringBuffer.append(formulaZ(y, x) + "\t\t");
            }
            stringBuffer.append("\n");
        }

        return stringBuffer.toString();
    }

    static int formulaZ(int x, int y) {
        return ((x + y - 1) * (x + y - 2) / 2 + y);
    }

}