package org.symagic.admin.action.level;

import java.util.ArrayList;
import java.util.List;

import org.symagic.common.action.catalog.CatalogBase;
import org.symagic.common.db.bean.BeanLevel;
import org.symagic.common.db.func.DaoLevel;
import org.symagic.common.utilty.presentation.bean.LevelBean;

public class UserLevelManagerAction extends CatalogBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 45110885091418325L;

	private DaoLevel daoLevel;
	
	private List<LevelBean> levelList;

	@Override
	public String execute() throws Exception {

		List<BeanLevel> levels = daoLevel.getAll();

		levelList = new ArrayList<LevelBean>();

		for (BeanLevel bean : levels) {
			LevelBean level = new LevelBean();
			level.setHight(bean.getUpLimit());
			level.setLevelID(bean.getId());
			level.setLevelName(bean.getName());
			level.setLow(bean.getLowLimit());
			level.setScoreRate(String.format("%.2f", bean.getRate()));
			levelList.add(level);
		}

		return super.execute();
	}

	public DaoLevel getDaoLevel() {
		return daoLevel;
	}

	public void setDaoLevel(DaoLevel daoLevel) {
		this.daoLevel = daoLevel;
	}

	public List<LevelBean> getLevelList() {
		return levelList;
	}

	public void setLevelList(List<LevelBean> levelList) {
		this.levelList = levelList;
	}

}
