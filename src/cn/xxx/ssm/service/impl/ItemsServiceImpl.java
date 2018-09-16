package cn.xxx.ssm.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.xxx.ssm.mapper.ItemsMapper;
import cn.xxx.ssm.mapper.ItemsMapperCustom;
import cn.xxx.ssm.po.Items;
import cn.xxx.ssm.po.ItemsCustom;
import cn.xxx.ssm.po.ItemsExample;
import cn.xxx.ssm.po.ItemsQueryVo;
import cn.xxx.ssm.service.ItemsService;

public class ItemsServiceImpl implements ItemsService {

	@Autowired
	private ItemsMapperCustom itemsMapperCustom;
	
	@Autowired
	private ItemsMapper itemsMapper;
	
	@Override
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {
		
		return itemsMapperCustom.findItemsList(itemsQueryVo);
	}

	@Override
	public ItemsCustom findItems(Integer id) throws Exception {
		
		Items items = itemsMapper.selectByPrimaryKey(id);
		ItemsCustom itemsCustom = null;
		
		if(items != null) {
			itemsCustom = new ItemsCustom();
			BeanUtils.copyProperties(items, itemsCustom);
		}
		
		return itemsCustom;
	}

	@Override
	public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception {
		
		itemsCustom.setId(id);
		itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);
	}

	@Override
	public void deleteItems(Integer[] items_id) throws Exception {
		
		for(Integer id:items_id) {
			itemsMapper.deleteByPrimaryKey(id);
		}
		
	}

	@Override
	public void updateItemsAll(ItemsQueryVo itemsQueryVo) throws Exception {
		
		for(ItemsCustom itemsCustom:itemsQueryVo.getItemsList()) {
			itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);
		}
		
	}

}
