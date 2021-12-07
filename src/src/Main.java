package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static List<Point> points = new ArrayList<>();
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Broj hromozona u generaciji?");
            Generation.size = scanner.nextInt();
            System.out.println("Broj tacaka?");
            Chromosone.size = scanner.nextInt();
            System.out.println("Dimenzije prostora?");
            Point.d = scanner.nextInt();
            System.out.println("Max?");
            Point.max = scanner.nextInt();
            System.out.println("Sansa mnozenja");
            Generation.crossover_chance = scanner.nextDouble();
            System.out.println("Sansa mutacije?");
            Generation.mutation_chance = scanner.nextDouble();
            for(int i = 0;i<Chromosone.size;i++)
            {
                Point point = new Point();
                //while(!points.contains(point))
                {
                    point = new Point();
               //     System.out.println("a");
                }
                points.add(point);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        Generation a = new Generation();
        int n = 20;
        int counter = 0;
        while(n>0 && counter < 10000)
        {
            double prev = a.population.get(0).fitness;
            a = new Generation(a);
            System.out.println("Generation #" + counter + " d=" + 1/a.population.get(0).fitness);
            if(Math.abs(prev-a.population.get(0).fitness) < 0.00000000001)
                n--;
            else n = 20;
            counter++;
        }
    }
}
