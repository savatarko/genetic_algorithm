package src;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static List<Point> points = new ArrayList<>();

    public static void main(String[] args) throws IOException {
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

            for (int i = 0; i < Chromosone.size; i++) {
                Point point = new Point();
                if (!points.contains(point))
                    points.add(point);
                else i--;
            }

            //values for testing
            /*
            Generation.size = 100;
            Chromosone.size = 16;
            Point.d = 2;
            Point.max = 50;
            Generation.crossover_chance = 0.6;
            Generation.mutation_chance = 0.03;
        }

        try (Scanner filescanner = new Scanner(new File("input.txt"))) {
            while(filescanner.hasNext()) {
                String output = filescanner.nextLine();
                int x = Integer.parseInt(output.substring(0, output.indexOf(" ")));
                int y = Integer.parseInt(output.substring(output.indexOf(" ") + 1));
                Point point = new Point(x, y);
                points.add(point);
            }
        }


        catch (Exception e)
        {
            e.printStackTrace();
        }

             */
            Generation a = new Generation();
            List<Point> bestpath = a.population.get(0).genes;
            int n = 100;
            int counter = 0;
            int maxgen;
            maxgen = 200 * Chromosone.size;
            //maxgen = 6000;
            double min = Integer.MAX_VALUE;
            FileWriter fileWriter = new FileWriter("output.txt");
            //while(n>0 && counter < maxgen)
            while (counter <= maxgen) {
                double prev = a.population.get(0).fitness;
                Generation b = new Generation(a);
                a = b;
                double dis = 1 / a.population.get(0).fitness;
                if (min > dis) {
                    bestpath = a.population.get(0).genes;
                }
                min = Double.min(min, dis);
                System.out.println("Generation #" + counter + " d=" + dis + " best=" + min);
                if (Math.abs(prev - a.population.get(0).fitness) < 0.000000000000001)
                    n--;
                else n = 100;
                fileWriter.write(1 / a.population.get(0).fitness + "\n");
                counter++;
            }
            System.out.println("Najkraci nadjen put:");
            for (var i : bestpath) {
                System.out.println(i.toString());
            }
            fileWriter.close();
        }
    }
}
