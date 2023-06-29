package com.igorms.cdcchallenge.region;

import javax.validation.constraints.NotBlank;

public class StateResponse {

    @NotBlank
    public Long id;

    @NotBlank
    public String name;

    public StateResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static StateResponse fromEntity(State state) {
        return new StateResponse(state.getId(), state.getName());
    }
}
