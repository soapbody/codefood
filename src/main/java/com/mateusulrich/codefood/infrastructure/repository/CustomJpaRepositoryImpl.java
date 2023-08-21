package com.mateusulrich.codefood.infrastructure.repository;

import com.mateusulrich.codefood.domain.repository.CustomJpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.util.Optional;

public class CustomJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements CustomJpaRepository<T, ID> {
	private EntityManager entityManager;
	public CustomJpaRepositoryImpl (JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super (entityInformation, entityManager);
		this.entityManager = entityManager;
	}

	public CustomJpaRepositoryImpl (Class<T> domainClass, EntityManager em) {
		super (domainClass, em);
	}

	@Override
	public Optional<T> buscarPrimeiro () {
		var jpql = "from " + getDomainClass ().getName ();

		T entity = entityManager.createQuery (jpql, getDomainClass ())
				.setMaxResults (1)
				.getSingleResult ();
		return Optional.ofNullable (entity);
	}
}
