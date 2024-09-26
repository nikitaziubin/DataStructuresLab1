/** @author Eimutis Karčiauskas, KTU IF Department of Software Engineering, 23 09 2014
 *
 * This is a demonstration class of car list tests, which is just for
 * testing actions with lists.
 *************************************************************************** */

package tests;

import java.util.Comparator;
import java.util.Locale;

import util.*;
import models.*;

public class ManualTest {

    CarList cars = new CarList();

    void execute() {
        createCars();
        createCarList();
// countRenault();
// appendCarList();
// checkCarMarketFilters();
// checkCarMarketSorting();
    }

    void createCars() {
        Car c1 = new Car("Renault", "Laguna", 1997, 50000, 1700);
        Car c2 = new Car("Renault", "Megane", 2001, 20000, 3500);
        Car c3 = new Car("Toyota", "Corolla", 2001, 20000, 8500.8);
        Car c4 = new Car();
        Car c5 = new Car();
        Car c6 = new Car();
        c4.parse("Renault Laguna 2001 115900 7500");
        c5.parse("Renault Megane 1946 365100 9500");
        c6.parse("Honda Civic 2007 36400 8500.3");

        Ks.oun(c1);
        Ks.oun(c2);
        Ks.oun(c3);
        Ks.oun("Average mileage of first 3 cars= "
                + (c1.getMileage() + c2.getMileage() + c3.getMileage()) / 3);
        Ks.oun(c4);
        Ks.oun(c5);
        Ks.oun(c6);
        Ks.oun("Sum of other 3 auto prices= "
                + (c4.getPrice() + c5.getPrice() + c6.getPrice()));
    }

    void createCarList() {
        Car c1 = new Car("Renault", "Laguna", 1997, 50000, 1700);
        Car c2 = new Car("Renault", "Megane", 2001, 20000, 3500);
        Car c3 = new Car("Toyota", "Corolla", 2001, 20000, 8500.8);
        cars.add(c1);
        cars.add(c2);
        cars.add(c3);
        cars.println("First 3 cars");
        cars.add("Renault Laguna 2001 115900 7500");
        cars.add("Renault Megane 1946 365100 9500");
        cars.add("Honda Civic 2007 36400 8500,3");

        cars.println("All 6 cars");
        cars.forEach(System.out::println);
        Ks.oun("Average mileage of the first 3 cars= "
                + (cars.get(0).getMileage() + cars.get(1).getMileage()
                + cars.get(2).getMileage()) / 3);

        Ks.oun("Sum of the prices of the next 3 cars= "
                + (cars.get(3).getPrice() + cars.get(4).getPrice()
                + cars.get(5).getPrice()));
        // gradually open the following rows and test
// cars.add(0, new Car("Mazda", "6",2007,50000,27000));
// cars.add(6, new Car("Hyundai", "Lantra",1998,9500,777));
// cars.set(4, c3);
// cars.println("After insertions");
// cars.remove(7);
// cars.remove(0);
// cars.println("After removals");
// cars.remove(0); cars.remove(0); cars.remove(0);
// cars.remove(0); cars.remove(0); cars.remove(0);
// cars.println("After all removals");
// cars.remove(0);
// cars.println("After all removals");
    }

    void countRenault() {
        int sk = 0;
        for (Car c : cars) {
            if (c.getMake().compareTo("Renault") == 0) {
                sk++;
            }
        }
        Ks.oun("Renault car is = " + sk);
    }

    void appendCarList() {
        for (int i = 0; i < 8; i++) {
            cars.add(new Car("Ford", "Focus",
                    2009 - i, 40000 + i * 10000, 36000 - i * 2000));
        }
        cars.add("Ford Mondeo 2009 37000 36000.0");
        cars.add("Fiat Bravo 2008 27000 32500.0");
        cars.add("Ford Fiesta 2009 37000 16000.0");
        cars.add("Audi A6 2006 87000 36000.0");
        cars.println("List of cars tested");
        cars.save("ban.txt");
    }

