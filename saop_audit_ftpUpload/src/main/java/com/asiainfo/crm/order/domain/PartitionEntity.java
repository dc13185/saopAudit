package com.asiainfo.crm.order.domain;

import java.io.Serializable;

public class PartitionEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String partitionName;

	private String highValue;


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getPartitionName() {
		return partitionName;
	}

	public PartitionEntity setPartitionName(String partitionName) {
		this.partitionName = partitionName;
		return this;
	}

	public String getHighValue() {
		return highValue;
	}

	public PartitionEntity setHighValue(String highValue) {
		this.highValue = highValue;
		return this;
	}
}
