package com.supralog.recruitment.springexamusercrud.users;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import com.supralog.recruitment.springexamusercrud.users.models.Gender;
import com.supralog.recruitment.springexamusercrud.users.models.User;
import com.supralog.recruitment.springexamusercrud.utils.AbstractDtoValidationTest;

public class UserDtoValidationTest extends AbstractDtoValidationTest<User> {

	//////// name ////////

	@Test
	public void name_nullShouldBeInvalid(){
		dto.setName(null);
		assertInvalidField("name");
	}

	@Test
	public void name_emptyShouldBeInvalid(){
		dto.setName("");
		assertInvalidField("name");
	}

	@Test
	public void name_lessThanTwoCharShouldBeInvalid(){
		dto.setName("a");
		assertInvalidField("name");
	}

	@Test
	public void name_twoCharShouldBeValid(){
		dto.setName("ab");
		assertValidDto();
	}

	@Test
	public void name_moreThanTwentyShouldBeInvalid(){
		dto.setName("abcdefghijklmnopqrstu");
		assertInvalidField("name");
	}

	@Test
	public void name_twentyCharShouldBeValid(){
		dto.setName("abcdefghijklmnopqrst");
		assertValidDto();
	}

	//////// country ////////

	@Test
	public void country_nullShouldBeInvalid(){
		dto.setCountry(null);
		assertInvalidField("country");
	}

	@Test
	public void country_franceShouldBeValid(){
		dto.setCountry("France");
		assertValidDto();
	}

	@Test
	public void country_notFranceShouldBeInvalid(){
		dto.setCountry("Other");
		assertInvalidField("country");
	}

	//////// age ////////

	@Test
	public void age_toYoungShouldBeInvalid(){
		LocalDate toYoungByOneDayInvalidBirthDate = LocalDate.now().minusYears(User.MINIMAL_AGE_REQUIRED-1).plusDays(1);
		dto.setBirthDate(toYoungByOneDayInvalidBirthDate);
		assertInvalidField("age");
	}

	//////// phoneNumber ////////

	@ParameterizedTest()
	@ValueSource(strings = { "", "123", "123456789", "12345678901" })
	public void phoneNumber_non10DigitNumberShouldBeInvalid(String invalidNumber){
		dto.setPhoneNumber(invalidNumber);
		assertInvalidField("phoneNumber");
	}

	///////////////////////////////////////////////////////////
	////////////////////////   UTILS   ////////////////////////
	///////////////////////////////////////////////////////////

	public User buildSimpleValidDto(){
		LocalDate minimalValidateBirthDate = LocalDate.now().minusYears(User.MINIMAL_AGE_REQUIRED);
		return new User(null, "Bobby", Gender.FEMALE, "France", minimalValidateBirthDate, "0701010101");
	}

	static User buildSimpleInvalidUser(){
		return new User();
	}
}
