package src;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Generation {
    public static int size;
    public static double crossover_chance;
    public static double mutation_chance;
    List<Chromosone> population = new ArrayList<>();

    public Generation() {
        for(int i = 0;i<size;i++)
        {
            population.add(new Chromosone(Main.points));
        }
        population.sort(new Comparator<Chromosone>() {
            @Override
            public int compare(Chromosone o1, Chromosone o2) {
                if(o1.fitness - o2.fitness < 0)
                    return 1;
                else if(o1.fitness -o2.fitness > 0)
                    return -1;
                else return 0;
            }
        });
    }
    public void sortgen()
    {
        population.sort(new Comparator<Chromosone>() {
            @Override
            public int compare(Chromosone o1, Chromosone o2) {

                if(o1.fitness - o2.fitness < 0)
                    return 1;
                else if(o1.fitness -o2.fitness > 0)
                    return -1;
                else return 0;

            }
        });
    }
    public Generation(Generation generation)
    {
        Random random = new Random();
        double roll = 0;
        double totalchance = 0;
        for(var i : generation.population)
        {
            totalchance +=i.fitness;
        }
        int testt = 5;
        for(int i = 0;i< size;i++) {

            //selection
            roll = 0 + totalchance * random.nextDouble()/2;
            roll -= generation.population.get(0).fitness;
            int index = 1;
            while (roll > 0) {
                if(index == generation.population.size())
                    break;
                roll -= generation.population.get(index).fitness;
                index++;
            }
            index--;
            //crossover
            double crossoverroll = random.nextDouble();
            Chromosone chromosone;
            if (crossoverroll <= crossover_chance) {
                roll = 0 + totalchance * random.nextDouble()/2;
                roll -= generation.population.get(0).fitness;
                int index1 = 1;
                while (roll > 0) {
                    if(index1 == generation.population.size())
                        break;
                    roll -= generation.population.get(index1).fitness;
                    index1++;
                }
                index1--;
                if(index == index1)
                {
                    if(index!=0)
                        index1--;
                    else if(index == generation.population.size() - 1)
                        index1--;
                    else index1++;
                }
                chromosone = Chromosone.crossover(generation.population.get(index), generation.population.get(index1));
            }
            else chromosone = generation.population.get(index);
            //mutation
            double mutationroll = random.nextDouble();
            if(mutationroll <= mutation_chance)
            {
                chromosone = Chromosone.mutate(chromosone);
            }
            if(1/chromosone.fitness == 0.0) {

                //System.out.println("ERROR");
                i--;
            }
            else population.add(chromosone);
            this.sortgen();
        }
    }
}
