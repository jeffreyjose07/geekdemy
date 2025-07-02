package com.example.geektrust;

public enum ApplicationError {
    NO_INPUT(1);

    private final int exitCode;

    ApplicationError(int exitCode) {
        this.exitCode = exitCode;
    }

    public int getExitCode() {
        return exitCode;
    }
}
