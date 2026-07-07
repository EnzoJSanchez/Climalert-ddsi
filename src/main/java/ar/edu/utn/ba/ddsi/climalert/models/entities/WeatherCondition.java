package ar.edu.utn.ba.ddsi.climalert.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherCondition {

    private Long id;
    private String city;
    private String country;
    private Double temperature;
    private Double humidity;

    public boolean isDangerous (){
        return temperature > 35 && humidity > 60;
    }
}
