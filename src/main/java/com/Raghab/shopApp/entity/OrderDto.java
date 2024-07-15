package com.Raghab.shopApp.entity;

import jakarta.validation.constraints.NotNull;

public record OrderDto(
        String status,
        String userId) {
}
