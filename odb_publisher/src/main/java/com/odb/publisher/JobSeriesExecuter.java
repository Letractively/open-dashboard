package com.odb.publisher;

import com.odb.collector.UserDataWrapperMapEntry;
import com.odb.publisher.dto.SeriesJob;

public interface JobSeriesExecuter {
	String dataSourceID=null;

	public Double jobExecutor(SeriesJob seriesJob) ;
}
