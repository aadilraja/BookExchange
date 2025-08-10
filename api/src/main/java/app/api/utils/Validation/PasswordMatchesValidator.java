package app.api.utils.Validation;

import app.api.Persistence.DTOS.UserCreateDto;
import app.api.utils.CustomAnnotation.PasswordMatches;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches,Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {}

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        UserCreateDto user = (UserCreateDto) object;
        return user.getPassword().equals(user.getConfirmPassword());
    }

}
