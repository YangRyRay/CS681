import java.util.LinkedList;

public class ParallelMapReduce {

    public static void main(String[] args) {
        LinkedList<Car> carList = new LinkedList<Car>();
        carList.add(new Car(2020, "A", "Model1", 1234, 200, 1000));
        carList.add(new Car(2020, "A", "Model1", 1234, 200, 1000));
        carList.add(new Car(2020, "A", "Model2", 1234, 200, 1000));
        carList.add(new Car(2020, "A", "Model2", 1234, 200, 1000));
        carList.add(new Car(2020, "A", "Model2", 1234, 200, 1000));
        carList.add(new Car(2020, "A", "Model1", 1234, 200, 1000));
        carList.add(new Car(2020, "A", "Model1", 1234, 200, 1000));
        carList.add(new Car(2020, "A", "Model1", 1234, 200, 1000));
        carList.add(new Car(2020, "A", "Model3", 1234, 200, 1000));
        carList.add(new Car(2020, "A", "Model3", 1234, 200, 1000));
        carList.add(new Car(2020, "A", "Model3", 1234, 200, 1000));
        carList.add(new Car(2020, "A", "Model3", 1234, 200, 1000));
        carList.add(new Car(2020, "A", "Model3", 1234, 200, 1000));
        carList.add(new Car(2020, "A", "Model3", 1234, 200, 1000));
        carList.add(new Car(2020, "A", "Model3", 1234, 200, 1000));
        carList.add(new Car(2020, "A", "Model4", 1234, 200, 1000));

        int carModels = carList.stream()
                .parallel()
                .map((Car car)->car.getModel())
                .reduce(0,
                        (result,carModel)->++result,
                        (finalResult,intermediateResult)->finalResult+intermediateResult);
        System.out.println(carModels);
    }
}
