package com.nts.seonghwan.be.preference.dto;

import com.nts.seonghwan.be.preference.entities.Preference;
import com.nts.seonghwan.be.preference.entities.PreferenceType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PreferenceResponse {
    private boolean ableToPreference;
    private PreferenceType type;
    private Long count;

    public static PreferenceResponse from(Preference preference, PreferenceType type, Long count){
        return new PreferenceResponse(preference.isDeleted(), type, count);
    }
}
