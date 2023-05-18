package BusinessLogic.Validators;

import Model.Product;

import java.util.regex.Pattern;


public class NumeValidatorProduct implements Validator<Product> {
    private static final String NAME_PATTERN = "[A-Z](?=.{1,29}$)[A-Za-z]{1,}([ ][A-Z][A-Za-z]{1,})*";
    @Override
    public void validate(Product p) {
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        if (!pattern.matcher(p.getNume()).matches()) {
            throw new IllegalArgumentException("Name is not a valid name!");
        }
    }
}