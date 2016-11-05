package nfracgen.dao;

import java.util.List;

import nfracgen.util.PowerLaw;

public interface PowerLawCoefficientsDAO {

	public List<PowerLaw> load();

	public void store(List<PowerLaw> powerLaws);

	public List<PowerLaw> filter(String text);

	public int generateId();


}
