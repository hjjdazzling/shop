package hjj.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hjj.dao.UserDao;
import hjj.entity.DeliverRecord;
import hjj.entity.Good;
import hjj.entity.PurchaseRecord;
import hjj.entity.ShopCarRecord;
import hjj.entity.User;
@Transactional
@Service(value = "userService")
public class UserService {
	@Resource(name = "userDao")
	private UserDao userDao;
	
	//查看商品
	public synchronized List<Good> selectGoods() {
		return userDao.selectGoods();
	}
	//添加购物车
	public synchronized boolean insertShopCar(Good buyGood, User user, int number) {
		return userDao.insertShopCar(buyGood, user, number);
	}
	//确认收货
	public synchronized boolean confirmGood(PurchaseRecord record) {
		return userDao.confirmGood(record);
	}
	//退货
	public synchronized boolean backGood(PurchaseRecord returnGood, User user) {
		return userDao.backGood(returnGood, user);
	}
	//查看收货记录
	public synchronized List<PurchaseRecord> selectAcceptRecord(User user) {
		// TODO Auto-generated method stub
		return userDao.selectAcceptRecord(user);
	}
	//查看送货记录
	public synchronized List<DeliverRecord> selectDeliverRecord(User user, List<PurchaseRecord> purchaseRecord) {
		return userDao.selectDeliverRecord(user, purchaseRecord);
	}
	//查看购物记录
	public synchronized List<PurchaseRecord> selectPurchaseRecord(User user) {
		return userDao.selectPurchaseRecord(user);
		
	}
	//购物车购买
	public synchronized boolean buyShopCar(Map<Good, Integer> buyGood,User user,List<ShopCarRecord> shopCarRecord) {
		return userDao.buyShopCar(buyGood, user, shopCarRecord);
	}
	//查看购物车
	public synchronized List<ShopCarRecord> selectShopCar(User user) {
		return userDao.selectShopCar(user);
	}
	//修改个人资料
	public synchronized boolean updateData(User user) throws Exception {
		return userDao.updateData(user);
	}
	//登录
	public synchronized boolean select(User user) {
		return userDao.select(user);
	}
	//注册
	public synchronized int insert(User user) throws Exception {
		return userDao.insert(user);
	}
	
	
}
