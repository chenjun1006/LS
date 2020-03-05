package com.ls.utils;

/**
 * 客户的押金状况和欠费情况
 * @author yangzj
 *
 */
public class CntrUserFee {
	
	private String cntrUserCod = null;		// 用户代码
	private String cntrUserNam;				// 用户名称
	private String portCod;					// 所属港口
	private String coinKindCod;				// 币种
	private double depositNum = 0;			// 押金金额
	private double cmFee = 0;				// 放箱欠款额
	private double overFee = 0;				// 滞期费修箱费欠款额
	private String lock = null;			// 加锁
	
	public String getCntrUserCod() {
		return cntrUserCod;
	}
	public synchronized void setCntrUserCod(String cntrUserCod) throws Exception {
		if (this.cntrUserCod == null) {
			this.cntrUserCod = cntrUserCod;
		}
		else {
			throw new Exception("不允许修改用户代码，只允许删除后重新添加！");
		}
	}
	public String getCntrUserNam() {
		return cntrUserNam;
	}
	public void setCntrUserNam(String cntrUserNam) {
		this.cntrUserNam = cntrUserNam;
	}
	public String getPortCod() {
		return portCod;
	}
	public void setPortCod(String portCod) {
		this.portCod = portCod;
	}
	public String getCoinKindCod() {
		return coinKindCod;
	}
	public void setCoinKindCod(String coinKindCod) {
		this.coinKindCod = coinKindCod;
	}
	public double getDepositNum() {
		return depositNum;
	}
	public void setDepositNum(double depositNum) {
		synchronized (cntrUserCod) {
			this.depositNum = depositNum;
		}
	}
	public double getCmFee() {
		return cmFee;
	}
	public void setCmFee(double cmFee) {
		synchronized (cntrUserCod) {
			this.cmFee = cmFee;
		}
	}
	public double getOverFee() {
		return overFee;
	}
	public void setOverFee(double overFee) {
		synchronized (cntrUserCod) {
			this.overFee = overFee;
		}
	}
	
	/**
	 * 增加放箱欠费额
	 * @param fee
	 * @author yangzj
	 * @create 2015-7-30 上午11:03:59
	 */
	public void addCmFee(double fee) {
		synchronized (cntrUserCod) {
			this.cmFee += fee;
		}
	}
	
	/**
	 * 增加滞箱费修箱费欠费额
	 * @param fee
	 * @author yangzj
	 * @create 2015-7-30 上午11:04:02
	 */
	public void addOverFee(double fee) {
		synchronized (cntrUserCod) {
			this.overFee += fee;
		}
	}
	
	/**
	 * 查询此用户是否已被锁定
	 * @return true:已被锁定；false:没有锁定
	 * @author yangzj
	 * @create 2015-7-30 上午11:04:05
	 */
	public boolean isLock() {
		return "1".equals(this.lock) || (this.depositNum <= this.cmFee + this.overFee);
	}
	public void setLock(String lock) {
		synchronized (cntrUserCod) {
			this.lock = "1".equals(lock) ? "1" : "0";
		}
	}
	
}
