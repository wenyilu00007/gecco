package com.geccocrawler.gecco.htmlunit;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.RequestParameter;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.request.HttpGetRequest;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;

@Gecco(matchUrl="http://item.jd.com/{code}.html", pipelines="consolePipeline", downloader="htmlUnitDownloder")
public class JDDetail implements HtmlBean {

	private static final long serialVersionUID = -377053120283382723L;

	@RequestParameter
	private String code;
	
	@Text
	@HtmlField(cssPath=".p-price")
	private String price;
	
	@Text
	@HtmlField(cssPath="#name > h1")
	private String title;
	
	@Text
	@HtmlField(cssPath="#p-ad")
	private String jdAd;
	
	@HtmlField(cssPath="#product-detail-2")
	private String detail;

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getJdAd() {
		return jdAd;
	}

	public void setJdAd(String jdAd) {
		this.jdAd = jdAd;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static void main(String[] args) throws Exception {
		HttpRequest request = new HttpGetRequest("http://item.jd.com/1455427.html");
		request.setCharset("GBK");
		GeccoEngine.create()
		.classpath("com.geccocrawler.gecco.htmlunit")
		//开始抓取的页面地址
		.start(request)
		//开启几个爬虫线程
		.thread(1)
		.run();
	}
}
