package sg.edu.rp.c346.fypcgh;


import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class Title extends ExpandableGroup<Content> {

    public final String name;
    public final String nric;
    public final String age;
    public final String ward;
    public final String gender;
    public final String priority;
    public final String bed;

    public Title(String title, List<Content> items, String name, String nric, String age, String ward, String gender, String priority, String bed) {
        super(title, items);
        this.name = name;
        this.nric = nric;
        this.age = age;
        this.ward = ward;
        this.gender = gender;
        this.priority = priority;
        this.bed = bed;
    }
}