package ar.edu.utn.ba.ddsi.climalert.models.entities.weather;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherCondition {

    private Long id;
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

    public boolean isDangerous (){
        return temperature > 35 && humidity > 60;
    }
}
