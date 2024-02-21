import java.util.*;

// Класс Laptop для хранения информации о каждом ноутбуке
class Laptop {
    private String model;
    private int ramSize;
    private int hddSize;
    private String os;
    private String color;

    // Конструктор для создания нового экземпляра класса Laptop
    public Laptop(String model, int ramSize, int hddSize, String os, String color) {
        this.model = model;
        this.ramSize = ramSize;
        this.hddSize = hddSize;
        this.os = os;
        this.color = color;
    }

    // Методы доступа к полям
    public String getModel() {
        return model;
    }

    public int getRamSize() {
        return ramSize;
    }

    public int getHddSize() {
        return hddSize;
    }

    public String getOs() {
        return os;
    }

    public String getColor() {
        return color;
    }

    // Метод для вывода информации о ноутбуке
    public void printInfo() {
        System.out.println("Model: " + model);
        System.out.println("RAM size: " + ramSize + " GB");
        System.out.println("HDD size: " + hddSize + " GB");
        System.out.println("Operating system: " + os);
        System.out.println("Color: " + color);
        System.out.println();
    }
}

// Класс TechStore для хранения и фильтрации ноутбуков
public class TechStore {
    private Set<Laptop> laptops;

    // Конструктор для создания магазина с пустым множеством ноутбуков
    public TechStore() {
        laptops = new HashSet<>();
    }

    // Метод для добавления нового ноутбука в магазин
    public void addLaptop(Laptop laptop) {
        laptops.add(laptop);
    }

    // Метод для фильтрации и вывода информации о ноутбуках
    public void filterAndPrint(Map<Integer, Object> filter) {
        System.out.println("Found laptops:");
        List<Laptop> filteredLaptops = new ArrayList<>();
        for (Laptop laptop : laptops) {
            boolean matchesFilter = true;
            for (Map.Entry<Integer, Object> entry : filter.entrySet()) {
                Integer criterion = entry.getKey();
                Object value = entry.getValue();
                switch (criterion) {
                    case 1:
                        if (laptop.getRamSize() < (int) value) {
                            matchesFilter = false;
                        }
                        break;
                    case 2:
                        if (laptop.getHddSize() < (int) value) {
                            matchesFilter = false;
                        }
                        break;
                    case 3:
                        String osFilter = (String) value;
                        if (!laptop.getOs().equalsIgnoreCase(osFilter)) {
                            matchesFilter = false;
                        }
                        break;
                    case 4:
                        if (!laptop.getColor().equals(value)) {
                            matchesFilter = false;
                        }
                        break;
                    default:
                        System.out.println("Unknown criterion: " + criterion);
                        return;
                }
            }
            if (matchesFilter) {
                filteredLaptops.add(laptop);
            }
        }

        if (filteredLaptops.isEmpty()) {
            System.out.println("No laptops found matching the criteria.");
        } else {
            for (Laptop laptop : filteredLaptops) {
                laptop.printInfo();
            }
        }
    }

    public static void main(String[] args) {
        // Создаем магазин техники
        TechStore store = new TechStore();

        // Создаем несколько ноутбуков и добавляем их в магазин
        Laptop laptop1 = new Laptop("Dell", 16, 512, "Windows", "Black");
        Laptop laptop2 = new Laptop("HP", 8, 256, "Linux", "Gray");
        Laptop laptop3 = new Laptop("Lenovo", 32, 1024, "Windows", "Blue");
        Laptop laptop4 = new Laptop("Asus", 16, 512, "Windows", "Black");
        store.addLaptop(laptop1);
        store.addLaptop(laptop2);
        store.addLaptop(laptop3);
        store.addLaptop(laptop4);

        // Запрашиваем критерии фильтрации у пользователя
        Scanner scanner = new Scanner(System.in);
        Map<Integer, Object> filter = new HashMap<>();
        System.out.println("Введите цифру, соответствующую критерию фильтрации:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");
        int criterion = scanner.nextInt();
        Object value;
        switch (criterion) {
            case 1:
            case 2:
                System.out.println("Введите минимальное значение:");
                value = scanner.nextInt();
                break;
            case 3:
            case 4:
                System.out.println("Введите значение:");
                scanner.nextLine(); // Чтение лишнего перевода строки после цифры
                value = scanner.nextLine();
                break;
            default:
                System.out.println("Некорректный критерий фильтрации.");
                return;
        }
        filter.put(criterion, value);

        // Фильтруем и выводим результат
        store.filterAndPrint(filter);
    }
}