    void checkCarMarketFilters() {
        CarMarket market = new CarMarket();

        market.allCars.load("ban.txt");
        market.allCars.println("Test set");

        cars = market.getNewerCars(2001);
        cars.println("Starting from 2001");

        cars = market.getByPrice(3000, 10000);
        cars.println("Price between 3000 and 10000");

        cars = market.getHighestMileageCars();
        cars.println("Most travelled");

        cars = market.getByMakeAndModel("F");
        cars.println("Must contain only Fiats and Fords");

        cars = market.getByMakeAndModel("Ford M");

        cars.println("Must contain only Ford Mondeo");
        int n = 0;
        for (Car c : cars) {
            n++; // test the loop
        }
        Ks.oun("Ford Mondeo quantity = " + n);
    }

    void checkCarMarketSorting() {
        CarMarket market = new CarMarket();

        market.allCars.load("ban.txt");
        Ks.oun("========" + market.allCars.get(0));
        market.allCars.println("Test set");
        market.allCars.sortBuble(Car.byMakeAndModel);
        market.allCars.println("Sort by Make and Model");
        market.allCars.sortBuble(Car.byPrice);
        market.allCars.println("Sorting by price");
        market.allCars.sortBuble(Car.byYearAndPrice);
        market.allCars.println("Sorting by Year and Price");
        market.allCars.sortBuble(byMileage);
        market.allCars.sortBuble(Comparator.comparingInt(Car::getMileage));
        market.allCars.println("Sort by Mileage");
        market.allCars.sortBuble();
        market.allCars.println("Sort by compareTo - Price");
    }

    static Comparator<Car> byMileage = (car1, car2) -> {
        int m1 = car1.getMileage();
        int m2 = car2.getMileage();
        // mileage in reverse descending order, starting with the highest
        if (m1 < m2) {
            return 1;
        }
        if (m1 > m2) {
            return -1;
        }
        return 0;
    };




    public static void main(String... args) {
        // unify number formats according to LT locale (10 decimal point)
        //Locale.setDefault(new Locale("LT"));
        //new ManualTest().execute();

        System.out.println("Testing ArrayStack...");
        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        arrayStack.push(10);
        arrayStack.push(20);
        arrayStack.push(30);
        System.out.println("Peek (should be 30): " + arrayStack.peak());
        System.out.println("Pop (should be 30): " + arrayStack.pop());
        System.out.println("Peak (should be 20): " + arrayStack.peak());
        arrayStack.pop();
        arrayStack.pop();
        System.out.println("Is empty (should be true): " + arrayStack.isEmpty());

        // Test LinkedListStack
        System.out.println("\nTesting LinkedListStack...");
        LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();
        linkedListStack.push(100);
        linkedListStack.push(200);
        linkedListStack.push(300);
        System.out.println("Peek (should be 300): " + linkedListStack.peak());
        System.out.println("Pop (should be 300): " + linkedListStack.pop());
        System.out.println("Peak (should be 200): " + linkedListStack.peak());
        linkedListStack.pop();
        linkedListStack.pop();
        System.out.println("Is empty (should be true): " + linkedListStack.isEmpty());

        //Test ArrayQueue
        System.out.println("\nTesting ArrayQueue...");
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>(3);
        arrayQueue.enqueue(1);
        arrayQueue.enqueue(2);
        arrayQueue.enqueue(3);
        System.out.println("Peak (should be 1): " + arrayQueue.peak());
        arrayQueue.enqueue(4);
        System.out.println("Size (should be 4): " + arrayQueue.getSize());
        System.out.println("Dequeue (should be 1): " + arrayQueue.dequeue());
        System.out.println("Peak (should be 1): " + arrayQueue.peak());
        System.out.println("Peak (should be 2): " + arrayQueue.peak());
        arrayQueue.dequeue();
        arrayQueue.dequeue();
        System.out.println("Is empty (should be true): " + arrayQueue.isEmpty());

        // Test LinkedListQueue
        System.out.println("\nTesting LinkedListQueue...");
        LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<>();
        linkedListQueue.enqueue(1);
        linkedListQueue.enqueue(2);
        linkedListQueue.enqueue(3);
        System.out.println("Peak (should be 1): " + linkedListQueue.peak());
        System.out.println("Dequeue (should be 1): " + linkedListQueue.dequeue());
        System.out.println("Peak (should be 2): " + linkedListQueue.peak());
        linkedListQueue.dequeue();
        linkedListQueue.dequeue();
        System.out.println("Is empty (should be true): " + linkedListQueue.isEmpty());


    }
}

