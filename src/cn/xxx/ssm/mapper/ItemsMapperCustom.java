package cn.xxx.ssm.mapper;

import java.util.List;

import cn.xxx.ssm.po.ItemsCustom;
import cn.xxx.ssm.po.ItemsQueryVo;

public interface ItemsMapperCustom {
	
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
	
}
