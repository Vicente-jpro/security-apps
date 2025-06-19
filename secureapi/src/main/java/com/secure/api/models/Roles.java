package com.secure.api.models;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Getter
@RequiredArgsConstructor
public enum Roles {
    VIEW_ACCOUNT("VIEW_ACCOUNT"),
    VIEW_CARDS("VIEW_CARDS"),
    VIEW_LOANS("VIEW_LOANS"),
    VIEW_BALANCE("VIEW_BALANCE");

    private final String value;

    public static Roles from(@NonNull final String role){
        return Optional.ofNullable(Roles.valueOf(role))
            .orElseThrow(() -> new IllegalArgumentException("Invalid role."));
    }
}
