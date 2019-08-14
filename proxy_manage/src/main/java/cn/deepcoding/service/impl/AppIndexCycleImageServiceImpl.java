package cn.deepcoding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.deepcoding.dao.AppIndexCycleImageDao;
import cn.deepcoding.model.AppIndexCycleImage;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.AppIndexCycleImageService;
import cn.deepcoding.util.ImagUtils;

@Service
@Transactional
public class AppIndexCycleImageServiceImpl implements AppIndexCycleImageService {
	
	@Autowired
	private AppIndexCycleImageDao appIndexCycleImageDao;

	/**
	 * 查询所有可用于轮播图的图片
	 */
	@Override
	public PageData getAll(AppIndexCycleImage appIndexCycleImage, Page page) {
		List<AppIndexCycleImage> list = appIndexCycleImageDao.getAll(appIndexCycleImage,page);
		Integer allCount = appIndexCycleImageDao.getAllCount(appIndexCycleImage);
		PageData pageData = new PageData();
		pageData.setTotal(allCount);
		pageData.setRows(list);
		return pageData;
	}
	
	/**
	 * 【是否使用当前图片】中所有的（是）改为（否）,即将数据库中的字段：image_use的值由1改为0
	 */
	@Override
	public void clearAllUse() {
		appIndexCycleImageDao.clearAllUse();
	}
	
	/**
	 * 添加完整的轮播图图片信息
	 */
	@Override
	public void addappIndexCycleImage(AppIndexCycleImage appIndexCycleImage) {
		appIndexCycleImageDao.addappIndexCycleImage(appIndexCycleImage);
	}

	/**
	 * 根据 id 查询轮播图图片信息
	 */
	@Override
	public AppIndexCycleImage getCycleImageById(Integer id) {
		AppIndexCycleImage appIndexCycleImage = appIndexCycleImageDao.getCycleImageById(id);
		// imUurl路径
		String imgUrl = ImagUtils.imgUrl();
		appIndexCycleImage.setImageSrc(imgUrl+appIndexCycleImage.getImageSrc());
		return appIndexCycleImage;
	}

	/**
	 * 修改轮播图图片信息
	 */
	@Override
	public void updateAppIndexCycleImage(AppIndexCycleImage appIndexCycleImage) {
		appIndexCycleImageDao.updateAppIndexCycleImage(appIndexCycleImage);
	}

	/**
	 * 根据 id 删除轮播图图片信息
	 */
	@Override
	public void deleteAppIndexCycleImage(Integer id) {
		appIndexCycleImageDao.deleteAppIndexCycleImage(id);
	}

}
