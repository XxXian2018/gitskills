package cn.xxx.ssm.service;

import java.util.List;

import cn.xxx.ssm.po.ItemsCustom;
import cn.xxx.ssm.po.ItemsQueryVo;

public interface ItemsService {
	
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
	
	public ItemsCustom findItems(Integer id) throws Exception;
	
	public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception;
	
	public void deleteItems(Integer[] items_id) throws Exception;
	
	public void updateItemsAll(ItemsQueryVo itemsQueryVo) throws Exception;
}
