package com.mateusulrich.codefood.core.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target ({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention (RetentionPolicy.RUNTIME)
@Constraint (validatedBy = {FileSizeValidator.class})
public @interface FileSize {
	String max ();

	//@OverridesAttribute (constraint = PositiveOrZero.class, name = "message")
	String message () default "Tamanho do Arquivo excede o limite permitido";

	Class<?>[] groups () default {};

	Class<? extends Payload>[] payload () default {};


}
