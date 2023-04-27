package fdv.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.awt.*;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ticket")
public class Ticket {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seance_id")
    private Long seanceId;

    @Column(name = "place_id")
    private Long placeId;

    @Column(name = "place_status", columnDefinition = "ENUM('FREE', 'RESERVED', 'TAKEN')")
    @Enumerated(EnumType.STRING)
    private PlaceStatus placeStatus;

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "code")
    private String code;



    public String getColorByStatus() {
        return switch (this.placeStatus) {
            case FREE -> "#8aff9d";
            case RESERVED -> "#efff8a";
            case TAKEN -> "#ff8aad";
            default -> "#FF0000";
        };
    }


}


//enum PlaceStatus {
//    FREE,
//    RESERVED,
//    TAKEN
//}