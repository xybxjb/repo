package cn.deepcoding.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.deepcoding.model.AppIndexCycleImage;
import cn.deepcoding.page.Page;

@Repository
public interface AppIndexCycleImageDao {
	
	/**
	 * 查询所有可用于轮播图的图片
	 * @param appIndexCycleImage
	 * @param page
	 * @return
	 */
	List<AppIndexCycleImage> getAll (@Param("appIndexCycleImage")AppIndexCycleImage appIndexCycleImage,@Param("page")Page page);
	Integer getAllCount(AppIndexCycleImage appIndexCycleImage);
	
	/**
	 * 【是否使用当前图片】中所有的（是）改为（否）,即将数据库中的字段：image_use的值由1改为0
	 */
	void clearAllUse();
	
	/**
	 * 添加完整的轮播图图片信息
	 * @param appIndexCycleImage
	 */
	void addappIndexCycleImage (AppIndexCycleImage appIndexCycleImage);
	
	/**
	 * 根据 id 查询轮播图图片信息
	 * @param id
	 * @return
	 */
	AppIndexCycleImage getCycleImageById (Integer id);
	
	/**
	 * 修改轮播图图片信息
	 * @param appIndexCycleImage
	 */
	void updateAppIndexCycleImage (AppIndexCycleImage appIndexCycleImage);
	
	/**
	 * 根据 id 删除轮播图图片信息
	 * @param id
	 */
	void deleteAppIndexCycleImage (Integer id);

}
