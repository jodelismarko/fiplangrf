package br.gov.mt.mti.fiplangrf.util.converter;

import br.gov.mt.mti.fiplangrf.model.fiplan.FIPLANUnidadeOrcamentaria;
import br.gov.mt.mti.fiplangrf.model.tabelas.UnidadeOrcamentaria;

public final class FiplanConverter {

	public static final UnidadeOrcamentaria getUnidadeOrcamentariaFromFiplan(FIPLANUnidadeOrcamentaria fiplanUnidadeOrcamentaria) {
		UnidadeOrcamentaria unidadeOrcamentaria = new UnidadeOrcamentaria();
		if (fiplanUnidadeOrcamentaria != null) {
			unidadeOrcamentaria.setCodigoUnidadeOrcamentaria(fiplanUnidadeOrcamentaria.getCodigoUnidadeOrcamentaria());
			unidadeOrcamentaria.setDescricaoUnidadeOrcamentaria(fiplanUnidadeOrcamentaria.getDescricaoUnidadeOrcamentaria());
			unidadeOrcamentaria.setSiglaUnidadeOrcamentaria(fiplanUnidadeOrcamentaria.getSiglaUnidadeOrcamentaria());
			unidadeOrcamentaria.setExercicioUnidadeOrcamentaria(fiplanUnidadeOrcamentaria.getExercicioUnidadeOrcamentaria());
		}
		return unidadeOrcamentaria;
	}

	public static final FIPLANUnidadeOrcamentaria getFiplanFromUnidadeOrcamentaria(UnidadeOrcamentaria unidadeOrcamentaria) {
		FIPLANUnidadeOrcamentaria fiplanUnidadeOrcamentaria = new FIPLANUnidadeOrcamentaria();
		if (unidadeOrcamentaria != null) {
			fiplanUnidadeOrcamentaria = new FIPLANUnidadeOrcamentaria();
			fiplanUnidadeOrcamentaria.setDescricaoUnidadeOrcamentaria(unidadeOrcamentaria.getDescricaoUnidadeOrcamentaria());
			fiplanUnidadeOrcamentaria.setSiglaUnidadeOrcamentaria(unidadeOrcamentaria.getSiglaUnidadeOrcamentaria());
			fiplanUnidadeOrcamentaria.setExercicioUnidadeOrcamentaria(unidadeOrcamentaria.getExercicioUnidadeOrcamentaria());
			fiplanUnidadeOrcamentaria.setCodigoUnidadeOrcamentaria(unidadeOrcamentaria.getCodigoUnidadeOrcamentaria());
		}
		return fiplanUnidadeOrcamentaria;
	}
}
