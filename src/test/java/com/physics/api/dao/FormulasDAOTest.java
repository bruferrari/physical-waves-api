package com.physics.api.dao;

import org.junit.Test;

public class FormulasDAOTest {

	@Test
	public void mustReturnUniqueByteArrayFormula() {
		FormulasDAO dao = new FormulasDAO();
		byte[] result = dao.findFormulaAsBytes(1L, 2L);
		System.out.println(result);
	}
}
