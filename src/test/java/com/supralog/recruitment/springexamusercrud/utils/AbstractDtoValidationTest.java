package com.supralog.recruitment.springexamusercrud.utils;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class AbstractDtoValidationTest<C> {

	protected Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	protected C dto;

	@BeforeEach
	public void initUser(){
		dto = buildSimpleValidDto();
	}

	@Test
	public void validDtoShouldNotThrow(){
		assertValidDto();
	}

	// ####################################################################################### //

	protected void assertValidDto(){
		Set<ConstraintViolation<C>> violations = validator.validate(dto);

		assertTrue(violations.isEmpty());
	}

	protected void assertInvalidField(String fieldName){
		Set<ConstraintViolation<C>> violations = validator.validate(dto);
		assertFalse(violations.isEmpty(), "The dto is valid");
		assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals(fieldName)), violations::toString);
	}

	public abstract C buildSimpleValidDto();
}
