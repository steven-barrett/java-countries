package com.lambdaschool.countries;

import java.util.concurrent.atomic.AtomicLong;

public class Country
{
    private static final AtomicLong counter = new AtomicLong();
    private long id;
    private String cname;
    private int population;
    private int lmass;
    private int medianage;

    public Country(String cname, int population, int lmass, int medianage)
    {
        this.id = counter.incrementAndGet();
        this.cname = cname;
        this.population = population;
        this.lmass = lmass;
        this.medianage = medianage;
    }

    public Country(Country toClone)
    {
        this.id = toClone.getId();
        this.cname = toClone.getCname();
        this.population = toClone.getPopulation();
        this.lmass = toClone.getLmass();
        this.medianage = toClone.getMedianage();
    }

    public long getId()
    {
        return id;
    }

    public String getCname()
    {
        return cname;
    }

    public void setCname(String cname)
    {
        this.cname = cname;
    }

    public int getPopulation()
    {
        return population;
    }

    public void setPopulation(int population)
    {
        this.population = population;
    }

    public int getLmass()
    {
        return lmass;
    }

    public void setLmass(int lmass)
    {
        this.lmass = lmass;
    }

    public int getMedianage()
    {
        return medianage;
    }

    public void setMedianage(int medianage)
    {
        this.medianage = medianage;
    }

    @Override
    public String toString()
    {
        return "Country{" + "id=" + id + ", cname='" + cname + '\'' + ", population=" + population + ", lmass=" + lmass + ", medianage=" + medianage + '}';
    }
}
