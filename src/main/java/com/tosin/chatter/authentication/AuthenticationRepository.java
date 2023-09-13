package com.tosin.chatter.authentication;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationRepository {

    private final DSLContext dslContext;


    public void save() {
    }

}
