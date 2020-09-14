package fr.alexpado.lib.rest.enums;

public enum RequestMethod {

    GET(false),
    POST(true),
    PUT(true),
    DELETE(true);

    boolean outputSupported;

    RequestMethod(boolean outputSupported) {

        this.outputSupported = outputSupported;
    }

    /**
     * Check if the current {@link RequestMethod} supports request body.
     *
     * @return True if a request body is supported, false otherwise.
     */
    public boolean isOutputSupported() {

        return outputSupported;
    }
}
