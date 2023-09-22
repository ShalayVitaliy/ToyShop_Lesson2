
import java.util.Scanner;

public class Choice_of_action extends Actions {


    public void startApp() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                Выберите действие (введите нужное число):
                1. Получить приз.
                2. Показать список всех игрушек.
                3. Добавить игрушку в список
                0. Выход из программы.""");
        String input = scanner.nextLine();

        while (true) {

            if (input.equals("1")) {
                
                System.out.printf("ПОЗДРАВЛЯЕ!!! ВАШ ПРИЗ: >>> %s <<<\n", getPrize());
                break;

            }

            if (input.equals("2")) {
                showToysList();

                System.out.println("============================================");
                System.out.println("Участвовать в розыгрыше, введите: \"1\", Для выхода введите: \"0\"");
                input = scanner.nextLine();
            }
            if (input.equals("3")) {
                addToy();
                break;
                
            }
            if (input.equals("0")) {
                break;
            
        }
        scanner.close();
    }
}
}
