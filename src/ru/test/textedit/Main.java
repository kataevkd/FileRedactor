package ru.test.textedit;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Main implements ActionListener {
    File file = new File(""); // path to file

    public Main() {


        JFrame frame = new JFrame("Text Editor"); // создали фрейм с заголовком (создание окна приложения)
        frame.setSize(800, 600); // задаем фрейму размеры
        frame.setVisible(true); // отображение фрейма параметр true
        frame.setLocationRelativeTo(null);

        JButton buttonOpen = new JButton("Open file"); // кнопка открыть файл
        buttonOpen.setLocation(10, 10); // задаем отступы слева и сверху для кнопки
        buttonOpen.setSize(100, 50); // ширина и высота кнопки

        JButton buttonSave = new JButton("Save file"); // кнопка сохранения
        buttonSave.setLocation(120, 10);  // задаем отступы слева и сверху для кнопки
        buttonSave.setSize(100, 50); // ширина и высота кнопки

        JButton buttonNew = new JButton("Create File"); // создание нового файла кнопка
        buttonNew.setLocation(230, 10);  // задаем отступы слева и сверху для кнопки
        buttonNew.setSize(100, 50); // ширина и высота кнопки

        JTextArea area = new JTextArea(); // создаем текстовое поле
        area.setLocation(10, 70); // размещаем поле
        area.setSize(770, 530); // size of our area
        ///////////////////////////////
        buttonOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser(); // создание окна диалога для работы с файлами
                chooser.showOpenDialog(frame); // вызов окна для сохранения
                file = chooser.getSelectedFile(); // получаем выбранный файл
                try (FileReader reader = new FileReader(file)) {
                    char[] buf = new char[(int) file.length()]; // буферная переменная для хранения файла
                    reader.read(buf); // считываем данные из файла
                    area.setText(new String(buf));
                } catch (Exception e2) {

                }

            }
        });

        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try (FileWriter writer = new FileWriter(file)) {

                    writer.write(area.getText()); // Записываем текст с области
                    writer.flush(); // передача данных из буфера

                } catch (Exception e){

                }

            }
        });

        buttonNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser(); // создание окна диалога для работы с файлами
                if (chooser.showSaveDialog(null)== JFileChooser.APPROVE_OPTION){ //  выбор файла в диалоговом окне прошел успешно; выбранный файл можно получить методом getFile();
                    file = chooser.getSelectedFile();
                    try (FileWriter writer = new FileWriter(file)) {

                        writer.write(""); // ничего не записываем так как у нас создается новый файл
                        writer.flush(); // передача данных из буфера

                    } catch (Exception e1){

                    }
                }
            }
        });

        ////////////////////////////////
        // Добавляем во фрейм компоненты 
        frame.add(buttonOpen);
        frame.add(buttonSave);
        frame.add(buttonNew);
        frame.add(area);
        frame.add(new JLabel()); // чтобы все было на своем месте - заполнить пустое пространство
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main(); // вызываваем экзмепляр
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}