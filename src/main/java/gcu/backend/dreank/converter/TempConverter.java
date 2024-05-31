package gcu.backend.dreank.converter;

import gcu.backend.dreank.dto.TempResponse;

public class TempConverter {
    public static TempResponse.TempTestDTO toTempTestDTO() {
        return TempResponse.TempTestDTO.builder()
                .testString("This is Test!")
                .build();
    }
}
