import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Actions {
    /*
     * метод, обработка и добавление данных об игрушке в файл
     */
    public void addToy() {
        Scanner scanner = new Scanner(System.in);

        int id = getPrizesList().size() + 1;

        System.out.println("Введите название игрушки: ");
        String name = scanner.nextLine();

        String count;
        do {
            System.out.println("Введите количество игрушек: ");
            count = scanner.nextLine();
        } while (!count.matches("\\d+"));

        String priority;
        do {
            System.out.println("Введите шанс получения игрушки от 1 до 9: ");
            priority = scanner.nextLine();
        } while (!priority.matches("[1-9]"));

        File_Work.writeData(new Toy(id, name, Integer.parseInt(count), Integer.parseInt(priority)), true);
        scanner.close();
    }

    /*
     * метод, получение данных о всех игрушках в виде списка
     */
    public List<Toy> getPrizesList() {
        return File_Work.readData();
    }

    /*
     * метод получения приза
     */
    public String getPrize() {
        List<Toy> toyList = File_Work.readData();
        int value = priorityValue();

        for (Toy toy : toyList) {
            if (toy.getFrequency() == value) {
                if (toy.getCount() > 0) {
                    toy.setCount(toy.getCount() - 1);
                    File_Work.reWriteData(toyList);
                    return toy.getName();
                } else {
                    getPrize();
                    toyList.remove(toy);
                    File_Work.reWriteData(toyList);
                }
            }
        }
        return getPrize();
    }

    /*
     * генерация рандомного значения, для получения приза(игрушки)
     * чем меньше значение, тем больше шанс получить приз.
     */
    private int priorityValue() {
        int seed = new Random().nextInt(9) + 1;
        return new Random().nextInt(seed + 1);
    }

    public void showToysList() {
        List<Toy> list = File_Work.readData();
        System.out.println("============================================");
        System.out.println("=========== Список игрушек =================");
        System.out.println("============================================");
        for (Toy toy : list) {
            System.out.printf("Название игрушки: %s, Количество: %d шт., Коэффициент получения приза: %d\n", toy.getName(), toy.getCount(), (toy.getFrequency()));
            System.out.println("__________________________________________________________________________");
        }
        
    }

}
