package com.uff_carona.carona.domain.trajeto;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lugares")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Lugar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome_lugar")
    private String nomeLugar;

    private Double latitude;

    private Double longitude;

    private String municipio;

    private String estado;
}
