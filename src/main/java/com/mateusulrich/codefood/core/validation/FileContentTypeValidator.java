package com.mateusulrich.codefood.core.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

public class FileContentTypeValidator implements ConstraintValidator<FileContentType, MultipartFile> {

	private List<String> allowedContentType;

	@Override
	public void initialize (FileContentType constraintAnnotation) {
		this.allowedContentType = Arrays.asList(constraintAnnotation.allowed());
	}

	@Override
	public boolean isValid (MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
		return multipartFile == null || this.allowedContentType.contains(multipartFile.getContentType ());
	}
}
