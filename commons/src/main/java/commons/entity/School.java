package commons.entity;

import java.util.List;

public class School {
    private final int id;
    private int capacity;
    private final List<Student> prioryStutends;

    public School(int id, int capacity, List<Student> prioryStutends) {
        this.id = id;
        this.capacity = capacity;
        this.prioryStutends = prioryStutends;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Student> getPrioryStutends() {
        return prioryStutends;
    }

    public void decCapacity() {
        this.capacity--;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Id of school : ").append(id);
        builder.append(" Capacity : ").append(capacity);
        builder.append(" Id of priority students : [");
        for (Student priority : prioryStutends) {
            builder.append(priority.getId()).append(" ");
        }
        builder.append(" Id of priority students : ]");
        return builder.toString();
    }
}
