package com.weizeliang.cms.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.weizeliang.cms.domain.Article;
import com.weizeliang.cms.domain.Comment;
import com.weizeliang.cms.domain.User;
import com.weizeliang.cms.service.ArticleService;
import com.weizeliang.cms.service.CommentService;
import com.weizeliang.cms.util.PageUtil;

@RequestMapping("article")
@Controller
public class ArticleController {

	@Resource
	private ArticleService articleService;

	@Resource
	private CommentService commentService;

	/**
	 * 
	 * @Title: selectByUser
	 * @Description: 普通用户看文章详情
	 * @param id
	 * @param model
	 * @return
	 * @return: String
	 */
	@GetMapping(value = "selectByUser")
	public String selectByUser(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(required = true) Integer id,
			Model model) {

		Article article = articleService.selectByPrimaryKey(id);
		model.addAttribute("article", article);// 封装文章对象

		// 文章评论
		PageInfo<Comment> info = commentService.selects(article.getId(), page, pageSize);

		String pages = PageUtil.page(page, info.getPages(), "/article/selectByUser", pageSize);

		model.addAttribute("comments", info.getList());// 封装评论
		model.addAttribute("pages", pages);// 评论的分页

		return "my/article";
	}

	/**
	 * 
	 * @Title: selects
	 * @Description: 注册用户查看自己发布的文章
	 * @return
	 * @return: String
	 */
	@RequestMapping("selectsByUser")
	public String selectsByUser(Model model, Article article, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "3") Integer pageSize, HttpServletRequest request) {

		// 个人只能查看自己的发布的文章

		HttpSession session = request.getSession(false);
		if (session == null) {// session过期.重新登录
			return "redirect:/passport/login";
		}

		User user = (User) session.getAttribute("user");
		article.setUserId(user.getId());// 文章作者

		PageInfo<Article> info = articleService.selects(article, page, pageSize);
		if(article.getTitle()!=null && !article.getTitle().equals("")) {
			String pages = PageUtil.page(page, info.getPages(), "/article/selectsByUser?title="+article.getTitle(), pageSize);
			model.addAttribute("pages", pages);//分页
		}else {
			String pages = PageUtil.page(page, info.getPages(), "/article/selectsByUser", pageSize);
			model.addAttribute("pages", pages);//分页
		}

		model.addAttribute("articles", info.getList());// 集合对象
		//
		Article a = articleService.selectSufByDate(article);

		return "my/articles";
	}

	/**
	 * 去发布页面
	 * 
	 * @Title: publish
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	@GetMapping("publish")
	public String publish() {
		return "my/publish";
	}

	@Value("${filePath}")
	private String path;// 文件上传的路径

	@ResponseBody
	@PostMapping("publish")
	public boolean publish(Article article, MultipartFile file, HttpServletRequest request) {
		// String path="d:/pic/";
		// 1文件标题图片上传
		if (!file.isEmpty()) {
			// 获取原始的文件名称
			String oldFileName = file.getOriginalFilename();

			// 为了防止文件名称重复,使用UUID 处理文件名称
			String newFilename = UUID.randomUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));

			File file2 = new File(path + newFilename);
			try {
				file.transferTo(file2);
				article.setPicture(newFilename);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// 2 保存文章内容
		// 文章的默认属性
		article.setStatus(0);// 文章默认为待审核状态
		article.setHot(0);// 默认非热点
		article.setDeleted(0);// 默认非删除
		article.setHits(0);// 点击量
		article.setCreated(new Date());
		article.setUpdated(new Date());
		// 发布人
		HttpSession session = request.getSession(false);
		if (session == null)
			return false;
		User user = (User) session.getAttribute("user");
		article.setUserId(user.getId());// 文章作者
		return articleService.insertSelective(article) > 0;

	}

	/**
	 * 
	 * @Title: publish
	 * @Description: 修改文章
	 * @param article
	 * @param file
	 * @param request
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@PostMapping("publishupdate")
	public boolean publishupdate(Article article, MultipartFile file, HttpServletRequest request) {
		// String path="d:/pic/";
		// 1文件标题图片上传
		if (!file.isEmpty()) {
			// 获取原始的文件名称
			String oldFileName = file.getOriginalFilename();

			// 为了防止文件名称重复,使用UUID 处理文件名称
			String newFilename = UUID.randomUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));

			File file2 = new File(path + newFilename);
			try {
				file.transferTo(file2);
				article.setPicture(newFilename);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// 2 保存文章内容
		article.setUpdated(new Date());
		// 发布人
		HttpSession session = request.getSession(false);
		if (session == null)
			return false;
		User user = (User) session.getAttribute("user");
		article.setUserId(user.getId());// 文章作者
		return articleService.updateByPrimaryKeySelective(article) > 0;

	}

	/**
	 * 
	 * @Title: selectByAdmin
	 * @Description: 管理员查看文章详情
	 * @param id
	 * @param model
	 * @return
	 * @return: String
	 */
	@GetMapping(value = "selectByAdmin")
	public String selectByAdmin(@RequestParam(required = true) Integer id, Model model) {

		Article article = articleService.selectByPrimaryKey(id);
		model.addAttribute("article", article);
		return "admin/article";
	}

	/**
	 * 
	 * @Title: selects
	 * @Description: 管理员查看文章
	 * @return
	 * @return: String
	 */
	@RequestMapping("selectsByAdmin")
	public String selects(Model model, Article article, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "3") Integer pageSize) {

		// 默认待审
		if (article.getStatus() == null) {
			article.setStatus(0);
		}
		PageInfo<Article> info = articleService.selects(article, page, pageSize);
		String pages = PageUtil.page(page, info.getPages(), "/article/selectsByAdmin?status=" + article.getStatus(),
				pageSize);

		model.addAttribute("articles", info.getList());// 集合对象
		model.addAttribute("article", article);// 查询条件
		model.addAttribute("pages", pages);// 分页
		return "admin/articles";
	}

	/**
	 * 
	 * @Title: update
	 * @Description: 去修改
	 * @return
	 * @return: String
	 */
	@GetMapping("update")
	public String update(Model model, Integer id) {
		Article article = articleService.selectByPrimaryKey(id);

		model.addAttribute("article", article);
		return "/my/publishupdate";
	}

	/**
	 * 
	 * @Title: update
	 * @Description: 更新文章
	 * @param article
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@PostMapping("update")
	public boolean update(Article article) {
		return articleService.updateByPrimaryKeySelective(article) > 0;
	}
	
	@ResponseBody
	@PostMapping("comment")
	public boolean comment(Comment comment, HttpServletRequest request) {

		comment.setCreated(new Date());// 评论时间

		// 从session获取评论人
		HttpSession session = request.getSession(false);
		if (null == session)
			return false;
		User user = (User) session.getAttribute("user");
		comment.setUser(user);// 评论人

		return commentService.insert(comment) > 0;
	}
	

	
	//检查前一篇是否为空/
	@ResponseBody
	@GetMapping("checkPre")
	public boolean checkPre(Article article) {
		Article a = articleService.selectPreByDate(article);
       return a!=null;

	}
	
	//检查后一篇是否为空/
		@ResponseBody
		@GetMapping("checkSuf")
		public boolean checkSuf(Article article) {
			Article a = articleService.selectSufByDate(article);
	       return a!=null;

		}

	/**
	 * 
	 * 
	 * @Title: selectByPre
	 * @Description: TODO
	 * @param page
	 * @param pageSize
	 * @param id
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = "selectByPre")
	public String selectByPre(Article article, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer pageSize, Model model) {
		article.setStatus(1);
		article.setDeleted(0);
		Article a = articleService.selectPreByDate(article);
		model.addAttribute("article", a);// 封装文章对象

		// 文章评论
		PageInfo<Comment> info = commentService.selects(article.getId(), page, pageSize);

		String pages = PageUtil.page(page, info.getPages(), "/article/selectByUser", pageSize);

		model.addAttribute("comments", info.getList());// 封装评论
		model.addAttribute("pages", pages);// 评论的分页
		
		
		

		return "my/article";
	}

	/**
	 * 
	 * 
	 * @Title: selectByPre
	 * @Description: TODO
	 * @param page
	 * @param pageSize
	 * @param id
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = "selectBySuf")
	public String selectBySuf(Article article, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer pageSize, Model model) {

		Article a = articleService.selectSufByDate(article);
		
		model.addAttribute("article", a);// 封装文章对象

		// 文章评论
		PageInfo<Comment> info = commentService.selects(article.getId(), page, pageSize);

		String pages = PageUtil.page(page, info.getPages(), "/article/selectByUser", pageSize);

		model.addAttribute("comments", info.getList());// 封装评论
		model.addAttribute("pages", pages);// 评论的分页

		return "my/article";
	}

}
