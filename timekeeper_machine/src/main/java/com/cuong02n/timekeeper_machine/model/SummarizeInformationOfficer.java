package com.cuong02n.timekeeper_machine.model;

public class SummarizeInformationOfficer {
    public int workingSession;
    public double early;
    public double late;
    public int userId;
    public String name;

    @Override
    public String toString() {
        return workingSession + " " + early + " " + late + " " + userId + " " + name;
    }
}
