package src;

import java.util.*;

public class Chromosone {
    double fitness = 0;
    List<Point> genes = new ArrayList<>();
    public static int size;

    public Chromosone() {
        for(int i = 0; i<size;i++)
        {
            genes.add(new Point(-1));
        }
    }

    public Chromosone(List<Point> genes) {
        this.genes = new ArrayList<>(genes);
        Collections.shuffle(this.genes);
        calculateFitness();
    }
    public void calculateFitness()
    {
        fitness = 0;
        Point start = genes.get(0);
        Point previous = null;
        for(var i : genes)
        {
            if(previous != null) {
                fitness += Point.calculatedistance(i, previous);
            }
            previous = i;
        }
        fitness +=Point.calculatedistance(previous,start);
        fitness = 1/fitness;
    }
    public static Chromosone crossover(Chromosone c1, Chromosone c2)
    {


        Chromosone chromosone = new Chromosone();
        Random random = new Random();
        int roll1, roll2;
        roll1 = random.nextInt(c1.genes.size() - 1);
        roll2 = random.nextInt(c1.genes.size()-roll1);
        for(int i = roll1;i<roll1 + roll2;i++)
        {
            chromosone.genes.set(i, c1.genes.get(i));
        }
        for(int i = 0;i<chromosone.genes.size();i++)
        {
            if(Point.isDummy(chromosone.genes.get(i)) && !chromosone.genes.contains(c2.genes.get(i)))
            {
                chromosone.genes.set(i, c2.genes.get(i));
            }
        }
        for(var i : c1.genes)
        {
            if(!chromosone.genes.contains(i))
            {
                for(int j = 0;j<chromosone.genes.size();j++)
                {
                    if(Point.isDummy(chromosone.genes.get(j)))
                    {
                        chromosone.genes.set(j,i);
                        j=chromosone.genes.size() + 1;

                    }
                }
            }
        }
        chromosone.calculateFitness();
        return chromosone;
    }
    public static Chromosone mutate(Chromosone chromosone)
    {
        Random random = new Random();
        double roll;
        for(int i = 0; i<chromosone.genes.size(); i++)
        {
            roll = random.nextDouble();
            if(roll<=Generation.mutation_chance)
            {
                int roll1 = random.nextInt(chromosone.genes.size());
                while(roll1==i)
                {
                    roll1 = random.nextInt(chromosone.genes.size());
                }
                Collections.swap(chromosone.genes, i, roll1);
            }
        }
        chromosone.calculateFitness();
        return chromosone;
    }
}
