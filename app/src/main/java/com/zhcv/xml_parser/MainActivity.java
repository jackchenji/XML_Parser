package com.zhcv.xml_parser;

import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	/**
	 * (1)������Ҫ�������ļ�����ΪXML�ļ���������srcĿ¼�£�����ͨ����װ�����ķ�ʽ����ļ�·���������������ķ�ʽ���������
	 * ��ΪSAX���������ķ�ʽ����ָ����XML���ݣ����Կ�ֱ�ӻ���ļ���������
	 * ��2�������Լ������Ĵ�������󣬲�ͨ��һ��SAXParserFactory����������SaxParser�����������Ĺ���ģʽ����ʹ����Ҫʹ�ò�ͬ�Ľ�����ʱ��
	 * Ҳ��ͨ���ı���Ӧ�Ļ��������������øı������ʵ�֡�
	 * (3)����XML��������ʽ����Ҫ����һ��XMLReader����ֱ��ʹ��SAXParser��
	 * ʹ�ã�XMLReader.
	 * 		XMLReader xmlReader = saxParser.getXMLReader();
	 * 		xmlReader.setContentHandler(handler);
	 * 		xmlReader.parse(new InputSource(is));
	 * ʹ�ã�SAXParser
	 * saxParser.parse(is,handler);
	 * SAXParserҪ��XMLReader��������ݡ�
	 * @return
	 * @throws Exception
	 */
public static List<Person> sax_XML()throws Exception{
	InputStream is = MainActivity.class.getClassLoader().getResourceAsStream("zhangxiaobo.xml");
	SAXForHandler handler = new SAXForHandler();
	SAXParserFactory spf = SAXParserFactory.newInstance();
	SAXParser saxParser = spf.newSAXParser();
	saxParser.parse(is, handler);
	List<Person>list = handler.getPersons();
	is.close();
	return list;
	
}
	
}
