package hjj.service;

import java.util.List;

import hjj.entity.Good;
import hjj.entity.Shop;

public interface ShopService {
	/**
	 * 上架自己的商品
	 * @param good
	 */
	void  insertGood(Good good);
	/**
	 * 查看自己的商品
	 * @param shop
	 * @return
	 */
	List<Good> selectGood(Shop shop);
	/**
	 * 更新商家信息
	 * @param shop
	 */
	void updateShop(Shop shop);
	/**
	 * 登录
	 * @param shop
	 * @return
	 */
	boolean login(Shop shop);
	/**
	 * 注册
	 * @param shop
	 * @return
	 * @throws Exception
	 */
	boolean register(Shop shop) throws Exception;
	/**
	 * 更新货物信息
	 * @param good
	 */
	void updateGood(Good good);
}
