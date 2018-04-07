package hjj.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hjj.dao.ShopDao;
import hjj.entity.Good;
import hjj.entity.Shop;

@Transactional
@Service(value = "shopService")
public class ShopServiceImpl implements ShopService {
	@Resource(name = "shopDao")
	private ShopDao shopDao;
	
	//上架商品
	public synchronized void  insertGood(Good good) {
		shopDao.insertGood(good);
	}
	//查看自己上架的商品
	public synchronized List<Good> selectGood(Shop shop) {
		return shopDao.selectGood(shop);
	}
	//更新个人信息
	public synchronized void updateShop(Shop shop) {
		shopDao.updateShop(shop);
	}
	//登录
	public synchronized boolean login(Shop shop) {
		return shopDao.login(shop);
	}
	//注册
	public synchronized boolean register(Shop shop) throws Exception {
		return shopDao.register(shop);
	}
	//更新货物信息
	public synchronized void updateGood(Good good) {
		shopDao.updateGood(good);
	}
	
}

