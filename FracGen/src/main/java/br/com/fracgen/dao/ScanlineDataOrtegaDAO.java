package br.com.fracgen.dao;

import br.com.fracgen.util.DataSCL;

public interface ScanlineDataOrtegaDAO {

	public DataSCL load();

	public DataSCL filter();

	public DataSCL store();

	public int generateId();
}
