package org.symagic.user.action.order;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.symagic.common.action.catalog.CatalogBase;
import org.symagic.common.db.bean.BeanBook;
import org.symagic.common.db.bean.BeanUser;
import org.symagic.common.db.func.DaoBook;
import org.symagic.common.db.func.DaoUser;
import org.symagic.user.utilty.UserSessionUtilty;

public class OrderCheckScoreAction extends CatalogBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6603324986655777287L;

	private Integer score;
	
	private Boolean checkResult;
	
	private String price;
	
	private DaoUser daoUser;
	
	private DaoBook daoBook;
	
	private String resultInfo;
	
	public DaoBook getDaoBook() {
		return daoBook;
	}

	public void setDaoBook(DaoBook daoBook) {
		this.daoBook = daoBook;
	}

	public String execute() throws Exception{
		if(score == null){
			checkResult = false;
			return ERROR;
		}
		BeanUser user = daoUser.getUser(UserSessionUtilty.getUsername());
		if(user == null)
			checkResult = false;
		else{
			int totalScore = user.getScore();
			checkResult = totalScore >= score;
			float price = 0;
			HashMap<Integer, Integer> order = UserSessionUtilty.getOrder();
			Iterator<Entry<Integer, Integer>> iterator = order.entrySet().iterator();
			while(iterator.hasNext()){
				Entry<Integer, Integer> item = iterator.next();
				BeanBook book = daoBook.getDetail(item.getKey());
				price += book.getMarketPrice() * book.getDiscount() * item.getValue();
			}
			if(checkResult){
				if(price - score * 0.1 < 0.0f){
					checkResult = false;
					resultInfo = "订单金额不能小于零";
				}
				else
					price -= score * 0.1f;
			}
			setPrice(String.format("%.2f", price));
		}
		return SUCCESS;
	}
	
	public void validate(){
		clearErrorsAndMessages();
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Boolean getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(Boolean checkResult) {
		this.checkResult = checkResult;
	}

	public DaoUser getDaoUser() {
		return daoUser;
	}

	public void setDaoUser(DaoUser daoUser) {
		this.daoUser = daoUser;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String totalPrice) {
		this.price = totalPrice;
	}

	public String getResultInfo() {
		return resultInfo;
	}

	public void setResultInfo(String resultInfo) {
		this.resultInfo = resultInfo;
	}
}
