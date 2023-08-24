package com.mateusulrich.codefood;

import com.mateusulrich.codefood.domain.model.Cozinha;
import com.mateusulrich.codefood.domain.repository.CozinhaRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import util.ResourceUtils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestPropertySource ("/application-test.properties")
class CadastroCozinha2IT {
	@LocalServerPort
	private int port;
	@Autowired
	private Flyway flyway;
	private ResourceUtils resourceUtils;
	private Cozinha cozinhaAmericana;
	private int quantidadeCozinhasCadastradas;
	private String jsonCozinhaTeste;
	private static final int COZINHA_ID_INEXISTENTE = 9999;

	@Autowired
	private CozinhaRepository cozinhaRepository;
	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails ();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";
		flyway.migrate ();
		jsonCozinhaTeste = ResourceUtils.getContentFromResource(
				"/json/cozinha-teste.json");
		//databaseCleaner.clearTables ();
		prepararDados ();
	}
	@Test
	public void deve_retornar_status_200_quando_consultar_cozinha() {
				given ()
					.accept (ContentType.JSON)
				.when ()
					.get ()
				.then ()
					.statusCode (HttpStatus.OK.value ());
	}
	@Test
	public void deve_conter_4_cozinhas_quando_consultar_cozinha() {
				given ()
					.accept (ContentType.JSON)
				.when ()
					.get ()
				.then ()
					.body ("", hasSize (quantidadeCozinhasCadastradas));
	}
	@Test
	public void deve_retornar_status_201_quando_cadastrar_cozinha() {
				given ()
					.body (jsonCozinhaTeste)
					.contentType (ContentType.JSON)
					.accept (ContentType.JSON)
				.when ()
					.post ()
				.then ()
					.statusCode (HttpStatus.CREATED.value ());

	}
	@Test
	public void deve_retornar_resposta_e_status_corretos_quando_consultar_cozinha_existente() {
				given ()
					.pathParam ("cozinhaId", cozinhaAmericana.getId ())
					.accept (ContentType.JSON)
				.when ()
					.get ("/{cozinhaId}")
				.then ()
					.statusCode (HttpStatus.OK.value ())
					.body ("nome", equalTo (cozinhaAmericana.getNome ()));
	}

	@Test
	public void deve_retornar_status_404_quando_consultar_cozinha_Inexistente() {
				given ()
					.pathParam ("cozinhaId", COZINHA_ID_INEXISTENTE)
					.accept (ContentType.JSON)
				.when ()
					.get ("/{cozinhaId}")
				.then ()
					.statusCode (HttpStatus.NOT_FOUND.value ());
	}

	private void prepararDados() {
		Cozinha cozinhaTailandesa = new Cozinha();
		cozinhaTailandesa.setNome("Tailandesa");
		cozinhaTailandesa.setDescricao ("Cozinha tailandesa");
		cozinhaRepository.save(cozinhaTailandesa);

		cozinhaAmericana = new Cozinha();
		cozinhaAmericana.setNome("Americana");
		cozinhaAmericana.setDescricao ("Cozinha americana");
		cozinhaRepository.save(cozinhaAmericana);

		quantidadeCozinhasCadastradas = (int) cozinhaRepository.count();
	}




}
