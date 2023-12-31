package com.mateusulrich.codefood.core.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target ({ElementType.TYPE})
@Retention (RetentionPolicy.RUNTIME)
@Constraint (validatedBy = {ValorZeroIncluiDescricaoValidator.class})
public @interface ValorZeroIncluiDescricao {

	String message() default "É obrigatório informar Frete Grátis no nome quando a taxa frete é igual Zero.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
	String valorField();
	String descricaoField();
	String descricaoObrigatoria();
}
