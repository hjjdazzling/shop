package hjj.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hjj.dao.DeliverDao;
import hjj.entity.Deliver;
import hjj.entity.DeliverRecord;

@Transactional
@Service(value = "deliverService")
public class DeliverServiceImpl implements DeliverService {
	@Resource(name = "deliverDao")
	private DeliverDao deliverDao;
	
	//更新送货记录
	public synchronized void updateDeliverRecord(DeliverRecord record) {
		deliverDao.updateDeliverRecord(record);
	}
	//查看送货记录
	public synchronized List<DeliverRecord> selectDeliverRecord(Deliver deliver) {
		return deliverDao.selectDeliverRecord(deliver);
	}
	
	//更新个人信息
	public synchronized void update(Deliver deliver) {
		deliverDao.update(deliver);
	}
	//登录
	public synchronized boolean select(Deliver deliver) {
		return deliverDao.login(deliver);
	}
	
	//注册
	public synchronized boolean insert(Deliver deliver) throws Exception {
		return deliverDao.insert(deliver);
	}

	
}
