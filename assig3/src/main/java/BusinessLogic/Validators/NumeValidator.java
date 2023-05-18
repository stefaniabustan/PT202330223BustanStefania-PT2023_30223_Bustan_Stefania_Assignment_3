package BusinessLogic.Validators;

import Model.Client;

import java.util.regex.Pattern;

public class NumeValidator implements Validator<Client> {
    private static final String NAME_PATTERN = "[A-Z](?=.{1,29}$)[A-Za-z]{1,}([ ][A-Z][A-Za-z]{1,})*";
    @Override
    public void validate(Client client) {
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        if (!pattern.matcher(client.getNume()).matches()) {
            throw new IllegalArgumentException("Name is not a valid name!");
        }
    }
}
