package com.nts.seonghwan.be.preference.dto;

import com.nts.seonghwan.be.preference.entities.Preference;
import com.nts.seonghwan.be.preference.entities.PreferenceType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PreferenceToggleResponse {
    private boolean ableToPreference;
    private PreferenceType type;
    private Long count;

    public static PreferenceToggleResponse from(Preference preference, PreferenceType type, Long count){
        return new PreferenceToggleResponse(preference.isDeleted(), type, count);
    }
}
