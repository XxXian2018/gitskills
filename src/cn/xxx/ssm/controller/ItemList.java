package cn.xxx.ssm.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.xxx.ssm.exception.CustomException;
import cn.xxx.ssm.po.ItemsCustom;
import cn.xxx.ssm.po.ItemsQueryVo;
import cn.xxx.ssm.service.ItemsService;

@Controller
@RequestMapping("/items")
public class ItemList {

	@Autowired
	private ItemsService itemsService;

	// 商品分类
	@ModelAttribute("itemtypes")
	public Map<String, String> getItemTypes() {

		Map<String, String> itemTypes = new HashMap<String, String>();
		itemTypes.put("101", "数码");
		itemTypes.put("102", "母婴");

		return itemTypes;
	}

	@RequestMapping("/queryItems")
	public ModelAndView queryItems(ItemsQueryVo itemsQueryVo) throws Exception {

		List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemsList", itemsList);
		modelAndView.setViewName("items/itemsList");

		return modelAndView;
	}

	// @RequestMapping(value="/editItems",method= {RequestMethod.GET,
	// RequestMethod.POST})
	// public ModelAndView editItems() throws Exception{
	//
	// ModelAndView modelAndView = new ModelAndView();
	// ItemsCustom itemsCustom = itemsService.findItems(1);
	// modelAndView.addObject("itemsCustom",itemsCustom);
	// modelAndView.setViewName("items/editItems");
	//
	// return modelAndView;
	// }

	@RequestMapping(value = "/editItems", method = { RequestMethod.GET, RequestMethod.POST })
	public String editItems(Model model, @RequestParam(value = "id", required = true) Integer items_id)
			throws Exception {

		ItemsCustom itemsCustom = itemsService.findItems(items_id);

		if (itemsCustom == null) {
			throw new CustomException("未找到该用户");
		}

		// 通过形参model将model数据传到页面
		model.addAttribute("items", itemsCustom);

		return "items/editItems";
	}
	

	@RequestMapping("/itemsView/{id}")
	public @ResponseBody ItemsCustom itemsView(@PathVariable("id") Integer id) 
			throws Exception{
		
		ItemsCustom itemsCustom = itemsService.findItems(id);
		
		return itemsCustom;
	}
	
	@RequestMapping("/editItemsSubmit")
	public String editItemsSubmit(Model model, Integer id, @ModelAttribute("items") ItemsCustom itemsCustom,MultipartFile items_pic) throws Exception {

		// //数据回显测试
		// if(true) {
		// return "items/editItems";
		// }
		// 原始图片名称
		String originalName = items_pic.getOriginalFilename();
		
		if (items_pic != null && originalName != null && originalName.length() > 0) {
			// 保存上传图片的物理路径
			String pic_path = "F:\\develop\\upload\\temp\\";
			// 新图片名称
			String newName = UUID.randomUUID() + originalName.substring(originalName.lastIndexOf("."));
			// 创建File对象
			File newFile = new File(pic_path + newName);
			// 将内存数据写入磁盘
			items_pic.transferTo(newFile);
			itemsCustom.setPic(newName);
		}

		itemsService.updateItems(id, itemsCustom);

		return "success";
	}

	// @RequestMapping("/editItemsSubmit")
	// public void editItemsSubmit(HttpServletRequest request, HttpServletResponse
	// response)
	// throws Exception{
	// response.sendRedirect("queryItems.action");
	// }

	@RequestMapping("/deleteItems")
	public String deleteItems(Integer[] items_id) throws Exception {

		itemsService.deleteItems(items_id);

		return "success";
	}

	@RequestMapping("/editItemsQuery")
	public ModelAndView editItemsQuery(ItemsQueryVo itemsQueryVo) throws Exception {

		List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemsList", itemsList);
		modelAndView.setViewName("items/editItemsQuery");

		return modelAndView;
	}

	@RequestMapping("/editItemsAllSubmit")
	public String editItemsAllSubmit(ItemsQueryVo itemsQueryVo) throws Exception {

		itemsService.updateItemsAll(itemsQueryVo);

		return "success";
	}
}
