package nfracgen.dao;

import nfracgen.util.DataSCL;

public interface ScanlineDataOrtegaDAO {

	public DataSCL load();

	public DataSCL filter();

	public DataSCL store();

	public int generateId();
}
