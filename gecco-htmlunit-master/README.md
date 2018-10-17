#java爬虫gecco支持htmlunit
java爬虫gecco增加了对htmlunit的支持。[htmlunit](http://htmlunit.sourceforge.net/)是一款开源的java 页面分析工具，读取页面后，可以有效的使用htmlunit分析页面上的内容。项目可以模拟浏览器运行，被誉为java浏览器的开源实现。这个没有界面的浏览器，运行速度也是非常迅速的。htmlunit采用的是rhino作为javascript的解析引擎。
##使用方法
- 下载
	
		<dependency>
		    <groupId>com.geccocrawler</groupId>
		    <artifactId>gecco-htmlunit</artifactId>
		    <version>1.0.9</version>
		</dependency>

##Demo
JD的商品详情信息里的价格的信息是通过ajax异步请求而来的，之前是利用@Ajax注解的方式实现的。这里用htmlunit来自动完成ajax请求。
	
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

##优缺点
使用htmlunit确实能省去很多工作，但是htmlunit也存在很多弊端：

1、效率低下，使用htmlunit后，下载器要将所有js一并下载下来，同时要执行所有js代码，下载一个页面有时需要5～10秒。

2、rhino引擎对js的兼容问题，rhino的兼容性还是存在不少问题的，上述demo就有很多js执行错误。如果大家在抓取时不想看到这些error日志输出可以配置log4j：

	log4j.logger.com.gargoylesoftware.htmlunit=OFF

3、使用selenium也可以达到类似目的，selenium本身并不解析js，通过调用不同的浏览器驱动达到模拟浏览器的目的。selenium支持chrome、IE、firefox等多个真实浏览器驱动，也支持htmlunit作为驱动，还支持PhantomJS这种js开发的驱动。

| driver类型        | 优点           | 缺点  |
| ------------- |:-------------:| -----:|
|真实浏览器driver| 真实模拟用户行为 | 效率、稳定性低 |
| HtmlUnit      | 速度快	      |  js引擎(Rhinojs)不是主流的浏览器支持的,故对js支持的不够好 |
| PhantomJS     | 速度中等、模拟行为接近真实      |    不能模拟不同/特定浏览器的行为 |
