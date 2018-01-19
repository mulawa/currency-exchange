package pl.marlena.currencyexchange.rates.controller;

public class ConvertInvalidException extends RuntimeException {

    private ValidationErrors validationErrors;


    public ConvertInvalidException(ValidationErrors validationErrors) {
        this.validationErrors = validationErrors;
    }

    public ValidationErrors getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(ValidationErrors validationErrors) {
        this.validationErrors = validationErrors;
    }
}
