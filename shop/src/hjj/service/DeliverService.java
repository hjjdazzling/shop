package hjj.service;

import java.util.List;

import hjj.entity.Deliver;
import hjj.entity.DeliverRecord;

public interface DeliverService {
	/**
	 * 更新送货记录
	 * @param record
	 */
	void updateDeliverRecord(DeliverRecord record);
	/**
	 * 查看自己的送货单
	 * @param deliver
	 * @return
	 */
	List<DeliverRecord> selectDeliverRecord(Deliver deliver);
	/**
	 * 更新信息
	 * @param deliver
	 */
	void update(Deliver deliver);
	/**
	 * 登录
	 * @param deliver
	 * @return
	 */
	boolean select(Deliver deliver);
	/**
	 * 注册
	 * @param deliver
	 * @return
	 * @throws Exception
	 */
	boolean insert(Deliver deliver) throws Exception;
}
