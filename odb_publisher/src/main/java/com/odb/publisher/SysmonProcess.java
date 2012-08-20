package com.odb.publisher;

import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.cmd.SigarCommandBase;

public class SysmonProcess  extends SigarCommandBase{

	public Sigar getSigar() {
		return sigar;
	}
	@Override
	public void output(String[] arg0) throws SigarException {
		// TODO Auto-generated method stub
		
	}

}
