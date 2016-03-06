package br.com.fracgen.dao;

import br.com.fracgen.util.DataSCL;

public interface ScanlineDataDAO {

	public DataSCL load();

	public DataSCL filter();

	public DataSCL store();
}
