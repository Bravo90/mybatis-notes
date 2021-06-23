package sun.study.note.model;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * @author sunzhen <sunzhen03@kuaishou.com>
 * Created on 2021-06-16
 */
public class Demo {

    @Range(min = 1,max = 100,message = "100 >= id >= 1")
    private int id;
    @NotNull(message = "name cannot be null!")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
