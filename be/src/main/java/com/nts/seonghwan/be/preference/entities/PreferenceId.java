package com.nts.seonghwan.be.preference.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
public class PreferenceId implements Serializable {
    private Long user;
    private Long post;
    private String type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PreferenceId that = (PreferenceId) o;
        return Objects.equals(user, that.user) && Objects.equals(post, that.post) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, post, type);
    }
}
