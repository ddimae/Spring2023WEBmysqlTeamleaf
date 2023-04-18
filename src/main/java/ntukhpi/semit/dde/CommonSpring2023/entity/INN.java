package ntukhpi.semit.dde.CommonSpring2023.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name="inns")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class INN {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inn")
    private Long id;

    @Column(name = "number", unique = true, columnDefinition ="bigint not null DEFAULT 1000000000, CONSTRAINT min_number CHECK (number>=1000000000)")   //
    private Long number;

    @Column(nullable=false)
    //@ColumnDefault(value=" ") //<===== Mistake!!!!
    private String issued;

    @Column(name = "date_issued",nullable = false)
    //https://overcoder.net/q/308183/localdate-%D0%B2-%D1%84%D0%BE%D1%80%D0%BC%D0%B5
    //This so importent!!!
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateIssued;

    @OneToOne
    @JoinColumn(name = "id_employee",nullable = false,updatable = false,unique = true)
    private Employee owner;

    public String toStringBase() {
        return "INN: "+ number +
                ", issued " + issued + " " + dateIssued;
    }
}
