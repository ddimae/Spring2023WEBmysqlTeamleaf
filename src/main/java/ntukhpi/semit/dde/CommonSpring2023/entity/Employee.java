package ntukhpi.semit.dde.CommonSpring2023.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;


@Entity
@Table(name = "employees")
//from lombok
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;  //Long!!! not long, not int
    @Column(name = "name", nullable=false, unique=true, length = 50)
    private String name;

    @Column(name = "pol", nullable=false)
    @ColumnDefault(value = "true")
    private boolean pol;
    //@Column(name = "age", columnDefinition = "value>=18 AND value<=70 defauit 18",nullable=false)
    //@ColumnDefault(value = "18")

    @Column(name="age", columnDefinition ="int not null DEFAULT 18, CONSTRAINT range_age CHECK (age>=18 AND age<=60)")
    //@Column(name="age", nullable = false)
    //@Check(constraints = "age>=18 AND age<=60")
    //@ColumnDefault(value = "18")
    private int age;

    @Column(name = "salary", columnDefinition ="DOUBlE(10,2) not null DEFAULT 6500.00, CONSTRAINT int_salary CHECK (salary>=6500)")
    //@ColumnDefault(value = "6500")
    private Double salary;

}
