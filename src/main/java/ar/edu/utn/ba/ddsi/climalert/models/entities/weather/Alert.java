package ar.edu.utn.ba.ddsi.climalert.models.entities.weather;

import ar.edu.utn.ba.ddsi.climalert.dtos.weatherDTO.ConditionRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alert {
    private Long id;
    private AlertState state;

    private String name;
    private String region;
    private String country;
    private String localtime;
    private String lastUpdated;
    private Double temperature;
    private String condition;
    private Double windKph;
    private Double precipMm;
    private Double humidity;
    private Integer cloud;
    private Double feelslikeC;
    private Double visKm;
}
