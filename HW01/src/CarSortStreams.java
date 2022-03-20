import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CarSortStreams {
    private static Car car1 = new Car(2020, "Toyota", "Model1", 1234, 200, 13000);
    private static Car car2 = new Car(2015, "Toyota", "Model2", 55555, 10, 4000);
    private static Car car3 = new Car(2021, "Fancy", "Model3", 0, 50, 70000);
    private static Car car4 = new Car(2017, "Fancy", "Model4", 15000, 800, 13001);
    private static Car carA = new Car(2021, "Best", "Car1", 0, 100, 100);
    private static Car carB = new Car(2020, "Best", "Car2", 0, 100, 100);
    private static Car carC = new Car(2020, "Best", "Car3", 100, 0, 100);
    private static Car carD = new Car(2020, "Best", "Car4", 100, 0, 200);
    private static LinkedList<Car> carList = new LinkedList<Car>();


    public static void main(String[] args) {
        carList.add(car1);
        carList.add(car2);
        carList.add(car3);
        carList.add(car4);
        carList.add(carA);
        carList.add(carB);
        carList.add(carC);
        carList.add(carD);

        List<Car> priceSort =
                carList.stream()
                        .sorted((Car c1, Car c2) -> c1.getPrice() - c2.getPrice())
                        .collect(Collectors.toList());
        List<String> priceSortDet =
                priceSort.stream()
                        .map((Car car) -> car.getCar())
                        .collect(Collectors.toList());
        System.out.printf("Sorted by price: %s \n",priceSortDet);

        List<Car> dateSort =
                carList.stream()
                        .sorted((Car c1, Car c2) -> c2.getYear() - c1.getYear())
                        .collect(Collectors.toList());
        List<String> dateSortDet =
                dateSort.stream()
                        .map((Car car) -> car.getCar())
                        .collect(Collectors.toList());
        System.out.printf("Sorted by date: %s \n", dateSortDet);

        List<Car> mileSort =
                carList.stream()
                        .sorted((Car c1, Car c2) -> c1.getMileage() - c2.getMileage())
                        .collect(Collectors.toList());
        List<String> mileSortDet =
                mileSort.stream()
                        .map((Car car) -> car.getCar())
                        .collect(Collectors.toList());
        System.out.printf("Sorted by mileage: %s \n", mileSortDet);

        for (int i = 0; i < carList.size(); i++) {
            for (int j = 0; j < carList.size(); j++) {
                Car c1 = carList.get(i);
                Car c2 = carList.get(j);
                if (c1.getMileage() < c2.getMileage() &&
                        c1.getYear() >= c2.getYear() &&
                        c1.getPrice() <= c2.getPrice()) {
                    c1.setDominationCount(c1.getDominationCount() + 1);
                } else if (c1.getYear() > c2.getYear() &&
                        c1.getMileage() <= c2.getMileage() &&
                        c1.getPrice() <= c2.getPrice()) {
                    c1.setDominationCount(c1.getDominationCount() + 1);
                } else if (c1.getPrice() < c2.getPrice() &&
                        c1.getMileage() <= c2.getMileage() &&
                        c1.getYear() >= c2.getYear()) {
                    c1.setDominationCount(c1.getDominationCount() + 1);
                }
            }
        }
        List<Car> paretoSort =
                carList.stream()
                        .sorted((Car c1, Car c2)->c2.getDominationCount()-c1.getDominationCount())
                        .collect(Collectors.toList());
        List<String> paretoSortDet =
                mileSort.stream()
                        .map((Car car) -> car.getCar())
                        .collect(Collectors.toList());
        System.out.printf("Sorted by pareto: %s \n", paretoSortDet);
    }
}
