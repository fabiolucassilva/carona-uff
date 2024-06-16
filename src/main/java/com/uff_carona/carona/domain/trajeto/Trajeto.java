package com.uff_carona.carona.domain.trajeto;

import com.uff_carona.carona.domain.trajeto.Lugar;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "trajeto")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Trajeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lugar_origem_id")
    private Lugar lugarOrigem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lugar_destino_id")
    private Lugar lugarDestino;

    @Column(name = "tempo_medio_minutos")
    private Integer tempoMedioMinutos;

    @Column(name = "trajeto_explicado")
    private String trajetoExplicado;

    @Column(name = "distancia_km")
    private Double distanciaKm;
}
