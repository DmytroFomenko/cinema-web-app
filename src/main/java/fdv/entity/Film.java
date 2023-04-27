package fdv.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Builder
//@NoArgsConstructor
@AllArgsConstructor
@Table(name = "film")
public class Film {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "duration")
    private LocalTime duration;

    @Column(name = "description")
    private String description;

    @Column(name = "year")
    private int year;


    public Film() {
    }
}
