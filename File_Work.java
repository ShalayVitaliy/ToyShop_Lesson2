import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class File_Work {
    private final static String path_toys_list = "toys_lottery_list.txt";

    /*
     * метод добавления игрушки в файл
     * toy - игрушка, которую нужно добавить в список
     * newLine - параметр для записи в файл. true: добавление с новой строки
     */
    public static void writeData(Toy toy, boolean newLine) {

        try (FileWriter writer = new FileWriter(path_toys_list, newLine)) {
            writer.write(toy.getId() + ",");
            writer.write(toy.getName() + ",");
            writer.write(toy.getCount() + ",");
            writer.write(toy.getFrequency() + "\n");
            writer.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /*
     * метод перезаписывает данные в файле
     * 
     * @toysList - список игрушек
     */
    public static void reWriteData(List<Toy> toysList) {
        int id = 1;
        for (Toy toy : toysList) {
            if (toy.getId() == 1) {
                toy.setId(id++);
                writeData(toy, false);
                continue;
            }
            toy.setId(id++);
            writeData(toy, true);
        }
    }

    /*
     * метод получает данные о игрушках из файла
     * 
     * @return - список игрушек
     */
    public static List<Toy> readData() {

        try {
            new File(path_toys_list).createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Toy> list = new ArrayList<>();

        try (BufferedReader bf = new BufferedReader(new FileReader(path_toys_list))) {
            String str;
            while ((str = bf.readLine()) != null) {

                String[] array = str.split(",");
                try {
                    Toy toy = new Toy(Integer.parseInt(array[0]), array[1], Integer.parseInt(array[2]),Integer.parseInt(array[3]));
                    list.add(toy);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
