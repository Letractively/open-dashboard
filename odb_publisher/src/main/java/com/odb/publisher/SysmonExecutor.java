package com.odb.publisher;

import java.util.HashMap;

import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.cmd.SigarCommandBase;

import com.odb.publisher.dto.SeriesJob;

public class SysmonExecutor  implements JobSeriesExecuter {
	String dataSourceID;
	public SysmonExecutor(String dsID) {
		dataSourceID = dsID;
	}
	public Double jobExecutor(SeriesJob seriesJob) {
		HashMap<String, String> attr = seriesJob.getJobAttributes();
		
		int cpuNum = Integer.parseInt(attr.get("cpunum"));
		
		double result = 0;
        try {
			CpuPerc[] cpus = new SysmonProcess().getSigar().getCpuPercList();
        	if (seriesJob.getJob().equals("idle")) {
    			result = cpus[cpuNum-1].getIdle()*100;
        	} else if (seriesJob.getJob().equals("user")) {
        		
        	}
		} catch (SigarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}


}
