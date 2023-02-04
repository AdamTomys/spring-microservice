package com.amigo.customer.controller;

public record CustomerRequest(
        String firstName,
        String lastName,
        String email) {}
