import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CarStats {
    private static Car car1 = new Car(2020, "Toyota", "Model1", 1234, 200, 1000);
    private static Car car2 = new Car(2015, "Toyota", "Model2", 55555, 10, 2000);
    private static Car car3 = new Car(2021, "Fancy", "Model3", 0, 50, 3000);
    private static Car car4 = new Car(2017, "Fancy", "Model4", 15000, 800, 4000);
    private static Car carA = new Car(2021, "Best", "Car1", 0, 100, 5000);
    private static Car carB = new Car(2020, "Best", "Car2", 0, 100, 6000);
    private static Car carC = new Car(2020, "Best", "Car3", 100, 0, 7000);
    private static Car carD = new Car(2020, "Best", "Car4", 100, 0, 8000);
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

        int minPrice = carList.stream()
                .mapToInt((Car car)->car.getPrice())
                .min()
                .getAsInt();
        System.out.printf("Min price: %s \n",minPrice);

        int maxPrice = carList.stream()
                .mapToInt((Car car)->car.getPrice())
                .max()
                .getAsInt();
        System.out.printf("Max price: %s \n",maxPrice);

        int avgPrice = carList.stream()
                .map( car->car.getPrice())
                .reduce(new int[2],
                        (result,price)->{
                            result[1]=(result[1]*result[0]+price)/(result[0]+1);
                            result[0]++;
                            return result;},
                        (finalResult, intermediateResult)->finalResult
                    )[1];
        System.out.printf("Avg price: %s \n",avgPrice);

    }
}