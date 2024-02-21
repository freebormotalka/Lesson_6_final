import java.util.*;

public class LaptopMain {
    public static void main(String[] args) {
        Laptop laptop1 = new Laptop("Asus", "Intel Core i5", 8, 512, "Windows 10", "Silver");
        Laptop laptop2 = new Laptop("Lenovo", "AMD Ryzen 7", 16, 1000, "Ubuntu", "Black");
        Laptop laptop3 = new Laptop("HP", "Intel Core i7", 16, 1000, "Windows 11", "White");
        Laptop laptop4 = new Laptop("Dell", "Intel Core i3", 4, 256, "Windows 10", "Black");
        Laptop laptop5 = new Laptop("Apple", "Apple M1", 16, 512, "macOS", "Silver");

        Set<Laptop> laptopSet = new HashSet<>();
        laptopSet.add(laptop1);
        laptopSet.add(laptop2);
        laptopSet.add(laptop3);
        laptopSet.add(laptop4);
        laptopSet.add(laptop5);

        Scanner scanner = new Scanner(System.in);
        Map<String, String> criteriaMap = new HashMap<>();
        System.out.println("Введите цифру, соответствующую необходимому критерию:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");

        int criteria = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        String criterion = "";
        switch (criteria) {
            case 1:
                criterion = "RAM";
                break;
            case 2:
                criterion = "HDD";
                break;
            case 3:
                criterion = "OS";
                break;
            case 4:
                criterion = "color";
                break;
            default:
                System.out.println("Некорректный критерий.");
                System.exit(1);
        }

        System.out.println("Введите минимальное значение для выбранного критерия:");
        String minValue = scanner.nextLine();
        criteriaMap.put(criterion, minValue);

        filterLaptops(laptopSet, criteriaMap);
    }

    public static void filterLaptops(Set<Laptop> laptops, Map<String, String> criteriaMap) {
        List<Laptop> filteredLaptops = new ArrayList<>();
        for (Laptop laptop : laptops) {
            boolean pass = true;
            for (Map.Entry<String, String> entry : criteriaMap.entrySet()) {
                String criterion = entry.getKey();
                String minValue = entry.getValue();
                switch (criterion) {
                    case "RAM":
                        if (laptop.getRAM() < Integer.parseInt(minValue)) {
                            pass = false;
                        }
                        break;
                    case "HDD":
                        if (laptop.getHDD() < Integer.parseInt(minValue)) {
                            pass = false;
                        }
                        break;
                    case "OS":
                        if (!laptop.getOS().equalsIgnoreCase(minValue)) {
                            pass = false;
                        }
                        break;
                    case "color":
                        if (!laptop.getColor().equalsIgnoreCase(minValue)) {
                            pass = false;
                        }
                        break;
                    default:
                        System.out.println("Некорректный критерий.");
                        System.exit(1);
                }
            }
            if (pass) {
                filteredLaptops.add(laptop);
            }
        }

        if (filteredLaptops.isEmpty()) {
            System.out.println("Ноутбуков, удовлетворяющих заданным критериям, не найдено.");
        } else {
            System.out.println("Найденные ноутбуки:");
            for (Laptop laptop : filteredLaptops) {
                System.out.println(laptop);
            }
        }
    }
}

class Laptop {
    private String brand;
    private String processor;
    private int RAM;
    private int HDD;
    private String OS;
    private String color;

    public Laptop(String brand, String processor, int RAM, int HDD, String OS, String color) {
        this.brand = brand;
        this.processor = processor;
        this.RAM = RAM;
        this.HDD = HDD;
        this.OS = OS;
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public String getProcessor() {
        return processor;
    }

    public int getRAM() {
        return RAM;
    }

    public int getHDD() {
        return HDD;
    }

    public String getOS() {
        return OS;
    }

    public String getColor() {
        return color;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public void setRAM(int RAM) {
        this.RAM = RAM;
    }

    public void setHDD(int HDD) {
        this.HDD = HDD;
    }

    public void setOS(String OS) {
        this.OS = OS;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Бренд: " + brand + "\n" +
            "Процессор: " + processor + "\n" +
            "ОЗУ: " + RAM + " ГБ\n" +
            "Жесткий диск: " + HDD + " ГБ\n" +
            "Операционная система: " + OS + "\n" +
            "Цвет: " + color;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Laptop laptop = (Laptop) obj;
        return RAM == laptop.RAM && HDD == laptop.HDD && brand.equals(laptop.brand) && processor.equals(laptop.processor) && OS.equals(laptop.OS) && color.equals(laptop.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, processor, RAM, HDD, OS, color);
    }
}
