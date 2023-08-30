package com.mateusulrich.codefood.core.validation;

import org.springframework.beans.BeanUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;


public class isValidProdutoValidator implements ConstraintValidator<isValidProduto, Object> {

	private String restauranteX;
	private String restauranteY;
	@Override
	public void initialize (isValidProduto constraintAnnotation) {
		this.restauranteX = constraintAnnotation.restauranteX ();
		this.restauranteY = constraintAnnotation.restauranteY ();
	}

	@Override
	public boolean isValid (Object obj, ConstraintValidatorContext constraintValidatorContext) {
		boolean valido = false;
		try {
			Long valorX = (Long) BeanUtils.getPropertyDescriptor (obj.getClass (), restauranteX)
					.getReadMethod ().invoke (obj);
			Long valorY = (Long) BeanUtils.getPropertyDescriptor (obj.getClass (), restauranteY)
					.getReadMethod ().invoke (obj);

			if (valorX != null && valorY != null) {
				valido = valorX.equals (valorY);
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException (e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException (e);
		}
		return valido;


	}
}
