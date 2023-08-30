package com.mateusulrich.codefood.core.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target ({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention (RetentionPolicy.RUNTIME)
@Constraint (
		validatedBy = {isValidProdutoValidator.class}
)
public @interface isValidProduto {

	String message() default "Esse produto não contem um restaurante válido.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String restauranteX ();

	String restauranteY ();
}
