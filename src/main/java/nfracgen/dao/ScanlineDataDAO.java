package nfracgen.dao;

import nfracgen.util.DataSCL;

public interface ScanlineDataDAO {

	public DataSCL load();

	public DataSCL filter();

	public DataSCL store();
}
