package fdv.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalTime;

import static java.time.temporal.ChronoField.MILLI_OF_DAY;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "seance")
public class Seance {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "film_id")
    private Long filmId;

    @Column(name = "begin_time")
    private Timestamp beginTime;

    @Column(name = "base_price")
    private double basePrice;

    @Column(name = "bill_to_pay_id")
    private Long billToPay;


    public Timestamp calcEndTime(LocalTime filmDuration) {
        return new Timestamp(beginTime.getTime() + filmDuration.getLong(MILLI_OF_DAY));
    }
}
