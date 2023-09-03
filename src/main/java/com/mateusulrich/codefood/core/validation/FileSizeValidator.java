package com.mateusulrich.codefood.core.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;

public class FileSizeValidator implements ConstraintValidator<FileSize, MultipartFile> {

	private DataSize maxSize;

	@Override
	public boolean isValid (MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
		return multipartFile == null || multipartFile.getSize () <= this.maxSize.toBytes ();
	}

	@Override
	public void initialize (FileSize constraintAnnotation) {
		//ConstraintValidator.super.initialize (constraintAnnotation);
		this.maxSize = DataSize.parse (constraintAnnotation.max ());
	}
}
