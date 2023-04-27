package fdv.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "place")
public class Place {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "placement_id")
    private Long placementId;

    @Column(name = "place_kind_id")
    private Long placeKindId;

    @Column(name = "number")
    private String number;
}
