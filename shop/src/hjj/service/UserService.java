package hjj.service;

import java.util.List;
import java.util.Map;

import hjj.entity.DeliverRecord;
import hjj.entity.Good;
import hjj.entity.PurchaseRecord;
import hjj.entity.ShopCarRecord;
import hjj.entity.User;

public interface UserService {
	/**
	 * 查看商品
	 * @return
	 */
	List<Good> selectGoods();
	/**
	 * 添加购物车
	 * @param buyGood
	 * @param user
	 * @param number
	 * @return
	 */
	boolean insertShopCar(Good buyGood, User user, int number);
	/**
	 * 确认收货
	 * @param record
	 * @return
	 */
	boolean confirmGood(PurchaseRecord record);
	/**
	 * 退货
	 * @param returnGood
	 * @param user
	 * @return
	 */
	boolean backGood(PurchaseRecord returnGood, User user);
	/**
	 * 查看收货记录
	 * @param user
	 * @return
	 */
	List<PurchaseRecord> selectAcceptRecord(User user);
	/**
	 * 查看送货记录
	 * @param user
	 * @param purchaseRecord
	 * @return
	 */
	List<DeliverRecord> selectDeliverRecord(User user, List<PurchaseRecord> purchaseRecord);
	/**
	 * 查看购物记录
	 * @param user
	 * @return
	 */
	List<PurchaseRecord> selectPurchaseRecord(User user);
	/**
	 * 购物车购买
	 * @param buyGood
	 * @param user
	 * @param shopCarRecord
	 * @return
	 */
	boolean buyShopCar(Map<Good, Integer> buyGood,User user,List<ShopCarRecord> shopCarRecord);
	/**
	 * 查看购物车
	 * @param user
	 * @return
	 */
	List<ShopCarRecord> selectShopCar(User user);
	/**
	 * 修改个人资料
	 * @param user
	 * @return
	 * @throws Exception
	 */
	boolean updateData(User user) throws Exception;
	/**
	 * 登录
	 * @param user
	 * @return
	 */
	boolean select(User user);
	/**
	 * 注册
	 * @param user
	 * @return
	 * @throws Exception
	 */

	int insert(User user) throws Exception;
}
