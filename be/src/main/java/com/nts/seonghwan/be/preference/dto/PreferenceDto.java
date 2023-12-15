package com.nts.seonghwan.be.preference.dto;

import com.nts.seonghwan.be.preference.entities.Preference;
import com.nts.seonghwan.be.preference.entities.PreferenceType;
import lombok.Getter;

import java.util.Objects;

@Getter
public class PreferenceDto {
    private boolean ableToPreference;
    private PreferenceType type;
    private Long count;

    public PreferenceDto(Preference preference, PreferenceType type, Long count) {
        this.ableToPreference = Objects.isNull(preference);
        this.type = type;
        this.count = count;
    }

    static public PreferenceDto defaultPreference(PreferenceType type){
        return new PreferenceDto(null, type, 0L);
    }
}
