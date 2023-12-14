package com.nts.seonghwan.be.preference.controller;

import com.nts.seonghwan.be.common.utils.ApiUtils;
import com.nts.seonghwan.be.config.security.SessionManagerAttribute;
import com.nts.seonghwan.be.preference.dto.PreferenceResponse;
import com.nts.seonghwan.be.preference.entities.PreferenceType;
import com.nts.seonghwan.be.preference.service.PreferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts/{postId}/preference")
public class PreferenceApiController {
    private final PreferenceService preferenceService;

    @PostMapping
    public ResponseEntity<ApiUtils.ApiResult<PreferenceResponse>> togglePreference(
            @PathVariable String postId,
            @RequestParam PreferenceType type,
            @SessionManagerAttribute Long userId
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiUtils.success(preferenceService.togglePreference(userId, postId, type)));
    }
}
