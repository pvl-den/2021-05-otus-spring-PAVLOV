package ru.otus.service;

import org.springframework.stereotype.Service;

import java.io.PrintStream;

@Service
public class IOServiceImpl implements IOService {

    private final PrintStream out;

    public IOServiceImpl() {
        this.out = System.out;
    }

    @Override
    public void out(final String message) {
    out.println(message);
    }
}
