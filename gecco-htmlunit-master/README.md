#java����gecco֧��htmlunit
java����gecco�����˶�htmlunit��֧�֡�[htmlunit](http://htmlunit.sourceforge.net/)��һ�Դ��java ҳ��������ߣ���ȡҳ��󣬿�����Ч��ʹ��htmlunit����ҳ���ϵ����ݡ���Ŀ����ģ����������У�����Ϊjava������Ŀ�Դʵ�֡����û�н����������������ٶ�Ҳ�Ƿǳ�Ѹ�ٵġ�htmlunit���õ���rhino��Ϊjavascript�Ľ������档
##ʹ�÷���
- ����
	
		<dependency>
		    <groupId>com.geccocrawler</groupId>
		    <artifactId>gecco-htmlunit</artifactId>
		    <version>1.0.9</version>
		</dependency>

##Demo
JD����Ʒ������Ϣ��ļ۸����Ϣ��ͨ��ajax�첽��������ģ�֮ǰ������@Ajaxע��ķ�ʽʵ�ֵġ�������htmlunit���Զ����ajax����
	
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
			//��ʼץȡ��ҳ���ַ
			.start(request)
			//�������������߳�
			.thread(1)
			.run();
		}
	}

##��ȱ��
ʹ��htmlunitȷʵ��ʡȥ�ܶ๤��������htmlunitҲ���ںܶ�׶ˣ�

1��Ч�ʵ��£�ʹ��htmlunit��������Ҫ������jsһ������������ͬʱҪִ������js���룬����һ��ҳ����ʱ��Ҫ5��10�롣

2��rhino�����js�ļ������⣬rhino�ļ����Ի��Ǵ��ڲ�������ģ�����demo���кܶ�jsִ�д�����������ץȡʱ���뿴����Щerror��־�����������log4j��

	log4j.logger.com.gargoylesoftware.htmlunit=OFF

3��ʹ��seleniumҲ���Դﵽ����Ŀ�ģ�selenium����������js��ͨ�����ò�ͬ������������ﵽģ���������Ŀ�ġ�selenium֧��chrome��IE��firefox�ȶ����ʵ�����������Ҳ֧��htmlunit��Ϊ��������֧��PhantomJS����js������������

| driver����        | �ŵ�           | ȱ��  |
| ------------- |:-------------:| -----:|
|��ʵ�����driver| ��ʵģ���û���Ϊ | Ч�ʡ��ȶ��Ե� |
| HtmlUnit      | �ٶȿ�	      |  js����(Rhinojs)���������������֧�ֵ�,�ʶ�js֧�ֵĲ����� |
| PhantomJS     | �ٶ��еȡ�ģ����Ϊ�ӽ���ʵ      |    ����ģ�ⲻͬ/�ض����������Ϊ |
